package src.rolgar2;

import src.modelo.Alianza;
import src.rolgar2.entidad.Entidad;
import src.rolgar2.entidad.Jugador;
import src.utils.Validaciones;

/**
 * Clase que representa una alianza en el juego Rolgar2.
 * Extiende la clase {@link Alianza} y agrega un líder específico de tipo {@link Entidad}.
 * 
 * <p>Una alianza en Rolgar2 tiene un líder que puede gestionar los miembros.</p>
 * 
 * @param <T> tipo de entidad que forma parte de la alianza, debe extender {@link Entidad}
 */
public class AlianzaRolgar2 <T extends Entidad> extends Alianza {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------

    private T lider = null;

//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Constructor de AlianzaRolgar2.
     * Crea una nueva alianza con un nombre y un líder.
     *
     * @param nombre nombre de la alianza. No puede ser null.
     * @param lider líder de la alianza. No puede ser null.
     * @throws RuntimeException si el nombre o el líder son null.
     * @pre {@code nombre != null}
     * @pre {@code lider != null}
     */
    public AlianzaRolgar2(String nombre, T lider) {
        super(nombre);
        setLider(lider);
        agregarMiembro(lider);
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Agrega una nueva entidad a la alianza.
     * Valida que la entidad no sea null y que no pertenezca ya a la alianza.
     * Si la entidad es un Jugador, se establece su alianza.
     *
     * @param entidad entidad que se desea agregar, no puede ser null
     * @throws RuntimeException si la entidad es null o ya pertenece a la alianza
     * @pre {@code entidad != null}
     * @pre {@code !estaEnAlianza(entidad)}
     * @post La entidad se agrega a la alianza y se establece su alianza si es Jugador
     */
    @SuppressWarnings("unchecked")
    public void agregarMiembro(T entidad) {
        Validaciones.validarDistintoDeNull(entidad, "miembro");
        Validaciones.validarFalse(estaEnAlianza(entidad), "miembro");

        super.agregarMiembro(entidad);
        if (entidad instanceof Jugador jugador) {
            jugador.setAlianza((AlianzaRolgar2<Jugador>) this);
        }
    }

    /**
     * Quita una entidad de la alianza.
     * Valida que la alianza no esté vacía, que la entidad no sea null y que pertenezca a la alianza.
     * Si la entidad es el líder, se establece en null.
     *
     * @param entidad entidad a eliminar, no puede ser null
     * @throws RuntimeException si la alianza está vacía, la entidad es null o no pertenece a la alianza
     * @pre {@code !estaVacia()}
     * @pre {@code entidad != null}
     * @pre {@code estaEnAlianza(entidad)}
     * @post La entidad se elimina de la alianza y su alianza se establece en null si es Jugador
     */
    public void quitarMiembro(T entidad) {
        Validaciones.validarFalse(estaVacia(), "estaVacia");
        Validaciones.validarDistintoDeNull(entidad, "miembro");
        Validaciones.validarTrue(estaEnAlianza(entidad), "miembro");

        if (entidad.equals(this.lider)) {
            this.lider = null;
        }

        super.quitarMiembro(entidad);

        if (entidad instanceof Jugador jugador) {
            jugador.setAlianza(null);
        }

    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------    
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el líder de la alianza.
     *
     * @return la entidad que es líder de la alianza
     */
    public T getLider(){
        return this.lider;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Establece el líder de la alianza.
     * Solo se puede establecer una vez durante la creación.
     *
     * @param lider entidad que será el líder, no puede ser null
     * @throws RuntimeException si el líder ya ha sido establecido o si el parámetro es null
     * @pre {@code lider != null}
     * @pre {@code this.lider == null}
     */
    public void setLider(T lider) {
        Validaciones.validarDistintoDeNull(lider, "lider");
        Validaciones.validarTrue(this.lider == null, "lider");
        this.lider = lider;
    }

}