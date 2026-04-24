package c2026_01.semana05.miercoles;

public class Perro extends Animal {

	private Boolean usarCollarElectronico = false;
	

	public Perro() {
		this(30, true);
	}
	
	public Perro(int edad, boolean usaCollar) {
		this.setEdad(edad);
		this.usarCollarElectronico = usaCollar;
	}

	public Perro(int edad, boolean usaCollar, String nombre) {
		super(edad);
		this.usarCollarElectronico = usaCollar;
	}

	public Boolean getUsarCollar() {
		return usarCollarElectronico;
	}


	public void setUsarCollar(Boolean usarCollar) {
		this.usarCollarElectronico = usarCollar;
	}
	
	@Override
	public void imprimir() {
		super.imprimir();
		if (this.usarCollarElectronico) {
			System.out.println("Soy un perro de " + this.getEdad() + " con collar. Digo Guauu!!!");
		} else {
			System.out.println("Soy un perro de " + this.getEdad() + ". Digo Guauu!!!");
		}
	}
	
	@Override
	public void hacerSonido() {
		System.out.println("Hace guau!!");
	}
}
