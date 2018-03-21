package com.prom.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Timed
public class ErrorController {



    @GetMapping("/exc")
    public ResponseEntity<String> error() {
        throw new RuntimeException("error");
    }

    @GetMapping("/500")
    public ResponseEntity<String> internal() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }
}
