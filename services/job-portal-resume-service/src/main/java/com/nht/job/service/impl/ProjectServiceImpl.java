package com.nht.job.service.impl;

import com.nht.job.dto.response.ProjectResponse;
import com.nht.job.mapper.ResumeMapper;
import com.nht.job.model.Project;
import com.nht.job.model.Resume;
import com.nht.job.payload.CreateProjectRequest;
import com.nht.job.repository.ProjectRepository;
import com.nht.job.service.ProjectService;
import com.nht.job.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ResumeService resumeService;
    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, CreateProjectRequest req) throws Exception {
        Resume resume = resumeService.getResumeEntity(resumeId);
        assertOwner(resume, candidateId);

        Project project = Project.builder()
                .resume(resume)
                .title(req.getTitle())
                .description(req.getDescription())
                .technologies(req.getTechnologies()!=null?req.getTechnologies():List.of())
                .projectUrl(req.getProjectUrl())
                .sourceCodeUrl(req.getSourceCodeUrl())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .isOngoing(Boolean.TRUE.equals(req.getIsOngoing()))
                .displayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : 0)
                .build();
        return ResumeMapper.toProjectResponse(projectRepository.save(project));
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepository.findByResumeIdOrderByDisplayOrderAsc(resumeId)
                .stream()
                .map(ResumeMapper::toProjectResponse)
                .toList();
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, CreateProjectRequest req) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new Exception("Project not found with id: " + projectId)
        );
        assertOwner(project.getResume(), candidateId);

        project.setTitle(req.getTitle());
        project.setDescription(req.getDescription());
        project.setTechnologies(req.getTechnologies()!=null?req.getTechnologies():project.getTechnologies());
        project.setProjectUrl(req.getProjectUrl());
        project.setSourceCodeUrl(req.getSourceCodeUrl());
        project.setStartDate(req.getStartDate());
        project.setEndDate(req.getEndDate());
        project.setIsOngoing(Boolean.TRUE.equals(req.getIsOngoing()));
        project.setDisplayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : project.getDisplayOrder());

        return ResumeMapper.toProjectResponse(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()-> new Exception("Project not found with id: " + projectId)
        );
        assertOwner(project.getResume(), candidateId);
        projectRepository.delete(project);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {

        if (!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Unauthorized access to resume with id: " + resume.getId());
        }
    }
}
