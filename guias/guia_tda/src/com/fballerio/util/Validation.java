package com.fballerio.util;

/**
 * Lanza excepciones si las validaciones fallan
 */
public class Validation {
	/**
	 * Valida que el texto no sea nulo o un string vacio
	 * @param texto String 
	 */
	public static void validString(String texto) {
		if (texto == null || texto.isBlank()) {
			throw new RuntimeException("texto" + texto + "invalido");
		}
	}

	/**
	 * Valida el año
	 * @param año int
	 */
	public static void validYear(int year) {
		if (1600 > year || year > 2026) {
			throw new RuntimeException("Año" + year + "invalido");
		}
	}

	/**
	 * Valida cantidad de copias
	 * @param copias int
	 */
	public static void validCopies(int copies) {
		if (copies <= 0 || copies > 100) {
			throw new RuntimeException("Cantidad de copias invalida");
		}
	}
}
