package com.nht.job.service;

import com.nht.job.dto.request.JobCategoryRequest;
import com.nht.job.dto.response.JobCategoryResponse;
import com.nht.job.model.JobCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface JobCategoryService {

    JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception;

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse getCategoryById(Long id) throws Exception;

    JobCategoryResponse updateCategory(Long id, JobCategoryRequest req) throws Exception;

    void deleteCategory(Long id) throws Exception;

    JobCategory getCategoryEntityById(Long id) throws Exception;


}
