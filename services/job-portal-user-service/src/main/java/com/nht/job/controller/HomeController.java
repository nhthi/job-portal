package com.nht.job.controller;

import com.nht.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String home(){
        return "Job portal User service" + UserRole.ROLE_JOB_SEEKER;
    }
}
