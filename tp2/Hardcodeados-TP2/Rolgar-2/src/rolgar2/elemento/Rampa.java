package src.rolgar2.elemento;

import src.rolgar2.Direcciones;
import src.utils.Validaciones;

import java.util.Objects;

/**
 * Representa una rampa en el mapa.
 * 
 * <p>La rampa es un elemento traspasable que permite cambiar de nivel.
 * Tiene una dirección y permite subir o bajar según su configuración.</p>
 *
 * @see ElementoRolgar2
 */
public class Rampa extends ElementoRolgar2 {

    //INTERFACES ----------------------------------------------------------------------------------------------

    //ENUMERADOS ----------------------------------------------------------------------------------------------

    //CONSTANTES ----------------------------------------------------------------------------------------------

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private Direcciones direccion;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un elemento de tipo Rampa con la dirección y acción especificadas.
     *
     * @param direccion dirección de la rampa, no puede ser null
     * @param accion acción de la rampa (PERMITE_SUBIR o PERMITE_BAJAR), no puede ser null
     * @throws RuntimeException si dirección o acción son null
     * @pre {@code direccion != null && accion != null}
     */
    public Rampa(Direcciones direccion, Propiedades accion) {
        super("rampa");
        setDireccion(direccion);
        Validaciones.validarDistintoDeNull(accion, "accion");
        this.propiedades.add(accion);
        this.propiedades.add(Propiedades.TRASPASABLE);
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
        
        Rampa rampa = (Rampa) o;
        return this.getDireccion() == rampa.getDireccion() && 
               Objects.equals(super.getPropiedades(), rampa.getPropiedades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getDireccion(), super.getPropiedades());
    }

    @Override
    public String toString() {
        return "Rampa con dirección " + this.getDireccion();
    }

    //METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------

    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------

    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene la dirección de la rampa.
     * 
     * @return la dirección de la rampa
     */
    public Direcciones getDireccion() {
        return this.direccion;
    }

    /**
     * Verifica si la rampa permite subir de nivel.
     * 
     * @return true si permite subir, false si permite bajar
     */
    public boolean permiteSubir(){
        return super.getPropiedades().contains(Propiedades.PERMITE_SUBIR);
    }

    //SETTERS COMPLEJOS----------------------------------------------------------------------------------------

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Establece la dirección de la rampa.
     * 
     * @param direccion dirección de la rampa, no puede ser null
     * @throws RuntimeException si dirección es null
     * @pre {@code direccion != null}
     */
    private void setDireccion(Direcciones direccion) {
        Validaciones.validarDistintoDeNull(direccion, "direccion");
        this.direccion = direccion;
    }
}
