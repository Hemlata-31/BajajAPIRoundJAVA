package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.BfhlRequestDTO;
import com.bajaj.bfhl.dto.BfhlResponseDTO;
import com.bajaj.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST Controller for BFHL API endpoints.
 */
@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     * Processes the input data array and returns categorized results.
     */
    @PostMapping
    public ResponseEntity<BfhlResponseDTO> processData(@Valid @RequestBody BfhlRequestDTO request) {
        BfhlResponseDTO response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /bfhl
     * Returns a hardcoded operation code.
     */
    @GetMapping
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }
}
