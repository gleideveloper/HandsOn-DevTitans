package com.company.api.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TBL_MAC_VENDOR")
public class CompanyModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false, unique = true, length = 10)
    private String macPrefix;
    @Column(nullable = false, unique = false, length = 50)
    private String vendorName;
    @Column(nullable = false, length = 5)
    private String countryCode;
}
