package com.sarthak.jobportal.service;

import com.sarthak.jobportal.model.User;
import com.sarthak.jobportal.repository.UserRepository;
import com.sarthak.jobportal.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Registration still uses MySQL
    public User register(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    // Login now uses LDAP Authentication
    public String login(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        try {

            // LDAP Authentication
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );

            User user = userOptional.get();

            // Generate JWT if LDAP auth succeeds
            return jwtUtil.generateToken(user.getEmail(), user.getRole());

        } catch (AuthenticationException e) {

            return "Invalid LDAP credentials";
        }
    }
}