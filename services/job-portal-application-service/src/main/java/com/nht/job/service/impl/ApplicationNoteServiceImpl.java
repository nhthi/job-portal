package com.nht.job.service.impl;

import com.nht.job.dto.response.ApplicationNoteResponse;
import com.nht.job.mapper.ApplicationMapper;
import com.nht.job.model.Application;
import com.nht.job.model.ApplicationNote;
import com.nht.job.payload.AddApplicationNoteRequest;
import com.nht.job.repository.ApplicationNoteRepository;
import com.nht.job.service.ApplicationNoteService;
import com.nht.job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationNoteServiceImpl implements ApplicationNoteService {

    private final ApplicationNoteRepository applicationNoteRepository;
    private final ApplicationService applicationService;

    @Override
    public ApplicationNoteResponse addNote(Long applicationId, Long employerId, AddApplicationNoteRequest req) throws Exception {
//        todo: fetch application
        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application, employerId);
        ApplicationNote applicationNote = ApplicationNote.builder()
                .application(application)
                .addedByUserId(employerId)
                .content(req.getContent())
                .build();
        ApplicationNote saved = applicationNoteRepository.save(applicationNote);

        return ApplicationMapper.toNoteResponse(saved);
    }

    private void assertEmployer(Application application, Long employerId) throws Exception {

        if(!application.getEmployerId().equals(employerId)){
            throw new Exception("you are not the employer for this application");
        }

    }

    @Override
    public List<ApplicationNoteResponse> getNotesByApplication(Long applicationId, Long employerId) {
        return applicationNoteRepository.findByApplicationId(applicationId)
                .stream().map(
                        ApplicationMapper::toNoteResponse
                ).toList();
    }

    @Override
    public void deleteNote(Long applicationId, Long noteId, Long employerId) throws Exception {
        Application application = applicationService.getApplicationEntity(applicationId);
        assertEmployer(application, employerId);
        ApplicationNote note = applicationNoteRepository.findById(noteId).orElseThrow(
                ()-> new Exception("Note does not belong to application")
        );
        applicationNoteRepository.delete(note);
    }
}
