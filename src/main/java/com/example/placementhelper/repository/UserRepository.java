/*
User Repository
-Provides methods to find users by email or enrollment.
-Checks if an email or enrollment already exists.
*/

package com.example.placementhelper.repository;

import com.example.placementhelper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEnrollment(String enrollment);
    boolean existsByEmail(String email);
    boolean existsByEnrollment(String enrollment);
}
