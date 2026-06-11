package com.nht.job.client;

import com.nht.job.dto.response.JobResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "JOB-PORTAL-JOB-SERVICE")
public interface JobClient {

    @GetMapping("/api/jobs/{id}")
    public JobResponse getJobById(
            @PathVariable Long id
    );
}
