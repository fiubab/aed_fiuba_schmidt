package com.fballerio.estrategia.ej4;

public class Main {
    /**
     * This function takes an int n and returns the nth fibonacci number
     * @param n int
     */
    static int fibonacciR(int n) {
        if (n < 1) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        if (n == 3) return 1;
        if (n == 4) return 2;
        return (fibonacciR(n-1) + fibonacciR(n-2));
    }

    static int fibonacciI(int n) {
        if (n < 1) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        if (n == 3) return 1;

        int iterator = n;
        int prev = 1;
        int last = 1;
        int res = 0;

        while (iterator > 3) {
            res = prev + last;
            prev = last;
            last = res;
            iterator--;
        }
        return res;
    }

    static void main() {
//        for (int i = 0; i <= 45; i++) {
//        System.out.printf("Fibonacci %d th number is %d\n", i, fibonacciR(i));
//        }
        int i = 46;
        System.out.printf("Fibonacci %d th number is %d\n", i, fibonacciI(i));
    }
}
