/*
Company Repository
Provides database access for Company entities.
Finds Company by email for authentication.
*/

package com.example.placementhelper.repository;

import com.example.placementhelper.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmail(String email);
}
