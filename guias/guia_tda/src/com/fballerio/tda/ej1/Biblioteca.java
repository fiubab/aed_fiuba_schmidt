package com.fballerio.tda.ej1;

import java.util.ArrayList;
import java.util.List;
public class Biblioteca {
//conjunto de libros q permita operaciones como agregar un libro, buscar por título o autor, y prestar
//o devolver un libro.

//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	List<Libro> libros; // array con libros
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
	// tendremos 2 constructores:
	//    - biblioteca vacia
	//    - biblioteca con libros
	public Biblioteca() {
		setLibros(null);
	}

	public Biblioteca(List<Libro> libros) {
		setLibros(libros);
	}
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	public void showLibros() {
		for (Libro libro : this.libros) {
			System.out.printf(libro.toString());
		}
	}

	public boolean vacia() {
		return this.libros.isEmpty();
	}

	public Libro buscarLibro(String titulo, String autor, int ano_publicacion) {
		Libro buscado = new Libro(titulo, autor, ano_publicacion, 0);
		for (Libro libro : this.libros) {
			if (libro.equals(buscado)) return libro;
		}
		System.err.println("Libro no encontrado");
		return null;
	}

	public List<Libro> buscarAutor(String autor) {
		List<Libro> buscados = new ArrayList<Libro>();
		for (Libro libro : this.libros) {
			if (libro.getAutor().equalsIgnoreCase(autor)) {
				buscados.add(libro);
			}
		}
		if  (buscados.isEmpty()) System.out.printf("No hay libros de:", autor, "\n");
		return buscados;
	}

 	public void agregarLibro(String titulo, String autor, int ano_publicacion, int copias) {
	  Libro nuevo = new Libro(titulo, autor, ano_publicacion, copias);
		this.libros.add(nuevo);
  }

	public void borrarLibro(String titulo, String autor, int ano_publicacion) {
		Libro libro = new Libro(titulo, autor, ano_publicacion, 0);
		this.libros.remove(libro);
	}

	public void prestarLibro() {}
	public void devolverLibro() {}
	public int cantidadPrestados() {}
	public List<Libro> prestados() {}
	public borrarLibro() {}
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	public List<Libro> getLibros() {
		return this.libros;
	}
//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
}
