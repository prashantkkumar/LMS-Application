package org.example.userservice.dto;

import lombok.Data;
import org.example.userservice.Role.Role;
@Data
public class UserCreateRequest {
    private String email;
    private String username;
    private Role role;
}

