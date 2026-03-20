package c2026_01.semana02.jueves;

import c2026_01.semana01.jueves.teclado.LectorDeTeclado;

public class Calculadora {
	
	private static String NombreDeArchivoHistorial = "historial.txt";
	
	/**
	 * 
	 * Realizar una calculadora con las operaciones:
	 * sumar, restar multiplicar y dividir
	 * 
	 * Y agregar la opcion de mostrar el historial de calculo
	 */
	public static void main(String[] args) {

		int opcion = 0;
		double valor1 = 0;
		double valor2 = 0;
		double resultado = 0;
		
		do {
			Pantalla.imprimir("**********Calculadora**************");
			Pantalla.imprimir("Ingrese una opcion:");
			Pantalla.imprimir("1 = para sumar");
			Pantalla.imprimir("2 = para restar");
			Pantalla.imprimir("3 = para multiplicar");
			Pantalla.imprimir("4 = para dividir");
			Pantalla.imprimir("5 = para expresiones ((5 + 5)^2 + 3 -5 * (12 / 3))^2");
			Pantalla.imprimir("6 = mostrar historial");
			Pantalla.imprimir("7 = borrar historial");
			Pantalla.imprimir("8 = cambiar precision");
			Pantalla.imprimir("9 = ultimo calculo");
			Pantalla.imprimir("0 = para salir");
			
			opcion = LectorDeTeclado.leerNumero();
			try {
				switch (opcion) {
					case 1:
						valor1 = LectorDeTeclado.leerPuntoFlotante("Valor 1");
						valor2 = LectorDeTeclado.leerPuntoFlotante("Valor 2");
						resultado = Matematica.sumar(valor1, valor2);
						Historial.imprimirYGuardarHistorial(NombreDeArchivoHistorial, "\n", "La suma de ", Pantalla.formatear(valor1), " + ", Pantalla.formatear(valor2), " = ",  Pantalla.formatear(resultado));
						break;
					case 2:
						valor1 = LectorDeTeclado.leerPuntoFlotante("Valor 1");
						valor2 = LectorDeTeclado.leerPuntoFlotante("Valor 2");
						resultado = Matematica.restar(valor1, valor2);
						Historial.imprimirYGuardarHistorial(NombreDeArchivoHistorial, "\n", "La resta de ", Pantalla.formatear(valor1), " - ", Pantalla.formatear(valor2), " = ",  Pantalla.formatear(resultado));
						break;
					case 3:
						valor1 = LectorDeTeclado.leerPuntoFlotante("Valor 1");
						valor2 = LectorDeTeclado.leerPuntoFlotante("Valor 2");
						resultado = Matematica.multiplicar(valor1, valor2);
						Historial.imprimirYGuardarHistorial(NombreDeArchivoHistorial, "\n", "La multiplicacion de ", Pantalla.formatear(valor1), " * ", Pantalla.formatear(valor2), " = ",  Pantalla.formatear(resultado));
						break;
					case 4:
						try {
							valor1 = LectorDeTeclado.leerPuntoFlotante("Valor 1");
							valor2 = LectorDeTeclado.leerPuntoFlotante("Valor 2");
							resultado = Matematica.dividir(valor1, valor2);
							Historial.imprimirYGuardarHistorial(NombreDeArchivoHistorial, "\n", "La division de ", Pantalla.formatear(valor1), " / ", Pantalla.formatear(valor2), " = ",  Pantalla.formatear(resultado));
						} catch (RuntimeException e) {
							Pantalla.imprimir("No se puede ejecutar la division de ", Pantalla.formatear(valor1), " / ", Pantalla.formatear(valor2));
						}
						break;
					case 5:
						String expresion = LectorDeTeclado.leerTexto("Ingrese la expresion");
						Historial.imprimirYGuardarHistorial(NombreDeArchivoHistorial, "La expresion ", expresion, " = ", Matematica.resolverExpresion(expresion));
						break;
					case 6:
						Historial.mostrarHistorial(NombreDeArchivoHistorial);
						break;		
					case 7:
						Historial.eliminarHistorial(NombreDeArchivoHistorial);
						break;	
					case 8:
						Pantalla.imprimir("Ingrese la precision de las operaciones");
						Pantalla.cambiarPrecision(LectorDeTeclado.leerNumero());
						break;	
					case 9:
						Pantalla.imprimir( Historial.leerUltimoHistorial(NombreDeArchivoHistorial));
						break;
					
				}
			} catch (Exception e) {
				Pantalla.imprimir("La operacion no se pudo realizar, por favor vuelva a intentar otra operacion");
			}
		} while (opcion > 0);
			
		Pantalla.imprimir("Gracias por utilizar la calculadora!!!");
	}
}
