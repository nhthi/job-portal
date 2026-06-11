package com.nht.job.mapper;

import com.nht.job.dto.response.*;
import com.nht.job.model.*;

import java.util.List;
import java.util.Locale;

public class ResumeMapper {

    public static PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo){
        if (personalInfo == null) {
            return null;
        }
        return PersonalInfoResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .email(personalInfo.getEmail())
                .phone(personalInfo.getPhone())
                .headLine(personalInfo.getHeadLine())
                .country(personalInfo.getCountry())
                .city(personalInfo.getCity())
                .githubUrl(personalInfo.getGithubUrl())
                .linkedInUrl(personalInfo.getLinkedInUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())

                .build();
    }

    public static ResumeResponse toResponse(Resume resume, List<WorkExperienceResponse> workExperiences,
                                            List<EducationResponse> educations,
                                            List<ResumeSkillResponse> skills,
                                            List<ProjectResponse> projects,
                                            List<LanguageResponse> languages){
        if(resume == null){
            return null;
        }

        return ResumeResponse.builder()
                .id(resume.getId())
                .candidateId(resume.getCandidateId())
                .title(resume.getTitle())
                .template(resume.getTemplate())
                .visibility(resume.getVisibility())
                .isDefault(resume.getIsDefault())
                .personalInfo(toPersonalInfoResponse(resume.getPersonalInfo()))
                .summary(resume.getSummary())
//                .uploadedFileUrl()
//                .uploadedFileName()
                .completionScore(resume.getCompletionScore())
//                .active(resume.getActive())
//                .lastViewedAt()
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .workExperiences(workExperiences)
                .educations(educations)
                .skills(skills)
                .projects(projects)
                .languages(languages)
                .build();
    }

    public static ResumeSkillResponse toResumeSkillResponse(ResumeSkill skill){
        if(skill==null) return null;
        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .build();
    }

    public static EducationResponse toEducationResponse(Education education){
        if(education==null) return null;
        return EducationResponse.builder()
                .id(education.getId())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .grade(education.getGrade())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .isCurrentlyStudying(education.getIsCurrentlyStudying())
                .description(education.getDescription())
                .displayOrder(education.getDisplayOrder())
                .build();
    }

    public static ProjectResponse toProjectResponse(Project project){
        if(project==null) return null;

        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .technologies(project.getTechnologies())
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOngoing(project.getIsOngoing())
                .displayOrder(project.getDisplayOrder())
                .build();
    }

    public static LanguageResponse toLanguageResponse(Language lang){
        if(lang==null) return null;

        return LanguageResponse.builder()
                .id(lang.getId())
                .languageName(lang.getLanguageName())
                .proficiency(lang.getProficiency())
                .displayOrder(lang.getDisplayOrder())
                .build();
    }
}
