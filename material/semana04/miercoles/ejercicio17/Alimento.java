package c2026_01.semana04.miercoles.ejercicio17;

import java.util.Objects;

/**
 * Define el comportamiento de un alimento.
 * Un alimento no puede cambiar el nombre despues de ser creado
 * Un alimento tiene calorias cada 100 gr y pueden cambiar
 */
public class Alimento extends Object {
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private String nombre = null;
	private double cantidadDeCalorias = 0;
		
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * Crea un alimento con el nombre dado y las calorias indicadas
	 * @param nombre: debe ser un texto de por lo menos 1 caracter
	 * @param cantidadDeCalorias: cada 100gr, debe ser 0 o mayor a cero
	 */
	public Alimento(String nombre, double cantidadDeCalorias) {
		setNombre(nombre);
		setCantidadDeCalorias(cantidadDeCalorias);
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return this.nombre + " (" + this.cantidadDeCalorias + " cal x 100gr)";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cantidadDeCalorias, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alimento other = (Alimento) obj;
		return Double.doubleToLongBits(cantidadDeCalorias) == Double.doubleToLongBits(other.cantidadDeCalorias)
				&& Objects.equals(nombre, other.nombre);
//		return Objects.equals(nombre, other.nombre);
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * @return devuelve el nombre del alimento
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return devuelve la cantidad de calorias cada 100 gr
	 */
	public double getCantidadDeCalorias() {
		return cantidadDeCalorias;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * 
	 * @param nombre: debe ser no nulo y tener al menos de 1 caracter
	 */
	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new RuntimeException("El nombre no puede ser nulo");
		}
		if (nombre.length() < 2) {
			throw new RuntimeException("El nombre debe tener por lo menos 1 caracter");
		}
		this.nombre = nombre;
	}

	/**
	 * Cambia la cantidad de calorias de un Alimento
	 * @param cantidadDeCalorias: debe ser igual o mayor a 0
	 */
	public void setCantidadDeCalorias(double cantidadDeCalorias) {
		if (cantidadDeCalorias < 0) {
			throw new RuntimeException("Las calorias deben ser mayor o igual a 0");
		}
		this.cantidadDeCalorias = cantidadDeCalorias;
	}	
}
