package com.nht.job.repository;

import com.nht.job.domain.JobStatus;
import com.nht.job.model.Job;
import com.nht.job.payload.JobSearchRequest;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobSpecification {

    private JobSpecification(){}

    public static Specification<Job> build(JobSearchRequest req){
        return (root, query, cb) ->{
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.isTrue(root.get("active")));

            JobStatus status = req.getStatus()!=null ?req.getStatus():JobStatus.OPEN;
            predicates.add(cb.equal(root.get("status"),status));

            if(req.getJobType()!=null){
                predicates.add(cb.equal(root.get("jobType"),req.getJobType()));
            }

            if(req.getWorkMode()!=null){
                predicates.add(cb.equal(root.get("workMode"),req.getWorkMode()));
            }


            if(req.getCompanyId()!=null){
                predicates.add(cb.equal(root.get("companyId"),req.getCompanyId()));
            }
            if(req.getCategoryId()!=null){
                predicates.add(cb.equal(root.get("category").get("id"),req.getCategoryId()));
            }
            if(req.getLocation()!=null && !req.getLocation().isBlank()){
                String pattern = "%"+req.getLocation().toLowerCase()+"%";
                Path<String> city = root.get("location").get("city");
                Path<String> state = root.get("location").get("state");
                Path<String> country = root.get("location").get("country");

                predicates.add(cb.or(
                        cb.like(cb.lower(city),pattern),
                        cb.like(cb.lower(state),pattern),
                        cb.like(cb.lower(country),pattern)

                ));
            }

            if(req.getMinSalary()!=null){
                predicates.add(cb.greaterThanOrEqualTo(root.get("salaryRange").get("maxSalary"),req.getMinSalary()));
            }

            if(req.getMaxSalary()!=null){
                predicates.add(cb.lessThan(root.get("salaryRange").get("minSalary"),req.getMaxSalary()));
            }

            if(req.getMinOpenings()!=null){
                predicates.add(cb.greaterThan(root.get("openings"),req.getMinOpenings()));
            }
            if(req.getMaxOpenings()!=null){
                predicates.add(cb.lessThan(root.get("openings"),req.getMaxOpenings()));
            }

//            todo: filter for tag, skills

            return cb.and(predicates.toArray(new Predicate[0]));


        };
    }
}
