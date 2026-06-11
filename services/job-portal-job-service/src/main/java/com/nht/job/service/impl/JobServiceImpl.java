package com.nht.job.service.impl;

import com.nht.job.client.CompanyClient;
import com.nht.job.domain.JobStatus;
import com.nht.job.dto.request.JobRequest;
import com.nht.job.dto.response.CompanyResponse;
import com.nht.job.dto.response.JobResponse;
import com.nht.job.mapper.JobMapper;
import com.nht.job.model.Job;
import com.nht.job.model.JobCategory;
import com.nht.job.model.JobSkill;
import com.nht.job.model.JobTag;
import com.nht.job.model.embeddable.JobLocation;
import com.nht.job.model.embeddable.SalaryRange;
import com.nht.job.payload.JobSearchRequest;
import com.nht.job.repository.JobRepository;
import com.nht.job.repository.JobSpecification;
import com.nht.job.service.JobCategoryService;
import com.nht.job.service.JobService;
import com.nht.job.service.JobSkillService;
import com.nht.job.service.JobTagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobCategoryService jobCategoryService;
    private final JobTagService jobTagService;
    private final JobSkillService jobSkillService;
    private final CompanyClient companyClient;

    @Override
    public JobResponse createJob(Long employerId, JobRequest req) throws Exception {

        JobCategory category = jobCategoryService.getCategoryEntityById(req.getCategoryId());

        Set<JobSkill> skills = req.getSkillIds()!=null?
                jobSkillService.getSkillsByIds(req.getSkillIds()): Collections.emptySet();

        Set<JobTag> tags = req.getTagIds()!=null?
                jobTagService.getTagByIds(req.getTagIds()): Collections.emptySet();

        //        toto fetch company By employer Id

        CompanyResponse companyResponse = companyClient.getMyCompany(employerId);

        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .requirements(req.getRequirements())
                .responsibilities(req.getResponsibilities())
                .benefits(req.getBenefits())
                .companyId(companyResponse.getId())
                .category(category)
                .skills(skills)
                .tags(tags)
                .employerId(employerId)
                .location(buildLocation(req))
                .salaryRange(buildSalaryRange(req))
                .jobType(req.getJobType())
                .workMode(req.getWorkMode())
                .experienceLevel(req.getExperienceLevel())
                .openings(req.getOpenings() !=null ? req.getOpenings() :1)
                .applicationDeadline(req.getApplicationDeadline())
                .expiredAt(req.getExpiredAt())
                .active(true)
                .status(JobStatus.DRAFT)
                .build();
        Job savedJob = jobRepository.save(job);

        return convertToResponse(savedJob);
    }



    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                ()->new Exception("Job not found")
        );

        assertEmployer(job,employerId);

        JobCategory category = jobCategoryService.getCategoryEntityById(req.getCategoryId());

        Set<JobSkill> skills = req.getSkillIds()!=null?
                jobSkillService.getSkillsByIds(req.getSkillIds()): Collections.emptySet();

        Set<JobTag> tags = req.getTagIds()!=null?
                jobTagService.getTagByIds(req.getTagIds()): Collections.emptySet();


        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setBenefits(req.getBenefits());
        //todo: category not implemented yet
        job.setCategory(category);
        job.setSkills(skills);
        job.setTags(tags);
        job.setLocation(buildLocation(req));
        job.setSalaryRange(buildSalaryRange(req));
        job.setJobType(req.getJobType());
        job.setWorkMode(req.getWorkMode());
        job.setExperienceLevel(req.getExperienceLevel());
        job.setOpenings(req.getOpenings()!=null ? req.getOpenings(): job.getOpenings());
        job.setApplicationDeadline(req.getApplicationDeadline());
        job.setExpiredAt(req.getExpiredAt());



        return convertToResponse(jobRepository.save(job));

    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        List<Job> jobs = jobRepository.findAll(JobSpecification.build(request));

        return jobs.stream().map(
                job-> convertToResponse(job)
        ).collect(Collectors.toList());
    }

    @Override
    public JobResponse getJobById(Long id) throws Exception {

        Job job = jobRepository.findById(id).orElseThrow(
                ()->new Exception("Job not found")
        );
        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {

        List<Job> jobs = jobRepository.findByCompanyId(companyId);

        return jobs.stream().map(
                this:: convertToResponse
        ).collect(Collectors.toList());
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                ()->new Exception("Job not found")
        );
        assertEmployer(job, employerId);
        if(job.getStatus()==JobStatus.CLOSED || job.getStatus()==JobStatus.EXPIRED){
            throw new Exception("Job is expired");
        }
        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);
        return convertToResponse(jobRepository.save(job));
    }



    @Override
    public JobResponse closedJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                ()->new Exception("Job not found")
        );
        assertEmployer(job, employerId);
        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);
        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job = jobRepository.findById(jobId).orElseThrow(
                ()->new Exception("Job not found")
        );
        jobRepository.delete(job);
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {

        return jobRepository.findAll().stream().map(
                this::convertToResponse
        ).collect(Collectors.toList());
    }


    private JobResponse convertToResponse(Job savedJob) {
        CompanyResponse companyResponse = companyClient.getCompanyById(savedJob.getCompanyId());
        return JobMapper.toResponse(savedJob,companyResponse);
    }

    private SalaryRange buildSalaryRange(JobRequest req) {
        return SalaryRange.builder()
                .minSalary(req.getMinSalary())
                .maxSalary(req.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest req) {
        return JobLocation.builder()
                .address(req.getAddress())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getCountry())
                .zipCode(req.getZipCode())
                .build();
    }

    private void assertEmployer(Job job, Long employerId) throws Exception {

        if(!job.getEmployerId().equals(employerId)){
            throw new Exception("you are not the employer who posted this job");
        }

    }
}
