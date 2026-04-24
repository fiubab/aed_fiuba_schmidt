package c2026_01.semana04.miercoles;

public class Complejo {
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------	
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private double real = 0;
	private double imaginaria = 0;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	public Complejo() {}

	public Complejo(double real, double imaginaria) {
		this.setReal(real);
		this.setImaginaria(imaginaria);
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	/**
	 * Suma al complejo actual, el valor por parametro y lo devuelve como un nuevo complejo
	 * @param complejo: un valor complejo
	 * @return: un nuevo complejo con la suma
	 */
	public Complejo sumar(Complejo complejo) {
		if (complejo == null) {
			throw new RuntimeException("El complejo a sumar no puede ser nulo");
		}
		return new Complejo( this.real + complejo.real, this.imaginaria + complejo.imaginaria);
	}
	
	/**
	 * Suma a la parte real del complejo, el valor real pasado por parametro
	 * @param real: valor flotante a sumar
	 * @return un nuevo complejo con la suma
	 */
	public Complejo sumar(double real) {
		return new Complejo( this.real + real, this.imaginaria);
	}
	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public double getReal() {
		return real;
	}
	
	public double getImaginaria() {
		return imaginaria;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
	public void setReal(double real) {
		this.real = real;
	}
	public void setImaginaria(double imaginaria) {
		this.imaginaria = imaginaria;
	}
}
