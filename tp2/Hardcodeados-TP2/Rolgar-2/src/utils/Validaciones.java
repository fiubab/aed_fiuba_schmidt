package src.utils;

/**
 * Clase de utilidades para validación de parámetros.
 * Proporciona métodos estáticos para validar condiciones comunes
 * y lanzar excepciones con mensajes descriptivos.
 */
public class Validaciones {
    //INTERFACES  ---------------------------------------------------
    //ENUMERADOS ----------------------------------------------------
    //CONSTANTES ----------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------
    //ATRIBUTOS -----------------------------------------------------
    //ATRIBUTOS TRANSITORIOS ----------------------------------------
    //CONSTRUCTORES -------------------------------------------------
    //METODOS ABSTRACTOS --------------------------------------------
    //METODOS HEREDADOS (CLASE) -------------------------------------
    //METODOS HEREDADOS (INTERFACE) ---------------------------------
    //METODOS DE CLASE ----------------------------------------------

    /**
     * Valida que el valor sea mayor a 0.
     * 
     * @param valor dato a validar
     * @param cadena nombre del parámetro para el mensaje de error
     * @throws RuntimeException si el valor es menor o igual a 0
     * @pre {@code valor > 0}
     */
    public static void validarMayorQueCero(double valor, String cadena){
        if (valor <= 0){
            throw new RuntimeException("El valor " + cadena + " debe ser mayor a 0");
        }
    }

    /**
     * Valida que el valor sea mayor o igual a 0.
     * 
     * @param valor dato a validar
     * @param cadena nombre del parámetro para el mensaje de error
     * @throws RuntimeException si el valor es menor a 0
     * @pre {@code valor >= 0}
     */
    public static void validarMayorIgualCero(double valor, String cadena){
        if (valor < 0){
            throw new RuntimeException("El valor " + cadena + " debe ser mayor o igual a 0");
        }
    }

    /**
     * Valida que el valor sea mayor o igual al mínimo especificado.
     * 
     * @param valor dato a validar
     * @param minimo valor mínimo permitido
     * @param cadena nombre del parámetro para el mensaje de error
     * @param cadenaMinimo nombre del mínimo para el mensaje de error
     * @throws RuntimeException si el valor es menor al mínimo
     * @pre {@code valor >= minimo}
     */
    public static void validarMayorIgual(double valor, double minimo, String cadena, String cadenaMinimo){
        if (valor < minimo){
            throw new RuntimeException("El valor " + cadena + " debe ser mayor o igual que " + cadenaMinimo);
        }
    }

    /**
     * Valida que el valor no sea null.
     * 
     * @param valor dato a validar
     * @param cadena nombre del parámetro para el mensaje de error
     * @throws RuntimeException si el valor es null
     * @pre {@code valor != null}
     */
    public static void validarDistintoDeNull(Object valor, String cadena){
        if (valor == null){
            throw new RuntimeException("El valor " + cadena + " no puede ser null");
        }
    }

    /**
     * Valida que el valor esté dentro del rango especificado (inclusive).
     * 
     * @param <T> tipo numérico del valor
     * @param valor el valor a validar
     * @param desde cantidad mínima (inclusive)
     * @param hasta cantidad máxima (inclusive)
     * @param nombreDelAtributo nombre del atributo para el mensaje de error
     * @throws RuntimeException si el valor está fuera del rango
     * @pre {@code valor >= desde && valor <= hasta}
     */
    public static <T extends Number> void validarRango(T valor, double desde, double hasta, String nombreDelAtributo) {
        double v = valor.doubleValue();
        if (v < desde || v > hasta) {
            throw new RuntimeException("El " + nombreDelAtributo + " debe estar entre " + desde + " y " + hasta + ". Se ingresó " + v);
        }
    }


    /**
     * Valida que el texto contenga solo letras y espacios.
     * Acepta caracteres acentuados (á, é, í, ó, ú, ñ).
     * 
     * @param valor cadena a validar
     * @param nombreDelAtributo nombre del atributo para el mensaje de error
     * @throws RuntimeException si la cadena contiene caracteres no alfabéticos
     * @pre {@code valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") }
     */
    public static void validarCaracteresAlfabeticos(String valor, String nombreDelAtributo) {
        // 3. Verificar que contenga solo letras y espacios
        // [a-zA-Z ]+ significa: cualquier carácter de la 'a' a la 'z',
        // mayúscula o minúscula, más el espacio. El '+' indica "uno o más".
        if (!valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new RuntimeException("El " + nombreDelAtributo + " debe tener solo letras y espacios. Se ingreso " + valor);
        }
    }


    /**
     * Valida que el booleano sea false.
     * 
     * @param valor booleano a validar
     * @param nombreDelAtributo nombre del atributo para el mensaje de error
     * @throws RuntimeException si el valor es true
     * @pre {@code valor == false}
     */
    public static void validarFalse(boolean valor, String nombreDelAtributo) {
        if (valor) {
            throw new RuntimeException("El " + nombreDelAtributo + " debe ser false");
        }
    }


    /**
     * Valida que el booleano sea true.
     * 
     * @param valor booleano a validar
     * @param nombreDelAtributo nombre del atributo para el mensaje de error
     * @throws RuntimeException si el valor es false
     * @pre {@code valor == true}
     */
    public static void validarTrue(boolean valor, String nombreDelAtributo) {
        if (!valor) {
            throw new RuntimeException("El " + nombreDelAtributo + " debe ser true");
        }
    }


    //METODOS GENERALES ---------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------
    //METODOS DE CONSULTA DE ESTADO ---------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------
    //SETTERS COMPLEJOS ---------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------
}