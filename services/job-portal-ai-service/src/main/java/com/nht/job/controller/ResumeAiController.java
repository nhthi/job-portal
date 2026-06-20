package com.nht.job.config;

import com.nht.job.payload.*;
import com.nht.job.service.ResumeAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai/resume")
public class ResumeAiController {

    private final ResumeAiService resumeAiService;

    @PostMapping("/summary")
    public ResponseEntity<AiTextResponse> generateSummary(
            @RequestBody ResumeSummaryRequest resumeSummaryRequest
            ) throws Exception {
        AiTextResponse response = resumeAiService.generateProfessionalSummary(resumeSummaryRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/experience-bullets")
    public ResponseEntity<WorkExperienceBulletResponse> generateBullets(
            @Valid @RequestBody WorkExperienceBulletRequest request
            ) throws Exception {
        WorkExperienceBulletResponse response = resumeAiService.generateWorkExperienceBullets(request);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/imporvements")
    public ResponseEntity<ResumeImprovementResponse> getImprovements(
            @Valid @RequestBody ResumeImprovementRequest request
    ) throws Exception {
        ResumeImprovementResponse response = resumeAiService.getResumeImprovementTips(request);
        return  ResponseEntity.ok(response);
    }


    @PostMapping("/career-feedback")
    public ResponseEntity<CareerFeedBackResponse> getCareerFeedbacks(
            @Valid @RequestBody CareerFeedBackRequest request
    ) throws Exception {
        CareerFeedBackResponse response = resumeAiService.getCareerFeedBack(request);
        return  ResponseEntity.ok(response);
    }

}
