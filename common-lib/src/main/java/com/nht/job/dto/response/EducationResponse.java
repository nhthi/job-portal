package com.nht.job.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationResponse {


    private Long id;


    private String institutionName;

    private String degree;

    private String fieldOfStudy;

    private String grade;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrentlyStudying;

    private String description;

    private Integer displayOrder ;

}
