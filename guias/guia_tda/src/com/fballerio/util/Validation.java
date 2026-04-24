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
			throw new IllegalArgumentException("texto" + texto + "invalido");
		}
	}

	/**
	 * Valida el año
	 * @param year int
	 */
	public static void validYear(int year) {
		if (1950 > year || year > 2026) {
			throw new IllegalArgumentException("Año" + year + "invalido");
		}
	}

	/**
	 * Valida cantidad de copias
	 * @param copies int
	 */
	public static void validCopies(int copies) {
		if (copies <= 0 || copies > 100) {
			throw new IllegalArgumentException("Cantidad de copias invalida");
		}
	}
}
