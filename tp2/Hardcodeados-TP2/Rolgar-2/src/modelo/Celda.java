package src.modelo;

import src.utils.Validaciones;

import java.util.Objects;

/**
 * Representa una celda individual en el tablero del juego.
 * 
 * <p>Una celda puede contener un valor de tipo genérico T y mantiene referencias
 * a sus celdas vecinas en un espacio tridimensional. También almacena su posición
 * en el tablero.</p>
 * 
 * @param <T> el tipo de contenido que almacena la celda
 */
public class Celda<T> {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private T contenidoCelda;
    private Celda<T>[][][] vecinos;
    private final int[] posicion;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea una celda con contenido null.
     * 
     * @param posicion coordenadas de la celda
     * @post La celda se crea con contenido null
     */
    public Celda(int[] posicion) {
        this(null, posicion);
    }

    /**
     * Crea una celda con un contenido específico.
     * 
     * @param contenidoCelda contenido inicial de la celda, puede ser null
     * @param posicion coordenadas de la celda
     * @post La celda se crea con el contenido especificado
     */
    public Celda(T contenidoCelda, int[] posicion){
        this.setContenido(contenidoCelda);
        this.posicion = posicion;
    }


//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "Celda con contenido: " + this.contenidoCelda.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(contenidoCelda);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Celda<T> other = (Celda<T>) obj;

        return Objects.equals(contenidoCelda, other.contenidoCelda);
    }

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Verifica si la celda está libre (sin contenido).
     * 
     * @return true si el contenido es null, false en caso contrario
     */
    public boolean estaLibre() {
        return this.contenidoCelda == null;
    }

    /**
     * Verifica si la celda está ocupada (con contenido).
     * 
     * @return true si el contenido no es null, false en caso contrario
     */
    public boolean estaOcupado() {
        return !this.estaLibre();
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    /**
     * Obtiene los vecinos de la celda en un espacio 3D.
     * 
     * @return matriz 3x3x3 con las celdas vecinas
     */
    public Celda<T>[][][] getVecinos() {
        return this.vecinos;
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el contenido almacenado en la celda.
     * 
     * @return el contenido de la celda, puede ser null
     */
    public T getContenido() {
        return this.contenidoCelda;
    }

    /**
     * Obtiene las coordenadas de la celda en el tablero.
     * 
     * @return arreglo con las coordenadas [x, y, z]
     */
    public int[] getPosicion() {
        return this.posicion;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Modifica el contenido de la celda.
     * 
     * @param nuevoContenido el nuevo contenido de la celda, puede ser null
     * @post El contenido de la celda se actualiza
     */
    public void setContenido(T nuevoContenido) {
        this.contenidoCelda = nuevoContenido;
    }

    /**
     * Establece los vecinos de la celda.
     * 
     * @param vecinos matriz 3x3x3 con las celdas vecinas, no puede ser null
     * @throws RuntimeException si vecinos es null
     * @pre {@code vecinos != null}
     * @post Los vecinos de la celda se establecen
     */
    public void setVecinos(Celda<T>[][][] vecinos) {
        Validaciones.validarDistintoDeNull(vecinos, "vecinos");
        this.vecinos = vecinos;
    }
}
