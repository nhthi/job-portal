package com.nht.job.mapper;

import com.nht.job.dto.response.JobSkillResponse;
import com.nht.job.model.JobSkill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class JobSkillMapper {

    public static JobSkillResponse toJobSkillResponse(JobSkill skill){
        return JobSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .slug(skill.getSlug())
                .category(skill.getCategory())
                .active(skill.getActive())
                .build();
    }
}
