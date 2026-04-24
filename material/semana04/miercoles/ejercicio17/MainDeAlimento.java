package c2026_01.semana04.miercoles.ejercicio17;

public class MainDeAlimento {

	public static void main(String[] args) {
		Alimento alimento1 = new Alimento("Papa", 1);
		System.out.println("Nombre: " + alimento1.getNombre());
		System.out.println("Calorias cada 100gr: " + alimento1.getCantidadDeCalorias());
		
		alimento1.setCantidadDeCalorias(20);
		System.out.println("Calorias cada 100gr: " + alimento1.getCantidadDeCalorias());
		
		
		System.out.println("Alimento: " + alimento1);
		
		
		System.out.println("Equals 1 vs 1: " + (alimento1.equals(alimento1)));
		System.out.println("Igual punteros: " + (alimento1 == alimento1));
		
		
		Alimento alimento2 = new Alimento("Papa", 50);
		System.out.println("Equals 1 vs 2: " + (alimento1.equals(alimento2)));
	}
}
