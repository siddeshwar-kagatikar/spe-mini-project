package com.example.calculator;

import java.math.BigInteger;

public class Calculator {

    public static double sqrt(double x) {
        if (x < 0) throw new IllegalArgumentException("sqrt: negative input");
        return Math.sqrt(x);
    }

    public static BigInteger factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("factorial: negative input");
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    public static double ln(double x) {
        if (x <= 0) throw new IllegalArgumentException("ln: input must be > 0");
        return Math.log(x);
    }
}
