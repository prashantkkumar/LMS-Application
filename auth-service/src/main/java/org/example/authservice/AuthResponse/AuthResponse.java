package org.example.authservice.AuthResponse;

import lombok.Data;

@Data
public class AuthResponse {
    private String email;
    private String role;
    private String jwtToken;
}
