package c2026_01.semana05.jueves.tarea.v1;

public class Eslabon {
	//CONSTANTES
	public static double ANCHO = 3.5;
	
	//ATRIBUTOS
	private double largo;
	
	//CONSTRUCTORES
	public Eslabon(double largo) {
		setLargo(largo);
	}
	
	//GETTERS
	public double getLargo() {
		return largo;
	}
	
	//SETTERS
	/**
	 * 
	 * @param largo debe ser mayor a 0
	 */
	public void setLargo(double largo) {
		this.largo = largo;
	}
}
