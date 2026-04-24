package src.app;

import src.rolgar2.Rolgar2;
import src.utils.Teclado;

import java.io.IOException;

/**
 * Clase principal que contiene el punto de entrada del programa Rolgar2.
 * 
 * <p>Inicializa el sistema, carga las configuraciones y ejecuta el juego.</p>
 */
public class Main {
    /**
     * Punto de entrada del programa.
     * Inicializa el teclado, crea un archivo de configuración por defecto si no existe,
     * lee las configuraciones del archivo JSON y crea una instancia de {@link Rolgar2}.
     * Luego, inicia el juego y finaliza el teclado una vez terminado.
     *
     * @param args argumentos de la línea de comandos
     * @throws IOException sí ocurre un error al leer o escribir el archivo de configuración
     */
    public static void main(String[] args) throws IOException {
        Teclado.inicializar();

        Rolgar2.inicializar();
        Rolgar2.jugar();

        Teclado.finalizar();
    }
}
