package com.nht.job.payload;

import com.nht.job.domain.AiShortListStatus;
import com.nht.job.domain.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyApplicationFilterRequest {


    private  Long jobId;
    private ApplicationStatus status;
    private boolean isStared;
    private AiShortListStatus aiShortListStatus;
    private Integer miniAiScore;
    private String sortBy;

}
