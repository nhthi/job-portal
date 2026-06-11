package com.nht.job.repository;


import com.nht.job.model.ApplicationNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationNoteRepository extends JpaRepository<ApplicationNote,Long> {

    List<ApplicationNote> findByApplicationId(Long applicationId);


}
