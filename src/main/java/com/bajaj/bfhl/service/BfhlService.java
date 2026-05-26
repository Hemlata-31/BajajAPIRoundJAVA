package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequestDTO;
import com.bajaj.bfhl.dto.BfhlResponseDTO;

/**
 * Service interface for BFHL data processing.
 * Defines the contract for processing mixed data arrays.
 */
public interface BfhlService {

    /**
     * Processes the incoming data array and returns a structured response
     * with separated numbers, alphabets, special characters, and computed values.
     *
     * @param request the request DTO containing the data array
     * @return the response DTO with processed results
     */
    BfhlResponseDTO processData(BfhlRequestDTO request);
}
