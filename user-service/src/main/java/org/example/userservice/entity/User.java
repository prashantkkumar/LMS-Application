package org.example.userservice.entity;

import jakarta.persistence.*;

import lombok.Data;
import org.example.userservice.Role.Role;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role; // STUDENT, INSTRUCTOR, ADMIN

    @Column(nullable = false)
    private boolean isActive = true;
}

