package c2026_01.semana01.jueves.tp1;

public class Persona {

	public String nombre;
	public String apellido;
	public String telefono;
	public String calle;
	
	public static String firmar(String nombre, String apellido) {
		return apellido + ", " + nombre;
	}
	
	public static String firmar(Persona persona) {
		return persona.apellido + ", " + persona.nombre;
	}
}
