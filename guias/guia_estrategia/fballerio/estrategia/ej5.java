package com.fballerio.estrategia;

public class ej5 {

	/**
	 * Recibe 2 enteros a y b, y calcula el MAXIMO COMUN DIVISOR utilizando el algoritmo de Euclides
	 * @param a int
	 * @param b int
	 */
	static int mcd(int a, int b) {
		if (b == 0) return a;
		int rest = a % b;
		return mcd(b, rest);
	}

	static void main() {
		int a = 48;
		int b = 18;
		System.out.printf("El mcd de %d y %d es %d", a, b, mcd(a, b));
	}
}
