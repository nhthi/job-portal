package com.nht.job.repository;

import com.nht.job.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> findByResumeIdOrderByDisplayOrderAsc(Long resumeId);




}
