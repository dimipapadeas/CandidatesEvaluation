package com.eurodyn.controllers;

import com.eurodyn.model.JwtRequest;
import com.eurodyn.model.JwtResponse;
import com.eurodyn.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Authenticates the User from the credentials provided during log in
     *
     * @param authenticationRequest provided credentials
     * @return ResponseEntity<?>
     * @throws Exception throws an Exception
     */
    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        JwtResponse response = authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@RequestBody String username) {
        JwtResponse response = authenticationService.logout(username);
        return ResponseEntity.ok(response);
    }
}
