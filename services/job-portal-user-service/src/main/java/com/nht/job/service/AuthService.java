package com.nht.job.service;

import com.nht.job.payload.AuthResponse;
import com.nht.job.payload.LoginRequest;
import com.nht.job.payload.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest req) throws Exception;
    AuthResponse login (LoginRequest req) throws Exception;
}
