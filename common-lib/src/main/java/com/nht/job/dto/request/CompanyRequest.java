package com.nht.job.dto.request;

import com.nht.job.domain.CompanySize;
import com.nht.job.domain.CompanyType;
import com.nht.job.domain.IndustryType;
import com.nht.job.dto.response.SocialLinkResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CompanyRequest {

    @NotBlank(message = "company name is required")
    private String name;

    private String tagLine;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    @Pattern(regexp = "^(http?://).*",message = "Website must be a valid url")
    private String website;

    @Email(message = "Company email must be valid")
    private String email;

    private String phone;

    @Min(value = 1800, message = "Founded year seems too old")
    @Max(value = 2100, message = "Founded year is invalid")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Industry type is required")
    private IndustryType industryType;

    private CompanyType companyType;

    private String registrationNumber;

    private List<SocialLinkResponse> socialLinks;

}
