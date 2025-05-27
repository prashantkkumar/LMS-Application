package org.example.user.service;
import lombok.extern.slf4j.Slf4j;
import org.example.user.dto.UserDto;
import org.example.user.enums.Role;

import lombok.RequiredArgsConstructor;
import org.example.user.dto.OtpVerificationRequest;
import org.example.user.dto.RegistrationRequest;
import org.example.user.entity.User;
import org.example.user.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final JavaMailSender mailSender;
    private final long OTP_EXPIRATION_MINUTES = 5;

    public void sendOtp(UserDto request) {
        String otp = generateOtp();

        Map<String, String> otpData = new HashMap<>();
        otpData.put("otp", otp);
        otpData.put("firstName", request.getFirstName());
        otpData.put("lastName", request.getLastName());
        otpData.put("email", request.getEmail());
        otpData.put("password", request.getPassword());
        otpData.put("role", request.getRole().name());

        redisTemplate.opsForHash().putAll(request.getEmail(), otpData);
        redisTemplate.expire(request.getEmail(), OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);

        log.info("OTP has been sent to the user with email: " + request.getEmail());
        log.info("OTP has been sent to the user with email: " + otp);
        System.out.println("OTP for " + request.getEmail() + ": " + otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        message.setFrom("rp95018089@gmail.com");

        mailSender.send(message);

        log.info("OTP sent to: {}", request.getEmail());
        log.info("Generated OTP: {}", otp);
    }

    public boolean verifyOtpAndRegister(OtpVerificationRequest req, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        Map<Object, Object> otpData = redisTemplate.opsForHash().entries(req.getEmail());
        if (otpData.isEmpty() || !otpData.get("otp").equals(req.getOtp())) {
            return false;
        }

        Object emailObj = otpData.get("email");
        if (emailObj != null) {
            String email = emailObj.toString();
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (email.equals(userOpt.get().getEmail())) {
                return false;
            }
        }

        User user = new User();
        user.setFirstName((String) otpData.get("firstName"));
        user.setLastName((String) otpData.get("lastName"));
        user.setEmail((String) otpData.get("email"));
        user.setPassword(passwordEncoder.encode((String) otpData.get("password")));
        user.setRole(Role.valueOf((String) otpData.get("role")));

        userRepository.save(user);
        redisTemplate.delete(req.getEmail());
        return true;
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}

