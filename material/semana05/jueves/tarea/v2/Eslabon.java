package c2026_01.semana05.jueves.tarea.v2;

import java.util.Objects;

import material.utiles.ValidacionesUtiles;

public class Eslabon {
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private double ancho;
	private double largo;
	private boolean anchoFijo = false;
	
	public Eslabon(double ancho, double largo) {
		this(ancho, largo, false);
	}
	
	/**
	 * Crea un eslabon con el ancho y largo dado. El ancho y el largo deben ser mayor a 0.
	 * @param ancho: debe ser mayor a 0
	 * @param largo: debe ser mayor a 0
	 */
	public Eslabon(double ancho, double largo, boolean anchoFijo) {
		setAncho(ancho);
		setLargo(largo);
		this.anchoFijo = anchoFijo;
	}

//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	@Override
	public int hashCode() {
		return Objects.hash(ancho, largo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eslabon other = (Eslabon) obj;
		return Double.doubleToLongBits(ancho) == Double.doubleToLongBits(other.ancho)
				&& Double.doubleToLongBits(largo) == Double.doubleToLongBits(other.largo);
	}
	
	@Override
	public String toString() {
		return "Eslabon de " + this.ancho + " x " + this.largo;
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	/**
	 * @return el ancho actual del eslabon
	 */
	public double getAncho() {
		return ancho;
	}

	/**
	 * @return el largo actual del eslabon
	 */
	public double getLargo() {
		return largo;
	}
	
	public boolean getAnchoFijo() {
		return anchoFijo;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
	/**
	 * Cambia el ancho del eslabon
	 * @param ancho: el ancho debe ser mayor a 0
	 */
	public void setAncho(double ancho) {
		ValidacionesUtiles.validarMayorACero(ancho, "ancho");
		ValidacionesUtiles.validarFalso(this.anchoFijo, "el ancho no se puede modificar");
		this.ancho = ancho;
	}
	
	/**
	 * Cambia el largo del eslabon
	 * @param largo: el largo debe ser mayor a 0
	 */
	public void setLargo(double largo) {
		ValidacionesUtiles.validarMayorACero(ancho, "ancho");
		this.largo = largo;
	}
}
