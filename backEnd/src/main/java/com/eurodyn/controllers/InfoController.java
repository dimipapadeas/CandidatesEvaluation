package com.eurodyn.controllers;

import com.eurodyn.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getVersion(@PathVariable("component") String component) {
        return ResponseEntity.ok(infoService.getVersion(component));
    }
}
