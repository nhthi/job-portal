package com.nht.job.service.impl;

import com.nht.job.domain.UserRole;
import com.nht.job.domain.UserStatus;
import com.nht.job.mapper.UserMapper;
import com.nht.job.model.User;
import com.nht.job.payload.AuthResponse;
import com.nht.job.payload.LoginRequest;
import com.nht.job.payload.SignupRequest;
import com.nht.job.repository.UserRepository;
import com.nht.job.security.CustomUserDetailsService;
import com.nht.job.security.JwtProvider;
import com.nht.job.service.AuthService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse signup(SignupRequest req) throws Exception {
        if(userRepository.existsByEmail(req.getEmail())){
            throw new Exception("Email already registered: "+req.getEmail());
        }

        if(req.getRole() == UserRole.ROLE_ADMIN){
            throw new Exception("cannot self-register as role admin");
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();
        User savedUser = userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication, savedUser.getId());
        AuthResponse res = new AuthResponse();
        res.setTitle("Welcome "+savedUser.getFullName());
        res.setMessage("Registered Successfully");
        res.setJwt(jwt);
        res.setUser(UserMapper.toDTO(savedUser));
        return res;
    }

    @Override
    public AuthResponse login(LoginRequest req) throws Exception {
        Authentication authentication = authenticate(
                req.getEmail(), req.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(req.getEmail());

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);
        AuthResponse res = new AuthResponse();
        res.setTitle("Welcome "+user.getFullName());
        res.setMessage("Login Successfully");
        res.setJwt(jwt);
        res.setUser(UserMapper.toDTO(user));
        return res;
    }

    private Authentication authenticate(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email, @NotBlank(message = "Email is mandatory") String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(userDetails==null){
            throw new Exception("user not found  email: "+email);
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new Exception("Invalid email or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
