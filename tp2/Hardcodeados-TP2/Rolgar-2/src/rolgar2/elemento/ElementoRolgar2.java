package src.rolgar2.elemento;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Elemento;

import java.util.List;

/**
 * Clase base para los elementos específicos del juego Rolgar2.
 * Extiende {@link Elemento} y agrega propiedades específicas del juego.
 * 
 * <p>Los elementos pueden tener propiedades como:</p>
 * <ul>
 *   <li>Traspasabilidad</li>
 *   <li>Capacidad de cambiar de nivel (rampas)</li>
 * </ul>
 */
public class ElementoRolgar2 extends Elemento {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    protected List<Propiedades> propiedades = new ListaSimplementeEnlazada<>();

    /**
     * Crea un nuevo elemento con el nombre especificado.
     *
     * @param nombre nombre del elemento, no puede ser null ni estar vacío
     * @throws RuntimeException si el nombre no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     */
    public ElementoRolgar2(String nombre) {
        super(nombre);
    }

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        
        if (!super.equals(o)) {
            return false;
        }
        
        ElementoRolgar2 that = (ElementoRolgar2) o;
        return java.util.Objects.equals(this.getPropiedades(), that.getPropiedades());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), this.getPropiedades());
    }

    @Override
    public String toString() {
        return "Elemento de Rolgar2 llamado " + this.getNombre();
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------    
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    /**
     * Obtiene una copia de la lista de propiedades del elemento.
     * 
     * @return lista con las propiedades del elemento
     */
    public List<Propiedades> getPropiedades() {
        List<Propiedades> lista = new ListaSimplementeEnlazada<>();
        lista.addAll(this.propiedades);
        return lista;
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
