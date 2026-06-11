package com.nht.job.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApplicationNoteResponse {

    private Long id;
    private Long addedByUserId;
    private String content;
    private LocalDateTime createdAt;

}
