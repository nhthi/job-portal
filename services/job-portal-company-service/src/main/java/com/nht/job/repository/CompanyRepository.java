package com.nht.job.repository;

import com.nht.job.domain.CompanySatus;
import com.nht.job.domain.IndustryType;
import com.nht.job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.nht.job.domain.CompanyType;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByOwnerId(Long ownerId);

    boolean existsByOwnerId(Long ownerId);
    boolean existByName(String name);
    boolean existsBySlug(String slug);
    boolean existsByRegistrationNumber(String registrationNumber);

    @Query("select c from Company c where " +
            "(:companyType is NULL or c.companyType=:companyType)" +
            "and (:industryType is NULL or c.industryType=:industryType)" +
            "and (:status is NULL or c.status=:status)")
    List<Company> findByFilter(
            @Param("companyType") CompanyType companyType,
            @Param("industryType")IndustryType industryType,
            @Param("status")CompanySatus status
            );



}
