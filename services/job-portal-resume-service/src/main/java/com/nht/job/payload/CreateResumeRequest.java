package com.nht.job.payload;

import com.nht.job.domain.ResumeTemplate;
import com.nht.job.domain.ResumeVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateResumeRequest {

    @NotBlank(message = "Resume title is required")
    private String title;


    private ResumeTemplate template;

    private ResumeVisibility visibility;

    private Boolean isDefault;



}
