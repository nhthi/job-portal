package com.nht.job.controller;

import com.nht.job.domain.ApplicationStatus;
import com.nht.job.dto.response.ApiResponse;
import com.nht.job.dto.response.ApplicationResponse;
import com.nht.job.payload.CompanyApplicationFilterRequest;
import com.nht.job.payload.CreateApplicationRequest;
import com.nht.job.payload.UpdateApplicationStatusRequest;
import com.nht.job.payload.WithdrawnApplicationRequest;
import com.nht.job.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid CreateApplicationRequest req
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                applicationService.createApplication(candidateId,req)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyApplication(
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        return ResponseEntity.ok(applicationService.getMyApplications(candidateId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForJob(
            @PathVariable Long jobId
    ) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationsForJob(jobId));
    }
    @GetMapping("/company")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsForCompany(
            @RequestHeader("X-User-Id") Long userId,
            @ModelAttribute CompanyApplicationFilterRequest filter
            ) throws Exception {
        return ResponseEntity.ok(applicationService.getApplicationsForCompany(userId, filter));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid UpdateApplicationStatusRequest req
            ) throws Exception {
        return ResponseEntity.ok(applicationService.updateStatus(id,employerId, req.getStatus()));
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<ApplicationResponse> withdraw(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid WithdrawnApplicationRequest req
    ) throws Exception {
        return ResponseEntity.ok(applicationService.withdraw(id,employerId, req));
    }

    @PatchMapping("/{id}/star")
    public ResponseEntity<ApplicationResponse> toggleStar(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return ResponseEntity.ok(applicationService.toggleStar(id,employerId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteApplication(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        applicationService.deleteApplication(id,candidateId);
        return ResponseEntity.ok(new ApiResponse("Application deleted successfully",true));
    }
}
