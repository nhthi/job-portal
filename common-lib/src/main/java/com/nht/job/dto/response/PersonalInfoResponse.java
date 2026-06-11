package com.nht.job.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfoResponse {

    private String firstName;

    private String lastName;

    private String headLine;

    private String email;

    private String phone;

    private String city;

    private String country;

    private String linkedInUrl;

    private String githubUrl;

    private String portfolioUrl;

    private String websiteUrl;

}
