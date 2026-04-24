package src.rolgar2;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Turno;
import src.rolgar2.entidad.Efectos;
import src.rolgar2.entidad.Entidad;
import src.rolgar2.entidad.Jugador;

import java.util.List;

/**
 * Clase que gestiona el sistema de turnos del juego Rolgar2.
 *
 * <p>Mantiene una lista circular de entidades que determina el orden de juego.
 * Las entidades muertas son eliminadas automáticamente de la lista de turnos.
 * Ordena las entidades por iniciativa al crear la lista.</p>
 * 
 * @param <T> tipo de entidad que participa en los turnos, debe extender {@link Entidad}
 */
public class TurnoRolgar2<T extends Entidad> extends Turno<T> {
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea una lista circular de entidades que representa el orden de los turnos.
     * Las entidades se ordenan por iniciativa de mayor a menor.
     *
     * @param entidades lista de todas las entidades, no puede ser null
     * @throws RuntimeException si entidades es null
     * @pre {@code entidades != null}
     * @post Las entidades se ordenan por iniciativa
     */
    public TurnoRolgar2(List<T> entidades) {
        List<T> entidadesOrdenadasPorIniciativa = quickSortPorIniciativa(entidades);
        super(entidadesOrdenadasPorIniciativa);
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Avanza al siguiente turno válido, eliminando entidades muertas automáticamente.
     * Desactiva efectos del turno anterior y aplica regeneración al jugador actual.
     * 
     * @post Se avanza al siguiente turno
     * @post Las entidades muertas se eliminan de la lista
     * @post Se aplican efectos de regeneración si corresponde
     */
    @Override
    public void siguienteTurno() {
        super.siguienteTurno();

        avanzarTurnoHastaSiguienteEntidadViva();

        T entidadActual = this.getPersonajeTurnoActual();

        if (entidadActual == null) {
            return;
        }

        if (entidadActual instanceof Jugador jugador) {
            // Borro todos los efectos activos
            for (Efectos efecto : jugador.getEfectos()) {
                jugador.eliminarEfecto(efecto);
            }

            // Aplicar regeneración
            jugador.regenerar();
        }
    }

    private void avanzarTurnoHastaSiguienteEntidadViva(){
        T entidadActual;

        // Borro los jugadores muertos hasta encontrar uno vivo
        do {
            entidadActual = super.getPersonajeTurnoActual();

            if (!estaVivo(entidadActual)) {
                this.removerPersonaje(entidadActual);
                super.siguienteTurno();
            }
        } while (entidadActual != null && !estaVivo(entidadActual));
    }

    /**
     * Verifica si una entidad está viva.
     * 
     * @param entidad la entidad a verificar
     * @return true si la entidad está viva, false si está muerta o es null
     */
    private boolean estaVivo(T entidad) {
        return entidad != null && entidad.getVida() > 0;
    }

    //METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS -----------------------------------------------------------------------------------------
    //SETTERS -----------------------------------------------------------------------------------------
    //METODOS DE ORDENAMIENTO (QuickSort) -----------------------------------------------------------

    /**
     * Ordena la lista de entidades por iniciativa usando QuickSort.
     * Las entidades se ordenan de mayor a menor iniciativa.
     * 
     * @param <T> tipo de entidad
     * @param entidades lista de entidades a ordenar
     * @return lista ordenada por iniciativa
     */
    private static <T extends Entidad> List<T> quickSortPorIniciativa(List<T> entidades) {
        if (entidades == null || entidades.isEmpty()) {
            return null;
        }

        List<T> entidadesOrdenadasPorIniciativa = new ListaSimplementeEnlazada<>();
        entidadesOrdenadasPorIniciativa.addAll(entidades);
        quickSortPorIniciativa(entidadesOrdenadasPorIniciativa, 0, entidades.size() - 1);

        return entidadesOrdenadasPorIniciativa;
    }

    private static <T extends Entidad> void quickSortPorIniciativa(List<T> entidades, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(entidades, bajo, alto);
            quickSortPorIniciativa(entidades, bajo, pi - 1);
            quickSortPorIniciativa(entidades, pi + 1, alto);
        }
    }

    private static <T extends Entidad> int particion(List<T> entidades, int bajo, int alto) {
        float pivote = entidades.get(alto).getIniciativa();
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (entidades.get(j).getIniciativa() >= pivote) {
                i++;
                intercambiar(entidades, i, j);
            }
        }
        intercambiar(entidades, i + 1, alto);
        return i + 1;
    }

    private static <T extends Entidad> void intercambiar(List<T> entidades, int i, int j) {
        T temp = entidades.get(i);
        entidades.set(i, entidades.get(j));
        entidades.set(j, temp);
    }

    //METODOS HEREDADOS -----------------------------------------------------------------------------
}