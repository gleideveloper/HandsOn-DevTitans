package com.company.api.services;

import com.company.api.models.CompanyModel;
import com.company.api.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Transactional
    public Object save(CompanyModel companyModel) {
        return companyRepository.save(companyModel);
    }
    public boolean existsByMacAddress(String Id) {
        return companyRepository.existsByMacPrefix(Id);
    }
    public List<CompanyModel> findAll() {
        return companyRepository.findAll();
    }

    public Optional<CompanyModel> findById(String id) {
        return companyRepository.findById(id);
    }

    @Transactional
    public void delete(CompanyModel companyModel) {
        companyRepository.delete(companyModel);
    }
}
