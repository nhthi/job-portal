package com.nht.job.mapper;

import com.nht.job.domain.ApplicationStatus;
import com.nht.job.dto.response.*;
import com.nht.job.model.Application;
import com.nht.job.model.ApplicationNote;
import com.nht.job.payload.CreateApplicationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
public class ApplicationMapper {

    public static Application toEntity(CreateApplicationRequest request,
                                       Long candidateId,
                                       Long companyId,
                                       Long employerId
                                       ){
        if(request ==null) return null;

        return Application.builder()
                .candidateId(candidateId)
                .jobId(request.getJobId())
                .companyId(companyId)
                .employerId(employerId)
                .resumeId(request.getResumeId())
                .coverLetter(request.getCoverLetter())
                .expectedSalary(request.getExpectedSalary())
                .availableForm(request.getAvailableForm())
                .status(ApplicationStatus.PENDING)
                .build();
    }


    public static ApplicationResponse toReponse(Application application,
//                                                List<ApplicationNote> notes,
                                                JobResponse jobResponse,
                                                CompanyResponse companyResponse,
                                                UserResponse userResponse
//                                                ApplicationScreening applicationScreening

    ){
        return ApplicationResponse.builder()
                .id(application.getId())
                .candidate(userResponse)
                .employerId(application.getEmployerId())
                .job(jobResponse)
                .company(companyResponse)
                .status(application.getStatus())
                .resumeId(application.getResumeId())
                .coverLetter(application.getCoverLetter())
                .expectedSalary(application.getExpectedSalary())

                .availableForm(application.getAvailableForm())
//                .isRead(application.getIsread())

                .isStared(application.getIsStared())
//                .notes()
                .withdrawnAt(application.getWithdrawnAt())
                .withdrawnReason(application.getWithdrawnReason())
                .appliedAt(application.getAppliedAt())
                .updatedAt(application.getUpdatedAt())
//                .screening(toScreeningResponse(screening))
                .build();
    }


    public static ApplicationNoteResponse toNoteResponse(ApplicationNote applicationNote){
        return ApplicationNoteResponse.builder()
                .id(applicationNote.getId())
                .addedByUserId(applicationNote.getAddedByUserId())
                .content(applicationNote.getContent())
                .createdAt(applicationNote.getCreatedAt())
                .build();
    }
}
