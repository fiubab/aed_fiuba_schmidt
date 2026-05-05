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

	public static void validDni(String dni) {
		if (dni == null || dni.isBlank()) {
			throw new IllegalArgumentException("dni" + dni + "invalido");
		}
		if (dni.length() != 8) {
			throw new IllegalArgumentException("dni" + dni + "invalido");
		}
		if (Integer.parseInt(dni) < 3000000 ||  Integer.parseInt(dni) > 60000000) {
			throw new IllegalArgumentException("dni" + dni + "no existente");
		}
	}
}
