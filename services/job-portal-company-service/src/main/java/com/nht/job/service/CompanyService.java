package com.nht.job.service;

import com.nht.job.domain.CompanySatus;
import com.nht.job.domain.CompanyType;
import com.nht.job.domain.IndustryType;
import com.nht.job.dto.request.CompanyRequest;
import com.nht.job.dto.response.CompanyResponse;
import com.nht.job.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest req) throws Exception;
    CompanyResponse getCompanyById(Long id) throws Exception;
    CompanyResponse getMyCompany(Long ownerId) throws Exception;
    List<CompanyResponse> getAllCompanies(CompanyType companyType,
                                          IndustryType industryType,
                                          CompanySatus companySatus);
    CompanyResponse updateCompany(Long companyId,Long ownerId, CompanyRequest req) throws Exception;
    CompanyResponse verifyCompany(Long companyId) throws Exception;
    void deleteCompany(Long companyId, Long ownerId) throws Exception;
    CompanyResponse deactivateCompany(Long companyId) throws Exception;

    Company getCompanyEntityById(Long id) throws Exception;

}
