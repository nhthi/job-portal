package com.nht.job.client;

import com.nht.job.dto.response.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "JOB-PORTAL-COMPANY-SERVICE")
public interface CompanyClient {

    @GetMapping("/api/companies/{id}")
    CompanyResponse getCompanyById(
            @PathVariable Long id
    );
    @GetMapping("/api/companies/my")
     CompanyResponse getMyCompany(
            @RequestHeader("X-User-Id")Long ownerId
    );
}
