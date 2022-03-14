package com.dterz.service;

import com.dterz.config.JwtTokenUtil;
import com.dterz.model.JwtResponse;
import com.dterz.model.User;
import com.dterz.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AuthenticationService {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    public JwtResponse authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new Exception("User not found");
            }
            String saltedAndHashed = DigestUtils.sha256Hex(user.getSalt() + password);
            if (saltedAndHashed.equals(user.getPass())) {
                final String token = jwtTokenUtil.generateToken(user);
                return new JwtResponse(token, user.getId());
            } else {
                throw new Exception("not Autherised");
            }
        } catch (Exception e) {
            throw new Exception("USER_DISABLED", e);
        }
    }
}
