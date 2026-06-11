package com.nht.job.client;

import com.nht.job.dto.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "JOB-PORTAL-USER-SERVICE")
public interface UserClient {

    @PutMapping("/api/users/{userId}")
    UserResponse getUserById(
            @PathVariable Long userId
    );


}
