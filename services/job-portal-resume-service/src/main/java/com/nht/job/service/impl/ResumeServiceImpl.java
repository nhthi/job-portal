package com.nht.job.service.impl;

import com.nht.job.dto.response.*;
import com.nht.job.mapper.ResumeMapper;
import com.nht.job.mapper.WorkExperienceMapper;
import com.nht.job.model.Education;
import com.nht.job.model.PersonalInfo;
import com.nht.job.model.Resume;
import com.nht.job.payload.CreateResumeRequest;
import com.nht.job.repository.*;
import com.nht.job.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final EducationRepository educationRepository;
    private final ResumeSkillRepository resumeSkillRepository;
    private final LanguageRepository languageRepository;
    private final ProjectRepository projectRepository;


    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest req) {
        if (Boolean.TRUE.equals(req.getIsDefault())) {
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId)
                    .ifPresent(existing -> {
                        existing.setIsDefault(false);
                        resumeRepository.save(existing);
                    });
        }
        Resume resume = Resume.builder()
                .candidateId(candidateId)
                .title(req.getTitle())
                .template(req.getTemplate())
                .visibility(req.getVisibility())
                .isDefault(Boolean.TRUE.equals(req.getIsDefault()))
                .isActive(true)
                .build();

        Resume saved = resumeRepository.save(resume);
        return buildFullResponse(saved);
    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        return buildFullResponse(resume);
    }


    @Override
    public List<ResumeResponse> getMyResume(Long candidateId) {
        return resumeRepository.findByCandidateIdAndIsActiveTrue(candidateId)
                .stream()
                .map(this::buildFullResponse)
                .toList();
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse req) throws Exception {

        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);
        PersonalInfo info = resume.getPersonalInfo();
        if (info == null) {
            info = new PersonalInfo();
        }

        if (req.getFirstName() != null) {
            info.setFirstName(req.getFirstName());
        }

        if (req.getLastName() != null) {
            info.setLastName(req.getLastName());
        }

        if (req.getEmail() != null) {
            info.setEmail(req.getEmail());
        }

        if (req.getHeadLine() != null) {
            info.setHeadLine(req.getHeadLine());
        }

        if (req.getPhone() != null) {
            info.setPhone(req.getPhone());
        }

        if (req.getCity() != null) {
            info.setCity(req.getCity());
        }

        if (req.getCountry() != null) {
            info.setCountry(req.getCountry());
        }

        if (req.getLinkedInUrl() != null) {
            info.setLinkedInUrl(req.getLinkedInUrl());
        }

        if (req.getGithubUrl() != null) {
            info.setGithubUrl(req.getGithubUrl());
        }

        if (req.getPortfolioUrl() != null) {
            info.setPortfolioUrl(req.getPortfolioUrl());
        }

        if (req.getWebsiteUrl() != null) {
            info.setWebsiteUrl(req.getWebsiteUrl());
        }

        resume.setPersonalInfo(info);
        Resume updated = resumeRepository.save(resume);
        return buildFullResponse(updated);

    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) throws Exception {

        Resume resume = getResumeEntity(resumeId);

        assertOwner(resume,candidateId);

        resume.setSummary(summary);

        return buildFullResponse(resumeRepository.save(resume));
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) throws Exception {

        Resume resume = getResumeEntity(resumeId);

        assertOwner(resume,candidateId);

        resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId)
                .ifPresent(existing -> {
                    existing.setIsDefault(false);
                    resumeRepository.save(existing);
                });

        resume.setIsDefault(true);
        return buildFullResponse(resumeRepository.save(resume));
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) throws Exception {
        Resume resume = getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        resume.setIsActive(false);
        resume.setIsDefault(false);
        resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeEntity(Long resumeId) throws Exception {
        return resumeRepository.findById(resumeId)
                .orElseThrow(() -> new Exception("Resume not found with id: " + resumeId));
    }

    private ResumeResponse buildFullResponse(Resume resume) {
        List<WorkExperienceResponse> workExperienceResponses =
                workExperienceRepository.findByResumeIdOrderByDisplayOrderAsc(resume.getId())
                        .stream()
                        .map(WorkExperienceMapper::toWorkExperienceResponse)
                        .toList();
        List<EducationResponse> educationResponses =
                educationRepository.findByResumeIdOrderByDisplayOrderAsc(resume.getId())
                        .stream()
                        .map(ResumeMapper::toEducationResponse)
                        .toList();
        List<ResumeSkillResponse> skillResponses =
                resumeSkillRepository.findByResumeIdOrderByDisplayOrderAsc(resume.getId())
                        .stream()
                        .map(ResumeMapper::toResumeSkillResponse)
                        .toList();
        List<LanguageResponse> languageResponses = languageRepository.findByResumeIdOrderByDisplayOrderAsc(resume.getId())
                .stream()
                .map(ResumeMapper::toLanguageResponse)
                .toList();
        List<ProjectResponse> projectResponses = projectRepository.findByResumeIdOrderByDisplayOrderAsc(resume.getId())
                .stream()
                .map(ResumeMapper::toProjectResponse)
                .toList();
        return ResumeMapper.toResponse(resume,workExperienceResponses,educationResponses,skillResponses,projectResponses,languageResponses);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {

        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Unauthorized access to resume with id: " + resume.getId());
        }
    }
}
