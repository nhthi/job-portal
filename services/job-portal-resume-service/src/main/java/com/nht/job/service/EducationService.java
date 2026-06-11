package com.nht.job.service;

import com.nht.job.dto.response.EducationResponse;
import com.nht.job.payload.AddEducationRequest;

import java.util.List;

public interface EducationService {

    EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest req) throws Exception;

    List<EducationResponse> getEducationsByResumeId(Long resumeId, Long candidateId);

    EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, AddEducationRequest req) throws Exception;

        void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception;

}
