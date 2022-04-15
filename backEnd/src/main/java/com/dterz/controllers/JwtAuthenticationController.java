package com.dterz.controllers;

import com.dterz.model.JwtRequest;
import com.dterz.model.JwtResponse;
import com.dterz.service.AuthenticationService;
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
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        JwtResponse response = authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(response);
    }
}
