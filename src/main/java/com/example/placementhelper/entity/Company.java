package com.example.placementhelper.entity;

import com.example.placementhelper.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.COMPANY;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean approved = false; // ✅ Approval status

    // ✅ Default Constructor
    public Company() {}

    // ✅ Parameterized Constructor
    public Company(String name, String location, String email, String password, boolean approved) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.password = password;
        this.approved = approved;
    }

    public boolean getApproved() {
        return approved;
    }
}