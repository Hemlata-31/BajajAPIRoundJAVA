package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequestDTO;
import com.bajaj.bfhl.dto.BfhlResponseDTO;
import com.bajaj.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for BfhlServiceImpl.
 * Covers all three examples from the problem statement plus edge cases.
 */
class BfhlServiceImplTest {

    private BfhlService bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    // ==================== Example A ====================
    @Test
    @DisplayName("Example A: Mixed data with numbers, alphabets, and special chars")
    void testExampleA() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("hemlata_kumawat_31052006", response.getUserId());
        assertEquals("hemlatakumawat231001@acropolis.in", response.getEmail());
        assertEquals("0827IT231052", response.getRollNumber());

        // Odd numbers: 1
        assertEquals(List.of("1"), response.getOddNumbers());

        // Even numbers: 334, 4
        assertEquals(List.of("334", "4"), response.getEvenNumbers());

        // Alphabets: A, R (uppercase)
        assertEquals(List.of("A", "R"), response.getAlphabets());

        // Special characters: $
        assertEquals(List.of("$"), response.getSpecialCharacters());

        // Sum: 1 + 334 + 4 = 339
        assertEquals("339", response.getSum());

        // Concat string: chars [a, R] → reverse [R, a] → alternating [R, a] = "Ra"
        assertEquals("Ra", response.getConcatString());
    }

    // ==================== Example B ====================
    @Test
    @DisplayName("Example B: Multiple alphabets and special characters")
    void testExampleB() {
        BfhlRequestDTO request = new BfhlRequestDTO(
                Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());

        // Odd: 5
        assertEquals(List.of("5"), response.getOddNumbers());

        // Even: 2, 4, 92
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());

        // Alphabets: A, Y, B
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());

        // Special: &, -, *
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());

        // Sum: 2 + 4 + 5 + 92 = 103
        assertEquals("103", response.getSum());

        // Concat: chars [a, y, b] → reverse [b, y, a] → alternating [B, y, A] = "ByA"
        assertEquals("ByA", response.getConcatString());
    }

    // ==================== Example C ====================
    @Test
    @DisplayName("Example C: Multi-character alphabetic strings")
    void testExampleC() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());

        // No numbers
        assertEquals(Collections.emptyList(), response.getOddNumbers());
        assertEquals(Collections.emptyList(), response.getEvenNumbers());

        // Alphabets: A, ABCD, DOE
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());

        // No special chars
        assertEquals(Collections.emptyList(), response.getSpecialCharacters());

        // Sum: 0
        assertEquals("0", response.getSum());

        // Concat: chars [A, A,B,C,D, D,O,E] → reverse [E,O,D,D,C,B,A,A]
        //   alternating: E,o,D,d,C,b,A,a = "EoDdCbAa"
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    // ==================== Edge Cases ====================

    @Test
    @DisplayName("Empty data array returns empty results")
    void testEmptyData() {
        BfhlRequestDTO request = new BfhlRequestDTO(Collections.emptyList());
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only numbers in data")
    void testOnlyNumbers() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("1", "2", "3", "4", "5"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertEquals(List.of("1", "3", "5"), response.getOddNumbers());
        assertEquals(List.of("2", "4"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("15", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only alphabets in data")
    void testOnlyAlphabets() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("a", "b", "c"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "B", "C"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        // chars [a,b,c] → reverse [c,b,a] → alternating [C,b,A] = "CbA"
        assertEquals("CbA", response.getConcatString());
    }

    @Test
    @DisplayName("Only special characters in data")
    void testOnlySpecialCharacters() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("$", "@", "#", "!"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(List.of("$", "@", "#", "!"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Single alphabet gives correct alternating caps")
    void testSingleAlphabet() {
        BfhlRequestDTO request = new BfhlRequestDTO(List.of("z"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertEquals(List.of("Z"), response.getAlphabets());
        // chars [z] → reverse [z] → alternating [Z] = "Z"
        assertEquals("Z", response.getConcatString());
    }

    @Test
    @DisplayName("Large numbers are handled correctly")
    void testLargeNumbers() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("1000000", "999999"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertEquals(List.of("999999"), response.getOddNumbers());
        assertEquals(List.of("1000000"), response.getEvenNumbers());
        assertEquals("1999999", response.getSum());
    }

    @Test
    @DisplayName("Zero is treated as even number")
    void testZeroIsEven() {
        BfhlRequestDTO request = new BfhlRequestDTO(List.of("0"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertEquals(List.of("0"), response.getEvenNumbers());
        assertEquals("0", response.getSum());
    }

    @Test
    @DisplayName("User credentials are always returned correctly")
    void testUserCredentials() {
        BfhlRequestDTO request = new BfhlRequestDTO(List.of("1"));
        BfhlResponseDTO response = bfhlService.processData(request);

        assertEquals("hemlata_kumawat_31052006", response.getUserId());
        assertEquals("hemlatakumawat231001@acropolis.in", response.getEmail());
        assertEquals("0827IT231052", response.getRollNumber());
    }
}
