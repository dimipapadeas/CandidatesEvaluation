package com.dterz.controllers;

import com.dterz.config.JwtTokenUtil;
import com.dterz.model.JWTToken;
import com.dterz.model.JwtRequest;
import com.dterz.model.JwtResponse;
import com.dterz.model.User;
import com.dterz.repositories.AuthRepository;
import com.dterz.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        JwtResponse authenticate = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(authenticate);
    }

    @DeleteMapping("/api/deleteToken/{username}")
    public boolean deleteToken(@PathVariable("username") String username) {
        JWTToken jwtByUsername = authRepository.getJWTByUsername(username);
        authRepository.delete(jwtByUsername);
        return true;
    }

    private JwtResponse authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            User user = userRepository.getUserByUsername(username);
            String saltedAndHashed = DigestUtils.sha256Hex(user.getSalt() + password);
            if (saltedAndHashed.equals(user.getPass())) {
                final String token = jwtTokenUtil.generateToken(user);
                JWTToken storedToekn = new JWTToken(token, username);
                authRepository.save(storedToekn);
                return new JwtResponse(token);
            } else {
                throw new Exception("not Autherised");
            }
        } catch (Exception e) {
            throw new Exception("USER_DISABLED", e);
        }
    }
}
