package com.nht.job.service.impl;

import com.nht.job.client.CompanyClient;
import com.nht.job.client.JobClient;
import com.nht.job.client.ResumeClient;
import com.nht.job.client.UserClient;
import com.nht.job.domain.ApplicationStatus;
import com.nht.job.dto.response.*;
import com.nht.job.mapper.ApplicationMapper;
import com.nht.job.model.Application;
import com.nht.job.payload.CompanyApplicationFilterRequest;
import com.nht.job.payload.CreateApplicationRequest;
import com.nht.job.payload.WithdrawnApplicationRequest;
import com.nht.job.repository.ApplicationRepository;
import com.nht.job.repository.ApplicationSpecification;
import com.nht.job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobClient jobClient;
    private final ResumeClient resumeClient;
    private final CompanyClient companyClient;
    private final UserClient userClient;


    @Override
    public ApplicationResponse createApplication(Long candidateId, CreateApplicationRequest req) throws Exception {

        if (applicationRepository.existsByCandidateIdAndJobId(candidateId, req.getJobId())) {
            throw new Exception("You have already applied");
        }

        JobResponse jobResponse = jobClient.getJobById(req.getJobId());
        Long companyId = jobResponse.getCompany().getId();
        Long employerId = jobResponse.getEmployerId();


        ResumeResponse resumeResponse = resumeClient.getResumeById(req.getResumeId(),candidateId);
        Application application = ApplicationMapper.toEntity(
                req, candidateId, companyId, employerId
        );

        Application savedApplication = applicationRepository.save(application);
        //todo: Ai screening runs in a background thread, no callback needed
        return buildFullResponse(savedApplication);
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) throws Exception {
        return buildFullResponse(getApplicationEntity(id));
    }

    @Override
    public List<ApplicationResponse> getMyApplications(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId).stream().map(
                this::buildFullResponse
        ).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId).stream().map(
                this::buildFullResponse
        ).toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsForCompany(Long userId, CompanyApplicationFilterRequest filter) {
        Long companyId = companyClient.getMyCompany(userId).getId();
        Sort sort = buildSort(filter.getSortBy());

        return applicationRepository.findAll(ApplicationSpecification.forCompanyWithFilters(
                companyId,
                filter.getJobId(),
                filter.getStatus(),
                filter.isStared(),
                filter.getAiShortListStatus(),
                filter.getMiniAiScore()
                ),sort).stream().map(
                this::buildFullResponse
        ).toList();
    }

    private Sort buildSort(String sortBy) {

        if("AI_SCORE_DESC".equals(sortBy)){
            return Sort.by(Sort.Order.desc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        } else if ("AI_SCORE_ASC".equals(sortBy)) {
            return Sort.by(Sort.Order.asc("aiScore").with(Sort.NullHandling.NULLS_LAST));
        }
        return Sort.by(Sort.Direction.DESC, "appliedAt");

    }

    @Override
    public ApplicationResponse updateStatus(Long applicationId, Long employerId, ApplicationStatus status) throws Exception {

        Application application = getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        if(application.getStatus()==ApplicationStatus.WITHDRAWN){
            throw new Exception("candidate have already withdrawn");
        }
        application.setStatus(status);
        Application savedApplication = applicationRepository.save(application);

        return buildFullResponse(savedApplication);
    }

    private void assertEmployer(Application application, Long employerId) throws Exception {

        if(!application.getEmployerId().equals(employerId)){
            throw new Exception("you are not the employer for this application");
        }

    }

    @Override
    public ApplicationResponse withdraw(Long applicationId, Long candidateId, WithdrawnApplicationRequest req) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertCandidate(application,candidateId);
        application.setStatus(ApplicationStatus.WITHDRAWN);

        application.setWithdrawnReason(req.getReason());
        return buildFullResponse(applicationRepository.save(application));
    }

    private void assertCandidate(Application application, Long candidateId) throws Exception {

        if(!application.getCandidateId().equals(candidateId)){
            throw new Exception("you are not the owner of this application");
        }

    }

    @Override
    public void deleteApplication(Long applicationId, Long candidateId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertCandidate(application,candidateId);
        applicationRepository.delete(application);
    }

    @Override
    public ApplicationResponse toggleStar(Long applicationId, Long employerId) throws Exception {
        Application application = getApplicationEntity(applicationId);
        assertEmployer(application,employerId);
        application.setIsStared(!application.getIsStared());
        Application savedApplication = applicationRepository.save(application);
        return buildFullResponse(savedApplication);
    }

    @Override
    public Application getApplicationEntity(Long id) throws Exception {

        return applicationRepository.findById(id).orElseThrow(
                ()->new Exception("Application not found with id: "+id)
        );
    }

    public ApplicationResponse buildFullResponse(Application application){

        JobResponse job = jobClient.getJobById(application.getJobId());
        CompanyResponse company = companyClient.getCompanyById(application.getCompanyId());
        UserResponse candidate = userClient.getUserById(application.getCandidateId());

        return ApplicationMapper.toReponse(application,job,company,candidate);
    }
}
