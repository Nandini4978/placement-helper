/*
Admin Repository
Provides database access for Admins.
Finds Admin by email for authentication.
 */

package com.example.placementhelper.repository;

import com.example.placementhelper.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
