package com.nht.job.dto.request;

import com.nht.job.domain.ExperienceLevel;
import com.nht.job.domain.JobType;
import com.nht.job.domain.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @NotBlank(message = "job title is required")
     private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String requirements;

    private String responsibilities;

    private String benefits;

    @NotNull(message = "Category id requried")
    private Long categoryId;


    private Set<Long> skillIds;

    private Set<Long> tagIds;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @DecimalMin(value = "0.0", inclusive = true, message = "Min salary must not be negative")
    private BigDecimal minSalary;

    @DecimalMin(value = "0.0", inclusive = true, message = "Max salary must not be negative")
    private BigDecimal maxSalary;

//    private String currency;
//
//    private SalaryPeriod salaryPeriod;
//    private Boolean salaryNegotiable;
//
//    private Boolean salaryDisclosed;

    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotNull(message = "Work mode is required")
    private WorkMode workMode;

    @NotNull(message = "Experience level is required")
    private ExperienceLevel experienceLevel;

    @Min(value =1,message = "Openings must be at least 1")
    private Integer openings = 1;

    private LocalDate applicationDeadline;
    private LocalDate expiredAt;
}
