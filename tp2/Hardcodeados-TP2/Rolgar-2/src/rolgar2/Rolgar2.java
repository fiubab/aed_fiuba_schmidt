package src.rolgar2;

import src.estructuras.conjuntos.Conjunto;
import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Personaje;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.elemento.ElementoRolgar2;
import src.rolgar2.entidad.Enemigo;
import src.rolgar2.entidad.Entidad;
import src.rolgar2.entidad.Jugador;
import src.utils.SistemaUtiles;
import src.utils.Temporizador;
import src.utils.Validaciones;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Clase principal del juego Rolgar2.
 * Gestiona la inicialización, ejecución y control del flujo del juego.
 * 
 * <p>Responsabilidades:</p>
 * <ul>
 *   <li>Inicializar el juego con configuraciones y jugadores</li>
 *   <li>Ejecutar el bucle principal del juego</li>
 *   <li>Gestionar turnos y combates</li>
 *   <li>Controlar alianzas y acciones de jugadores</li>
 * </ul>
 */
public class Rolgar2 {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private static List<Jugador> jugadores;
    private static List<Enemigo> enemigos;
    private static Mapa<Entidad, ElementoRolgar2> mapa;
    private static List<Combate> combates;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------

    /**
     * Inicializa el juego configurando todos los parámetros necesarios.
     * Muestra el menú principal, permite al usuario seleccionar la dificultad,
     * configura los jugadores y enemigos, y genera el tablero de juego.
     * 
     * <p>El proceso de inicialización incluye:</p>
     * <ul>
     *   <li>Mostrar menú y seleccionar dificultad</li>
     *   <li>Configurar número de jugadores</li>
     *   <li>Crear jugadores con estadísticas según dificultad</li>
     *   <li>Generar enemigos apropiados</li>
     *   <li>Crear tablero con dimensiones correspondientes</li>
     * </ul>
     */
    public static void inicializar() throws IOException {
        ConfiguracionesRolgar2.inicializar();
        Salida.mostrarMenu();
        jugadores = new ListaSimplementeEnlazada<>();
        enemigos = new ListaSimplementeEnlazada<>();
        combates = new ListaSimplementeEnlazada<>();

        Dificultades dificultad = Entrada.seleccionarDificultad();
        Integer cantidadJugadores = null;

        do {
            try { cantidadJugadores = Entrada.ingresarCantidad("jugadores"); }
            catch (RuntimeException _) {}

            if (cantidadJugadores != null && cantidadJugadores <= 0) {
                Salida.mostrarMensajeError("Se debe crear al menos un jugador");
                Salida.esperar();
            }

        } while ((cantidadJugadores == null) || (cantidadJugadores <= 0));

        jugadores = Entrada.ingresarJugadores(cantidadJugadores);

        switch (dificultad) {
            case FACIL -> {
                mapa = new Mapa<>(
                        ConfiguracionesRolgar2.getAnchoMapaFacil(),
                        ConfiguracionesRolgar2.getAltoMapaFacil(),
                        ConfiguracionesRolgar2.getLargoMapaFacil()
                );
                enemigos = generarEnemigos(dificultad, ConfiguracionesRolgar2.getCantidadEnemigosFacil());
            }

            case MEDIO -> {
                mapa = new Mapa<>(
                        ConfiguracionesRolgar2.getAnchoMapaMedio(),
                        ConfiguracionesRolgar2.getAltoMapaMedio(),
                        ConfiguracionesRolgar2.getLargoMapaMedio()
                );
                enemigos = generarEnemigos(dificultad, ConfiguracionesRolgar2.getCantidadEnemigosMedio());
            }

            case DIFICIL -> {
                mapa = new Mapa<>(
                        ConfiguracionesRolgar2.getAnchoMapaDificil(),
                        ConfiguracionesRolgar2.getAltoMapaDificil(),
                        ConfiguracionesRolgar2.getLargoMapaDificil()
                );
                enemigos = generarEnemigos(dificultad, ConfiguracionesRolgar2.getCantidadEnemigosDificil());
            }

            case CUSTOM -> {
                do {
                    // Ingreso dimensiones del mapa
                    int ancho = Entrada.ingresarCantidad("ancho mapa");
                    int alto = Entrada.ingresarCantidad("alto mapa");
                    int largo = Entrada.ingresarCantidad("largo mapa");

                    try { mapa = new Mapa<>(ancho, alto, largo); }
                    catch (RuntimeException _) {
                        Salida.mostrarMensajeError("No se pudo generar mapa, inténtelo de nuevo");
                        Salida.esperar();
                    }
                } while (mapa == null);

                // Ingreso enemigos
                Integer cantidadEnemigos = null;

                do {
                    try { cantidadEnemigos = Entrada.ingresarCantidad("enemigos"); }
                    catch (RuntimeException _) {}

                    if (cantidadEnemigos != null && cantidadJugadores <= 1 && cantidadEnemigos <= 0){
                        Salida.mostrarMensajeError("Se debe crear al menos un enemigo");
                        Salida.esperar();
                    }

                } while ((cantidadEnemigos == null) || (cantidadJugadores <= 1 && cantidadEnemigos <= 0));

                if (cantidadEnemigos > 0){
                    enemigos = generarEnemigos(dificultad, cantidadEnemigos);
                }
            }
        }

        try { mapa.generarMapa(jugadores, enemigos); }
        catch (RuntimeException e) { Salida.mostrarMensajeError("Hubo error al crear mapa"); }
    }

    /**
     * Ejecuta el bucle principal del juego.
     * Maneja los turnos de los jugadores hasta que se cumplan las condiciones de finalización.
     * 
     * <p>Durante cada turno:</p>
     * <ul>
     *   <li>Se muestra el tablero desde la perspectiva del jugador</li>
     *   <li>Se muestra la interfaz del jugador</li>
     *   <li>Se procesan las acciones del jugador</li>
     *   <li>Se avanza al siguiente turno</li>
     * </ul>
     * 
     * <p>El juego termina cuando:</p>
     * <ul>
     *   <li>No quedan jugadores vivos</li>
     *   <li>Solo queda un jugador y no hay enemigos</li>
     *   <li>Solo queda una alianza y no hay enemigos</li>
     * </ul>
     *
     * @throws IOException sí hay error al generar los bitmaps del tablero.
     */
    public static void jugar() throws IOException {
        List<Entidad> entidades = new ListaSimplementeEnlazada<>();
        entidades.addAll(jugadores);
        entidades.addAll(enemigos);
        TurnoRolgar2<Entidad> turnos = new TurnoRolgar2<>(entidades);
        Temporizador.iniciar();

        Render.generarBitmapTablero(jugadores.getFirst());


        while (!juegoFinalizado()) {
            Entidad entidad = turnos.getPersonajeTurnoActual();

            switch (entidad) {
                case Jugador jugador -> {
                    Render.mostrarBitmaps(jugador);
                    Salida.mostrarInterfazJugador(jugador);

                    manejarAccionesJugador(jugador);

                    SistemaUtiles.esperar(500);
                    Render.mostrarBitmaps(jugador);

                    Salida.mostrarSeparador();
                }
                case Enemigo enemigo -> {
                    if (enemigo.estaEnCombate()) {
                        enemigo.getCombate().atacar(enemigo);
                    }
                }
                default -> {}
            }

            limpiarEntidadesMuertas();
            verificarCombates();
            turnos.siguienteTurno();
        }

        Temporizador.pausar();
        Salida.mostrarMensajeFinal(getAlianzas(), getJugadores());
    }


//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Maneja las acciones seleccionadas por un jugador en su turno.
     * Procesa la acción y actualiza el estado del juego.
     *
     * @param jugador jugador cuyas acciones se manejan, no puede ser null
     * @pre {@code jugador != null}
     */
    private static void manejarAccionesJugador(Jugador jugador) {
        List<Acciones> accionesPosibles = getAccionesDisponibles(jugador);

        Salida.mostrarAccionesDelJugador(accionesPosibles);
        Acciones accion = Entrada.ingresarAccion(accionesPosibles);
        AlianzaRolgar2<Jugador> alianzaJugador = jugador.getAlianza();
        List<Jugador> miembrosAlianzaJugador = new ListaSimplementeEnlazada<>();

        if (alianzaJugador != null) {
            for (Personaje miembro : alianzaJugador.getMiembros()){
                if (!miembro.equals(jugador)) {
                    miembrosAlianzaJugador.add((Jugador) miembro);
                }
            }
        }

        switch (accion) {
            case MOVER -> {
                if (jugador.estaEnCombate()){
                    jugador.getCombate().escapar(jugador);
                } else {
                    AdministradorAcciones.mover(jugador);
                }
            }

            case CREAR_ALIANZA -> {
                if (confirmarAccion(accion)) {
                    AdministradorAcciones.crearAlianza(jugador, getAlianzas());
                }
            }

            case BORRAR_ALIANZA -> {
                if (confirmarAccion(accion)) {
                    AdministradorAcciones.borrarAlianza(jugador, alianzaJugador, miembrosAlianzaJugador);
                }
            }

            case AGREGAR_JUGADOR_ALIANZA -> {
                if (confirmarAccion(accion)) {
                    AdministradorAcciones.agregarJugadorAlianza(jugador, alianzaJugador, getJugadoresSinAlianza());
                }
            }

            case QUITAR_JUGADOR_ALIANZA -> {
                if (confirmarAccion(accion)) {
                    AdministradorAcciones.quitarJugadorAlianza(jugador, alianzaJugador, miembrosAlianzaJugador);
                }
            }

            case ABANDONAR_ALIANZA -> {
                if (confirmarAccion(accion)) {
                    AdministradorAcciones.abandonarAlianza(jugador, alianzaJugador);
                }
            }

            case INTERCAMBIAR_CARTA -> {
                if (confirmarAccion(accion)){
                    AdministradorAcciones.intercambiarCartas(jugador, alianzaJugador, miembrosAlianzaJugador);
                }
            }

            case SELECCIONAR_CARTA -> {
                if (confirmarAccion(accion)){
                    List<Jugador> jugadoresNoMiembros = getJugadores();
                    jugadoresNoMiembros.removeAll(miembrosAlianzaJugador);
                    jugadoresNoMiembros.remove(jugador);

                    AdministradorAcciones.seleccionarCarta(jugador, jugadoresNoMiembros);
                }
            }

            case ATACAR -> {
                try { jugador.getCombate().atacar(jugador); }
                catch (RuntimeException _) { Salida.mostrarMensajeError("No estas en combate"); }
            }

            case PAUSAR -> AdministradorAcciones.pausarJuego();

            case SALIR -> {
                if (confirmarAccion(accion)) {
                    jugadores.remove(jugador);
                    AdministradorAcciones.abandonarJuego(jugador, alianzaJugador, miembrosAlianzaJugador);
                }
            }

            default -> Salida.mostrarMensajeError("Acción Invalida");
        }

        if (jugadores.contains(jugador)) {
            AdministradorAcciones.interactuar(jugador);
        }
    }

    /**
     * Elimina del juego todas las entidades que han muerto.
     */
    private static void limpiarEntidadesMuertas(){
        enemigos.removeIf(enemigo -> enemigo.getVida() <= 0);
        jugadores.removeIf(jugador -> jugador.getVida() <= 0);
    }

    /**
     * Verifica el estado de todos los combates activos.
     * Finaliza los combates que han terminado.
     */
    private static void verificarCombates(){
        for (Combate combate : combates) {
            combate.eliminarEntidadesFueraDeCombate();
            if (combate.combateFinalizado()){
                combate.finalizar();
                quitarCombate(combate);
            }
        }
    }

    /**
     * Obtiene la lista de acciones disponibles para un jugador en su turno.
     * Las acciones disponibles dependen del estado del jugador (en combate, en alianza, etc.).
     * 
     * @param jugador jugador para el cual se obtienen las acciones, no puede ser null
     * @return lista de acciones disponibles
     * @throws RuntimeException si jugador es null
     * @pre {@code jugador != null}
     */
    private static List<Acciones> getAccionesDisponibles(Jugador jugador){
        Validaciones.validarDistintoDeNull(jugador, "jugador");

        List<Acciones> accionesPosibles = new ListaSimplementeEnlazada<>();

        accionesPosibles.add(Acciones.MOVER);

        if (!jugador.tieneInventarioVacio()) {
            accionesPosibles.add(Acciones.SELECCIONAR_CARTA);
        }

        if (jugador.estaEnCombate()) {
            accionesPosibles.add(Acciones.ATACAR);
        } else {
            AlianzaRolgar2<Jugador> alianza = jugador.getAlianza();

            if (alianza == null) {
                accionesPosibles.add(Acciones.CREAR_ALIANZA);
            } else {
                accionesPosibles.add(Acciones.ABANDONAR_ALIANZA);

                if (alianza.getLider().equals(jugador)) {
                    accionesPosibles.add(Acciones.BORRAR_ALIANZA);
                    if (!getJugadoresSinAlianza().isEmpty()){
                        accionesPosibles.add(Acciones.AGREGAR_JUGADOR_ALIANZA);
                    }
                    if (alianza.getCantidadMiembros() > 1) {
                        accionesPosibles.add(Acciones.QUITAR_JUGADOR_ALIANZA);
                    }
                }

                if (alianza.getCantidadMiembros() > 1 && !jugador.tieneInventarioVacio()) {
                    accionesPosibles.add(Acciones.INTERCAMBIAR_CARTA);
                }
            }
        }

        accionesPosibles.add(Acciones.PAUSAR);
        accionesPosibles.add(Acciones.SALIR);

        return accionesPosibles;
    }

    /**
     * Solicita al jugador que confirme una acción importante.
     * 
     * @param accion acción a confirmar
     * @return true si el jugador confirma, false si cancela
     */
    private static boolean confirmarAccion(Acciones accion){
        Salida.mostrarMensaje("¿Seguro que querés " + accion.toString().toLowerCase() + "?");

        List<Acciones> accionesDisponibles = new ListaSimplementeEnlazada<>();

        accionesDisponibles.add(accion);
        accionesDisponibles.add(Acciones.CANCELAR);

        Salida.mostrarAccionesDelJugador(accionesDisponibles);
        Acciones accionSeleccionada = Entrada.ingresarAccion(accionesDisponibles);
        return accionSeleccionada == accion;
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Verifica si el juego ha terminado según las condiciones de victoria/derrota.
     * El juego termina cuando:
     * - No hay jugadores vivos
     * - Solo queda un jugador y no hay enemigos
     * - Solo queda una alianza con todos los jugadores y no hay enemigos
     *
     * @return true si el juego ha finalizado, false en caso contrario.
     */
    private static boolean juegoFinalizado() {
        return (jugadores.isEmpty()) ||
                (jugadores.size() == 1 && enemigos.isEmpty()) ||
                (getAlianzas().size() == 1 && enemigos.isEmpty() && getAlianzas().iterator().next().getCantidadMiembros() == jugadores.size());
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    /**
     * Obtiene todas las alianzas activas en el juego.
     *
     * @return conjunto de alianzas activas
     */
    public static Set<AlianzaRolgar2<Jugador>> getAlianzas() {
        Set<AlianzaRolgar2<Jugador>> alianzas = new Conjunto<>();

        for (Jugador jugador : jugadores) {
            if (jugador.getAlianza() != null) {
                alianzas.add(jugador.getAlianza());
            }
        }

        return alianzas;
    }

    /**
     * Genera una lista de enemigos según la dificultad especificada.
     * Cada enemigo recibe vida, daño e iniciativa según la dificultad.
     *
     * @param dificultad nivel de dificultad del juego, no puede ser null
     * @param cantidad número de enemigos a generar, debe ser mayor o igual a 0
     * @return lista de enemigos generados
     * @throws RuntimeException si dificultad es null o cantidad es negativa
     * @pre {@code dificultad != null}
     * @pre {@code cantidad >= 0}
     */
    private static List<Enemigo> generarEnemigos(Dificultades dificultad, int cantidad) {
        Validaciones.validarMayorIgualCero(cantidad, "cantidad");
        Validaciones.validarDistintoDeNull(dificultad, "dificultad");
        List<Enemigo> enemigos = new ListaSimplementeEnlazada<>();

        Float danioCustom = null;
        Integer iniciativaCustom = null;

        if (dificultad == Dificultades.CUSTOM) {
            danioCustom = Entrada.ingresarDanio();
            iniciativaCustom = Entrada.ingresarCantidad("iniciativa");
        }

        for (int i = 0; i < cantidad; i++) {
            Enemigo enemigo;

            int vida = new Random().nextInt((int) ConfiguracionesRolgar2.getVidaMinimaEnemigos(), (int) (ConfiguracionesRolgar2.getVidaMaximaEnemigos() + 1));

            switch (dificultad) {
                case FACIL -> enemigo = new Enemigo(vida, ConfiguracionesRolgar2.getDanioEnemigoFacil(), ConfiguracionesRolgar2.getIniciativaEnemigoFacil());
                case MEDIO -> enemigo = new Enemigo(vida, ConfiguracionesRolgar2.getDanioEnemigoMedio(), ConfiguracionesRolgar2.getIniciativaEnemigoMedio());
                case DIFICIL -> enemigo = new Enemigo(vida, ConfiguracionesRolgar2.getDanioEnemigoDificil(), ConfiguracionesRolgar2.getIniciativaEnemigoDificil());
                default -> enemigo = new Enemigo(vida, danioCustom, iniciativaCustom);
            }

            enemigos.add(enemigo);
        }

        return enemigos;
    }

    /**
     * Obtiene una copia de la lista de jugadores activos.
     *
     * @return lista de jugadores
     */
    public static List<Jugador> getJugadores() {
        List<Jugador> jugadoresCopia = new ListaSimplementeEnlazada<>();
        jugadoresCopia.addAll(jugadores);

        return jugadoresCopia;
    }

    /**
     * Obtiene la lista de jugadores que no pertenecen a ninguna alianza.
     *
     * @return lista de jugadores sin alianza
     */
    public static List<Jugador> getJugadoresSinAlianza() {
        List<Jugador> jugadoresSinAlianza = new ListaSimplementeEnlazada<>();

        for (Jugador jugador : getJugadores()) {
            if (jugador.getAlianza() == null) {
                jugadoresSinAlianza.add(jugador);
            }
        }

        return jugadoresSinAlianza;
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene el mapa del juego.
     * 
     * @return el mapa actual
     */
    public static Mapa<Entidad, ElementoRolgar2> getMapa(){
        return mapa;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Agrega un nuevo combate a la lista de combates activos.
     * 
     * @param combate combate a agregar, no puede ser null
     * @throws RuntimeException si combate es null
     * @pre {@code combate != null}
     */
    public static void agregarCombate(Combate combate) {
        Validaciones.validarDistintoDeNull(combate, "combate");
        combates.add(combate);
    }

    /**
     * Remueve un combate de la lista de combates activos.
     * 
     * @param combate combate a remover, no puede ser null
     * @throws RuntimeException si combate es null o no está en la lista
     * @pre {@code combate != null}
     * @pre {@code combates.contains(combate)}
     */
    public static void quitarCombate(Combate combate) {
        Validaciones.validarDistintoDeNull(combate, "combate");
        Validaciones.validarTrue(combates.contains(combate), "combate");
        combates.remove(combate);
    }
}
