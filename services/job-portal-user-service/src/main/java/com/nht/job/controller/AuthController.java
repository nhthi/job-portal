package com.nht.job.controller;

import com.nht.job.payload.AuthResponse;
import com.nht.job.payload.LoginRequest;
import com.nht.job.payload.SignupRequest;
import com.nht.job.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid SignupRequest req) throws Exception {
        return ResponseEntity.ok(authService.signup(req));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) throws Exception {
        return ResponseEntity.ok(authService.login(req));
    }
}
