package com.nht.job.service;

import com.nht.job.dto.response.ProjectResponse;
import com.nht.job.payload.CreateProjectRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public interface ProjectService {

    ProjectResponse addProject(
            Long resumeId, Long candidateId, CreateProjectRequest req
    ) throws Exception;

    List<ProjectResponse> getAllProjects(Long resumeId);

    ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, CreateProjectRequest req) throws Exception;

    void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception;


}
