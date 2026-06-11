package com.nht.job.service.impl;

import com.nht.job.dto.response.ResumeSkillResponse;
import com.nht.job.mapper.ResumeMapper;
import com.nht.job.model.Resume;
import com.nht.job.model.ResumeSkill;
import com.nht.job.payload.AddResumeSkillRequest;
import com.nht.job.repository.ResumeSkillRepository;
import com.nht.job.service.ResumeService;
import com.nht.job.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService {

    private final ResumeSkillRepository resumeSkillRepository;
    private final ResumeService resumeService;


    @Override
    public ResumeSkillResponse addResumeSkill(Long resumeId, Long candidateId, AddResumeSkillRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        ResumeSkill skill = ResumeSkill.builder()
                .resume(resume)
                .skillName(req.getSkillName())
                .proficiencyLevel(req.getProficiencyLevel())
                .yearsOfExperience(req.getYearsOfExperience())
                .displayOrder(req.getDisplayOrder()!=null ? req.getDisplayOrder() : 0)
                .build();
        ResumeSkill saved = resumeSkillRepository.save(skill);

        return ResumeMapper.toResumeSkillResponse(saved);
    }

    @Override
    public List<ResumeSkillResponse> getResumeSkills(Long resumeId) {
        return resumeSkillRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toResumeSkillResponse)
                .toList();
    }

    @Override
    public ResumeSkillResponse updateResumeSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest req) throws Exception {
        ResumeSkill skill = resumeSkillRepository.findById(skillId)
                .orElseThrow(() -> new Exception("Skill not found with id: " + skillId));
        assertOwner(skill.getResume(), candidateId);

        skill.setSkillName(req.getSkillName());
        skill.setProficiencyLevel(req.getProficiencyLevel());
        skill.setYearsOfExperience(req.getYearsOfExperience());
        skill.setDisplayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : skill.getDisplayOrder());

        ResumeSkill updated = resumeSkillRepository.save(skill);
        return ResumeMapper.toResumeSkillResponse(updated);
    }

    @Override
    public void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception {
        ResumeSkill skill = resumeSkillRepository.findById(skillId)
                .orElseThrow(() -> new Exception("Skill not found with id: " + skillId));
        assertOwner(skill.getResume(), candidateId);

        resumeSkillRepository.delete(skill);
    }


    private void assertOwner(Resume resume, Long candidateId) throws Exception {

        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Unauthorized access to resume with id: " + resume.getId());
        }
    }
}
