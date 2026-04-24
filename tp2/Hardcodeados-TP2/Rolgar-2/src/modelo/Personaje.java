package src.modelo;

import src.utils.Validaciones;

import java.util.Objects;

/**
 * Clase base que representa un personaje.
 * 
 * <p>Un personaje tiene un nombre que lo identifica.</p>
 */
public class Personaje {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private String nombre;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un nuevo personaje con el nombre especificado.
     * 
     * @param nombre nombre del personaje, no puede ser null ni estar vacío
     * @throws RuntimeException si el nombre no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     * @pre El nombre debe contener solo caracteres alfabéticos
     */
    public Personaje(String nombre){
        this.setNombre(nombre);
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Personaje llamado " + this.getNombre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getNombre());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Personaje other = (Personaje) obj;
        
        return Objects.equals(getNombre(), other.getNombre());
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------    
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el nombre del personaje.
     * 
     * @return nombre del personaje
     */
    public String getNombre(){
        return this.nombre;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Establece el nombre del personaje.
     * 
     * @param nombre nombre del personaje, no puede ser null ni estar vacío
     * @throws RuntimeException si el nombre no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     * @pre El nombre debe contener solo caracteres alfabéticos
     */
    private void setNombre(String nombre){
        Validaciones.validarDistintoDeNull(nombre, "nombre");
        Validaciones.validarCaracteresAlfabeticos(nombre, "nombre");
        Validaciones.validarFalse(nombre.isBlank(), "nombre");
        this.nombre = nombre;
    }
}