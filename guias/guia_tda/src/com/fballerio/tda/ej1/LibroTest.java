package com.fballerio.tda.ej1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

	// ---------------------------------------------------------
	// 1. HAPPY PATH (Successful creation)
	// ---------------------------------------------------------
	@Test
	public void testLibroCreacionExitosa() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);

		assertEquals("1984", libro.getTitulo());
		assertEquals("George Orwell", libro.getAutor());
		assertEquals(1969, libro.getAnoPublicacion());
		assertEquals(5, libro.getCopiasDisponibles());
	}

	// ---------------------------------------------------------
	// 2. SAD PATH (Testing your Validation library)
	// ---------------------------------------------------------
	@Test
	public void testTituloInvalidoLanzaExcepcion() {
		// We expect an exception (like IllegalArgumentException) when passing an empty string
		assertThrows(IllegalArgumentException.class, () -> {
			new Libro("", "George Orwell", 1969, 5);
		}, "Debería fallar al intentar crear un libro sin título.");
	}

	@Test
	public void testAutorNuloLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Libro("1984", null, 1969, 5);
		}, "Debería fallar al intentar crear un libro con autor nulo.");
	}

	@Test
	public void testCopiasNegativasLanzaExcepcion() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Libro("1984", "George Orwell", 1969, -1);
		}, "Debería fallar al intentar crear un libro con copias negativas.");
	}

	// ---------------------------------------------------------
	// 3. BEHAVIOR (Testing equals and toString)
	// ---------------------------------------------------------
	@Test
	public void testEqualsLibrosIdenticos() {
		Libro libro1 = new Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1954, 3);
		Libro libro2 = new Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1954, 5); // Different copies

		// Should be true because equals only compares Title and Author
		assertTrue(libro1.equals(libro2));
	}

	@Test
	public void testEqualsIgnoraMayusculas() {
		Libro libro1 = new Libro("harry potter", "j.k. rowling", 1997, 10);
		Libro libro2 = new Libro("Harry Potter", "J.K. Rowling", 1997, 10);

		// Should be true because of the equalsIgnoreCase we implemented
		assertTrue(libro1.equals(libro2));
	}

	@Test
	public void testEqualsLibrosDiferentes() {
		Libro libro1 = new Libro("1984", "George Orwell", 1969, 5);
		Libro libro2 = new Libro("Rebelión en la Granja", "George Orwell", 1965, 5);

		assertFalse(libro1.equals(libro2));
	}

	@Test
	public void testEqualsConNuloYOtroObjeto() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);

		assertFalse(libro.equals(null));
		assertFalse(libro.equals("Esto es un String, no un Libro"));
	}

	@Test
	public void testToStringFormatoCorrecto() {
		Libro libro = new Libro("Dune", "Frank Herbert", 1965, 2);
		String resultado = libro.toString();

		// Check that the toString contains the critical information
		assertTrue(resultado.contains("Dune"));
		assertTrue(resultado.contains("Frank Herbert"));
		assertTrue(resultado.contains("1965"));
	}

	// ---------------------------------------------------------
	// 4. PRESTABLE INTERFACE (Lending behavior)
	// ---------------------------------------------------------
	@Test
	public void testPrestarLibroDisponible() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 1);

		assertTrue(libro.estaDisponible());
		libro.prestar();
		assertFalse(libro.estaDisponible());
	}

	@Test
	public void testPrestarNoExcedeCopiasDisponibles() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 2);

		libro.prestar();
		libro.prestar();
		libro.prestar();

		assertFalse(libro.estaDisponible());
	}

	@Test
	public void testDevolverLibro() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 3);

		libro.prestar();
		libro.prestar();
		libro.prestar();
		assertFalse(libro.estaDisponible());

		libro.devolver();
		assertTrue(libro.estaDisponible());
	}

	@Test
	public void testDevolverNoNegativo() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 2);

		libro.devolver(); // Should not go below 0
		assertTrue(libro.estaDisponible());
	}

	@Test
	public void testMultiplePrestarDevolver() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);

		libro.prestar();
		libro.prestar();
		libro.prestar();
		libro.devolver();
		libro.devolver();

		assertTrue(libro.estaDisponible()); // 2 borrowed, 3 available
	}

	// ---------------------------------------------------------
	// 5. SETTERS
	// ---------------------------------------------------------
	@Test
	public void testSetAutor() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);
		libro.setAutor("G. Orwell");
		assertEquals("G. Orwell", libro.getAutor());
	}

	@Test
	public void testSetAnoPublicacion() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);
		libro.setPublicacion(1950);
		assertEquals(1950, libro.getAnoPublicacion());
	}

	@Test
	public void testSetCopiasDisponibles() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);
		libro.setCopiasDisponibles(10);
		assertEquals(10, libro.getCopiasDisponibles());
	}

	@Test
	public void testSetAutorInvalidoLanzaExcepcion() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);
		assertThrows(IllegalArgumentException.class, () -> {
			new Libro("1984", "George Orwell", 1969, 5).setAutor("");
		});
	}

	// ---------------------------------------------------------
	// 6. EDGE CASES
	// ---------------------------------------------------------
	@Test
	public void testConstructorAnoLimite() {
		assertThrows(IllegalArgumentException.class, () -> new Libro("Test", "Author", 1800, 1));
	}

	@Test
	public void testEqualsMismoObjeto() {
		Libro libro = new Libro("1984", "George Orwell", 1969, 5);
		assertTrue(libro.equals(libro));
	}
}
