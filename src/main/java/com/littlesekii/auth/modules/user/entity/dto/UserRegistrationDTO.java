package com.littlesekii.auth.modules.user.entity.dto;

import com.littlesekii.auth.modules.user.entity.enums.UserRole;

public record UserRegistrationDTO(String username, String password, UserRole role) {
}
