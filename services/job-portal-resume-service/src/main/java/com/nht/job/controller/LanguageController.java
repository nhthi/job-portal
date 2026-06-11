package com.nht.job.controller;

import com.nht.job.dto.response.ApiResponse;
import com.nht.job.dto.response.LanguageResponse;
import com.nht.job.dto.response.ProjectResponse;
import com.nht.job.payload.AddLanguageRequest;
import com.nht.job.payload.CreateProjectRequest;
import com.nht.job.service.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/{resumeId}/languages")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public ResponseEntity<LanguageResponse> addLanguage(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddLanguageRequest req
    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(languageService.addLanguage(resumeId, candidateId, req));
    }

    @GetMapping
    public ResponseEntity<List<LanguageResponse>> getAllLanguages(
            @PathVariable Long resumeId
    ) throws Exception {
        return ResponseEntity.ok(languageService.getLanguages(resumeId));
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<LanguageResponse> updateProject(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddLanguageRequest req
    ) throws Exception {
        return ResponseEntity.ok(languageService.updateLanguage(languageId, resumeId, candidateId, req));
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<ApiResponse> deleteProject(
            @PathVariable Long resumeId,
            @PathVariable Long languageId,
            @RequestHeader("X-User-Id") Long candidateId

    ) throws Exception {
        languageService.deleteLanguage(languageId, resumeId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Language deleted successfully", true));
    }


}
