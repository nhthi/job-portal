package com.nht.job.service;

import com.nht.job.dto.response.ApplicationNoteResponse;
import com.nht.job.dto.response.ApplicationResponse;
import com.nht.job.payload.AddApplicationNoteRequest;

import java.util.List;

public interface ApplicationNoteService {

    ApplicationNoteResponse addNote(
            Long applicationId, Long employerId, AddApplicationNoteRequest req
    ) throws Exception;

    List<ApplicationNoteResponse> getNotesByApplication(
            Long applicationId,
            Long employerId
    );

    void deleteNote(Long applicationId, Long noteId, Long employerId) throws Exception;




}
