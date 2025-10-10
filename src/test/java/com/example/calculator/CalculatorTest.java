package com.example.calculator;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testSqrtPositive() {
        assertEquals(3.0, Calculator.sqrt(9.0), 1e-9);
    }

    @Test
    void testSqrtNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.sqrt(-4));
    }

    @Test
    void testFactorialSmall() {
        assertEquals(BigInteger.valueOf(120), Calculator.factorial(5));
    }

    @Test
    void testFactorialZero() {
        assertEquals(BigInteger.ONE, Calculator.factorial(0));
    }

    @Test
    void testLnPositive() {
        assertEquals(Math.log(2.0), Calculator.ln(2.0), 1e-9);
    }

    @Test
    void testLnZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> Calculator.ln(0));
    }

    @Test
    void testPow() {
        assertEquals(8.0, Calculator.pow(2.0, 3.0), 1e-9);
    }
}
