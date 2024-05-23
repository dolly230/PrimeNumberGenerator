package com.jspiders.prime.servlets;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {

    public static List<Integer> generatePrimesSimple(int min, int max) {
        List<Integer> primes = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static List<Integer> generatePrimesSieve(int min, int max) {
        boolean[] isPrime = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            isPrime[i] = true;
        }
        for (int factor = 2; factor * factor <= max; factor++) {
            if (isPrime[factor]) {
                for (int j = factor * factor; j <= max; j += factor) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(min, 2); i <= max; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}
