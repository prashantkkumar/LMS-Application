package org.example.userservice.dto;

import lombok.Data;
import org.example.userservice.Role.Role;
@Data
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private Role role;
    private boolean isActive;
}

