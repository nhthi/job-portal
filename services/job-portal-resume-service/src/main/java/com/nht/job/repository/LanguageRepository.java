package com.nht.job.repository;

import com.nht.job.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    List<Language> findByResumeIdOrderByDisplayOrderAsc (Long resumeId);



}
