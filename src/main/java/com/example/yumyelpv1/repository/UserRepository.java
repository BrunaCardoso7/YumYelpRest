package com.example.yumyelpv1.repository;

import com.example.yumyelpv1.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
}
