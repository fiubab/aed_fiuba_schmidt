package src.rolgar2.entidad;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa un enemigo en el juego.
 * 
 * <p>Un enemigo es una {@link Entidad} controlada por el juego.
 * Tiene estadísticas básicas de vida, fuerza e iniciativa.</p>
 */
public class Enemigo extends Entidad {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un nuevo enemigo con las estadísticas especificadas.
     * 
     * @param vida vida inicial del enemigo, debe ser mayor o igual a 0
     * @param fuerza fuerza del enemigo, debe ser mayor a 0
     * @param iniciativa iniciativa del enemigo, debe ser mayor o igual a 0
     * @throws RuntimeException si algún parámetro no cumple las condiciones
     * @pre {@code vida >= 0 && fuerza > 0 && iniciativa >= 0}
     */
    public Enemigo(float vida, float fuerza, int iniciativa) {
        super("Enemigo", vida, fuerza, iniciativa);
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Enemigo llamado " + super.getNombre() +
               " con " + super.getVida() + "/" + super.getVidaMaxima() + " de vida" +
               ", " + super.getFuerza() + " de fuerza" +
               " y " + super.getIniciativa() + " de iniciativa";
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.getNombre(),
            super.getVida(),
            super.getVidaMaxima(),
            super.getFuerza(),
            super.getIniciativa(),
            Arrays.hashCode(super.getPosicion())
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        Enemigo other = (Enemigo) obj;
        
        return Objects.equals(super.getNombre(), other.getNombre()) &&
               Float.compare(super.getVida(), other.getVida()) == 0 &&
               Float.compare(super.getVidaMaxima(), other.getVidaMaxima()) == 0 &&
               Float.compare(super.getFuerza(), other.getFuerza()) == 0 &&
               super.getIniciativa() == other.getIniciativa() &&
               Arrays.equals(super.getPosicion(), other.getPosicion());
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------    
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}