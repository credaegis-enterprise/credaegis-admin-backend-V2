package com.credaegis.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @Email
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
