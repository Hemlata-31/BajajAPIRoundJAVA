package com.bajaj.bfhl.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Request DTO for the /bfhl POST endpoint.
 * Accepts an array of mixed data (numbers, alphabets, special characters).
 */
public class BfhlRequestDTO {

    @NotNull(message = "Data array must not be null")
    private List<String> data;

    public BfhlRequestDTO() {
    }

    public BfhlRequestDTO(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
