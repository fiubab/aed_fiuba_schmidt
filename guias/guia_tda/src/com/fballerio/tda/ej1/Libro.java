package com.fballerio.tda.ej1;
import com.fballerio.util.Validation;


public class Libro {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
	//ATRIBUTOS DE CLASE ------------------------------------------------------------------------------------
	//ATRIBUTOS ---------------------------------------------------------------------------------------------
	private String titulo;
	private String autor;
	private int ano_publicacion;
	private int copias_disponibles;
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
	//CONSTRUCTORES -----------------------------------------------------------------------------------------
	public Libro(String titulo, String autor, int ano_publicacion, int copias) {
		setTitulo(titulo);
		setAutor(autor);
		setPublicacion(ano_publicacion);
		setCopiasDisponibles(copias);
	}
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true; // It's the exact same object in memory
		if (obj == null || getClass() != obj.getClass()) return false;

		Libro other = (Libro) obj;
		return this.titulo.equalsIgnoreCase(other.titulo) && this.autor.equals(other.autor) && this.ano_publicacion == other.ano_publicacion;
	}

	@Override
	public String toString() {
		return "Titulo:" + this.titulo + "\nAutor" + this.autor + "\nPublicado:" + this.ano_publicacion + "\n";
	}

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
	//GETTERS SIMPLES ---------------------------------------------------------------------------------------
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getAnoPublicacion() {
		return ano_publicacion;
	}

	public int getCopiasDisponibles() {
		return copias_disponibles;
	}

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
	//SETTERS SIMPLES ---------------------------------------------------------------------------------------
	/**
	 Asigna el titulo al objeto libro
	 @param titulo String
	 */
	public void setTitulo(String titulo) {
		Validation.validString(titulo);
		this.titulo = titulo;
	}

	/**
	 Asigna el autor al objeto libro
	 @param autor String
	 */
	public void setAutor(String autor) {
		Validation.validString(autor);
		this.autor = autor;
	}

	/**
	 Asigna el ano de publicacion al objeto libro
	 @param ano_publicacion int
	 */
	public void setPublicacion(int ano_publicacion) {
		Validation.validYear(ano_publicacion);
		this.ano_publicacion = ano_publicacion;
	}

	/**
	 Asigna la cantidad de copias disponibles al objeto libro
	 @param copias int
	 */
	public void setCopiasDisponibles(int copias) {
		Validation.validCopies(copias);
		this.copias_disponibles = copias;
	}
}