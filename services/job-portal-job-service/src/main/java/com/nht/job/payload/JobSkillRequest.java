package com.nht.job.payload;

import com.nht.job.domain.SkillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobSkillRequest {

    @NotBlank(message = "Skill name is required")
    @Size(max =100, message = "Name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Skill category is required")
    private SkillCategory category;

}
