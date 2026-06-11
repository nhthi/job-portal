package com.nht.job.dto.response;

import com.nht.job.domain.ResumeTemplate;
import com.nht.job.domain.ResumeVisibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {
    private Long id;
    private Long candidateId;
    private String title;
    private ResumeTemplate template;
    private ResumeVisibility visibility;
    private Boolean isDefault;
    private PersonalInfoResponse personalInfo;
    private String summary;
//    private String uploadedFileName;
//    private String uploadedFileUrl;
    private Integer completionScore;
//    private Boolean active;
//    private LocalDateTime lastViewedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

// Todo:
    private List<WorkExperienceResponse> workExperiences;
    private List<EducationResponse> educations;
    private List<ResumeSkillResponse> skills;
    private List<ProjectResponse> projects;
//    private List<CertificateResponse> certifications;
//    private List<AwardResponse> awards;
    private List<LanguageResponse> languages;
}
