package com.nht.job.repository;

import com.nht.job.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.function.LongFunction;

public interface ApplicationRepository  extends JpaRepository<Application,Long>,
        JpaSpecificationExecutor<Application> {

    List<Application> findByCandidateId(Long candidateId);
    List<Application> findByJobId(Long jobId);
    boolean existsByCandidateIdAndJobId(Long candidateId,Long jobId);




}
