package com.nht.job.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private Long id;


    private String title;

    private String description;

    private List<String> technologies ;

    private String projectUrl;

    private String sourceCodeUrl;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isOngoing;

    private Integer displayOrder ;

}
