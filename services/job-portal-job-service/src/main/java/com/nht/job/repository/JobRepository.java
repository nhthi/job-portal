package com.nht.job.repository;

import com.nht.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>, JpaSpecificationExecutor<Job> {

    List<Job> findByCompanyId(Long companyId);

}
