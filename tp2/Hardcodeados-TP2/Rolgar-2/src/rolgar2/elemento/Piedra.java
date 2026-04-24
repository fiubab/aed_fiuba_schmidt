package src.rolgar2.elemento;

import java.util.Objects;

/**
 * Representa una piedra en el mapa.
 * 
 * <p>La piedra es un elemento no traspasable que bloquea el movimiento.</p>
 *
 * @see ElementoRolgar2
 */
public class Piedra extends ElementoRolgar2 {

    //INTERFACES ----------------------------------------------------------------------------------------------

    //ENUMERADOS ----------------------------------------------------------------------------------------------

    //CONSTANTES ----------------------------------------------------------------------------------------------

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un elemento de tipo Piedra.
     * La piedra es no traspasable por defecto.
     */
    public Piedra() {
        super("piedra");
        this.propiedades.add(Propiedades.NO_TRASPASABLE);
    }

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
        
        Piedra piedra = (Piedra) o;
        return Objects.equals(this.getPropiedades(), piedra.getPropiedades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), super.getPropiedades());
    }

    @Override
    public String toString() {
        return "Piedra";
    }

    //METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------

    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------

    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    //SETTERS COMPLEJOS----------------------------------------------------------------------------------------

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}