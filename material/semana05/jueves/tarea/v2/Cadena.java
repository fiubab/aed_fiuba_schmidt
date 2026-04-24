package c2026_01.semana05.jueves.tarea.v2;

import java.util.Arrays;
import java.util.Objects;

import material.utiles.ValidacionesUtiles;

public class Cadena {
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Eslabon[] eslabones;
	private boolean anchoFijo = false;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * Creamos una cadena con la cantidad maxima de eslabones requeridas y un eslabon por defecto que define
	 * el ancho posible
	 * @param cantidadMaximaDeEslabones: debe ser mayor a 0
	 * @param eslabon: no puede ser nulo
	 */
	public Cadena(int cantidadMaximaDeEslabones, Eslabon eslabon) {
		this(cantidadMaximaDeEslabones, eslabon, false);
	}
		
	public Cadena(int cantidadMaximaDeEslabones, Eslabon eslabon, boolean anchoFijo) {		
		ValidacionesUtiles.validarMayorACero(cantidadMaximaDeEslabones, "cantidad de eslabones");
		ValidacionesUtiles.esDistintoDeNull(eslabon, "eslabon");
		this.eslabones = new Eslabon[cantidadMaximaDeEslabones];
		for(int i = 0; i < this.eslabones.length; i++) {
			this.eslabones[i] = null;
		}
		this.eslabones[0] = new Eslabon(eslabon.getAncho(), eslabon.getLargo(), anchoFijo);
		this.anchoFijo = anchoFijo;
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(eslabones);
		result = prime * result + Objects.hash(anchoFijo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cadena other = (Cadena) obj;
		return anchoFijo == other.anchoFijo && Arrays.equals(eslabones, other.eslabones);
	}
	
	@Override
	public String toString() {
		return "Una cadena de " + this.getCantidadDeEslabones() + " eslabones";
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	/**
	 * 
	 * @param eslabon
	 */
	public void agregarEslabon(Eslabon eslabon){
		ValidacionesUtiles.esDistintoDeNull(eslabon, "eslabon");
		if (anchoFijo) {
			ValidacionesUtiles.validarVerdadero( this.getAncho() == eslabon.getAncho(), "no respeta el ancho");
		}
		ValidacionesUtiles.validarFalso( this.estaCompleta(), "la cadena esta completa");
		this.eslabones[ this.getCantidadDeEslabones()] = new Eslabon(eslabon.getAncho(), eslabon.getLargo(), anchoFijo);
	}
	

	/**
	 * Devuelve verdadero si la cadena ya tiene todos los eslabones
	 * @return
	 */
	public boolean estaCompleta() {
		return this.getCantidadDeEslabones() == this.getCantidadMaximaDeEslabones();
	}
	
	/**
	 * Quita el eslabon del final de la cadena y lo devuelve. Si tiene 1 eslabon, da error
	 * @return
	 */
	public Eslabon quitarEslabon(){
		ValidacionesUtiles.validarVerdadero(this.getCantidadDeEslabones() == 1, "No puede quedar con menos de 1 eslabon");
		Eslabon resultado = this.eslabones[ this.getCantidadDeEslabones()];
		this.eslabones[ this.getCantidadDeEslabones()] = null;
		return resultado;
	}
	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * Devuelve la cantidad maxima de eslabones que la cadena puede contener.
	 * @return 
	 */
	public int getCantidadMaximaDeEslabones() {
		return this.eslabones.length;
	}
	
	/**
	 * Devuelve la cantidad actual de eslabones
	 * @return
	 */
	public int getCantidadDeEslabones() {
		for(int i = 0; i < this.eslabones.length; i++) {
			if (this.eslabones[i] == null) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * @return el ancho de la cadena
	 */
	public double getAncho() {
		if (this.anchoFijo) {
			return this.eslabones[0].getAncho();
		}
		Double maximo = null;
		for(Eslabon eslabon: this.eslabones) {
			if (eslabon != null) {
				if ((maximo == null) ||
				    maximo < eslabon.getAncho()) {
					maximo = eslabon.getAncho();
				}
			}
		}
		return maximo;
	}
	
	/**
	 * Devuelve el eslabon de la posicion i
	 * @param i
	 * @return
	 */
	public Eslabon getEslabon(int i) {
		ValidacionesUtiles.validarRangoNumerico(i, 1, this.getCantidadDeEslabones(), "posicion");
		return this.eslabones[i];
	}
	
	/**
	 * Devuelve el largo total de la cadena, sumando todos los largos de los eslabones
	 * @return
	 */
	public double getLargo() {
		double total = 0;
		for(int i = 0; i < this.eslabones.length; i++) {
			if (this.eslabones[i] != null) {
				total += this.eslabones[i].getLargo();
			}
		}
		return total;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
