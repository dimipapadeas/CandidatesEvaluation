package com.dterz.service;

import com.dterz.config.JwtTokenUtil;
import com.dterz.model.JwtResponse;
import com.dterz.model.User;
import com.dterz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    /**
     * Authenticates the User from the credentials provided during log in
     *
     * @param username the username provided
     * @param password the plain text password provided
     * @return JwtResponse
     * @throws Exception throws an Exception
     */
    public JwtResponse authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            User user = userRepository.findByUserName(username);
            if (user == null) {
                throw new Exception("User not found");
            }
            String saltedAndHashed = DigestUtils.sha256Hex(user.getSalt() + password);
            if (saltedAndHashed.equals(user.getPass())) {
                final String token = jwtTokenUtil.generateToken(user);
                return new JwtResponse(token, user.getId(), user.isSuperAdmin());
            } else {
                throw new Exception("not Autherised");
            }
        } catch (Exception e) {
            throw new Exception("USER_DISABLED", e);
        }
    }
}
