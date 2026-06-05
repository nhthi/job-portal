package com.nht.job.model;

import com.nht.job.domain.SocialPlatform;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class SocialLink {

    private SocialPlatform platform;
    private String url;

}
