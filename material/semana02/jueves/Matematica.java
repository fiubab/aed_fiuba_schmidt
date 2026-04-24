package c2026_01.semana02.jueves;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Matematica {

	//Suma 2 valores double
	public static double sumar(double valor1, double valor2) {
		return valor1 + valor2;
	}
	
	public static double restar(double valor1, double valor2) {
		return valor1 - valor2;
	}

	public static double multiplicar(double valor1, double valor2) {
		return valor1 * valor2;
	}
	
	public static double dividir(double valor1, double valor2) {
		if (valor2 == 0) {
			throw new RuntimeException("No se puede dividir por 0");
		}
		return valor1 / valor2;
	}
	
	public static String resolverExpresion(String expresion) {
        Expression e = new ExpressionBuilder(expresion).build();
        return Pantalla.formatear( e.evaluate() );
	}
}
