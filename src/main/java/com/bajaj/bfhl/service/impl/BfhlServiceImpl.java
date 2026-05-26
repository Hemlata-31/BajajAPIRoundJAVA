package com.bajaj.bfhl.service.impl;

import com.bajaj.bfhl.dto.BfhlRequestDTO;
import com.bajaj.bfhl.dto.BfhlResponseDTO;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of BfhlService.
 * Processes data arrays by separating numbers, alphabets, and special characters,
 * computing sum, and generating alternating-caps concatenation string.
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    // User credentials
    private static final String USER_ID = "hemlata_kumawat_31052006";
    private static final String EMAIL = "hemlatakumawat231001@acropolis.in";
    private static final String ROLL_NUMBER = "0827IT231052";

    @Override
    public BfhlResponseDTO processData(BfhlRequestDTO request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            if (isNumeric(item)) {
                // It's a number
                long number = Long.parseLong(item);
                sum += number;
                if (number % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                // It's purely alphabetic — store as uppercase
                alphabets.add(item.toUpperCase());
            } else {
                // It's a special character or mixed — treat as special character
                specialCharacters.add(item);
            }
        }

        // Build the concat_string:
        // 1. Extract all individual alphabetical characters from alphabetic entries (in order)
        // 2. Reverse the sequence
        // 3. Apply alternating caps: index 0 = upper, index 1 = lower, index 2 = upper, ...
        String concatString = buildConcatString(data);

        BfhlResponseDTO response = new BfhlResponseDTO();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcatString(concatString);

        return response;
    }

    /**
     * Checks if a string represents a valid integer (including negatives).
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string is purely alphabetic (a-z, A-Z).
     */
    private boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds the concatenation string:
     * 1. Collect all individual alphabetical characters from alphabetic items in order
     * 2. Reverse the entire list
     * 3. Apply alternating caps (upper, lower, upper, lower, ...)
     */
    private String buildConcatString(List<String> data) {
        // Step 1: Collect all alphabetical characters in order
        List<Character> allChars = new ArrayList<>();
        for (String item : data) {
            if (item == null || item.isEmpty()) continue;
            if (isAlphabetic(item)) {
                for (char c : item.toCharArray()) {
                    allChars.add(c);
                }
            }
        }

        if (allChars.isEmpty()) {
            return "";
        }

        // Step 2: Reverse
        Collections.reverse(allChars);

        // Step 3: Alternating caps — index 0 uppercase, index 1 lowercase, ...
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allChars.size(); i++) {
            char c = allChars.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }
}
