package com.nht.job.payload;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class CreateProjectRequest {


    @NotBlank(message = "Project title is required")
    private String title;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> technologies ;

    @Pattern(regexp = "^(https?://).*", message = "Invalid project URL")
    private String projectUrl;
    @Pattern(regexp = "^(https?://).*", message = "Invalid source code URL")
    private String sourceCodeUrl;

    private LocalDate startDate;

    private LocalDate endDate;

    @Builder.Default
    private Boolean isOngoing = false;

    private Integer displayOrder ;

}
