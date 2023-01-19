package com.company.api.controllers;

import com.company.api.dtos.CompanyDto;
import com.company.api.models.CompanyModel;
import com.company.api.services.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/mac-company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCompany(@PathVariable(value = "id") String id) {
        Optional<CompanyModel> companyModelOptional = companyService.findById(id);
        /**
         * Solution#01 - usando condicional
         * if(companyModelOptional.isPresent()){
         *      return ResponseEntity.status(HttpStatus.OK).body(companyModelOptional.get());
         * }
         *      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found!");
         */

        /**
         * Solution#02 - Programação funcional
         */
        return companyModelOptional.
                <ResponseEntity<Object>>map(
                companyModel -> ResponseEntity.status(HttpStatus.OK).body(companyModel)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found!"));
    }

    @GetMapping()
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveCompany(@RequestBody @Valid CompanyDto companyDto) {
        if (companyService.existsByMacAddress(companyDto.getMacAddress())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Mac Address is already exists!");
        }
        var companyModel = new CompanyModel();
        BeanUtils.copyProperties(companyDto, companyModel);
        companyModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(companyModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable(value = "id") String id) {
        Optional<CompanyModel> companyModelOptional = companyService.findById(id);
        if (!companyModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found!");
        }
        companyService.delete(companyModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMacCompany(@PathVariable(value = "id") String id,
                                                    @RequestBody @Valid CompanyDto companyDto) {
        Optional<CompanyModel> companyModelOptional = companyService.findById(id);
        if (!companyModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found!");
        }
        var companyModel = new CompanyModel();
        BeanUtils.copyProperties(companyDto, companyModel);
        companyModel.setMacAddress(companyModelOptional.get().getMacAddress());
        companyModel.setRegistrationDate(companyModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(companyModel));
    }
}
