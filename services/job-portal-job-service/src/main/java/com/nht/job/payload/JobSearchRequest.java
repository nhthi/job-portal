package com.nht.job.payload;

import com.nht.job.domain.ExperienceLevel;
import com.nht.job.domain.JobStatus;
import com.nht.job.domain.JobType;
import com.nht.job.domain.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSearchRequest {

    private String jobId;
    private Long categoryId;
    private List<Long> skillIds;

    private List<Long> tagIds;

    private Long companyId;

    private String location;

    private BigDecimal minSalary;
    private BigDecimal maxSalary;

    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private JobStatus status;

    private Integer minOpenings;
    private Integer maxOpenings;



}
