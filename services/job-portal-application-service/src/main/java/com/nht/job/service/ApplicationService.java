package com.nht.job.service;

import com.nht.job.domain.ApplicationStatus;
import com.nht.job.dto.response.ApplicationResponse;
import com.nht.job.model.Application;
import com.nht.job.payload.CompanyApplicationFilterRequest;
import com.nht.job.payload.CreateApplicationRequest;
import com.nht.job.payload.WithdrawnApplicationRequest;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse createApplication(
            Long candidate,
            CreateApplicationRequest req
    ) throws Exception;

    ApplicationResponse getApplicationById(Long id) throws Exception;

    List<ApplicationResponse> getMyApplications(Long candidateId);

    List<ApplicationResponse> getApplicationsForJob(Long jobId);

    List<ApplicationResponse> getApplicationsForCompany(Long userId, CompanyApplicationFilterRequest filter);

    ApplicationResponse updateStatus(Long applicationId,Long employerId, ApplicationStatus status) throws Exception;

    ApplicationResponse withdraw(Long applicationId, Long candidateId,
                                 WithdrawnApplicationRequest req) throws Exception;

    void deleteApplication(Long applicationId, Long candidateId) throws Exception;

    ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception;

    Application getApplicationEntity(Long id) throws Exception;

}
