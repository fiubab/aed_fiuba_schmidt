package src.modelo;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.utils.Validaciones;

import java.util.List;
import java.util.Objects;

/**
 * Representa una alianza de personajes.
 * <p>
 * Una {@code Alianza} agrupa varios {@link Personaje}s bajo un mismo nombre.
 * El nombre de la alianza no puede estar vacio.
 * </p>
 *
 * <p>Las operaciones principales incluyen agregar o quitar miembros, consultar si está vacía, llena,
 * si un jugador pertenece a una alianza y modificar el nombre de la alianza.</p>
 */
public class Alianza {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    /** Nombre identificador de la alianza. */
    private String nombre;

    /** Lista de personajes que forman parte de la alianza. */
    private final List<Personaje> miembros;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea una nueva alianza con el nombre especificado.
     *
     * @param nombre nombre de la alianza, no puede ser null ni estar vacío
     * @throws RuntimeException si el nombre no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     * @pre El nombre debe contener solo caracteres alfabéticos válidos
     */
    public Alianza(String nombre) {
        this.setNombre(nombre);
        this.miembros = new ListaSimplementeEnlazada<>();
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(this.getNombre(), this.getMiembros());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Alianza other)) {
            return false;
        }

        return Objects.equals(this.getNombre(), other.getNombre()) &&
               Objects.equals(this.getMiembros(), other.getMiembros());
    }

    @Override
    public String toString() {
        return "Alianza '" + this.getNombre() +
               " con " + this.getCantidadMiembros() + " miembro" + 
               (this.getCantidadMiembros() != 1 ? "s" : "");
    }

    /**
     * Agrega un nuevo personaje a la alianza.
     *
     * @param miembro personaje que se desea agregar, no puede ser null
     * @throws RuntimeException si el miembro es null o ya pertenece a la alianza
     * @pre {@code miembro != null}
     * @pre {@code !estaEnAlianza(miembro)}
     * @post El miembro se agrega a la alianza
     */
    public void agregarMiembro(Personaje miembro) {
        Validaciones.validarDistintoDeNull(miembro, "miembro");
        Validaciones.validarFalse(estaEnAlianza(miembro), "miembro");

        this.miembros.add(miembro);
    }

    /**
     * Quita un personaje de la alianza.
     *
     * @param miembro personaje a eliminar, no puede ser null
     * @throws RuntimeException si la alianza está vacía
     * @throws RuntimeException si el miembro es null o no pertenece a la alianza
     * @pre {@code !estaVacia()}
     * @pre {@code miembro != null}
     * @pre {@code estaEnAlianza(miembro)}
     * @post El miembro se elimina de la alianza
     */
    public void quitarMiembro(Personaje miembro) {
        Validaciones.validarFalse(estaVacia(), "estaVacia");
        Validaciones.validarDistintoDeNull(miembro, "miembro");
        Validaciones.validarTrue(estaEnAlianza(miembro), "miembro");

        this.miembros.remove(miembro);
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Verifica si la alianza no tiene miembros.
     *
     * @return true si la cantidad de miembros es 0, false en caso contrario
     */
    public boolean estaVacia() {
        return this.getCantidadMiembros() == 0;
    }

    /**
     * Verifica si un personaje forma parte de la alianza.
     *
     * @param miembro personaje a verificar, no puede ser null
     * @return true si el personaje está en la alianza, false en caso contrario
     * @throws RuntimeException si miembro es null
     * @pre {@code miembro != null}
     */
    public boolean estaEnAlianza(Personaje miembro) {
        Validaciones.validarDistintoDeNull(miembro, "miembro");
        return this.getMiembros().contains(miembro);
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    /**
     * Obtiene una copia de la lista de miembros de la alianza.
     *
     * @return lista con todos los personajes que forman parte de la alianza
     */
    public List<Personaje> getMiembros() {
        List<Personaje> miembros = new ListaSimplementeEnlazada<>();
        miembros.addAll(this.miembros);

        return miembros;
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el nombre actual de la alianza.
     *
     * @return nombre de la alianza
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene la cantidad actual de miembros dentro de la alianza.
     *
     * @return número entero con la cantidad de miembros
     */
    public int getCantidadMiembros() {
        return this.miembros.size();
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Modifica el nombre de la alianza.
     *
     * @param nombre nuevo nombre de la alianza, no puede ser null ni estar vacío
     * @throws RuntimeException si el nombre no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     * @pre El nombre debe contener únicamente caracteres alfabéticos válidos
     */
    public void setNombre(String nombre) {
        Validaciones.validarFalse(nombre.isBlank(), "nombre");
        Validaciones.validarDistintoDeNull(nombre, "nombre");
        Validaciones.validarCaracteresAlfabeticos(nombre, "nombre");
        this.nombre = nombre;
    }
}
