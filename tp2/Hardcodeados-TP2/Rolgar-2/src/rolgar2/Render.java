package src.rolgar2;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Elemento;
import src.modelo.Personaje;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.elemento.*;
import src.rolgar2.elemento.carta.Carta;
import src.rolgar2.entidad.Efectos;
import src.rolgar2.entidad.Enemigo;
import src.rolgar2.entidad.Jugador;
import src.utils.bitmap.Bitmap;
import src.utils.bitmap.BitmapViewer;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.min;

/**
 * Clase encargada de renderizar el tablero del juego.
 * Genera bitmaps que representan la vista del jugador en el mapa 3D.
 * 
 * <p>Proporciona métodos para:</p>
 * <ul>
 *   <li>Generar bitmaps del tablero desde la perspectiva del jugador</li>
 *   <li>Dibujar celdas individuales con sus elementos</li>
 *   <li>Mostrar múltiples niveles del mapa</li>
 * </ul>
 */
public class Render {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------

    private static final int TAMANIO_CELDA = 64;
    private static final List<Bitmap> bitmaps = new ListaSimplementeEnlazada<>();

//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------

    /**
     * Genera los bitmaps del tablero desde la perspectiva del jugador.
     * Crea múltiples capas de bitmaps representando diferentes niveles de altura.
     *
     * @param jugador jugador cuya perspectiva se renderiza. No puede ser null.
     * @throws IOException sí hay error al cargar las imágenes.
     * @pre {@code jugador != null}
     * @post Se generan y muestran los bitmaps del tablero.
     */
    public static void generarBitmapTablero(Jugador jugador) throws IOException {
        int ancho = TAMANIO_CELDA*jugador.getVision();
        int profundo = TAMANIO_CELDA*jugador.getVision();

        for (int alto = 0; alto < min(jugador.getVision(), Rolgar2.getMapa().getAlto()); alto++) {
            Bitmap bitmap = new Bitmap(ancho, profundo);
            bitmaps.add(bitmap);
        }

        new BitmapViewer(bitmaps);
        mostrarBitmaps(jugador);
    }

    /**
     * Dibuja un bitmap del tablero centrado en una posición específica.
     * Renderiza todos los elementos y personajes visibles desde esa posición.
     *
     * @param bitmap bitmap a dibujar. No puede ser null.
     * @param posicionCentral posición central del renderizado. No puede ser null.
     * @throws IOException sí hay error al cargar las imágenes.
     * @pre {@code bitmap != null}
     * @pre {@code posicionCentral != null}
     * @pre {@code visionJugador > 0}
     */
    private static void dibujarBitmap(Bitmap bitmap, int[] posicionCentral, int radioVision) throws IOException {
        bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenFondo()), 0, 0);

        /*
        * d - - - -
        * - - - - -
        * - - x - - d: (p_x - (vision / 2),p_y 2 - (vision / 2))
        * - - - - - h: (p_x + (vision / 2),p_y 2 + (vision / 2))
        * - - - - h
        * */

        if (posicionCentral[1] < 1 ||  posicionCentral[1] > Rolgar2.getMapa().getAlto()) {
            return;
        }

        int desdeX = (posicionCentral[0] - radioVision);
        int hastaX = (posicionCentral[0] + radioVision);

        int desdeZ = (posicionCentral[2] - radioVision);
        int hastaZ = (posicionCentral[2] + radioVision);

        for (int x = desdeX; x <= hastaX; x++) {
            for (int z = desdeZ; z <= hastaZ; z++) {
                if (x < 1 || x > Rolgar2.getMapa().getAncho() || z < 1 || z > Rolgar2.getMapa().getProfundo()) {
                    continue;
                }
                int[] posicionActual = new int[]{x, posicionCentral[1], z};
                mostrarCelda(posicionActual, bitmap, x - desdeX, z - desdeZ);
            }
        }
    }

    /**
     * Renderiza una celda específica en el bitmap.
     * Dibuja todos los elementos y personajes contenidos en la celda.
     *
     * @param bitmap bitmap donde se dibuja. No puede ser null.
     * @param x coordenada x en el bitmap.
     * @param z coordenada z en el bitmap.
     * @throws IOException sí hay error al cargar las imágenes.
     * @pre {@code bitmap != null}
     */
    private static void mostrarCelda(int[] posicion, Bitmap bitmap, int x, int z) throws IOException {
        if (Rolgar2.getMapa().getCelda(posicion) == null){
            return;
        }

        for (Elemento elemento : Rolgar2.getMapa().getElementos(Rolgar2.getMapa().getCelda(posicion))){
            switch (elemento){
                case Piedra _ -> bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenPiedra()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                case Carta _ -> bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenCarta()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                case Rampa rampa -> {
                    if (rampa.permiteSubir()){
                        bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenRampaSubida()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                    } else {
                        bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenRampaBajada()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                    }
                }
                case Agua _ -> bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenAgua()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                default -> bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenFondo()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
            }
        }

        for (Personaje personaje : Rolgar2.getMapa().getEntidades(Rolgar2.getMapa().getCelda(posicion))){
            switch (personaje){
                case Jugador jugador -> {
                    if (!jugador.tieneEfecto(Efectos.INVISIBILIDAD)){
                        bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenJugador()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                    }
                }
                case Enemigo _ -> bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenEnemigo()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
                default -> bitmap.pasteBitmap(Bitmap.loadFromFile(ConfiguracionesRolgar2.getImagenFondo()), x*TAMANIO_CELDA, z*TAMANIO_CELDA);
            }
        }

    }

    /**
     * Muestra los bitmaps generados en la interfaz gráfica.
     * Dibuja cada nivel del mapa desde la perspectiva del jugador.
     *
     * @param jugador jugador cuya perspectiva se muestra. No puede ser null.
     * @throws IOException sí hay error al cargar las imágenes.
     * @pre {@code bitmaps != null}
     * @pre {@code jugador != null}
     */
    public static void mostrarBitmaps(Jugador jugador) throws IOException {
        int[] posicionJugador = jugador.getPosicion();
        int visionMaxima = min(jugador.getVision(), Rolgar2.getMapa().getAlto());
        int radioVision = jugador.getVision() / 2;

        int desdeY = (posicionJugador[1] - visionMaxima/2);
        int hastaY = (posicionJugador[1] + visionMaxima/2);

        for (int y = desdeY; y <= hastaY; y++) {
            int[] posicionCentralBitmap = new int[]{posicionJugador[0], y, posicionJugador[2]};
            dibujarBitmap(bitmaps.get(y - desdeY), posicionCentralBitmap, radioVision);
        }
    }

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------    
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}