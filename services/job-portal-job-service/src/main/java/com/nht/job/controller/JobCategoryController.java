package com.nht.job.controller;

import com.nht.job.dto.request.JobCategoryRequest;
import com.nht.job.dto.response.ApiResponse;
import com.nht.job.dto.response.JobCategoryResponse;
import com.nht.job.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
@RequiredArgsConstructor
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    @PostMapping
    public ResponseEntity<JobCategoryResponse> createCategory(
            @RequestBody @Valid JobCategoryRequest req
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobCategoryService.createCategory(req));
    }


    @GetMapping
    public ResponseEntity<List<JobCategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getCategoryById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid JobCategoryRequest req
    ) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateCategory(id,req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable Long id
    ) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully",true));
    }
}
