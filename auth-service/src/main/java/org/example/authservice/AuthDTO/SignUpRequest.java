package org.example.authservice.AuthDTO;


import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String role; // STUDENT, INSTRUCTOR, ADMIN
}
