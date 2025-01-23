package com.crio.video_rental_system_advanced.dto;

import com.crio.video_rental_system_advanced.util.Role;
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
