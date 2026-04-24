package c2026_01.semana01.jueves;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import c2026_01.semana01.jueves.teclado.LectorDeTeclado;
import c2026_01.semana01.jueves.tp1.Persona;

public class Principal {

	public static void imprimirNumero(double numero) {
		imprimirTexto("" + numero);
	}
	
	public static void imprimirTexto(String texto) {
		System.out.println(texto);
	}
		
	/**
	 * Programa hola mundo
	 * @param args
	 */
	public static void introduccion(String[] args) {
		if (args != null) {
			for(int i = 0; i < args.length; i++) {
				imprimirTexto("Parametro numero " + (i+1) + " es: " + args[i]);
			}
			
			int entero = 0;
			if (entero == args.length) {
				imprimirTexto("No hay argumentos");
			}
		}
		
		Random random = new Random(System.nanoTime());
		
		int numero = random.nextInt(100);
		if (numero > 50) {
			imprimirTexto("Es un numero grande " + numero);
		}
		
		boolean esVerdadero = true;
		if (esVerdadero) {
			imprimirTexto("Es verdadero");
		}
		
		int[] vectorDeEnteros = new int[500];
		for(int i = 0; i < vectorDeEnteros.length; i++) {
			vectorDeEnteros[i] = random.nextInt(100);
		}
		
		imprimirTexto("El promedio es: " + promedio(vectorDeEnteros));
		
		
		leerPorTeclado();
		leerPorTeclado();
		
		LectorDeTeclado.finalizar();
	}

	public static void leerPorTeclado() {		
		String texto = LectorDeTeclado.leerTexto();
		imprimirTexto("Se leyo: " + texto);		
	}

	public static double promedio(int[] vectorDeEnteros) {
		if ((vectorDeEnteros != null) &&
		   (vectorDeEnteros.length > 0)) {
			double promedio = 0;
			for(int i = 0; i < vectorDeEnteros.length; i++) {
				promedio += vectorDeEnteros[i];
			}
			return promedio / vectorDeEnteros.length;
		}
		leerPorTeclado();
		return 0.0;
	}
	
	
	public static void e01g01() {
		imprimirTexto("Por favor ingrese un numero entero");
		System.out.println( "El numero ingresado es: " + LectorDeTeclado.leerNumero());
		System.err.println( "El numero ingresado es: " + LectorDeTeclado.leerNumero());
	}
	
	public static void e02g01() {
		imprimirTexto("Por favor ingrese el primer numero entero");
		int numero1 = LectorDeTeclado.leerNumero();
		imprimirTexto("Por favor ingrese el segundo numero entero");
		int numero2 = LectorDeTeclado.leerNumero();
		
		System.err.println( "La suma es: " + (numero1 + numero2));
		System.err.println( "La resta es: " + (numero1 - numero2));
		System.err.println( "La multiplicacion es: " + (numero1 * numero2));
		if (numero2 != 0) {
			System.err.println( "La division es: " + formatear(Double.valueOf(numero1) / numero2));
		} else {
			System.err.println( "La division no se puede calcular");
		}
	}
	
	public static String formatear(double numero) {
		DecimalFormat decimalFormat = new DecimalFormat("#0.000");
		return decimalFormat.format(numero);
	}
	
	public static void e13g01() {
		imprimirTexto("Por favor ingrese un numero entero");
		int numero = LectorDeTeclado.leerNumero();
		String temp = "";
		for(int i = 0; i <= 20; i++) {
			temp += (numero + i) + ", ";
		}
		System.out.println("[" + temp.substring(0, temp.length() - 2) + "]");
	}
	
	public static void ejemplosDeStrings() {
		String texto = "Hola mundo  ";
		
		System.out.println("Largo: " + texto.length());
		System.out.println("Mayusculas: " + texto.toUpperCase());
		System.out.println("Minusculas: " + texto.toLowerCase());
		System.out.println("Caracter en la posicion 1: " + texto.charAt(1));
		System.out.println("Sacar espacions (inicio y fin): " + texto.trim());
		System.out.println("Sub texto entre 0 y 4: '" + texto.substring(0, 4) + "'");
		System.out.println("Sub texto entre 0 y 4: '" + texto.substring(0, 4).trim() + "'");
		System.out.println("Sub texto entre 0 y 4: '" + texto.substring(0, 10) + "'");
		System.out.println("Sub texto entre 0 y 4: '" + texto.substring(0, texto.length() - 2) + "'");
		System.out.println("Contiene 'mun': " + texto.contains("mun"));
		System.out.println("Contiene 'mun': " + texto.contains("hol"));
		System.out.println("Contiene 'mun': " + texto.toLowerCase().contains("hol"));
		System.out.println("Es igual: " + texto.equals("hola mundo"));
		System.out.println("Es igual: " + texto.trim().equals("hola mundo"));
		System.out.println("Es igual: " + texto.equalsIgnoreCase("hola mundo"));
		System.out.println("Es igual: " + texto.trim().equalsIgnoreCase("hola mundo"));
		System.out.println("Empieza con: " + texto.startsWith("hola")); //false
		System.out.println("Termina con: " + texto.endsWith("ndo"));
		System.out.println("Posicion de: " + texto.indexOf("ndo"));
		System.out.println("Reemplaza: " + texto.replace("mundo", "hola")); //Hola hola		
		System.out.println("Split: " + Arrays.toString( texto.split("o")));  //"H|la mund|  " 
	}
	
	
	public static void main(String[] args) {
		//e01g01();
		testAgenda();		
		
	}
	
	public static void testAgenda() {
		Persona[] agenda = new Persona[100];
		
		for(int i = 0; i < agenda.length; i++) {
			agenda[i] = null;
		}
		
		agenda[0] = new Persona();
		agenda[0].nombre = "Gustavo";
		agenda[0].apellido = "Schmidt";
		agenda[0].telefono = "5555-4444";
		
		System.out.println( Persona.firmar(agenda[0]));
		System.out.println( Persona.firmar(agenda[0].apellido, agenda[0].nombre));
		
		agenda[1] = new Persona();
		agenda[1].nombre = "Gaston";
		agenda[1].telefono = "5555-4445";
		
		for(int i = 0; i < agenda.length; i++) {
			if (agenda[i] != null) {
				System.out.println("-----------------------");
				System.out.println("Nombre:\t\t" + agenda[i].nombre);
				System.out.println("Telefono:\t" + agenda[i].telefono);
				System.out.println("Calle:\t\t" + agenda[i].calle);
			}			
		}
	}
}
