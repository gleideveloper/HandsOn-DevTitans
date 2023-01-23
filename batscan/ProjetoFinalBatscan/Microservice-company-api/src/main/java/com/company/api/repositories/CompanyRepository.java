package com.company.api.repositories;

import com.company.api.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, String> {
    boolean existsByMacPrefix(String Id);
}
