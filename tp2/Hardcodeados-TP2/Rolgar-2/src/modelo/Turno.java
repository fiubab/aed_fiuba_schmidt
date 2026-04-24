package src.modelo;

import src.estructuras.listas.ListaCircularSimplementeEnlazada;
import src.utils.Validaciones;

import java.util.List;

/**
 * <p>Representa un sistema basico de turnos.</p>
 * <p>Permite guardar personajes e ir recorriendolos en orden de manera infinita.</p>
 * <p>
 *     Se pueden consultar la lista de personajes y el personaje al cual le corresponde
 *     el turno actual.
 * </p>
 */
public class Turno<T extends Personaje> {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final ListaCircularSimplementeEnlazada<T> personajes;
    private int indiceActual = 0;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un sistema de turnos con la lista de personajes especificada.
     * 
     * @param personajes lista de personajes que componen la lista de turnos, no puede ser null
     * @throws RuntimeException si personajes es null
     * @pre {@code personajes != null}
     * @pre La lista de personajes ya debe estar previamente ordenada
     */
    public Turno(List<T> personajes) {
        Validaciones.validarDistintoDeNull(personajes, "personajes");

        this.personajes = new ListaCircularSimplementeEnlazada<>();
        this.personajes.addAll(personajes);
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Avanza al siguiente personaje de la lista de turnos.
     * Si se alcanza el final, vuelve al inicio (comportamiento circular).
     * 
     * @post El índice actual se incrementa o se reinicia a 0
     */
    public void siguienteTurno() {
        if (this.personajes.isEmpty()) {
            return;
        }

        // incrementa el indice actual o vuelve a cero
        this.indiceActual = (this.indiceActual + 1) % this.personajes.size();
    }

    /**
     * Remueve un personaje de la lista de turnos.
     * 
     * @param personaje personaje a remover, no puede ser null
     * @throws RuntimeException si personaje es null
     * @pre {@code personaje != null}
     * @post El personaje se elimina de la lista de turnos
     */
    public void removerPersonaje(T personaje) {
        Validaciones.validarDistintoDeNull(personaje, "personaje");
        this.personajes.remove(personaje);
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Obtiene el personaje al cual le toca el turno actual.
     * 
     * @return el personaje cuyo turno es ahora, o null si la lista está vacía
     */
    public T getPersonajeTurnoActual() {
        if (this.personajes.isEmpty()) {
            return null;
        }

        return this.personajes.get(this.indiceActual);
    }


//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene una copia de la lista de personajes que componen la lista de turnos.
     *
     * @return copia de la lista de personajes
     */
    public List<T> getPersonajes() {
        ListaCircularSimplementeEnlazada<T> copia = new ListaCircularSimplementeEnlazada<>();

        copia.addAll(this.personajes);

        return copia;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

}
