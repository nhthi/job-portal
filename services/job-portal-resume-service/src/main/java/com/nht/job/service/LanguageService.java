package com.nht.job.service;


import com.nht.job.dto.response.LanguageResponse;
import com.nht.job.payload.AddLanguageRequest;

import java.util.List;

public interface LanguageService {

    LanguageResponse addLanguage(Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception;

    List<LanguageResponse> getLanguages(Long resumeId);

    LanguageResponse updateLanguage(Long languageId, Long resumeId, Long candidateId, AddLanguageRequest req) throws Exception;

    void deleteLanguage(Long languageId, Long resumeId, Long candidateId) throws Exception;



}
