package com.company.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CompanyDto {
    @NotBlank
    @Size(max = 17)
    private String macAddress;
    @NotBlank
    @Size(max = 20)
    private String companyName;
    @NotBlank
    @Size(max = 2)
    private String countryCode;
}
