package com.crio.CoderHack.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "user id cannot be blank")
    private String userId;

    @NotBlank(message = "Username cannot be blank")
    private String username;
}
