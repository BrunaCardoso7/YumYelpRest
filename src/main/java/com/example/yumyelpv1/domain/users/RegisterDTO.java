package com.example.yumyelpv1.domain.users;

public record RegisterDTO(String username, String email, String password, UserRole role) {
}
