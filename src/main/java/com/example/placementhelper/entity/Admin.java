package com.example.placementhelper.entity;

import com.example.placementhelper.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ADMIN;

    // ✅ Constructors
    public Admin() {}

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
