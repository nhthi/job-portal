package com.nht.job.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfo {

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
