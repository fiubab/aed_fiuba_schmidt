package c2026_01.semana04.miercoles;

public class Principal {

	public static void imprimir() {
		System.err.println();
		//this no existe
	}
	
	public static void main(String[] args) {
		Complejo complejo1  = new Complejo(10, 10);
		complejo1.setReal( 8 );
		
		System.out.println( complejo1.getReal() ); //Imprime 8
		System.out.println( complejo1.getImaginaria() ); //Imprime 0 -> -10 -> 10
		
		
		Complejo complejo2  = new Complejo(1, 3);
		complejo2.setReal( 8 );
		System.out.println( complejo2.getReal() ); //Imprime 8
		complejo2.setReal( 10 );
		System.out.println( complejo2.getReal() ); //Imprime 10
		System.out.println( complejo1.getReal() ); //Imprime 8
		
		Complejo complejo3  = new Complejo(0, 0);
		
		Complejo complejo4  = new Complejo();
		
		complejo4.getImaginaria();
		
		complejo4.sumar(complejo3);
		complejo4.sumar(18);
		
		imprimir();

	}

}
