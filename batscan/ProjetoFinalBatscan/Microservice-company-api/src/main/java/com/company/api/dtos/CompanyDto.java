package com.company.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CompanyDto {
    @NotBlank
    @Size(max = 10)
    private String macPrefix;
    @NotBlank
    @Size(max = 50)
    private String vendorName;
    @NotBlank
    @Size(max = 5)
    private String countryCode;
}
