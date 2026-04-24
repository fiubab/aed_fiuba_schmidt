package src.utils;

/**
 * Clase de utilidades del sistema.
 * Proporciona métodos auxiliares para operaciones del sistema.
 */
public class SistemaUtiles {

    /**
     * Detiene la ejecución del programa por el tiempo especificado.
     * 
     * @param milisegundos tiempo de espera en milisegundos, debe ser mayor o igual a 0
     * @throws RuntimeException si milisegundos es negativo
     * @throws RuntimeException si ocurre una interrupción durante el sleep
     * @pre {@code milisegundos >= 0}
     */
    public static void esperar(long milisegundos) {
        Validaciones.validarMayorIgualCero(milisegundos, "milisegundos");
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Genera la ruta absoluta de un archivo del proyecto.
     * 
     * @param rutaRelativa ruta relativa que comienza con "src"
     * @return ruta absoluta completa del archivo
     * @throws RuntimeException si ocurre un error al obtener la ruta
     */
    public static String generarRutaAbsoluta(String rutaRelativa) {
        try {
            String rutaBase = SistemaUtiles.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (rutaBase.endsWith("bin/")) {
                rutaBase = rutaBase.substring(0, rutaBase.length() - 4);
            }
            return rutaBase + rutaRelativa;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
