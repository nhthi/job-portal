package com.nht.job.controller;

import com.nht.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return "Hello - This is company service " + UserRole.ROLE_EMPLOYER;
    }
}
