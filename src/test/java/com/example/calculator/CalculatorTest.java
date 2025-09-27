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

   
}
