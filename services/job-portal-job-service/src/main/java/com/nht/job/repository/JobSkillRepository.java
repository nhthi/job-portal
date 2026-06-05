package com.nht.job.repository;

import com.nht.job.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill,Long> {

    List<JobSkill> findByActiveTrue();
    boolean existsByName(String name);
    boolean existsBySlug(String slug);

}
