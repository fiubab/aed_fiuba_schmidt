package c2026_01.semana05.jueves.tarea.v1;

public class MainTareaV1 {

	public static void main(String[] args) {
		Cadena cadena1 = new Cadena(10, 10);
		cadena1.agregarEslabon();
		cadena1.agregarEslabon();
		cadena1.agregarEslabon();
		
		for(int i = 0; i < cadena1.getLargo(); i++) {
			System.out.println(cadena1.getCadena()[i].getLargo());
			cadena1.getCadena()[i] = null;
		}
		
	}
}
