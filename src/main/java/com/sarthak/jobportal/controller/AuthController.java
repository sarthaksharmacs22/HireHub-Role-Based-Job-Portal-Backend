package com.sarthak.jobportal.controller;

import com.sarthak.jobportal.model.User;
import com.sarthak.jobportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        return authService.login(
                request.get("email"),
                request.get("password")
        );
    }
}
