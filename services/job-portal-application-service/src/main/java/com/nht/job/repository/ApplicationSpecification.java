package com.nht.job.repository;

import com.nht.job.domain.AiShortListStatus;
import com.nht.job.domain.ApplicationStatus;
import com.nht.job.model.Application;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;

import java.util.ArrayList;
import java.util.List;

public class ApplicationSpecification {


    public static Specification<Application> forCompanyWithFilters(
            Long companyId,
            Long jobId,
            ApplicationStatus status,
            boolean isStared,
            AiShortListStatus aiShortListStatus,
            Integer miniAiScore
    ){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("companyId"),companyId));
            if(jobId!=null) predicates.add(cb.equal(root.get("jobId"),jobId));
            if(status!=null) predicates.add(cb.equal(root.get("status"),status));
            if(isStared) predicates.add(cb.equal(root.get("isStared"),isStared));
            if(aiShortListStatus!=null) predicates.add(cb.equal(root.get("aiShortListStatus"),aiShortListStatus));
            if(miniAiScore!=null) predicates.add(cb.greaterThanOrEqualTo(root.get("aiScore"),miniAiScore));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
