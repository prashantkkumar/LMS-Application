package org.example.authservice.AuthService;

import org.example.authservice.AuthResponse.AuthResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RabbitTemplate rabbitTemplate;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider,
                       RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.rabbitTemplate = rabbitTemplate;
    }

    public String signup(SignupRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with this email.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("STUDENT"); // Or "INSTRUCTOR" or "ADMIN" based on request

        user.setVerified(false);
        user.setOtp(generateOtp());

        userRepository.save(user);

        // Send OTP to Notification Service
        OtpEvent otpEvent = new OtpEvent(user.getEmail(), user.getOtp());
        rabbitTemplate.convertAndSend("otp.exchange", "otp.routing.key", otpEvent);

        return "User registered. Please verify OTP sent to your email.";
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isVerified()) {
            throw new RuntimeException("Account not verified. Please verify your email using OTP.");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, "Login successful.");
    }

    public String verifyOtp(OtpRequest otpRequest) {
        User user = userRepository.findByEmail(otpRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (user.getOtp().equals(otpRequest.getOtp())) {
            user.setVerified(true);
            user.setOtp(null); // clear OTP
            userRepository.save(user);
            return "OTP verified. You can now log in.";
        } else {
            throw new RuntimeException("Invalid OTP. Please try again.");
        }
    }

    private String generateOtp() {
        int otp = new Random().nextInt(900000) + 100000; // 6-digit OTP
        return String.valueOf(otp);
    }
}
