package com.example.yumyelpv1.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
