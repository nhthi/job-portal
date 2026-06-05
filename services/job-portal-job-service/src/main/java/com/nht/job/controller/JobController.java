package com.nht.job.controller;

import com.nht.job.dto.request.JobRequest;
import com.nht.job.dto.response.ApiResponse;
import com.nht.job.dto.response.JobResponse;
import com.nht.job.payload.JobSearchRequest;
import com.nht.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;


    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid JobRequest jobRequest
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.createJob(employerId,jobRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(
            @PathVariable Long id
    ) throws Exception {
//        jobService.incrementViewCount(id);
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getJobs(
            @ModelAttribute JobSearchRequest req
    ) throws Exception {
        return ResponseEntity.ok(jobService.getJobs(req));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<JobResponse>> getJobByCompany(
            @PathVariable Long companyId
    ) throws Exception {
//        jobService.incrementViewCount(id);
        return ResponseEntity.ok(jobService.getJobsByCompany(companyId));
    }

    @GetMapping(("/admin"))
    public ResponseEntity<List<JobResponse>> getAllJobs(
            @ModelAttribute JobSearchRequest req
    ) throws Exception {
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }


    @PutMapping(("/{id}"))
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid JobRequest req
    ) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(id,employerId,req));
    }

    @PatchMapping(("/{id}/publish"))
    public ResponseEntity<JobResponse> publishJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return ResponseEntity.ok(jobService.publishJob(id,employerId));
    }

    @PatchMapping(("/{id}/close"))
    public ResponseEntity<JobResponse> closeJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return ResponseEntity.ok(jobService.closedJob(id,employerId));
    }


    @DeleteMapping(("/{id}"))
    public ResponseEntity<ApiResponse> deleteJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        jobService.deleteJob(id,employerId);

        return ResponseEntity.ok(new ApiResponse("Job deteled successfully",true));
    }

}
