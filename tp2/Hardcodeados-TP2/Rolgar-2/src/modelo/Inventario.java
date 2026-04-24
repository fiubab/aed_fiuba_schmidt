package src.modelo;

import src.utils.Validaciones;

import java.util.Arrays;

/**
 * Representa un inventario genérico con capacidad limitada.
 * 
 * <p>El inventario almacena elementos de tipo T en un arreglo de tamaño fijo.
 * Permite agregar, obtener, remover y consultar elementos, así como verificar
 * si está lleno o vacío.</p>
 * 
 * @param <T> el tipo de elementos que almacena el inventario
 */
public class Inventario<T> {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final T[] datos;
    private int cantidad = 0;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un nuevo inventario con la capacidad máxima indicada.
     * 
     * @param capacidad debe ser mayor a 0
     * @throws RuntimeException si la capacidad es menor o igual a 0
     * @pre {@code capacidad > 0}
     */
    @SuppressWarnings("unchecked")
    public Inventario(int capacidad){
        Validaciones.validarMayorQueCero(capacidad, "capacidad");
        this.datos = (T[]) new Object[capacidad];
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Agrega al inventario el dato indicado en la primera posición libre.
     * 
     * @param dato debe ser distinto de null
     * @throws RuntimeException si el inventario está lleno
     * @throws RuntimeException si el dato es null
     * @pre {@code dato != null}
     * @pre {@code !estaLleno()}
     */
    public void agregar(T dato){
        Validaciones.validarFalse(this.estaLleno(), "inventario lleno");
        Validaciones.validarDistintoDeNull(dato, "dato");

        for(int i = 0; i < this.getCapacidad(); i++){
            if (this.datos[i] == null) {
                this.datos[i] = dato;
                this.cantidad++;
                return;
            }
        }
    }

    /**
     * Agrega un dato en una posición específica del inventario.
     * 
     * @param posicion valor entre 1 y la capacidad máxima
     * @param dato el dato a guardar
     * @throws RuntimeException si la posición está fuera de rango
     * @pre {@code posicion >= 1 && posicion <= getCapacidad()}
     * @post El dato se guarda en la posición dada
     */
    public void agregar(int posicion, T dato){
        Validaciones.validarRango(posicion, 1, this.getCapacidad(), "posicion");

        this.datos[posicion - 1] = dato;
    }
    
    /**
     * Devuelve el objeto en la posición indicada.
     * 
     * @param posicion posición basada en 1
     * @return el elemento almacenado en la posición indicada
     * @throws RuntimeException si la posición está fuera de rango
     * @pre {@code posicion >= 1 && posicion <= getCapacidad()}
     */
    public T obtener(int posicion){
        Validaciones.validarRango(posicion, 1, this.getCapacidad(), "posicion");
        return this.datos[posicion - 1];
    }

    /**
     * Elimina el objeto en la posición indicada.
     * 
     * @param posicion posición basada en 1
     * @return el elemento removido
     * @throws RuntimeException si la posición está fuera de rango
     * @throws RuntimeException si el inventario está vacío
     * @throws RuntimeException si la posición contiene null
     * @pre {@code posicion >= 1 && posicion <= getCapacidad()}
     * @pre {@code !estaVacio()}
     */
    public T remover(int posicion){
        Validaciones.validarFalse(this.estaVacio(), "inventario vacio");
        Validaciones.validarRango(posicion, 1, this.getCapacidad(), "posicion");
        Validaciones.validarDistintoDeNull(this.datos[posicion - 1], "dato");

        T dato = this.datos[posicion - 1];
        this.datos[posicion - 1] = null;
        this.cantidad--;
        return dato;
    }
    
    /**
     * Revisa si el inventario contiene el dato buscado.
     * 
     * @param dato el dato a buscar, debe ser distinto de null
     * @return true si el inventario contiene el dato, false en caso contrario
     * @throws RuntimeException si el dato es null
     * @pre {@code dato != null}
     */
    public boolean contiene(T dato){
        Validaciones.validarDistintoDeNull(dato, "dato");
        for(T objeto: datos){
            if (objeto != null && objeto.equals(dato)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Revisa si el dato en la posición indicada es {@code null}.
     * 
     * @param posicion que contiene el dato a validar
     * @return devuelve true si está vacía, false en caso contrario
     * @throws RuntimeException si la posición está fuera de rango
     * @pre {@code posicion >= 1 && posicion <= getCapacidad()}
     */
    public boolean posicionVacia(int posicion){
        return this.obtener(posicion) == null;
    }

    @Override
    public String toString() {
        return "Inventario de capacidad " + getCapacidad() + " con " + getCantidad() + " elementos";
    }

    @Override
    public int hashCode() {
        return 31 * Arrays.hashCode(datos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Inventario<?> other = (Inventario<?>) obj;

        return Arrays.equals(this.datos, other.datos) &&
                this.getCantidad() == other.getCantidad() &&
                this.getCapacidad() == other.getCapacidad();
    }


//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Verifica si el inventario está lleno.
     * 
     * @return true si la cantidad de elementos es igual a la capacidad, false en caso contrario
     */
    public boolean estaLleno() {
        return getCantidad() == getCapacidad();
    }

    /**
     * Verifica si el inventario está vacío.
     * 
     * @return true si la cantidad de elementos es 0, false en caso contrario
     */
    public boolean estaVacio() {
        return getCantidad() == 0;
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene la capacidad máxima del inventario.
     * 
     * @return la capacidad del inventario
     */
    public int getCapacidad() {
        return this.datos.length;
    }

    /**
     * Obtiene la cantidad actual de objetos en el inventario.
     * 
     * @return la cantidad de elementos almacenados
     */
    public int getCantidad() {
        return this.cantidad;
    }

    /**
     * Obtiene una copia del contenido del inventario.
     * 
     * @return un arreglo con una copia de todos los elementos
     */
    @SuppressWarnings("unchecked")
    public T[] getContenido(){
        T[] vectorCopia = (T[]) new Object[this.getCapacidad()];
        System.arraycopy(this.datos, 0, vectorCopia, 0, this.getCapacidad());
        return vectorCopia;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}