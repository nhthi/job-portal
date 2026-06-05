package com.nht.job.repository;

import com.nht.job.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTagRepository extends JpaRepository<JobTag, Long> {

    boolean existsByName(String name);
    boolean existsBySlug(String slug);
}
