package com.nht.job.controller;

import com.nht.job.dto.response.ApiResponse;
import com.nht.job.dto.response.JobTagResponse;
import com.nht.job.payload.JobTagRequest;
import com.nht.job.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-tags")
public class JobTagController {
    private final JobTagService jobTagService;


    @PostMapping
    public ResponseEntity<JobTagResponse> createJobTag(
            @RequestBody @Valid JobTagRequest req
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTagService.createJobTag(req));
    }

    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllJobTags() {
        return ResponseEntity.ok(jobTagService.getAllTags());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateTag(
            @PathVariable Long id,
            @RequestBody @Valid JobTagRequest req
    ) throws Exception {
        return ResponseEntity.ok(jobTagService.updateTag(id, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getTagById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(jobTagService.getById(id));
    }


        @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Long id) throws Exception {
        jobTagService.deleteTag(id);
        return ResponseEntity.ok(new ApiResponse( "tag deleted successfully",true));
        }
}
