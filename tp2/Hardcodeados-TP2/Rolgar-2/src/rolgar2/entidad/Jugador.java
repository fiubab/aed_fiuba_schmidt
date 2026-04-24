package src.rolgar2.entidad;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Inventario;
import src.rolgar2.AlianzaRolgar2;
import src.rolgar2.elemento.carta.Carta;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.utils.Validaciones;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.min;

/**
 * Representa un jugador en el juego.
 * 
 * <p>Un jugador es una {@link Entidad} controlada por un usuario.
 * Tiene características adicionales como inventario, efectos, visión y alianzas.</p>
 */
public class Jugador extends Entidad {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private int vision;
    private float regeneracion;
    private final Inventario<Carta> inventario;
    private final List<Efectos> efectos;
    private AlianzaRolgar2<Jugador> alianza;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea un nuevo jugador con las estadísticas iniciales.
     * 
     * @param nombre nombre del jugador, no puede ser null ni estar vacío
     * @param iniciativa iniciativa del jugador, debe ser mayor o igual a 0
     * @throws RuntimeException si el nombre no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     * @pre {@code iniciativa >= 0}
     */
    public Jugador(String nombre, int iniciativa){
        super(nombre, ConfiguracionesRolgar2.getVidaInicial(), ConfiguracionesRolgar2.getDanioBaseJugador(), iniciativa);

        final int capacidadInventario = ConfiguracionesRolgar2.getCantidadMaximaCartasInventario();
        this.inventario = new Inventario<>(capacidadInventario);

        this.efectos = new ListaSimplementeEnlazada<>();
        this.setRegeneracion(ConfiguracionesRolgar2.getProporcionVidaAgregada());
        this.setVision(ConfiguracionesRolgar2.getVision());
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

        Jugador jugador = (Jugador) o;

        return Float.compare(super.getVida(), jugador.getVida()) == 0 &&
                Float.compare(super.getVidaMaxima(), jugador.getVidaMaxima()) == 0 &&
                Float.compare(super.getFuerza(), jugador.getFuerza()) == 0 &&
                super.getIniciativa() == jugador.getIniciativa() &&
                Arrays.equals(super.getPosicion(), jugador.getPosicion()) &&
                this.getVision() == jugador.getVision() &&
                Float.compare(this.getRegeneracion(), jugador.getRegeneracion()) == 0 &&
                this.inventario.equals(jugador.inventario) &&
                Objects.equals(this.getEfectos(), jugador.getEfectos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), 
            super.getVida(),
            super.getVidaMaxima(),
            super.getFuerza(),
            super.getIniciativa(),
            Arrays.hashCode(super.getPosicion()),
            this.getVision(),
            this.getRegeneracion(), 
            this.inventario.hashCode(), 
            this.getEfectos()
        );
    }

    @Override
    public String toString() {
        return "Jugador llamado " + super.getNombre() +
               " con " + super.getVida() + "/" + super.getVidaMaxima() + " de vida" +
               ", " + super.getFuerza() + " de fuerza" +
               ", " + super.getIniciativa() + " de iniciativa" +
               ", " + this.getVision() + " de vision" +
               " y " + this.getRegeneracion() + " de regeneración";
    }

    /**
     * Guarda una carta en el inventario del jugador.
     *
     * @param carta carta a guardar, no puede ser null
     * @throws RuntimeException si el inventario está lleno o la carta es null
     * @pre {@code carta != null && !tieneInventarioLleno()}
     */
    public void guardarElemento(Carta carta){
        inventario.agregar(carta);
    }

    /**
     * Elimina una carta del inventario en la posición indicada.
     *
     * @param posicion posición de la carta (basada en 1), debe ser válida
     * @return la carta removida
     * @throws RuntimeException si la posición es inválida o está vacía
     * @pre {@code posicion >= 1 && posicion <= capacidadDelInventario()}
     */
    public Carta quitarElemento(int posicion){
        return inventario.remover(posicion);
    }

    /**
     * Agrega un efecto de estado al jugador.
     *
     * @param efecto efecto a agregar, no puede ser null
     * @throws RuntimeException si el efecto es null o ya lo tiene el jugador
     * @pre {@code efecto != null && !tieneEfecto(efecto)}
     */
    public void agregarEfecto(Efectos efecto){
        Validaciones.validarDistintoDeNull(efecto, "efecto");
        Validaciones.validarFalse(efectos.contains(efecto), "contiene efecto");
        this.efectos.add(efecto);
    }

    /**
     * Elimina un efecto de estado del jugador.
     *
     * @param efecto efecto a eliminar, no puede ser null
     * @throws RuntimeException si el efecto es null o no lo tiene el jugador
     * @pre {@code efecto != null && tieneEfecto(efecto)}
     */
    public void eliminarEfecto(Efectos efecto){
        Validaciones.validarDistintoDeNull(efecto, "efecto");
        Validaciones.validarTrue(efectos.contains(efecto), "contiene efecto");
        this.efectos.remove(efecto);
    }

    /**
     * Regenera vida del jugador según su tasa de regeneración.
     * La vida no puede exceder la vida máxima.
     * 
     * @post La vida se incrementa hasta un máximo de vidaMaxima
     */
    public void regenerar(){
        float nuevaVida = min(super.getVida() + this.getRegeneracion(), this.getVidaMaxima());
        this.setVida(nuevaVida);
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Verifica si el jugador tiene un efecto específico.
     * 
     * @param efecto efecto a verificar, no puede ser null
     * @return true si el jugador tiene el efecto, false en caso contrario
     * @throws RuntimeException si efecto es null
     * @pre {@code efecto != null}
     */
    public boolean tieneEfecto(Efectos efecto){
        Validaciones.validarDistintoDeNull(efecto, "efecto");
        return this.efectos.contains(efecto);
    }

    /**
     * Verifica si el inventario está vacío.
     * 
     * @return true si no hay cartas, false en caso contrario
     */
    public boolean tieneInventarioVacio(){
        return inventario.estaVacio();
    }

    /**
     * Verifica si el inventario está lleno.
     * 
     * @return true si está en capacidad máxima, false en caso contrario
     */
    public boolean tieneInventarioLleno(){
        return inventario.estaLleno();
    }

    /**
     * Obtiene la capacidad máxima del inventario.
     * 
     * @return capacidad del inventario
     */
    public int capacidadDelInventario(){
        return inventario.getCapacidad();
    }

    /**
     * Verifica si una posición del inventario está vacía.
     * 
     * @param posicion posición a verificar (basada en 1)
     * @return true si la posición está vacía, false en caso contrario
     */
    public boolean posicionInventarioInvalida(int posicion){
        return inventario.posicionVacia(posicion);
    }

    /**
     * Obtiene una copia del contenido del inventario.
     * 
     * @return arreglo con todas las cartas del inventario
     */
    public Carta[] contenidoDelInventario(){
        Carta[] contenido = new Carta[inventario.getCapacidad()];

        System.arraycopy(inventario.getContenido(),  0, contenido, 0, inventario.getCapacidad());

        return contenido;
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * Obtiene el rango de visión del jugador.
     * 
     * @return el rango de visión
     */
    public int getVision() {
        return this.vision;
    }

    /**
     * Obtiene la tasa de regeneración de vida del jugador.
     * 
     * @return el porcentaje de vida recuperado por turno
     */
    public float getRegeneracion() {
        return this.regeneracion;
    }

    /**
     * Obtiene una copia de la lista de efectos del jugador.
     * 
     * @return lista con los efectos activos
     */
    public List<Efectos> getEfectos() {
        List<Efectos> efectosCopia = new ListaSimplementeEnlazada<>();
        efectosCopia.addAll(efectos);
        return efectosCopia;
    }

    /**
     * Obtiene la alianza a la que pertenece el jugador.
     * 
     * @return la alianza del jugador, o null si no pertenece a ninguna
     */
    public AlianzaRolgar2<Jugador> getAlianza() {
        return this.alianza;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Establece el rango de visión del jugador.
     * 
     * @param vision rango de visión, debe ser mayor a 0
     * @throws RuntimeException si la visión es menor o igual a 0
     * @pre {@code vision > 0}
     */
    private void setVision(int vision) {
        Validaciones.validarMayorQueCero(vision, "vision");
        this.vision = vision;
    }

    /**
     * Establece la tasa de regeneración de vida del jugador.
     * 
     * @param regeneracion tasa de regeneración, debe ser mayor a 0
     * @throws RuntimeException si la regeneración es menor o igual a 0
     * @pre {@code regeneracion > 0}
     */
    private void setRegeneracion(float regeneracion) {
        Validaciones.validarMayorQueCero(regeneracion, "regeneracion");
        this.regeneracion = regeneracion;
    }

    /**
     * Establece la alianza a la que pertenece el jugador.
     * 
     * @param alianza la alianza, puede ser null para salir de la alianza
     */
    public void setAlianza(AlianzaRolgar2<Jugador> alianza) {
        AlianzaRolgar2<Jugador> alianzaJugador = this.getAlianza();

        if (alianzaJugador != null && alianza == null && alianzaJugador.estaEnAlianza(this)){
            alianzaJugador.quitarMiembro(this);
        }

        this.alianza = alianza;
    }

}