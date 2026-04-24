package c2026_01.semana05.miercoles;

public abstract class Animal {

	private int edad = 0;
	
	public Animal() {
		this.edad = 20;
	}
	
	public Animal(int edad) {
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void imprimir() {
		System.out.println("Soy un animal de " + this.edad);
	}
	
	public abstract void hacerSonido();
}
