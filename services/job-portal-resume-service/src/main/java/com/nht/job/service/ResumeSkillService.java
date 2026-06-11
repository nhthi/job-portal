package com.nht.job.service;

import com.nht.job.dto.response.ResumeSkillResponse;
import com.nht.job.payload.AddResumeSkillRequest;

import java.util.List;

public interface ResumeSkillService {

    ResumeSkillResponse addResumeSkill(Long reumseId, Long candidateId, AddResumeSkillRequest req) throws Exception;

    List<ResumeSkillResponse> getResumeSkills(Long resumeId);

    ResumeSkillResponse updateResumeSkill(
            Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest req
    ) throws Exception;

    void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception;



}
