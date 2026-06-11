package com.nht.job.repository;

import com.nht.job.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume,Long> {

    List<Resume> findByCandidateIdAndIsActiveTrue(Long candidateId);
    Optional<Resume> findByCandidateIdAndIsDefaultTrue(Long candidateId);
}
