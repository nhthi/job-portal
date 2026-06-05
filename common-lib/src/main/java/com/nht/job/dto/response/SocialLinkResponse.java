package com.nht.job.dto.response;

import com.nht.job.domain.SocialPlatform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SocialLinkResponse {
    private SocialPlatform platform;
    private String url;
}
