package com.nht.job.service;

import com.nht.job.dto.request.JobRequest;
import com.nht.job.dto.response.JobResponse;
import com.nht.job.payload.JobSearchRequest;

import java.util.List;


public interface JobService {

    JobResponse createJob(Long employerId, JobRequest req) throws Exception;

    JobResponse updateJob (Long jobId, Long employerId, JobRequest req) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest request);

    JobResponse getJobById(Long id) throws Exception;

    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse publishJob(Long jobId, Long employerId) throws Exception;

    JobResponse closedJob(Long jobId, Long employerId) throws Exception;

    void deleteJob(Long jobId, Long employerId) throws Exception;

    List<JobResponse> getAllJobsAdmin();

//    void incrementApplicationCount(Long jobId);
}
