package com.nht.job.dto.response;

import com.nht.job.domain.CompanySatus;
import com.nht.job.domain.CompanySize;
import com.nht.job.domain.CompanyType;
import com.nht.job.domain.IndustryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompanyResponse {
    private Long id;
    private String name;
    private String slug;
    private String tagline;
    private String description;
    private String logoUrl;
    private String coverImageUrl;
    private String website;
    private String email;
    private String phone;
    private Integer foundedYear;

    private CompanySize companySize;
    private CompanyType companyType;
    private IndustryType industryType;
    private CompanySatus status;
    private Boolean active;
    private Boolean isVerified;
    private Long ownerId;

    private List<SocialLinkResponse> socialLinks;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime verifiedAt;
}
