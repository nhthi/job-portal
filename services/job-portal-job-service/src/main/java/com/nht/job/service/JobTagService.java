package com.nht.job.service;

import com.nht.job.dto.response.JobTagResponse;
import com.nht.job.model.JobTag;
import com.nht.job.payload.JobTagRequest;

import java.util.List;
import java.util.Set;

public interface JobTagService {

    JobTagResponse createJobTag(JobTagRequest req) throws Exception;


    List<JobTagResponse> getAllTags();
    JobTagResponse getById(Long id) throws Exception;
    JobTagResponse updateTag(Long id, JobTagRequest req) throws Exception;
    void deleteTag(Long id) throws Exception;
    JobTag getTagEntityById(Long id) throws Exception;
    Set<JobTag> getTagByIds(Set<Long> ids);

}
