package com.crio.video_rental_system.dto;

import com.crio.video_rental_system.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private Role role;
}
