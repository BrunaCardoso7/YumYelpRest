package com.example.yumyelpv1.domain.users;

public record RequestUser(String username, String email, String password, UserRole role) {
}
