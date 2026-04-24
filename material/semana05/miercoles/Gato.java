package c2026_01.semana05.miercoles;

public class Gato extends Animal {

	
	@Override
	public void imprimir() {
		System.out.println("Soy un gato de " + this.getEdad() + ". Digo Miauuu!!!");
	}

	@Override
	public void hacerSonido() {
		System.out.println("miau");
	}
}
