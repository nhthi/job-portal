package com.nht.job.dto.response;

import com.nht.job.domain.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobSkillResponse {

    private Long id;
    private String name;
    private String slug;
    private SkillCategory category;
    private Boolean active;


}
