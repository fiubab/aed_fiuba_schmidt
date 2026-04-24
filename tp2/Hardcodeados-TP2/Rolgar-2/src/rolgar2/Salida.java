package src.rolgar2;

import src.modelo.Personaje;
import src.rolgar2.elemento.carta.Carta;
import src.rolgar2.elemento.carta.TipoCarta;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.entidad.Entidad;
import src.rolgar2.entidad.Jugador;
import src.utils.SistemaUtiles;
import src.utils.Temporizador;

import java.util.List;
import java.util.Set;

/**
 * Clase que gestiona la interfaz de menús y mensajes del juego.
 * 
 * <p>Proporciona métodos para mostrar el título, instrucciones, dificultades,
 * acciones disponibles y opciones de cartas.</p>
 */
public class Salida {
    /**
     * Muestra el título del juego en la consola con formato.
     * 
     * @param titulo texto del título a mostrar
     */
    public static void mostrarTitulo(String titulo) {
        mostrarMensaje("======= " + titulo + " =======");
    }

    /**
     * Muestra las instrucciones del juego.
     * 
     * <p>Las instrucciones incluyen:</p>
     * <ul>
     *   <li>Descripción del juego</li>
     *   <li>Controles de movimiento básico</li>
     *   <li>Controles de movimiento diagonal</li>
     * </ul>
     */
    public static void mostrarInstrucciones() {
        mostrarTitulo("Instrucciones");

        mostrarMensaje("DESCRIPCIÓN:");
        mostrarMensaje("Rolgar-2 es un juego multijugador en un tablero tridimensional donde hasta N personajes compiten simultáneamente.");
        mostrarMensaje("Los jugadores navegan por un mundo 3D con obstáculos, recolectan cartas de poder especiales, forman alianzas temporales y combaten contra enemigos y otros jugadores.");
        mostrarMensaje("objetivo es ser el último en pie o formar parte de una alianza victoriosa, utilizando estrategia, combate y gestión de recursos en un entorno de visibilidad limitada.");

        // Controles básicos
        mostrarMensaje("CONTROLES DEL JUGADOR:");
        mostrarMensaje("Mover arriba: " + ConfiguracionesRolgar2.getMoverArriba());
        mostrarMensaje("Mover abajo: " + ConfiguracionesRolgar2.getMoverAbajo());
        mostrarMensaje("Mover izquierda: " + ConfiguracionesRolgar2.getMoverIzquierda());
        mostrarMensaje("Mover derecha: " + ConfiguracionesRolgar2.getMoverDerecha());

        mostrarMensaje("Mover en diagonal:");
        mostrarMensaje("Arriba-Izquierda: " + ConfiguracionesRolgar2.getMoverArribaIzquierda());
        mostrarMensaje("Arriba-Derecha:   " + ConfiguracionesRolgar2.getMoverArribaDerecha());
        mostrarMensaje("Abajo-Izquierda:  " + ConfiguracionesRolgar2.getMoverAbajoIzquierda());
        mostrarMensaje("Abajo-Derecha:    " + ConfiguracionesRolgar2.getMoverAbajoDerecha());

        mostrarSeparador();
    }

    /**
     * Muestra las dificultades disponibles del juego con sus características.
     * 
     * <p>Muestra información de cuatro dificultades:</p>
     * <ul>
     *   <li>FACIL: Configuración básica</li>
     *   <li>MEDIO: Configuración intermedia</li>
     *   <li>DIFICIL: Configuración avanzada</li>
     *   <li>CUSTOM: Configuración personalizada</li>
     * </ul>
     */
    public static void mostrarDificultades() {
        mostrarTitulo("DIFICULTADES DEL JUEGO");
        for (Dificultades dificultad : Dificultades.values()) {
            dificultad.mostrarDescripcion();
        }
        mostrarSeparador();
    }

    /**
     * Muestra el menú principal del juego.
     * Incluye título, instrucciones y dificultades disponibles.
     */
    public static void mostrarMenu(){
        mostrarTitulo("ROLGAR 2");
        mostrarInstrucciones();
        mostrarDificultades();
    }

    /**
     * Muestra la interfaz del jugador con su información actual.
     * Incluye nombre, vida, posición y alianza (si la tiene).
     *
     * @param jugador jugador cuya información se muestra. No puede ser null.
     * @pre {@code jugador != null}
     */
    public static void mostrarInterfazJugador(Jugador jugador) {
        String nombreJugador = jugador.getNombre();
        float vidaJugador = jugador.getVida();
        int[] posicionJugador = jugador.getPosicion();

        mostrarMensaje("Nombre: " + nombreJugador + " Vida: " + vidaJugador + " Posicion: " + posicionJugador[0] + " " + posicionJugador[1] + " " + posicionJugador[2]);

        if (jugador.estaEnCombate()) {
            mostrarMensaje("Estas en combate");
        }

        AlianzaRolgar2<Jugador> alianzaDelJugador = jugador.getAlianza();
        if (alianzaDelJugador != null && !alianzaDelJugador.estaVacia()) {
            Salida.mostrarMensaje("Alianza: " + alianzaDelJugador.getNombre());

            if (alianzaDelJugador.getCantidadMiembros() > 1) {
                for (Personaje miembro : alianzaDelJugador.getMiembros()) {
                    if (miembro.equals(jugador)) {
                        continue;
                    }

                    int[] posicionMiembro = ((Jugador) miembro).getPosicion();
                    String nombre = miembro.getNombre();

                    mostrarMensajeMismaLinea(nombre + ": " + posicionMiembro[0] + " " + posicionMiembro[1] + " " + posicionMiembro[2] + "\t");
                }
            }
        }

        mostrarMensajeMismaLinea("\n");
    }

    /**
     * Muestra las acciones disponibles que puede realizar un personaje en su turno.
     * 
     * <p>Las acciones son las siguientes:</p>
     * <ul>
     *   <li>Moverse</li>
     *   <li>Atacar</li>
     *   <li>Usar carta</li>
     *   <li>Crear alianza</li>
     *   <li>Borrar alianza</li>
     *   <li>Abandonar alianza</li>
     *   <li>Agregar personaje a alianza</li>
     *   <li>Quitar personaje de la alianza</li>
     *   <li>Intercambiar carta</li>
     *   <li>Pausar</li>
     *   <li>Salir</li>
     * </ul>
     */
    public static void mostrarAccionesDelJugador(List<Acciones> acciones){
        int indiceAcciones = 1;

        for (Acciones accion : acciones) {
            mostrarMensaje( indiceAcciones + ". " + accion);
            indiceAcciones++;
        }
    }

    /**
     * Muestra un separador visual que se utiliza para delimitar secciones en el menú del juego.
     */
    public static void mostrarSeparador() {
        mostrarMensaje("===================================");
    }


    /**
     * Muestra los tipos de cartas disponibles para seleccionar.
     *
     * @param tipoCartas lista de tipos de cartas a mostrar. No puede ser null.
     * @pre {@code tipoCartas != null}
     */
    public static void mostrarTiposDeCarta(List<TipoCarta> tipoCartas){
        mostrarMensaje("Seleccione un tipo de carta:");

        int indiceCartaOpcion = 1;

        for (TipoCarta tipo : tipoCartas) {
            mostrarMensaje(indiceCartaOpcion + ". " + tipo);
            indiceCartaOpcion++;
        }
    }

    /**
     * Muestra una lista de jugadores numerada para seleccionar.
     *
     * @param jugadores lista de jugadores a mostrar. No puede ser {@code null}.
     * @pre {@code jugadores != null}
     */
    public static void mostrarListaDeJugadores(List<Jugador> jugadores){
        mostrarMensaje("Seleccione un jugador");

        int indiceJugador = 1;

        for (Jugador jugador : jugadores) {
            mostrarMensaje(indiceJugador + ". " + jugador.getNombre());
            indiceJugador++;
        }
    }

    /**
     * Muestra una lista de personajes numerada para seleccionar.
     *
     * @param personajes lista de personajes a mostrar. No puede ser {@code null}.
     * @pre {@code personajes != null}
     */
    public static void mostrarListaDePersonjes(List<? extends Personaje> personajes){
        mostrarMensaje("Seleccione un objetivo");

        int indicePersonaje = 1;

        for (Personaje personaje : personajes) {
            mostrarMensaje(indicePersonaje + ". " + personaje.getNombre() + " (vida: " + ((Entidad)personaje).getVida() + ")" );
            indicePersonaje++;
        }
    }

    /**
     * Muestra el inventario de un jugador.
     *
     * @param jugador jugador cuyo inventario se muestra. No puede ser null.
     * @pre {@code jugador != null}
     */
    public static void mostrarInventario(Jugador jugador) {
        int indice = 1;

        for (Carta carta : jugador.contenidoDelInventario()) {
            mostrarMensajeMismaLinea(indice + ". ");

            if(carta == null) {
                mostrarMensajeMismaLinea(".");
            } else {
                mostrarMensajeMismaLinea(carta.getNombre());
            }

            mostrarMensajeMismaLinea("\n");
            indice++;
        }

        mostrarMensajeMismaLinea("\n");
    }

    /**
     * Muestra el mensaje final del juego con los ganadores.
     *
     * @param alianzas conjunto de alianzas del juego. No puede ser null.
     * @param jugadores lista de jugadores ganadores. No puede ser null.
     * @pre {@code alianzas != null}
     * @pre {@code jugadores != null}
     */
    public static void mostrarMensajeFinal(Set<AlianzaRolgar2<Jugador>> alianzas, List<Jugador> jugadores){
        mostrarMensaje("Juego finalizado en: " + Temporizador.getTiempoFormateado());

        if (!alianzas.isEmpty()) {
            mostrarMensaje("La alianza ganadora es: " + alianzas.iterator().next().getNombre());
        }

        if (!jugadores.isEmpty()) {
            if (jugadores.size() == 1) {
                mostrarMensaje("El ganador es " + jugadores.getFirst().getNombre());
            } else {
                mostrarMensaje("Los ganadores son: ");
                for (Jugador jugador : jugadores) {
                    mostrarMensaje(jugador.getNombre());
                }
            }
        } else {
            mostrarMensaje("Los jugadores perdieron");
        }

    }

    /**
     * Muestra un mensaje de error en la salida de error estándar.
     *
     * @param error mensaje de error a mostrar, no puede ser null
     */
    public static void mostrarMensajeError(String error) {
        System.err.println(error);
        esperar();
    }

    /**
     * Muestra un mensaje en la salida estándar.
     *
     * @param mensaje mensaje a mostrar, no puede ser null
     */
    public static void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    /**
     * Muestra un mensaje en la misma línea sin salto de línea.
     *
     * @param mensaje mensaje a mostrar, no puede ser null
     */
    public static void mostrarMensajeMismaLinea(String mensaje) { System.out.print(mensaje); }

    /**
     * Pausa la ejecución del programa por un tiempo predeterminado.
     */
    public static void esperar(){
        SistemaUtiles.esperar(50);
    }
}


