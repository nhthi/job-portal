package com.nht.job.service.impl;

import com.nht.job.domain.UserStatus;
import com.nht.job.dto.response.UserResponse;
import com.nht.job.mapper.UserMapper;
import com.nht.job.model.User;
import com.nht.job.payload.UpdateUserRequest;
import com.nht.job.repository.UserRepository;
import com.nht.job.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse updateProfile(String email, UpdateUserRequest req) {
        User user = userRepository.findByEmail(email);

        if(req.getFullName()!=null){
            user.setFullName(req.getFullName());
        }
        if(req.getPhone()!=null){
            user.setFullName(req.getPhone());
        }
        if(req.getProfileImage()!=null){
            user.setFullName(req.getProfileImage());
        }
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse suspendUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.SUSPENDED);
        user.setSuspendedAt(LocalDateTime.now());
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse activateUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.ACTIVE);
        user.setSuspendedAt(null);
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponse deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        user.setStatus(UserStatus.DELETED);
        user.setDeletedAt(LocalDateTime.now());
        return UserMapper.toDTO(userRepository.save(user));
    }
}
