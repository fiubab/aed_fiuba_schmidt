package src.rolgar2;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.*;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.elemento.Agua;
import src.rolgar2.elemento.Rampa;
import src.rolgar2.elemento.carta.Carta;
import src.rolgar2.elemento.carta.TipoCarta;
import src.rolgar2.entidad.Entidad;
import src.rolgar2.entidad.Jugador;
import src.utils.Temporizador;
import src.utils.Validaciones;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.lang.Math.max;


/**
 * Clase que administra todas las acciones que pueden realizar los jugadores durante el juego.
 * Proporciona métodos para manejar movimiento, combate, alianzas, cartas e interacciones.
 */
public class AdministradorAcciones {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------

    /**
     * Permite a una entidad mover a una posición adyacente válida.
     * Solicita al usuario que ingrese una dirección y valida que la nueva posición
     * sea accesible (no ocupada por otra entidad y sin elementos no traspasables).
     *
     * @param entidad entidad que se mueve. No puede ser null.
     * @throws RuntimeException si la posición actual es null o no contiene una entidad.
     * @pre {@code entidad != null}
     */
    public static void mover(Entidad entidad) {
        Validaciones.validarDistintoDeNull(entidad, "entidad");
        Direcciones direccion;
        int[] nuevaPosicion;

        do {
            direccion = Entrada.ingresarDireccion();
            nuevaPosicion = Rolgar2.getMapa().getVecino(entidad.getPosicion(), direccion).getPosicion();
        } while ((Rolgar2.getMapa().getCelda(nuevaPosicion) == null) ||
                (!Rolgar2.getMapa().esCeldaTraspasable(Rolgar2.getMapa().getCelda(nuevaPosicion))));

        mover(entidad, nuevaPosicion);
    }

    /**
     * Mueve una entidad a una posición específica.
     * Valida que la posición sea válida y traspasable antes de mover.
     *
     * @param entidad entidad a mover. No puede ser null.
     * @param nuevaPosicion nueva posición. No puede ser null.
     * @throws RuntimeException si la entidad o la posición son null.
     * @throws RuntimeException si la posición no es traspasable.
     * @pre {@code entidad != null}
     * @pre {@code nuevaPosicion != null}
     * @pre {@code Mapa.esCeldaTraspasable(nuevaPosicion) == true}
     */
    public static void mover(Entidad entidad, int[] nuevaPosicion) {
        Validaciones.validarDistintoDeNull(entidad, "entidad");
        Validaciones.validarDistintoDeNull(Rolgar2.getMapa().getCelda(nuevaPosicion), "nuevaPosicion");
        Validaciones.validarTrue(Rolgar2.getMapa().esCeldaTraspasable(nuevaPosicion), "nuevaPosicion");

        Rolgar2.getMapa().quitarPersonaje(entidad, entidad.getPosicion());
        entidad.setPosicion(nuevaPosicion);
        Rolgar2.getMapa().agregarPersonaje(entidad, nuevaPosicion);

        Salida.mostrarMensaje("movido a: " +  nuevaPosicion[0] + " " + nuevaPosicion[1] + " " + nuevaPosicion[2]);
    }

    /**
     * Maneja la interacción de una entidad con los elementos en su celda.
     * Procesa elementos como rampas, agua y cartas, y detecta combates.
     *
     * @param entidad entidad que interactúa. No puede ser null.
     * @pre {@code entidad != null}
     */
    public static void interactuar(Entidad entidad) {
        // Elementos
        for (Elemento elemento: Rolgar2.getMapa().getElementos(entidad.getPosicion())) {
            switch (elemento) {
                case Agua agua -> {
                    entidad.setVida(max(0, entidad.getVida() - (ConfiguracionesRolgar2.getDanioAgua() * agua.getNivel())));
                    Salida.mostrarMensaje("Nivel del agua: " + agua.getNivel());
                }
                case Carta carta -> {
                    if (entidad instanceof Jugador jugador) {
                        if(jugador.tieneInventarioLleno()) {
                            Salida.mostrarMensajeError("Inventario lleno, no se puede agarrar carta");
                            continue;
                        }

                        jugador.guardarElemento(carta);
                        Rolgar2.getMapa().quitarElemento(carta, jugador.getPosicion());
                        Salida.mostrarMensaje("Obtuviste carta " + carta.getNombre());
                    }
                }
                default -> {}
            }
        }
        
        // Personajes
        if (Rolgar2.getMapa().getEntidades(entidad.getPosicion()).size() > 1) {
            Rolgar2.agregarCombate(new Combate(entidad.getPosicion()));
        }

        // Rampas
        for (Elemento elemento: Rolgar2.getMapa().getElementos(entidad.getPosicion())) {
            if (elemento instanceof Rampa rampa) {
                try { usarRampa(entidad, rampa); }
                catch (RuntimeException | IOException _) { Salida.mostrarMensajeError("No se puede usar la rampa");}
            }
        }
    }

    /**
     * Permite a una entidad usar una rampa para cambiar de nivel.
     * Calcula la nueva posición según la dirección y tipo de rampa.
     *
     * @param entidad entidad que usa la rampa. No puede ser null.
     * @throws RuntimeException si la entidad o la rampa son null.
     * @pre {@code entidad != null}
     * @pre {@code rampa != null}
     */
    private static void usarRampa(Entidad entidad, Rampa rampa) throws IOException {
        final int NORTE = -1;
        final int SUR = 1;
        final int ESTE = 1;
        final int OESTE = -1;

        final int ARRIBA = -1;
        final int ABAJO = 1;

        final int X = 0;
        final int Y = 1;
        final int Z = 2;

        int[] posicionActual = entidad.getPosicion();
        int[] nuevaPosicion = Arrays.copyOf(posicionActual, posicionActual.length);

        Salida.mostrarMensaje("Orientación: " + Rolgar2.getMapa().getDireccionContraria(rampa.getDireccion()).toString().toLowerCase());

        switch (Rolgar2.getMapa().getDireccionContraria(rampa.getDireccion())) {
            case ARRIBA -> nuevaPosicion[Z] += NORTE;
            case IZQUIERDA -> nuevaPosicion[X] += OESTE;
            case DERECHA -> nuevaPosicion[X] += ESTE;
            case ABAJO -> nuevaPosicion[Z] += SUR;
            case ARRIBA_IZQUIERDA -> {
                nuevaPosicion[Z] += NORTE;
                nuevaPosicion[X] += OESTE;
            }
            case ARRIBA_DERECHA -> {
                nuevaPosicion[Z] += NORTE;
                nuevaPosicion[X] += ESTE;
            }
            case ABAJO_IZQUIERDA -> {
                nuevaPosicion[Z] += SUR;
                nuevaPosicion[X] += OESTE;
            }
            case ABAJO_DERECHA -> {
                nuevaPosicion[Z] += SUR;
                nuevaPosicion[X] += ESTE;
            }
        }

        nuevaPosicion[Y] += rampa.permiteSubir() ? ARRIBA : ABAJO;

        mover(entidad, nuevaPosicion);
    }

    /**
     * Permite al jugador seleccionar y usar una carta de su inventario.
     * Valida que el inventario no esté vacío y maneja diferentes tipos de cartas.
     * El orden de parámetros para usar es: usuario, objetivo, tipo.
     *
     * @param jugador jugador que usa la carta. No puede ser null.
     * @param jugadoresNoMiembros lista de jugadores no aliados. No puede ser null.
     * @throws RuntimeException si el jugador es null.
     * @pre {@code jugador != null}
     * @pre {@code turnos != null}
     * @pre {@code jugadoresNoMiembros != null}
     */
    public static void seleccionarCarta(Jugador jugador, List<Jugador> jugadoresNoMiembros) {
        if(jugador.tieneInventarioVacio()) {
            Salida.mostrarMensajeError("Inventario vacío");
            return;
        }

        Salida.mostrarMensaje("Selecciona una carta");

        Salida.mostrarInventario(jugador);

        int posicionInventario = Entrada.ingresarPosicionInventario(jugador);
        Carta carta;

        try {
            carta = jugador.quitarElemento(posicionInventario);
        } catch (RuntimeException e) {
            Salida.mostrarMensajeError("No hay carta en esa posición");
            return;
        }

        TipoCarta tipoCartaActual = carta.getTipoDeCarta();
        TipoCarta tipo = null;
        Jugador objetivo = null;

        switch (tipoCartaActual) {
            case COMODIN -> {
                List<TipoCarta> tiposCartaSeleccionable = new ListaSimplementeEnlazada<>();

                for (TipoCarta tipoCartaSeleccionable : TipoCarta.values()) {
                    if (tipoCartaSeleccionable == TipoCarta.COMODIN) {
                        continue;
                    }

                    tiposCartaSeleccionable.add(tipoCartaSeleccionable);
                }

                Salida.mostrarTiposDeCarta(tiposCartaSeleccionable);
                Salida.mostrarMensajeMismaLinea("Elección: ");
                tipo = Entrada.obtenerTipoCarta(tiposCartaSeleccionable);
            }

            case ROBO_CARTA -> {
                if (jugadoresNoMiembros.isEmpty()) {
                    Salida.mostrarMensajeError("No hay jugadores disponibles");
                    return;
                }

                Salida.mostrarMensaje("Selecciona jugador para robarle una carta");
                Salida.mostrarListaDeJugadores(jugadoresNoMiembros);
                objetivo = Entrada.seleccionar(jugadoresNoMiembros);
            }

            default -> { }
        }

        try { carta.usar(jugador, objetivo, tipo); }
        catch (RuntimeException e) { Salida.mostrarMensajeError("No se pudo utilizar la carta"); } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Permite a un jugador crear una nueva alianza.
     * Valida que el jugador no pertenezca a una alianza existente.
     *
     * @param jugador jugador que crea la alianza. No puede ser null.
     * @param alianzas conjunto de alianzas existentes. No puede ser null.
     * @throws RuntimeException si el jugador es null.
     * @pre {@code jugador != null}
     * @pre {@code alianzas != null}
     */
    public static void crearAlianza(Jugador jugador, Set<AlianzaRolgar2<Jugador>> alianzas) {
        if (jugador.getAlianza() != null) {
            Salida.mostrarMensajeError("Ya estas en una alianza");
            return;
        }

        Salida.mostrarMensaje("Creando alianza");

        AlianzaRolgar2<Jugador> nuevaAlianza = null;

        do {
            String nombreAlianza = Entrada.ingresarNombreAlianza(alianzas);
            
            try { nuevaAlianza = new AlianzaRolgar2<>(nombreAlianza, jugador); }
            catch (RuntimeException _) { Salida.mostrarMensajeError("El nombre de la alianza debe tener solo letras y espacios"); }
        } while (nuevaAlianza == null);

        jugador.setAlianza(nuevaAlianza);
    }


    /**
     * Permite al líder de una alianza agregar un nuevo jugador.
     * Valida que el jugador sea el líder y que el objetivo no esté en otra alianza.
     *
     * @param jugador líder de la alianza. No puede ser null.
     * @param alianzaJugador alianza a la que se agrega el jugador. No puede ser null.
     * @param jugadoresSinAlianza lista de jugadores disponibles. No puede ser null.
     * @throws RuntimeException si el jugador no es el líder.
     * @pre {@code jugador != null}
     * @pre {@code alianzaJugador != null}
     * @pre {@code jugadoresSinAlianza != null}
     */
    public static void agregarJugadorAlianza(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador, List<Jugador> jugadoresSinAlianza) {
        if (!validarAlianzaJugador(jugador, alianzaJugador, jugadoresSinAlianza)) {
            return;
        }

        Salida.mostrarMensaje("Seleccione jugador para agregarlo a tu alianza");
        Salida.mostrarListaDeJugadores(jugadoresSinAlianza);
        Jugador objetivo = Entrada.seleccionar(jugadoresSinAlianza);

        if(objetivo.getAlianza() != null) {
            if (alianzaJugador.estaEnAlianza(objetivo)) {
                Salida.mostrarMensajeError("El objetivo ya esta en tu alianza");
            } else {
                Salida.mostrarMensajeError("El objetivo ya se encuentra en otra alianza");
            }

            return;
        }

        alianzaJugador.agregarMiembro(objetivo);
        Salida.mostrarMensaje("Nuevo miembro de la alianza agregado correctamente");
    }

    private static boolean validarAlianzaJugador(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador, List<Jugador> jugadoresSinAlianza) {
        if (alianzaJugador == null) {
            Salida.mostrarMensajeError("No perteneces a ninguna alianza");
            return true;
        }

        if (jugador != alianzaJugador.getLider()) {
            Salida.mostrarMensajeError("Esta acción debe realizarla el líder de la alianza");
            return true;
        }

        if (jugadoresSinAlianza.isEmpty()) {
            Salida.mostrarMensajeError("No hay jugadores disponibles");
            return true;
        }

        return false;
    }


    /**
     * Permite al líder de una alianza remover un jugador.
     * Valida que el jugador sea el líder y que el objetivo esté en la alianza.
     *
     * @param jugador líder de la alianza. No puede ser null.
     * @param alianzaJugador alianza de la que se remueve el jugador. No puede ser null.
     * @param miembrosAlianza lista de miembros de la alianza. No puede ser null.
     * @throws RuntimeException si el jugador no es el líder.
     * @pre {@code jugador != null}
     * @pre {@code alianzaJugador != null}
     * @pre {@code miembrosAlianza != null}
     */
    public static void quitarJugadorAlianza(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador, List<Jugador> miembrosAlianza) {
        if (!validarAlianzaJugador(jugador, alianzaJugador, miembrosAlianza)) {
            return;
        }

        Salida.mostrarMensaje("Selecciona jugador para quitarlo de tu alianza");

        Salida.mostrarListaDeJugadores(miembrosAlianza);
        Jugador objetivo = Entrada.seleccionar(miembrosAlianza);

        if (!alianzaJugador.estaEnAlianza(objetivo)) {
            Salida.mostrarMensajeError("Ese Entidad no esta en tu alianza");
            return;
        }

        alianzaJugador.quitarMiembro(objetivo);
        Salida.mostrarMensaje("Miembro quitado de la alianza");
    }

    /**
     * Permite intercambiar cartas entre dos jugadores aliados.
     * Valida que ambos jugadores tengan cartas en sus inventarios y que pertenezcan
     * a la misma alianza antes de realizar el intercambio.
     *
     * @param jugador jugador que inicia el intercambio. No puede ser null.
     * @param alianzaJugador alianza del jugador. No puede ser null.
     * @param miembrosAlianza lista de miembros de la alianza. No puede ser null.
     * @throws RuntimeException si el jugador no pertenece a una alianza.
     * @pre {@code jugador != null}
     * @pre {@code alianzaJugador != null}
     * @pre {@code miembrosAlianza != null}
     */
    public static void intercambiarCartas(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador, List<Jugador> miembrosAlianza) {
        if (alianzaJugador == null) {
            Salida.mostrarMensajeError("No perteneces a ninguna alianza");
            return;
        }

        if (miembrosAlianza.isEmpty()) {
            Salida.mostrarMensajeError("No hay jugadores disponibles");
            return;
        }

        if(jugador.tieneInventarioVacio()) {
            Salida.mostrarMensajeError("Tu inventario esta vacío");
            return;
        }

        Salida.mostrarMensaje("Selecciona jugador para intercambiar cartas");

        Salida.mostrarListaDeJugadores(miembrosAlianza);
        Jugador objetivo = Entrada.seleccionar(miembrosAlianza);

        if (!alianzaJugador.estaEnAlianza(objetivo)) {
            Salida.mostrarMensajeError("Ese jugador no esta en tu alianza");
            return;
        }

        if(objetivo.tieneInventarioVacio()) {
            Salida.mostrarMensajeError("El inventario del objetivo esta vacío");
            return;
        }

        Salida.mostrarInventario(jugador);
        int posicionInventarioJugador = Entrada.ingresarPosicionInventario(jugador);

        Salida.mostrarInventario(objetivo);
        int posicionInventarioObjetivo = Entrada.ingresarPosicionInventario(objetivo);

        Carta cartaEntidad = jugador.quitarElemento(posicionInventarioJugador);
        Carta cartaObjetivo = objetivo.quitarElemento(posicionInventarioObjetivo);

        jugador.guardarElemento(cartaObjetivo);
        objetivo.guardarElemento(cartaEntidad);
    }

    /**
     * Pausa el juego y muestra las instrucciones al jugador.
     * El jugador debe ingresar la acción de pausa nuevamente para despausar.
     */
    public static void pausarJuego() {
        try { Temporizador.pausar(); }
        catch(RuntimeException _) { Salida.mostrarMensajeError("El juego ya esta en pausa"); }

        Salida.mostrarMensaje("Juego en pausa.");

        Salida.mostrarInstrucciones();

        Acciones accionDespausar;

        List<Acciones> accionesDespausar = new ListaSimplementeEnlazada<>();
        accionesDespausar.add(Acciones.PAUSAR);

        do {
            Salida.mostrarMensaje("Ingrese de nuevo la acción de pausa para despausar");
            accionDespausar = Entrada.ingresarAccion(accionesDespausar);
        } while (accionDespausar != Acciones.PAUSAR);

        AdministradorAcciones.despausarJuego();
    }

    /**
     * Reanuda el juego después de una pausa.
     *
     * @throws RuntimeException si el juego ya está corriendo.
     */
    public static void despausarJuego() {
        try {
            Temporizador.iniciar();
        } catch(RuntimeException _) {
            Salida.mostrarMensajeError("El juego ya esta corriendo");
        }
    }

    /**
     * Maneja el abandono del juego por parte de un jugador.
     * Remueve al jugador del mapa y de su alianza si la tiene.
     *
     * @param jugador jugador que abandona el juego. No puede ser null.
     * @param alianzaJugador alianza del jugador. Puede ser null.
     * @throws RuntimeException si el jugador es null.
     * @pre {@code jugador != null}
     */
    public static void abandonarJuego(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador, List<Jugador> miembrosAlianzaJugador) {
        Salida.mostrarMensaje("Saliendo del juego.");

        Rolgar2.getMapa().quitarPersonaje(jugador, jugador.getPosicion());

        if (alianzaJugador != null) {
            if (alianzaJugador.getLider().equals(jugador)) {
                AdministradorAcciones.borrarAlianza(jugador, alianzaJugador, miembrosAlianzaJugador);
            } else {
                AdministradorAcciones.abandonarAlianza(jugador, alianzaJugador);
            }
        }
    }
    
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Elimina una alianza y todos sus miembros.
     * Solo el líder puede ejecutar esta acción.
     *
     * @param jugador líder de la alianza. No puede ser null.
     * @param alianzaJugador alianza a eliminar. No puede ser null.
     * @throws RuntimeException si el jugador no es el líder.
     * @pre {@code jugador != null}
     * @pre {@code alianzaJugador != null}
     */
    public static <T extends Personaje> void borrarAlianza(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador, List<T> miembrosAlianzaJugador) {
        if (alianzaJugador == null) {
            Salida.mostrarMensajeError("El Jugador no pertenece a una alianza");
            return;
        }

        if (jugador != alianzaJugador.getLider()) {
            Salida.mostrarMensajeError("Esta acción debe realizarla el líder de la alianza");
            return;
        }

        for (T miembro : miembrosAlianzaJugador){
            alianzaJugador.quitarMiembro((Jugador) miembro);
        }

        alianzaJugador.quitarMiembro(jugador);

        Salida.mostrarMensaje("Alianza borrada");
    }

    /**
     * Permite a un jugador abandonar su alianza actual.
     *
     * @param jugador jugador que abandona la alianza. No puede ser null.
     * @param alianzaJugador alianza a abandonar. No puede ser null.
     * @throws RuntimeException si el jugador no pertenece a una alianza.
     * @pre {@code jugador != null}
     * @pre {@code alianzaJugador != null}
     */
    public static void abandonarAlianza(Jugador jugador, AlianzaRolgar2<Jugador> alianzaJugador) {
        if (alianzaJugador == null) {
            Salida.mostrarMensajeError("No perteneces a una alianza");
            return;
        }

        if (alianzaJugador.getLider().equals(jugador)) {
            borrarAlianza(jugador, alianzaJugador, alianzaJugador.getMiembros());
            return;
        }

        alianzaJugador.quitarMiembro(jugador);

        Salida.mostrarMensaje("Abandonaste la alianza");
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
