package com.study.study.controllers;

import com.study.study.component.JwtUtils;
import com.study.study.models.Auth.JwtResponse;
import com.study.study.models.Auth.LoginRequest;
import com.study.study.models.Auth.RegisterRequest;
import com.study.study.models.Auth.Users;
import com.study.study.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@org.jetbrains.annotations.NotNull @RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.username(), req.password()
                )
        );
        System.out.println("Login");
        String token = jwtUtils.generateJwtToken(req.username());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody RegisterRequest req) {
        Users created = usersService.registerUser(req);
        return ResponseEntity.ok(created);
    }
}
