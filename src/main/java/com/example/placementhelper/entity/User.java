package com.example.placementhelper.entity;

import com.example.placementhelper.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient  // ✅ Prevents storing in the database
    private String confirmPassword;

    @Column(nullable = false, unique = true)
    private String enrollment;

    @Column(nullable = false)
    private double cgpa;

    private String resumeLink;
    private String linkedinProfile;
    private String githubLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.Student;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean approved = false;

    // ✅ Default Constructor
    public User() {
    }

    // ✅ Parameterized Constructor
    public User(String email, String password, String confirmPassword, String enrollment, double cgpa,
                String resumeLink, String linkedinProfile, String githubLink, boolean approved) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.enrollment = enrollment;
        this.cgpa = cgpa;
        this.resumeLink = resumeLink;
        this.linkedinProfile = linkedinProfile;
        this.githubLink = githubLink;
        this.approved = approved;
    }
}
