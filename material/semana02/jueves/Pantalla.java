package c2026_01.semana02.jueves;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.ibm.icu.text.RuleBasedNumberFormat;

public class Pantalla {

	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	private static NumberFormat numberFormat = NumberFormat.getInstance( new Locale("es", "AR")); 
	
	/**
	 * 
	 * imprimir();
	 * imprimir("hola");
	 * imprimir("hola", " ", "Mundo");
	 * 
	 * String [] vector
	 */
	public static void imprimir(String... textos) {
		if ((textos == null) ||
			(textos.length == 0)) {
			System.out.println();
		}
		for(String texto: textos) {
			System.out.print(texto);
		}
		System.out.println();
	}
	
	//Formatea el valor double a String
	public static String formatear(double valor1) {		
		return decimalFormat.format(valor1);
	}

	public static void cambiarPrecision(int cantidad) {
		String patron = "#0.";
		for(int i = 0; i < cantidad; i++) {
			patron +="0";
		}
		decimalFormat = new DecimalFormat(patron);
	}
	
	public static String formatear(long valor1) {		
		return numberFormat.format(valor1);
	}
	
	public static String formatearEnLetras(long valor1) {		
        RuleBasedNumberFormat formatter =
                new RuleBasedNumberFormat(new Locale("es", "AR"),
                                          RuleBasedNumberFormat.SPELLOUT);

        return formatter.format(valor1);
	}
}
