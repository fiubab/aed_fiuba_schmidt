package c2026_01.semana01.jueves.teclado;

import java.util.Scanner;

import c2026_01.semana02.jueves.Pantalla;

public class LectorDeTeclado {

	public static Scanner teclado = new Scanner(System.in);
	
	public static String leerTexto(String... titulo) {
		if (titulo != null) {
			Pantalla.imprimir(titulo);
		}
		System.out.print(">");
		String texto;
		do {
			texto = teclado.nextLine();
		} while (texto.trim().isEmpty());
		return texto;
	}
	
	//Le un entero por teclado y lo devuelve
	public static int leerNumero() {
		return teclado.nextInt();
	}
	
	public static double leerNumeroDecimal() {
		return teclado.nextDouble();
	}
	
	public static void finalizar() {
		teclado.close();
	}
	
	//Pone el titulo y le un double
	public static double leerPuntoFlotante(String... titulo) {
		if (titulo != null) {
			Pantalla.imprimir(titulo);
		}
		return LectorDeTeclado.leerNumeroDecimal();
	}
}
