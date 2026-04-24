package src.rolgar2.elemento;

import src.utils.Validaciones;

import java.util.Objects;

/**
 * Representa agua en el mapa.
 * 
 * <p>El agua es un elemento traspasable que causa daño según su nivel de profundidad.
 * Niveles más altos causan más daño.</p>
 *
 * @see ElementoRolgar2
 */
public class Agua extends ElementoRolgar2 {

    //INTERFACES ----------------------------------------------------------------------------------------------

    //ENUMERADOS ----------------------------------------------------------------------------------------------

    //CONSTANTES ----------------------------------------------------------------------------------------------

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    /**
     * El nivel de profundidad del agua.
     * Representa la intensidad del daño causado.
     * Un nivel mayor indica mayor profundidad y más daño.
     */
    private int nivel;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un elemento de tipo Agua con el nivel de profundidad especificado.
     *
     * @param nivel nivel de profundidad del agua, debe ser mayor o igual a 0
     * @throws RuntimeException si el nivel es negativo
     * @pre {@code nivel >= 0}
     */
    public Agua(int nivel) {
        super("agua");
        setNivel(nivel);
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
        
        Agua agua = (Agua) o;
        return this.getNivel() == agua.getNivel() && 
               Objects.equals(super.getPropiedades(), agua.getPropiedades());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getNivel(), super.getPropiedades());
    }

    @Override
    public String toString() {
        return "Agua de nivel " + this.getNivel();
    }

    //METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------

    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------

    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el nivel de profundidad del agua.
     * 
     * @return el nivel de profundidad
     */
    public int getNivel() {
        return this.nivel;
    }

    //SETTERS COMPLEJOS----------------------------------------------------------------------------------------

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Establece el nivel de profundidad del agua.
     * 
     * @param nivel nivel de profundidad, debe ser mayor o igual a 0
     * @throws RuntimeException si el nivel es negativo
     * @pre {@code nivel >= 0}
     */
    private void setNivel(int nivel) {
        Validaciones.validarMayorIgualCero(nivel, "nivel");
        this.nivel = nivel;
    }
}