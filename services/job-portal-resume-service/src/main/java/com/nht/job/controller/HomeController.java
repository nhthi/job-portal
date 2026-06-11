package com.nht.job.controller;

import com.nht.job.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<ApiResponse> home(){
        return ResponseEntity.ok(new ApiResponse("Welcome to Job Portal Resume Service API",true));
    }
}
