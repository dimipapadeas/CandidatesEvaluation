package com.dterz.controllers;

import com.dterz.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/info/")
@CrossOrigin
@RequiredArgsConstructor
public class InfoController {

    @GetMapping("getVersion")
    public ResponseEntity<Map<String, Object>> getVersion() {
        Map<String, Object> response = new HashMap<>();
        response.put("backVersion", Constants.BACK_VERSION);
        response.put("frontVersion", Constants.FRONT_VERSION);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
