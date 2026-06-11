package com.nht.job.repository;

import com.nht.job.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    List<WorkExperience> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);



}
