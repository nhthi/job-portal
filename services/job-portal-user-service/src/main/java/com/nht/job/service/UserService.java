package com.nht.job.service;

import com.nht.job.dto.response.UserResponse;
import com.nht.job.model.User;
import com.nht.job.payload.UpdateUserRequest;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email) throws Exception;

    User getUserById(Long id) throws Exception;

    List<User> getAllUsers();

    UserResponse updateProfile(String email, UpdateUserRequest req);

    // admin action

    UserResponse suspendUser(Long id) throws Exception;
    UserResponse activateUser(Long id) throws Exception;
    UserResponse deleteUser(Long id) throws Exception;
}
