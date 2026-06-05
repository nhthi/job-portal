package com.nht.job.mapper;

import com.nht.job.dto.response.JobTagResponse;

public class JobTagMapper {

    public static JobTagResponse toJobTagResponse(com.nht.job.model.JobTag tag){
        return JobTagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .slug(tag.getSlug())
                .build();
    }
}
