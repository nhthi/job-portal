package com.nht.job.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCategoryRequest {

    @NotBlank(message = "category name is required")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private String iconUrl;

    private Long parentId;

}
