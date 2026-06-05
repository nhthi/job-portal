package com.nht.job.mapper;

import com.nht.job.dto.response.CompanyResponse;
import com.nht.job.dto.response.SocialLinkResponse;
import com.nht.job.model.Company;
import com.nht.job.model.SocialLink;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    public static SocialLinkResponse toSocialLinkResponse(SocialLink socialLink){
        return SocialLinkResponse.builder()

                .platform(socialLink.getPlatform()).url(socialLink.getUrl()).build();
    }

    public static CompanyResponse toResponse(Company company){
        List<SocialLinkResponse> socialLinks = company.getSocialLinks()==null ? Collections.emptyList()
                :company.getSocialLinks().stream()
                .map(CompanyMapper::toSocialLinkResponse)
                .toList();
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagline(company.getTagLine())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .email(company.getEmail())
                .phone(company.getPhone())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .status(company.getStatus())
                .active(company.getActive())
                .ownerId(company.getOwnerId())
                .socialLinks(socialLinks)
                .isVerified(company.isVerified())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .verifiedAt(company.getVerifiedAt())
                .build();
    }

}
