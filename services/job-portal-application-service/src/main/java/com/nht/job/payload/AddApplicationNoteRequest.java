package com.nht.job.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddApplicationNoteRequest {

    @NotBlank(message = "Note content is required")
    @Size(max = 2000, message = "Note must be not exceed 2000 characters")
    private String content;

}
