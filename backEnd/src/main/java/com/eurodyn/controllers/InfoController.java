package com.eurodyn.controllers;

import com.eurodyn.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/info/")
@CrossOrigin
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    /**
     * Returns the current Version of the components of this Application
     *
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping("getVersion/{component}")
    public ResponseEntity<Map<String, Object>> getVersion(@PathVariable("component") String component) {
        Map<String, Object> version = infoService.getVersion(component);
        return new ResponseEntity<>(version, HttpStatus.OK);
    }
}
