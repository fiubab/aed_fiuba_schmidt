package src.utils;

import java.time.Duration;
import java.time.Instant;

/**
 * Clase que gestiona el temporizador del juego.
 * 
 * <p>Permite iniciar, pausar y reiniciar el temporizador, así como obtener
 * el tiempo transcurrido en formato legible.</p>
 */
public class Temporizador {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private static Instant tiempoSegmentoInicial = null;
    private static Duration tiempoTranscurrido = Duration.ZERO;
    private static boolean enMarcha = false;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Inicia (reanuda en caso de estar pausado) el temporizador.
     * 
     * @throws RuntimeException si el temporizador ya está en marcha
     * @pre {@code !estaEnMarcha()}
     * @post El temporizador comienza a contar
     */
    public static void iniciar() {
        Validaciones.validarFalse(estaEnMarcha(), "en marcha");
        tiempoSegmentoInicial = Instant.now();
        enMarcha = true;
    }

    /**
     * Pausa el temporizador y suma el tiempo transcurrido.
     * 
     * @throws RuntimeException si el temporizador no está en marcha
     * @pre {@code estaEnMarcha()}
     * @post El temporizador se detiene y el tiempo se acumula
     */
    public static void pausar() {
        Validaciones.validarTrue(estaEnMarcha(), "en marcha");
        Validaciones.validarDistintoDeNull(tiempoSegmentoInicial, "tiempo inicial");

        Duration duracionSegmento = Duration.between(tiempoSegmentoInicial, Instant.now());
        tiempoTranscurrido = tiempoTranscurrido.plus(duracionSegmento);
        enMarcha = false;
    }

    /**
     * Reinicia el temporizador y el tiempo guardado.
     * 
     * @post El temporizador se reinicia a cero
     * @post El estado se establece como no en marcha
     */
    public static void reiniciar() {
        tiempoTranscurrido = Duration.ZERO;
        tiempoSegmentoInicial = null;
        enMarcha = false;
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Verifica si el temporizador está en marcha.
     * 
     * @return true si el temporizador está activo, false en caso contrario
     */
    public static boolean estaEnMarcha() {
        return enMarcha;
    }

    /**
     * Obtiene el tiempo transcurrido sin formatear.
     * 
     * @return duración total transcurrida
     */
    public static Duration getTiempoTranscurrido() {
        if (estaEnMarcha() && tiempoSegmentoInicial != null) {
            Duration duracionActual = Duration.between(tiempoSegmentoInicial, Instant.now());
            return tiempoTranscurrido.plus(duracionActual);
        }
        return tiempoTranscurrido;
    }

    /**
     * Obtiene el tiempo transcurrido formateado.
     * 
     * @return tiempo en formato HH:MM:SS
     */
    public static String getTiempoFormateado() {
        Duration total = getTiempoTranscurrido();
        long horas = total.toHours();
        long minutos = total.toMinutesPart();
        long segundos = total.toSecondsPart();
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}