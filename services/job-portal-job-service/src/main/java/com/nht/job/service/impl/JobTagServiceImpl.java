package com.nht.job.service.impl;

import com.nht.job.dto.response.JobTagResponse;
import com.nht.job.mapper.JobTagMapper;
import com.nht.job.model.JobTag;
import com.nht.job.payload.JobTagRequest;
import com.nht.job.repository.JobTagRepository;
import com.nht.job.service.JobTagService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {
    private final JobTagRepository jobTagRepository;
    @Override
    public JobTagResponse createJobTag(JobTagRequest req) throws Exception {
        if(jobTagRepository.existsByName(req.getName())){
            throw new Exception("tag name already exist");
        }
        String slug = generateUniqueSlug(req.getName());
        JobTag jobTag = JobTag.builder()
                .name(req.getName())
                .slug(slug)
                .build();
        jobTagRepository.save(jobTag);
        return JobTagMapper.toJobTagResponse(jobTag);
    }

    private String generateUniqueSlug(@NotBlank(message = "tag name is required") String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]","").trim()
                .replaceAll("[\\s-]","-");
        if(!jobTagRepository.existsBySlug(base)){
            return base;
        }

        int counter = 1;
        while(jobTagRepository.existsBySlug(base+"-"+counter)){
            counter++;
        }
        return base+"-"+counter;
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return jobTagRepository.findAll().stream()
                .map(JobTagMapper::toJobTagResponse)
                .toList();
    }

    @Override
    public JobTagResponse getById(Long id) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        return JobTagMapper.toJobTagResponse(jobTag);
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest req) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        if(!jobTag.getName().equals(req.getName()) && jobTagRepository.existsByName(req.getName())){
            throw new Exception("tag name already exist");
        }
        jobTag.setName(req.getName());
        jobTag.setSlug(generateUniqueSlug(req.getName()));
        jobTagRepository.save(jobTag);
        return JobTagMapper.toJobTagResponse(jobTag);
    }

    @Override
    public void deleteTag(Long id) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        jobTagRepository.delete(jobTag);
    }

    @Override
    public JobTag getTagEntityById(Long id) throws Exception {
        return jobTagRepository.findById(id).orElseThrow(
                () -> new Exception("Tag not found"));
    }

    @Override
    public Set<JobTag> getTagByIds(Set<Long> ids) {
        List<JobTag> jobTags =  jobTagRepository.findAllById(ids);
        return new HashSet<>(jobTags);

    }
}
