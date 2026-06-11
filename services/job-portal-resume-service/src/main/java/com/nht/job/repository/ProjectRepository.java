package com.nht.job.repository;

import com.nht.job.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);
    
    
}
