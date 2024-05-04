package com.example.yumyelpv1.controllers;

import com.example.yumyelpv1.domain.users.RegisterDTO;
import com.example.yumyelpv1.domain.users.ResponseDTO;
import com.example.yumyelpv1.domain.users.User;
import com.example.yumyelpv1.infra.security.TokenService;
import com.example.yumyelpv1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.yumyelpv1.domain.users.AuthenticationDTO;
import javax.validation.Valid;

@RestController
@RequestMapping("auth")

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @PostMapping("login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth =  this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok("authentication realized success: " + new ResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        System.out.println(this.userRepository.findByEmail(data.email()) != null);
        String encrypetedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.username(), data.email(), encrypetedPassword, data.role());

        this.userRepository.save(user);

        return ResponseEntity.ok("register user success");
    }
}
