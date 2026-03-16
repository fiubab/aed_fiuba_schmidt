/*
Hacer una función que, dado los coeficientes de un polinomio de segundo
grado (3 números reales), indique si tiene o no raíces reales, devolviendo un
valor booleano.
 */

public class Ej24 {
	// Ax^2 + Bx + C  2 grade polynomial
	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Insert A B C to check roots of Ax^2 + Bx + C");
			return;
		}

		int a = Integer.parseInt((args[0]));
		int b = Integer.parseInt((args[1]));
		int c = Integer.parseInt((args[2]));

		System.out.printf("""
			%sx^2 + %sx + %s
			Polynomial has roots? %b
			""",a, b, c, roots(a, b, c));
	}

	static boolean roots(int a, int b, int c) {
		// discriminant to determine real roots, disc = b^2 - 4ac
		int disc = b * b - 4 * a * c;
		return disc >= 0;
	}
}
