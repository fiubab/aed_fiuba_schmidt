package src.utils;
import java.util.Scanner;

/**
 * Clase que gestiona la entrada de datos desde el teclado.
 * Proporciona métodos para leer diferentes tipos de datos del usuario.
 */
public class Teclado {

    /** Scanner para leer entrada del usuario. */
    public static Scanner teclado;

    /**
     * Inicializa el scanner para leer entrada del teclado.
     */
    public static void inicializar() {
        teclado = new Scanner(System.in);
    }

    /**
     * Cierra el scanner y libera los recursos.
     */
    public static void finalizar() {
        teclado.close();
    }

    /**
     * Lee una línea completa ingresada por el usuario.
     * 
     * @return la línea ingresada
     */
    public static String leerLinea() {
        return teclado.nextLine();
    }

    /**
     * Lee un número entero ingresado por el usuario.
     * Sigue pidiendo hasta que se ingrese un entero válido.
     *
     * @return El número entero ingresado
     */
    public static int leerEntero() {
        while (true) {
            String temporal = leerLinea();

            if (temporal != null && !temporal.isEmpty()) {
                return Integer.parseInt(temporal.trim());
            }
        }
    }


    /**
     * Lee un número float ingresado por el usuario.
     * Sigue pidiendo hasta que se ingrese un float válido.
     *
     * @return El número float ingresado
     */
    public static float leerFloat() {
        while (true) {
            String temporal = leerLinea();

            if (temporal != null && !temporal.isEmpty()) {
                return Float.parseFloat(temporal.trim());
            }
        }
    }


    /**
     * Lee el primer carácter ingresado por el usuario.
     * Sigue pidiendo hasta que se ingrese al menos un carácter.
     *
     * @return El primer carácter válido ingresado
     */
    public static char leerCaracter() {
        while (true) {
            String temporal = leerLinea();

            if (!temporal.isEmpty()) {
                return temporal.charAt(0); // Devolver primer carácter válido
            }
        }
    }

}