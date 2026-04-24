package c2026_01.semana05.miercoles;

public class Principal {

	//Version 1
	public static void festejarCumpleanos(Animal animal) {
		animal.setEdad( animal.getEdad() + 1);	
		System.out.println("Hoy es el cumpleaños de " + animal);
	}
	
	//Version 2
	public static void festejarCumpleanos2(Perro animal) {
		animal.setEdad( animal.getEdad() + 1);	
	}
	
	public static void festejarCumpleanos2(Gato animal) {
		animal.setEdad( animal.getEdad() + 1);	
	}
	
	public static void main(String[] args) {
		Animal animalGato = new Gato();
		animalGato.imprimir();
		
		Animal animalSapo = new Sapo();
		animalSapo.imprimir();
		animalSapo.hacerSonido();
		
		Animal animalPerro = new Perro();
		animalPerro.imprimir();	//Imprimir de perro
		
		animalPerro = animalSapo;
		animalPerro.imprimir(); //imprimir del sapo (que no tiene) y luego el de animal
		
		festejarCumpleanos(animalPerro);
		
		//java 8
		if (animalPerro instanceof Perro) {
			Perro perro = (Perro) animalPerro;
			perro.setUsarCollar(false);
		}
		
		//Java +8
		if (animalPerro instanceof Perro perro) {
			perro.setUsarCollar(false);
		}
		
		//No se puede
		//Perro Perro = new Animal();
		
		Gato gato = new Gato();
		festejarCumpleanos(gato);
		
		festejarCumpleanos(new Sapo());
		
		
	}

}
