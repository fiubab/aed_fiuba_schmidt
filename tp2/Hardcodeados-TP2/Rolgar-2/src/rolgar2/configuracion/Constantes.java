package src.rolgar2.configuracion;

import src.utils.SistemaUtiles;

/**
 * Clase que centraliza todas las constantes del juego para facilitar
 * el mantenimiento y la configuración.
 * 
 * <p>Define valores para vida, combate, alianzas, cartas, elementos del mapa
 * y configuraciones específicas de cada nivel de dificultad.</p>
 */
public class Constantes {
    public static final int VISION = 3;

    // === CONFIGURACIÓN DE MAPA  === //
    /** Porcentaje máximo de celdas del mapa que pueden estar ocupadas. */
    public static final float PORCENTAJE_MAXIMO_OCUPACION_MAPA = 0.25f;
    /** Probabilidad de generar una piedra en el mapa (5%) */
    public static final float PROBABILIDAD_GENERAR_PIEDRA = 0.2f;
    /** Probabilidad de generar agua en el mapa (5%) */
    public static final float PROBABILIDAD_GENERAR_AGUA = 0.2f;
    /** Probabilidad de generar una carta en el mapa (5%) */
    public static final float PROBABILIDAD_GENERAR_CARTA = 0.05f;

    // === CONFIGURACIÓN DE VIDA ===
    /** Vida inicial del jugador */
    public static final float VIDA_INICIAL_JUGADOR = 100;
    /** Vida máxima que pueden tener los enemigos al generarse */
    public static final float VIDA_MAXIMA_ENEMIGOS = 120;
    /** Vida mínima que pueden tener los enemigos al generarse */
    public static final float VIDA_MINIMA_ENEMIGOS = 40;
    /** Proporción de vida recuperada cada turno */
    public static final float PROPORCION_VIDA_AGREGADA = VIDA_INICIAL_JUGADOR * 0.05f;

    //=== CONFIGURACIÓN DE COMBATE ===//
    /** Daño base que efectúa el jugador en cualquier enemigo */
    public static final float DANIO_BASE_JUGADOR = VIDA_INICIAL_JUGADOR * 0.1f;
    /** Probabilidad de acertar un golpe crítico (20%) */
    public static final float PROBABILIDAD_CRITICO = 0.20f;
    public static final float PROBABILIDAD_ESCAPAR = 0.20f;
    /** Multiplicador del daño por golpe crítico */
    public static final float MULTIPLICADOR_CRITICO = 2;
    /** Cantidad de caras del dado utilizado para calcular golpes críticos */
    public static final int CANTIDAD_CARAS_DADO_CRITICO = 20;

    // === CONFIGURACIÓN DE CARTAS ===
    /** Cantidad máxima de cartas que puede tener el jugador en su inventario */
    public static final int CANTIDAD_MAXIMA_CARTAS_INVENTARIO = 10;
    /** Puntos de vida recuperados al usar una carta de curación */
    public static final float CURACION_CARTA = VIDA_INICIAL_JUGADOR * 0.25f;
    /** Factor de reducción de daño al usar una carta de escudo (50%) */
    public static final float FACTOR_REDUCCION_DANIO_ESCUDO = 0.5f;


    // === CONFIGURACIÓN DEL AGUA === //
    /** Daño que causa el agua al jugador por turno */
    public static final float DANIO_AGUA = 5;

    // === CONFIGURACIÓN DE DIFICULTADES ===
    // === FÁCIL ===
    /** Alto del mapa en dificultad fácil (niveles verticales) */
    public static final int ALTO_MAPA_FACIL = 3;
    /** Ancho del mapa en dificultad fácil */
    public static final int ANCHO_MAPA_FACIL = 11;
    /** Largo del mapa en dificultad fácil */
    public static final int LARGO_MAPA_FACIL = 11;
    /** Cantidad de enemigos en dificultad fácil */
    public static final int CANTIDAD_ENEMIGOS_FACIL = 10;
    /** Daño que causan los enemigos en dificultad fácil (7% de vida inicial) */
    public static final float DANIO_ENEMIGO_FACIL = VIDA_INICIAL_JUGADOR * 0.07f;
    /** Iniciativa de los enemigos en dificultad fácil */
    public static final int INICIATIVA_ENEMIGO_FACIL = 0;

    // === MEDIO ===
    /** Alto del mapa en dificultad medio (niveles verticales) */
    public static final int ALTO_MAPA_MEDIO = 5;
    /** Ancho del mapa en dificultad medio */
    public static final int ANCHO_MAPA_MEDIO = 21;
    /** Largo del mapa en dificultad medio */
    public static final int LARGO_MAPA_MEDIO = 21;
    /** Cantidad de enemigos en dificultad media */
    public static final int CANTIDAD_ENEMIGOS_MEDIO = 15;
    /** Daño que causan los enemigos en dificultad media (10% de vida inicial) */
    public static final float DANIO_ENEMIGO_MEDIO = VIDA_INICIAL_JUGADOR * 0.1f;
    /** Iniciativa de los enemigos en dificultad media */
    public static final int INICIATIVA_ENEMIGO_MEDIO = 5;

    // === DIFÍCIL ===
    /** Alto del mapa en dificultad difícil (niveles verticales) */
    public static final int ALTO_MAPA_DIFICIL = 7;
    /** Ancho del mapa en dificultad difícil */
    public static final int ANCHO_MAPA_DIFICIL = 31;
    /** Largo del mapa en dificultad difícil */
    public static final int LARGO_MAPA_DIFICIL = 31;
    /** Cantidad de enemigos en dificultad difícil */
    public static final int CANTIDAD_ENEMIGOS_DIFICIL = 20;
    /** Daño que causan los enemigos en dificultad difícil (15% de vida inicial) */
    public static final float DANIO_ENEMIGO_DIFICIL = VIDA_INICIAL_JUGADOR * 0.15f;
    /** Iniciativa de los enemigos en dificultad difícil */
    public static final int INICIATIVA_ENEMIGO_DIFICIL = 10;

    // === CONFIGURACIÓN POR DEFECTO ===
    // === CONTROLES
    /** Tecla por defecto para mover hacia arriba */
    public static final char MOVER_ARRIBA = 'W';
    /** Tecla por defecto para mover hacia abajo */
    public static final char MOVER_ABAJO = 'S';
    /** Tecla por defecto para mover hacia la izquierda */
    public static final char MOVER_IZQUIERDA = 'A';
    /** Tecla por defecto para mover hacia la derecha */
    public static final char MOVER_DERECHA = 'D';
    /** Tecla por defecto para mover en diagonal arriba-izquierda */
    public static final char MOVER_ARRIBA_IZQUIERDA = 'Q';
    /** Tecla por defecto para mover en diagonal arriba-derecha */
    public static final char MOVER_ARRIBA_DERECHA = 'E';
    /** Tecla por defecto para mover en diagonal abajo-izquierda */
    public static final char MOVER_ABAJO_IZQUIERDA = 'Z';
    /** Tecla por defecto para mover en diagonal abajo-derecha */
    public static final char MOVER_ABAJO_DERECHA = 'C';

    // === Visual
    /** Ruta de la imagen de fondo del juego */
    public static final String IMAGEN_FONDO = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/fondo.bmp");
    /** Ruta de la imagen de piedra */
    public static final String IMAGEN_PIEDRA = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/piedra.bmp");
    /** Ruta de la imagen de rampa de subida */
    public static final String IMAGEN_RAMPA_SUBIDA = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/escalera_subida.bmp");
    /** Ruta de la imagen de rampa de bajada */
    public static final String IMAGEN_RAMPA_BAJADA = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/escalera_bajada.bmp");
    /** Ruta de la imagen de agua */
    public static final String IMAGEN_AGUA = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/agua.bmp");
    /** Ruta de la imagen de carta */
    public static final String IMAGEN_CARTA = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/carta.bmp");
    /** Ruta de la imagen del jugador */
    public static final String IMAGEN_JUGADOR = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/jugador.bmp");
    /** Ruta de la imagen del enemigo */
    public static final String IMAGEN_ENEMIGO = SistemaUtiles.generarRutaAbsoluta("src/rolgar2/Imagenes/enemigo.bmp");
}
