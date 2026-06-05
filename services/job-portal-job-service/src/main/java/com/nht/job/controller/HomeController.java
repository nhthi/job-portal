package com.nht.job.controller;

import com.nht.job.domain.UserRole;
import com.nht.job.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse home(){
        return new ApiResponse("Service for managing job posting, search and filtering --- "+ UserRole.ROLE_EMPLOYER,true);
    }
}
