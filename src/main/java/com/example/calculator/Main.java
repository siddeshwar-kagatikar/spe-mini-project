package com.example.calculator;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Scientific Calculator ---");
            System.out.println("1) sqrt(x)");
            System.out.println("2) factorial(n)");
            System.out.println("3) ln(x)");
            // System.out.println("4) power x^b");
            System.out.println("0) exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter x (double): ");
                        double x = Double.parseDouble(sc.nextLine());
                        System.out.println("sqrt(" + x + ") = " + Calculator.sqrt(x));
                    }
                    case "2" -> {
                        System.out.print("Enter n (non-negative integer): ");
                        int n = Integer.parseInt(sc.nextLine());
                        BigInteger f = Calculator.factorial(n);
                        System.out.println(n + "! = " + f);
                    }
                    case "3" -> {
                        System.out.print("Enter x (double > 0): ");
                        double x = Double.parseDouble(sc.nextLine());
                        System.out.println("ln(" + x + ") = " + Calculator.ln(x));
                    }
                    case "0" -> {
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
