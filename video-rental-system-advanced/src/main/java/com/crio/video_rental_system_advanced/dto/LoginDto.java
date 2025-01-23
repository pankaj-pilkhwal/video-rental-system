package com.crio.video_rental_system_advanced.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class LoginDto {
    @Email
    private String email;
    private String password;
}
