package com.nht.job.service;

import com.nht.job.dto.response.SavedJobResponse;
import com.nht.job.payload.SaveJobRequest;

import java.util.List;

public interface SavedJobService {

    SavedJobResponse saveJob(Long candidateId, SaveJobRequest req) throws Exception;
    void unsaveJob(Long candidateId, Long savedJobId) throws Exception;
    List<SavedJobResponse> getSavedJob(Long candidateId);
    boolean isSaved(Long candidateId,Long jobId);




}
