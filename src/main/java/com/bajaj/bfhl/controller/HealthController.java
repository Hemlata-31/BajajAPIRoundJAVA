package com.bajaj.bfhl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Health check controller.
 */
@RestController
@RequestMapping("/health")
@CrossOrigin(origins = "*")
public class HealthController {

    /**
     * GET /health
     * Returns a simple health check response.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "message", "BFHL API is running"
        ));
    }
}
