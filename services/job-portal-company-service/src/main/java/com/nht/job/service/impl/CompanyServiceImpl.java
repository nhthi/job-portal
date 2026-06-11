package com.nht.job.service.impl;

import com.nht.job.domain.CompanySatus;
import com.nht.job.domain.CompanyType;
import com.nht.job.domain.IndustryType;
import com.nht.job.dto.request.CompanyRequest;
import com.nht.job.dto.response.CompanyResponse;
import com.nht.job.dto.response.SocialLinkResponse;
import com.nht.job.mapper.CompanyMapper;
import com.nht.job.model.Company;
import com.nht.job.model.SocialLink;
import com.nht.job.repository.CompanyRepository;
import com.nht.job.service.CompanyService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest req) throws Exception {

        if(companyRepository.existsByOwnerId(ownerId)){
            throw new Exception("You already have a company registered. " +
                    "Only one company per account is allowed!");
        }

        if(companyRepository.existsByName((req.getName()))){
            throw new Exception("Company already exist. Please choose a different name.");
        }

        if(req.getRegistrationNumber()!=null &&
        companyRepository.existsByRegistrationNumber((req.getRegistrationNumber()))){
            throw new Exception("Company already exists. Please choose a different registration number.");
        }

        String slug = generateUniqueSlug(req.getName());

        Company company = Company.builder()
                .name(req.getName())
                .slug(slug)
                .tagLine(req.getTagLine())
                .description(req.getDescription())
                .logoUrl(req.getLogoUrl())
                .coverImageUrl(req.getCoverImageUrl())
                .website(req.getWebsite())
                .email(req.getEmail())
                .phone(req.getPhone())
                .foundedYear(req.getFoundedYear())
                .companySize(req.getCompanySize())
                .companyType(req.getCompanyType())
                .industryType(req.getIndustryType())
                .registrationNumber(req.getRegistrationNumber())
                .ownerId(ownerId)
                .socialLinks(mapSocialLinks(req.getSocialLinks()))
                .build();
        Company saved = companyRepository.save(company);
        return CompanyMapper.toResponse(saved);
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinks) {

        if(socialLinks == null || socialLinks.isEmpty()){
            return new ArrayList<SocialLink>();
        }
        return socialLinks.stream()
                .map(e->SocialLink.builder()
                        .platform(e.getPlatform())
                        .url(e.getUrl())
                        .build()
                ).collect(Collectors.toList());

    }

    private String generateUniqueSlug(@NotBlank(message = "company name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]","").trim()
                .replaceAll("[\\s-]","-");
        if(!companyRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(companyRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company =  companyRepository.findById(id).orElseThrow(
                ()->new Exception("company not found with id")
        );
        return CompanyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {

        Company company = companyRepository.findByOwnerId(ownerId).orElseThrow(
                ()-> new Exception("company not found for owner "+ownerId)
        );

        return CompanyMapper.toResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanySatus companySatus) {


        return companyRepository.findByFilter(
                        companyType,
                        industryType,
                        companySatus)
                .stream().map(CompanyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) throws Exception {

        Company company = getCompanyEntityById(companyId);
        if(!company.getName().equals(req.getName())&&companyRepository.existsByName(req.getName())){
            throw new Exception("Company already exists. Please choose a different name.");
        }

        if (req.getRegistrationNumber() != null && !req.getRegistrationNumber().equals(company.getRegistrationNumber())
                && companyRepository.existsByRegistrationNumber(req.getRegistrationNumber())
        ) {

            throw new Exception("Company already exists. Please choose a different registration number.");
        }

        company.setName(req.getName());
        company.setTagLine(req.getTagLine());
        company.setDescription(req.getDescription());
        company.setLogoUrl(req.getLogoUrl());
        company.setCoverImageUrl(req.getCoverImageUrl());
        company.setWebsite(req.getWebsite());
        company.setEmail(req.getEmail());
        company.setPhone(req.getPhone());
        company.setFoundedYear(req.getFoundedYear());
        company.setCompanyType(req.getCompanyType());
        company.setCompanySize(req.getCompanySize());
        company.setIndustryType(req.getIndustryType());
        company.setRegistrationNumber(req.getRegistrationNumber());
        company.setSocialLinks(mapSocialLinks(req.getSocialLinks()));



        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanySatus.ACTIVE);
        company.setVerified(true);
        company.setVerifiedAt(LocalDateTime.now());
        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        asserOwner(company,ownerId);
        companyRepository.delete(company);
    }

    private void asserOwner(Company company, Long ownerId) throws Exception {

        if(!company.getOwnerId().equals(ownerId)){
            throw new Exception("you are not the owner of this company.");
        }

    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company = getCompanyEntityById(companyId);
        company.setStatus(CompanySatus.SUSPENDED);
        company.setVerified(false);
        return CompanyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return   companyRepository.findById(id).orElseThrow(
                ()->new Exception("company not found with id")
        );
    }
}
