package com.nht.job.payload;

import com.nht.job.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;


    private String phone;

    @NotBlank(message = "Role is mandatory")
    private UserRole role;


}
