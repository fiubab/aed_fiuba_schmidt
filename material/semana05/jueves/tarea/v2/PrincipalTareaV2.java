package c2026_01.semana05.jueves.tarea.v2;

public class PrincipalTareaV2 {

	public static void main(String[] args) {
		Eslabon eslabon = new Eslabon(10, 10);
		
		Cadena cadena = new Cadena(20, eslabon, true);
		
		for(int i = 1; i <= cadena.getCantidadDeEslabones(); i++) {
			System.out.println("El largo del eslabon " + i  + " es " + cadena.getEslabon(i).getLargo());
			cadena.getEslabon(i).setAncho(50);
		}
	}
	
	public static void mainArtesano(String[] args) {
		Eslabon eslabon = new Eslabon(10, 10);
		
		Cadena cadena = new Cadena(20, eslabon);
		
		for(int i = 1; i <= cadena.getCantidadDeEslabones(); i++) {
			System.out.println("El largo del eslabon " + i  + " es " + cadena.getEslabon(i).getLargo());
			cadena.getEslabon(i).setAncho(50);
		}
	}
}
