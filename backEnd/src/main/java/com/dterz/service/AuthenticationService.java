package com.dterz.service;

import com.dterz.model.JwtResponse;
import com.dterz.model.UserPrincipal;
import com.dterz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserService userService;
    
    /**
     * Authenticates the User from the credentials provided during log in
     *
     * @param username the username provided
     * @param password the plain text password provided
     * @return JwtResponse
     */
    public JwtResponse authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserPrincipal userDetails = userService.loadUserByUsername(username);
        String token = jwtService.generateToken(userDetails);
        return JwtResponse.builder().jwttoken(token).userId(userDetails.getUserId()).isAdmin(userDetails.isSuperAdmin()).build();
    }
}
