package com.company.api.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_COMPANY")
public class CompanyModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false, unique = true, length = 17)
    private String macAddress;
    @Column(nullable = false, unique = false, length = 25)
    private String companyName;
    @Column(nullable = false, length = 70)
    private String countryCode;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
}
