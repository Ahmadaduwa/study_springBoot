package com.study.study.controllers;


import com.study.study.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {

    private final UsersService usersService;

    public SecurityController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public  String greet(HttpServletRequest request){
        return "SessionId: " + request.getSession().getId();
    }

    @GetMapping("/api/security/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
