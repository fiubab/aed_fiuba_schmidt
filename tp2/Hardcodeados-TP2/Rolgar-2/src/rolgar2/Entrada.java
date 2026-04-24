package src.rolgar2;

import src.estructuras.conjuntos.Conjunto;
import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Alianza;
import src.modelo.Dado;
import src.rolgar2.elemento.carta.TipoCarta;
import src.rolgar2.entidad.Jugador;
import src.utils.Teclado;
import src.utils.Validaciones;

import java.util.List;
import java.util.Set;

/**
 * Clase encargada de gestionar la entrada de datos del usuario.
 * Proporciona métodos para solicitar y validar entrada del teclado.
 */
public class Entrada {

    /**
     * Permite al usuario seleccionar un elemento de una lista.
     * 
     * @param <T> tipo de elementos en la lista
     * @param opciones lista de opciones disponibles, no puede ser null
     * @return el elemento seleccionado por el usuario
     * @pre {@code opciones != null && opciones.size() > 0}
     */
    public static <T> T seleccionar(List<T> opciones) {
        Integer eleccion = null;

        do {
            try { eleccion = Teclado.leerEntero(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un numero");
                
            }

            if (eleccion == null || eleccion < 1 || eleccion > opciones.size()) {
                Salida.mostrarMensajeError("Personaje invalido");
            }
        } while (eleccion == null || eleccion < 1 || eleccion > opciones.size());

        return opciones.get(eleccion - 1);
    }

    /**
     * Solicita al usuario que ingrese una dirección válida usando el teclado.
     * Mapea las teclas de control definidas en Configuraciones a direcciones del juego.
     *
     * @return la dirección seleccionada por el usuario
     */
    public static Direcciones ingresarDireccion() {
        Direcciones direccion = null;
        char opcion;

        do {
            Salida.mostrarMensajeMismaLinea("Dirección: ");
            opcion = Teclado.leerCaracter();
            opcion = Character.toUpperCase(opcion);

            try {direccion = Direcciones.obtenerDireccion(opcion); }
            catch (IllegalArgumentException _) {
                Salida.mostrarMensajeError("Dirección inválida. Intente nuevamente.");
                
            }
        } while (direccion == null);

        return direccion;
    }

    /**
     * Solicita al usuario que ingrese una posición válida del inventario.
     * Valida que la posición esté dentro del rango permitido y que no esté vacía.
     *
     * @param jugador jugador cuyo inventario se está consultando. No puede ser null.
     * @return posición del inventario (basada en 1, donde 1 es la primera posición)
     * @pre {@code jugador != null}
     */
    public static int ingresarPosicionInventario(Jugador jugador) {
        Integer posicion = null;

        do {
            Salida.mostrarMensajeMismaLinea("Posición inventario "+ jugador.getNombre() +": ");

            try { posicion = Teclado.leerEntero(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un numero");
                
            }

            if (posicion != null) {
                if ((posicion < 1 || posicion > jugador.capacidadDelInventario())) {
                    Salida.mostrarMensajeError("Posición invalida");
                } else if (jugador.posicionInventarioInvalida(posicion)) {
                    Salida.mostrarMensajeError("La posición esta vacía");
                }
            }

        } while ((posicion == null) || (posicion < 1 || posicion > jugador.capacidadDelInventario()) && (jugador.posicionInventarioInvalida(posicion)));

        return posicion;
    }

    /**
     * Solicita al usuario que ingrese una acción válida.
     *
     * @return la acción seleccionada por el usuario.
     */
    public static Acciones ingresarAccion(List<Acciones> acciones) {
        Integer accion = null;

        do {
            Salida.mostrarMensajeMismaLinea("Ingrese acción: ");
            try { accion = Teclado.leerEntero(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un numero");
                
            }

            if (accion != null && (accion < 1 || accion > acciones.size())) {
                Salida.mostrarMensajeError("Acción incorrecta");
            }

        } while (accion == null || accion < 1 || accion > acciones.size());

        return acciones.get(accion - 1);
    }

    /**
     * Solicita al usuario que ingrese el daño de los enemigos.
     * Valida que el valor sea positivo.
     *
     * @return el daño ingresado por el usuario.
     */
    public static float ingresarDanio() {
        Float danio = null;

        do {
            Salida.mostrarMensajeMismaLinea("Ingrese el daño de los enemigos: ");

            try { danio = Teclado.leerFloat(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un número");
                
            }

            if (danio != null && danio <= 0) {
                Salida.mostrarMensajeError("El daño debe ser positivo");
            }

        } while (danio == null || danio <= 0);

        return danio;
    }

    /**
     * Solicita al usuario que ingrese una cantidad positiva.
     *
     * @param atributo nombre del atributo a ingresar. No puede ser null.
     * @return la cantidad ingresada por el usuario.
     * @pre {@code atributo != null}
     */
    public static int ingresarCantidad(String atributo) {
        Integer cantidad = null;

        do {
            Salida.mostrarMensajeMismaLinea("Ingrese el numero de " + atributo + ": ");

            try { cantidad = Teclado.leerEntero(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un numero");
                
            }

            if (cantidad != null && cantidad < 0 ) {
                Salida.mostrarMensajeError(atributo + " debe ser mayor o igual a 0");
            }

        } while (cantidad == null || cantidad < 0);

        return cantidad;
    }

    /**
     * Crea los jugadores según la cantidad especificada.
     * Solicita nombres únicos para cada jugador y les asigna una iniciativa aleatoria.
     * Cada jugador comienza con una carta aleatoria.
     *
     * @param cantidad número de jugadores a crear. Debe ser mayor a 0.
     * @return lista de jugadores creados con sus respectivas configuraciones.
     * @throws RuntimeException si la cantidad es menor o igual a 0.
     * @pre {@code cantidad > 0}
     */
    public static ListaSimplementeEnlazada<Jugador> ingresarJugadores(int cantidad) {
        Validaciones.validarMayorQueCero(cantidad, "cantidad");
        Conjunto<String> nombres = new Conjunto<>();

        ListaSimplementeEnlazada<Jugador> jugadores = new ListaSimplementeEnlazada<>();
        Dado d20 = new Dado(20);

        for (int i = 0; i < cantidad; i++) {
            String nombre;

            do {
                Salida.mostrarMensajeMismaLinea("Ingrese el nombre del jugador " + (i + 1) + ": ");
                try {
                    nombre = Teclado.leerLinea();
                    Validaciones.validarCaracteresAlfabeticos(nombre, "nombre");
                    if (nombres.contains(nombre)) {
                        Salida.mostrarMensajeError(nombre + " ya existe");
                    }
                } catch (RuntimeException _) {
                    Salida.mostrarMensajeError("Se debe ingresar un nombre con caracteres alfabéticos");
                    nombre = null;
                    
                }
            } while (nombre == null || nombres.contains(nombre));

            nombres.add(nombre);

            Jugador jugador = new Jugador(nombre, d20.tirar());

            jugador.guardarElemento(TipoCarta.RANDOM.crear());

            jugadores.add(jugador);
        }

        return jugadores;
    }

    /**
     * Solicita al usuario que ingrese un nombre único para una nueva alianza.
     *
     * @param alianzas conjunto de alianzas existentes. No puede ser null.
     * @return el nombre de la nueva alianza.
     * @pre {@code alianzas != null}
     */
    public static String ingresarNombreAlianza(Set<AlianzaRolgar2<Jugador>> alianzas) {
        String nombre;

        do {
            Salida.mostrarMensajeMismaLinea("nombre de la nueva alianza: ");
            nombre = Teclado.leerLinea();
            if (existeAlianza(nombre, alianzas)){
                Salida.mostrarMensajeError("La alianza " + nombre + " ya existe");
            }
        } while (existeAlianza(nombre, alianzas));

        return nombre;
    }

    /**
     * Solicita al usuario que seleccione un tipo de carta válido.
     * Continúa pidiendo entrada hasta que se proporcione una opción válida.
     *
     * @param tiposCartasSeleccionables lista de tipos de cartas disponibles. No puede ser null.
     * @return el tipo de carta seleccionado por el usuario.
     * @pre {@code tiposCartasSeleccionables != null}
     * @pre {@code tiposCartasSeleccionables.size() > 0}
     */
    public static TipoCarta obtenerTipoCarta(List<TipoCarta> tiposCartasSeleccionables) {
        Integer opcion = null;

        do {
            try { opcion = Teclado.leerEntero(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un numero");
                
            }

            if (opcion != null && (opcion < 1 || opcion > tiposCartasSeleccionables.size())) {
                Salida.mostrarMensajeError("Opción invalida");
            }
        } while (opcion == null || opcion < 1 || opcion > tiposCartasSeleccionables.size());

        return tiposCartasSeleccionables.get(opcion - 1);
    }


    /**
     * Permite al usuario seleccionar la dificultad del juego.
     *
     * @return la dificultad seleccionada por el usuario.
     */
    public static Dificultades seleccionarDificultad() {
        Integer indiceOpcion = null;

        do {
            Salida.mostrarMensajeMismaLinea("Dificultad (1 - " + Dificultades.values().length + "): ");

            try { indiceOpcion = Teclado.leerEntero(); }
            catch (NumberFormatException _) {
                Salida.mostrarMensajeError("Se debe ingresar un número");
                
            }

            if (indiceOpcion != null && (indiceOpcion <= 0 || indiceOpcion > Dificultades.values().length)) {
                Salida.mostrarMensajeError("Opción invalida");
            }

        } while (indiceOpcion == null || indiceOpcion <= 0 || indiceOpcion > Dificultades.values().length);

        return Dificultades.values()[indiceOpcion - 1];
    }

    /**
     * Verifica si una alianza con el nombre especificado ya existe.
     *
     * @param nombre nombre de la alianza a buscar. No puede ser null.
     * @param alianzas conjunto de alianzas. No puede ser null.
     * @return true si la alianza existe, false en caso contrario.
     * @pre {@code nombre != null}
     * @pre {@code alianzas != null}
     */
    private static boolean existeAlianza(String nombre, Set<AlianzaRolgar2<Jugador>> alianzas) {
        for (Alianza alianza : alianzas) {
            if (alianza.getNombre().equals(nombre)) {
                return true;
            }
        }

        return false;
    }
}
