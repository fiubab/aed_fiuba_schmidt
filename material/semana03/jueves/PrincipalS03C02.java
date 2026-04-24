package c2026_01.semana03.jueves;

import java.math.BigDecimal;
import java.util.Arrays;

import c2026_01.semana02.jueves.Pantalla;

public class PrincipalS03C02 {

	public static int sumar1(int entero1, int entero2) {
		return entero1 + entero2;
	}
	
	/**
	 * Por valor. No cambia al salir
	 * @param entero1
	 * @param entero2
	 * @return
	 */
	public static int sumar2(int entero1, int entero2) {
		entero1 = 3;
		System.out.println( entero1 ); // 3
		return entero1 + entero2;
	}
	
	public static int sumar3(final int entero1, final int entero2) {
		return entero1 + entero2;
	}
	
	public static int sumar4(final Entero entero1, final Entero entero2) {
		return entero1.valor + entero2.valor;
	}
	
	public static int sumar5(final Entero entero1, final Entero entero2) {
		//entero1 = entero2; no se puede por final
		entero1.valor = 3; // si se puede
		return entero1.valor + entero2.valor;
	}
	
	/**
	 * suma e intercambia los valores de los punteros. Afecta al main
	 * @param entero1
	 * @param entero2
	 * @return
	 */
	public static int sumar6(final Entero entero1, final Entero entero2) {
		int valor = entero1.valor; 
		entero1.valor = entero2.valor;
		entero2.valor = valor; 
		return entero1.valor + entero2.valor;
	}
	
	/**
	 * Suma e intercambia los punteros. No afecta al main.
	 * @param entero1
	 * @param entero2
	 * @return
	 */
	public static int sumar7(Entero entero1, Entero entero2) {
		Entero valor = entero1; 
		entero1 = entero2;
		entero2= valor; 
		return entero1.valor + entero2.valor;
	}
	
	public static void ejercicio06() {
		System.out.println( sumar1(1, 3)); //da 4
		System.out.println( sumar2(1, 3)); //da 6
		{
			int entero1 = 1;
			System.out.println( sumar2(entero1, 3)); //da 6
			System.out.println( entero1 ); // 1
		}
		{
			Entero entero1 = new Entero();
			entero1.valor = 1;
			Entero entero2 = new Entero();
			entero2.valor = 3;
			System.out.println( sumar4(entero1, entero2)); //da 4
			System.out.println( entero1.valor ); //da 1
		}
		{
			Entero entero1 = new Entero();
			entero1.valor = 1;
			Entero entero2 = new Entero();
			entero2.valor = 3;
			System.out.println( sumar5(entero1, entero2)); //da 6
			System.out.println( entero1.valor ); // da 3
		}
		{
			Entero entero1 = new Entero();
			entero1.valor = 1;
			Entero entero2 = new Entero();
			entero2.valor = 3;
			System.out.println( sumar6(entero1, entero2)); //da 4
			System.out.println( entero1.valor ); // da 3
			System.out.println( entero2.valor ); // da 1
		}
		
		{
			Entero entero1 = new Entero();
			entero1.valor = 1;
			Entero entero2 = new Entero();
			entero2.valor = 3;
			System.out.println( sumar7(entero1, entero2)); //da 4
			System.out.println( entero1.valor ); // da 1
			System.out.println( entero2.valor ); // da 3
		}
	}

	public static void modificar(final int[] vector) {
		vector[8] = 10;
	}
	
	public static void ejercicio09() {
		int[] vector = new int[10];
		vector[0] = 10;
		System.out.println( Arrays.toString(vector));
		modificar(vector);
		System.out.println( Arrays.toString(vector));
		
		Integer entero = Integer.valueOf(8);
		entero = 0 + entero;
		entero = null; // 0x00000000
		
		Integer[] vectorI = new Integer[8];
		
		char[] texto1 = new char[1000];
		
		String texto2 = new String("Hola") + " mundo";
		StringBuilder texto3 = new StringBuilder("Hola").append(" mundo");
		
		int entero1 = 0;
		Integer entero2 = Integer.valueOf(8);
		Double flotante1 = Double.valueOf(8.56);
		double flotante2 = 8.5d;
		entero1 = flotante1.intValue();
		BigDecimal flotante3 = BigDecimal.ZERO;
	}
	
	public static void ejercicio10() {
		int entero1 = 5;
		int entero2 = 5;
		if (entero1 == entero2) {
			System.out.println("Son iguales");
		}
		
		String texto1 = String.valueOf("Hola");
		String texto2 = new String("Hola");
		if (texto1.equals(texto2)) {
			System.out.println("Son iguales");
		}
		if (texto1 == texto2) {
			
		}
	}
	
	public static long factorial(int n) {
		if (n < 0) {
			throw new RuntimeException("No puede ser negativo");
		}
		if ((n == 0) ||
		    (n == 1)) {
			return 1;
		}
		return n * factorial(n-1);
	}
	
	public static void ejercicio15() {
		long numero = factorial(14);
		System.out.println( Pantalla.formatear(numero));
		System.out.println( Pantalla.formatearEnLetras(numero));		
	}
	
	public static int[] duplicarA(int[] origen) {
		int[] resultado = new int[origen.length];
		for(int i = 0; i < origen.length; i++) {
			resultado[i] = origen[i];
		}
		return resultado;
	}
	
	public static int[] duplicarB(int[] origen) {
		int[] resultado = origen;
		return resultado;
	}
	
	public static void ejercicio20A() {
		int [] origen = new int[15];
		for(int i = 0; i < origen.length; i++) {
			origen[i] = i;
		}
		int [] destino = duplicarA(origen);
		System.out.println( "El origen: " + Arrays.toString(origen));		
		System.out.println( "El destino: " + Arrays.toString(destino));
		destino[8] = 0;
		
		System.out.println( "El origen: " + Arrays.toString(origen));		
		System.out.println( "El destino: " + Arrays.toString(destino));
	}
	
	public static void ejercicio20B() {
		int [] origen = new int[15];
		for(int i = 0; i < origen.length; i++) {
			origen[i] = i;
		}
		int [] destino = duplicarB(origen);
		System.out.println( "El origen: " + Arrays.toString(origen));		
		System.out.println( "El destino: " + Arrays.toString(destino));
		destino[8] = 0;
		
		System.out.println( "El origen: " + Arrays.toString(origen));		
		System.out.println( "El destino: " + Arrays.toString(destino));
	}
	
	public static void ejercicio20C() {
		int [] origen = new int[15];
		for(int i = 0; i < origen.length; i++) {
			origen[i] = i;
		}
		//int [] destino = duplicarB(origen);
		int [] destino = new int[origen.length];
		System.arraycopy(origen, 0, destino, 0, origen.length);
		System.out.println( "El origen: " + Arrays.toString(origen));		
		System.out.println( "El destino: " + Arrays.toString(destino));
		destino[8] = 0;
		
		System.out.println( "El origen: " + Arrays.toString(origen));		
		System.out.println( "El destino: " + Arrays.toString(destino));
	}
	
	public static void main(String[] args) {
		ejercicio20C();
	}
}
