package com.company.api.repositories;

import com.company.api.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, String> {
    boolean existsByMacAddress(String Id);
}
