package com.nht.job.service.impl;

import com.nht.job.dto.response.SavedJobResponse;
import com.nht.job.mapper.PreferenceMapper;
import com.nht.job.model.SavedJob;
import com.nht.job.payload.SaveJobRequest;
import com.nht.job.repository.SavedJobRepository;
import com.nht.job.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SavedJobServiceImpl implements SavedJobService {
    private final SavedJobRepository savedJobRepository;


    @Override
    public SavedJobResponse saveJob(Long candidateId, SaveJobRequest req) throws Exception {
        if(isSaved(candidateId, req.getJobId())){
            throw new Exception("jb already saved");
        }
        SavedJob savedJob = SavedJob.builder()
                .candidateId(candidateId)
                .jobId(req.getJobId())
                .build();
        savedJob = savedJobRepository.save(savedJob);

        return PreferenceMapper.toSavedJobResponse(savedJob);
    }

    @Override
    public void unsaveJob(Long candidateId, Long savedJobId) throws Exception {
        SavedJob savedJob = savedJobRepository.findById(savedJobId).orElseThrow(
                ()-> new Exception("job not found")
        );

        if(!savedJob.getCandidateId().equals(candidateId)){
            throw new Exception("job not saved");
        }

        savedJobRepository.delete(savedJob);
    }

    @Override
    public List<SavedJobResponse> getSavedJob(Long candidateId) {
        return savedJobRepository.findByCandidateId(candidateId).stream()
                .map(PreferenceMapper::toSavedJobResponse).toList();
    }

    @Override
    public boolean isSaved(Long candidateId, Long jobId) {
        return savedJobRepository.existsByCandidateIdAndJobId(candidateId,jobId);
    }
}
