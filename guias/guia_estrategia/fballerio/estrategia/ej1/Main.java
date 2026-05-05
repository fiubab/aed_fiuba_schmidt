package com.fballerio.estrategia.ej1;

public class Main {
	static int factorial(int n) {
		if (n == 0) { return 1;}
		if (n == 1) { return 1; }
		return factorial(n-1) * n;
	}
	static void main(String[] args) {
		int res = factorial(7);
		System.out.printf("Factorial de 7 (7!) es: %d%n", res);
	}
}
