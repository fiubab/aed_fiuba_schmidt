package src.modelo;

import src.utils.Validaciones;
import java.util.Objects;
import java.util.Random;

/**
 * La clase Dado representa un dado de juego con un número específico de caras.
 *
 * <p>Proporciona funcionalidad para simular lanzamientos de dados, generando
 * números aleatorios dentro del rango correspondiente al número de caras.</p>
 *
 * <p>El dado puede ser configurado con diferentes números de caras y permite
 * lanzamientos individuales o múltiples, con validación de parámetros.</p>
 *
 *
 * @see Validaciones
 */
public class Dado {

    //INTERFACES ----------------------------------------------------------------------------------------------

    //ENUMERADOS ----------------------------------------------------------------------------------------------

    //CONSTANTES ----------------------------------------------------------------------------------------------
    /** Número de caras por defecto para un dado estándar. */
    private static final int CARAS_DADOS_POR_DEFECTO = 6;
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    /**
     * Generador de números aleatorios compartido por todas las instancias de Dado.
     */
    private static final Random random = new Random();

    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    /**
     * Número de caras del dado. Determina el rango de valores posibles al lanzarlo.
     * Debe ser siempre un valor mayor que cero.
     */
    private int caras;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Construye un nuevo dado con el número predeterminado de caras ({@value CARAS_DADOS_POR_DEFECTO}).
     *
     * @post Se crea un dado con {@value CARAS_DADOS_POR_DEFECTO} caras
     * @post El dado está listo para ser lanzado
     */
    public Dado() {
        this(CARAS_DADOS_POR_DEFECTO);
    }

    /**
     * Construye un nuevo dado con el número específico de caras.
     *
     * @param caras número de caras del dado, debe ser mayor que 0
     * @throws RuntimeException si el número de caras es menor o igual a 0
     * @pre {@code caras > 0}
     * @post Se crea un dado con el número especificado de caras
     * @post El dado está listo para ser lanzado
     */
    public Dado(int caras) {
        setCaras(caras);
    }

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------

    //METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Dado dado)) {
            return false;
        }

        return this.getCaras() == dado.getCaras();
    }

    @Override
    public int hashCode() {
        return Objects.hash(caras);
    }

    @Override
    public String toString() {
        return "Dado de " + this.getCaras() + " caras";
    }

    //METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------

    //METODOS DE CLASE ----------------------------------------------------------------------------------------

    //METODOS GENERALES ---------------------------------------------------------------------------------------

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Lanza el dado una vez y devuelve el resultado.
     * El resultado es un número aleatorio en el rango [1, número de caras].
     *
     * @return un valor entero aleatorio entre 1 y el número de caras (inclusive)
     * @post El resultado está garantizado a estar en el rango válido
     * @see Random#nextInt(int)
     */
    public int tirar() {
        return random.nextInt(this.getCaras()) + 1;
    }

    //METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------

    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------

    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el número de caras de este dado.
     *
     * @return el número de caras del dado
     * @post El valor retornado es siempre positivo
     */
    public int getCaras() {
        return this.caras;
    }

    //SETTERS COMPLEJOS----------------------------------------------------------------------------------------

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Establece el número de caras para este dado.
     * Este método es privado para controlar la modificación del estado interno
     * y garantizar la consistencia de los datos.
     *
     * @param caras nuevo número de caras, debe ser mayor que 0
     * @throws RuntimeException si el número de caras es menor o igual a 0
     * @pre {@code caras > 0}
     * @post El número de caras del dado es actualizado al valor especificado
     * @post Todos los lanzamientos futuros usarán el nuevo número de caras
     */
    private void setCaras(int caras) {
        Validaciones.validarMayorQueCero(caras, "caras");
        this.caras = caras;
    }
}
