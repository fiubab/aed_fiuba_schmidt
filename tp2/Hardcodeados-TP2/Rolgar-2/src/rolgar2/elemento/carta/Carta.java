package src.rolgar2.elemento.carta;

import src.rolgar2.elemento.ElementoRolgar2;
import src.rolgar2.elemento.Propiedades;
import src.rolgar2.entidad.Jugador;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase que representa una carta en el juego.
 * Cada carta tiene un tipo asociado que define su comportamiento.
 */
public class Carta extends ElementoRolgar2 {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------

    private final TipoCarta tipoDeCarta;

//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Constructor de Carta.
     * Crea una nueva carta con un nombre y un tipo específico.
     *
     * @param nombre nombre de la carta. No puede ser null.
     * @param tipoDeCarta tipo de carta que define su comportamiento. No puede ser null.
     * @pre {@code nombre != null}
     * @pre {@code tipoDeCarta != null}
     */
    public Carta(String nombre, TipoCarta tipoDeCarta) {
        super(nombre);
        this.tipoDeCarta = tipoDeCarta;
        this.propiedades.add(Propiedades.TRASPASABLE);
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Usa la carta con los parámetros especificados.
     *
     * @param usuario jugador que usa la carta. No puede ser null.
     * @param objetivo jugador objetivo de la carta.
     * @param tipo tipo de carta (para cartas comodín).
     * @return true si la carta se usó exitosamente, false en caso contrario.
     * @pre {@code usuario != null}
     */
    public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo) throws IOException {
        return tipoDeCarta.usar(usuario, objetivo, tipo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Carta carta = (Carta) o;
        return getTipoDeCarta() == carta.getTipoDeCarta();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTipoDeCarta());
    }

    @Override
    public String toString() {
        return "Carta de tipo " + tipoDeCarta.toString();
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public TipoCarta getTipoDeCarta() {
        return this.tipoDeCarta;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}