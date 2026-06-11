package com.nht.job.controller;

import com.nht.job.dto.response.ApiResponse;
import com.nht.job.dto.response.PersonalInfoResponse;
import com.nht.job.dto.response.ResumeResponse;
import com.nht.job.model.PersonalInfo;
import com.nht.job.payload.CreateResumeRequest;
import com.nht.job.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponse> createResume(
            @RequestBody @Valid CreateResumeRequest req,
            @RequestHeader("X-User-Id") Long candidateId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resumeService.createResume(candidateId, req));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        return ResponseEntity.ok(resumeService.getResumeById(candidateId, resumeId));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResumes(
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        return ResponseEntity.ok(resumeService.getMyResume(candidateId));
    }

    @PutMapping("/{resumeId}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfor(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            @RequestBody @Valid PersonalInfoResponse req
    ) throws Exception {
        return ResponseEntity.ok(resumeService.updatePersonalInfo(candidateId, resumeId, req));
    }

    @PatchMapping("/{resumeId}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId,
            @RequestParam String summary
    ) throws Exception {
        return ResponseEntity.ok(resumeService.updateSummary(candidateId, resumeId, summary));
    }

    @PatchMapping("/{resumeId}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId
    ) throws Exception {
        return ResponseEntity.ok(resumeService.setDefaultResume(candidateId, resumeId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<ApiResponse> deleteResume(
            @RequestHeader("X-User-Id") Long candidateId,
            @PathVariable Long resumeId
    ) throws Exception {
        resumeService.deleteResume(candidateId, resumeId);
        return ResponseEntity.ok(new ApiResponse("Resume deleted successfully", true));
    }
}
