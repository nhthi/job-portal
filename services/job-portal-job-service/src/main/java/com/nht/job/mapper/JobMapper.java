package com.nht.job.mapper;

import com.nht.job.dto.response.CompanyResponse;
import com.nht.job.dto.response.JobResponse;
import com.nht.job.dto.response.JobSkillResponse;
import com.nht.job.dto.response.JobTagResponse;
import com.nht.job.model.Job;
import com.nht.job.model.JobCategory;
import com.nht.job.model.embeddable.JobLocation;
import com.nht.job.model.embeddable.SalaryRange;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {

    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){

        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getSalaryRange();

        Set<JobSkillResponse> skillResponseSet = job.getSkills()==null?
                Collections.emptySet():
                job.getSkills().stream().map(JobSkillMapper::toJobSkillResponse)
                        .collect(Collectors.toSet());

        Set<JobTagResponse> tagResponseSet = job.getTags()==null?
                Collections.emptySet():
                job.getTags().stream().map(JobTagMapper::toJobTagResponse)
                        .collect(Collectors.toSet());

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
//                .employerId(job.getE)
                .category(JobCategoryMapper.toJobCategoryResponse(job.getCategory(),false))
                .skills(skillResponseSet)
                .tags(tagResponseSet)
                .address(loc!=null ? loc.getAddress():null)
                .city(loc!=null?loc.getCity():null)
                .state(loc!=null?loc.getState():null)
                .country(loc!=null?loc.getCountry():null)
                .zipCode(loc!=null?loc.getZipCode():null)

                .minSalary(sal!=null?sal.getMinSalary():null)
                .maxSalary(sal!=null?sal.getMaxSalary():null)

                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experimenceLevel(job.getExperienceLevel())
                .status(job.getStatus())
                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiredAt(job.getExpiredAt())
                .active(job.getActive())

//                .viewCount(job.getViewCount())
//                .applicationCount(job.getApplicationCount())
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())
                .build();

    }

}
