package com.nht.job.service.impl;

import com.nht.job.dto.response.LanguageResponse;
import com.nht.job.mapper.ResumeMapper;
import com.nht.job.model.Language;
import com.nht.job.model.Resume;
import com.nht.job.payload.AddLanguageRequest;
import com.nht.job.repository.LanguageRepository;
import com.nht.job.service.LanguageService;
import com.nht.job.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final ResumeService resumeService;

    @Override
    public LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        Language language = Language.builder()
                .resume(resume)
                .languageName(req.getLanguageName())
                .proficiency(req.getProficiency())
                .displayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : 0)
                .build();
        Language saved = languageRepository.save(language);

        return ResumeMapper.toLanguageResponse(saved);
    }

    @Override
    public List<LanguageResponse> getLanguages(Long resumeId) {
        return languageRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId).stream()
                .map(ResumeMapper::toLanguageResponse)
                .toList();
    }

    @Override
    public LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception {
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
        assertOwner(language.getResume(), candidateId);
        language.setLanguageName(req.getLanguageName());
        language.setProficiency(req.getProficiency());
        language.setDisplayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : language.getDisplayOrder());
        Language updated = languageRepository.save(language);

        return ResumeMapper.toLanguageResponse(updated);
    }

    @Override
    public void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception {
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new Exception("Language not found with id: " + languageId));
        assertOwner(language.getResume(), candidateId);
        languageRepository.delete(language);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {

        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Unauthorized to modify this resume");
        }
    }
}
