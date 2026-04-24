package src.rolgar2.configuracion;

import java.io.IOException;

/**
 * Clase centralizada para el acceso a todas las configuraciones y constantes del juego Rolgar2.
 *
 * <p>Esta clase proporciona acceso estático a todas las configuraciones cargadas desde JSON
 * y a todas las constantes del juego. Debe inicializarse llamando a {@link #inicializar()}
 * antes de acceder a cualquier valor.</p>
 *
 * <p>Las configuraciones incluyen:</p>
 * <ul>
 *   <li>Controles del jugador (teclas de movimiento)</li>
 *   <li>Rutas de imágenes para elementos visuales</li>
 *   <li>Constantes del juego (vida, daño, probabilidades, etc.)</li>
 *   <li>Configuraciones específicas por dificultad</li>
 * </ul>
 *
 * <p>Uso:</p>
 * <pre>
 * ConfiguracionesRolgar2.inicializar();
 * int vision = ConfiguracionesRolgar2.getVision();
 * </pre>
 *
 * @see Configuraciones
 * @see Constantes
 * @see Json
 */
public class ConfiguracionesRolgar2 {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    // Controles del juego
    private static char moverArriba;
    private static char moverIzquierda;
    private static char moverAbajo;
    private static char moverDerecha;
    private static char moverArribaIzquierda;
    private static char moverArribaDerecha;
    private static char moverAbajoIzquierda;
    private static char moverAbajoDerecha;

    // Representación visual
    private static String imagenFondo;
    private static String imagenPiedra;
    private static String imagenRampaSubida;
    private static String imagenRampaBajada;
    private static String imagenAgua;
    private static String imagenCarta;
    private static String imagenJugador;
    private static String imagenEnemigo;

    // Constantes del juego
    private static int vision;
    private static float porcentajeMaximaOcupacionMapa;
    private static float probabilidadGenerarPiedra;
    private static float probabilidadGenerarAgua;
    private static float probabilidadGenerarCarta;
    private static float vidaInicial;
    private static float vidaMaximaEnemigos;
    private static float vidaMinimaEnemigos;
    private static float proporcionVidaAgregada;
    private static float danioBaseJugador;
    private static float probabilidadCritico;
    private static float probabilidadEscapar;
    private static float multiplicadorCritico;
    private static int cantidadCarasDadoCritico;
    private static int cantidadMaximaCartasInventario;
    private static float curacionCarta;
    private static float efectoEscudo;
    private static float danioAgua;
    private static int altoMapaFacil;
    private static int anchoMapaFacil;
    private static int largoMapaFacil;
    private static int cantidadEnemigosFacil;
    private static float danioEnemigoFacil;
    private static int iniciativaEnemigoFacil;
    private static int altoMapaMedio;
    private static int anchoMapaMedio;
    private static int largoMapaMedio;
    private static int cantidadEnemigosMedio;
    private static float danioEnemigoMedio;
    private static int iniciativaEnemigoMedio;
    private static int altoMapaDificil;
    private static int anchoMapaDificil;
    private static int largoMapaDificil;
    private static int cantidadEnemigosDificil;
    private static float danioEnemigoDificil;
    private static int iniciativaEnemigoDificil;

//ATRIBUTOS -----------------------------------------------------------------------------------------------
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Inicializa todas las configuraciones y constantes del juego.
     *
     * <p>Este método debe ser llamado una vez al inicio del programa antes de
     * acceder a cualquier configuración. Lee las configuraciones desde el archivo
     * JSON y carga todas las constantes desde la clase {@link Constantes}.</p>
     *
     * @throws IOException si ocurre un error al leer el archivo de configuración
     * @post Todas las configuraciones y constantes están cargadas y listas para usar
     * @see Json#leerConfiguraciones()
     */
    public static void inicializar() throws IOException {
        // Cargar configuraciones desde JSON
        Configuraciones config = Json.leerConfiguraciones();

        moverArriba = config.getMoverArriba();
        moverAbajo = config.getMoverAbajo();
        moverIzquierda = config.getMoverIzquierda();
        moverDerecha = config.getMoverDerecha();
        moverArribaIzquierda = config.getMoverArribaIzquierda();
        moverArribaDerecha = config.getMoverArribaDerecha();
        moverAbajoIzquierda = config.getMoverAbajoIzquierda();
        moverAbajoDerecha = config.getMoverAbajoDerecha();
        
        imagenFondo = config.getImagenFondo();
        imagenPiedra = config.getImagenPiedra();
        imagenRampaSubida = config.getImagenRampaSubida();
        imagenRampaBajada = config.getImagenRampaBajada();
        imagenAgua = config.getImagenAgua();
        imagenCarta = config.getImagenCarta();
        imagenJugador = config.getImagenJugador();
        imagenEnemigo = config.getImagenEnemigo();
        
        // Cargar constantes
        vision = Constantes.VISION;
        porcentajeMaximaOcupacionMapa = Constantes.PORCENTAJE_MAXIMO_OCUPACION_MAPA;
        probabilidadGenerarPiedra = Constantes.PROBABILIDAD_GENERAR_PIEDRA;
        probabilidadGenerarAgua = Constantes.PROBABILIDAD_GENERAR_AGUA;
        probabilidadGenerarCarta = Constantes.PROBABILIDAD_GENERAR_CARTA;
        vidaInicial = Constantes.VIDA_INICIAL_JUGADOR;
        vidaMaximaEnemigos = Constantes.VIDA_MAXIMA_ENEMIGOS;
        vidaMinimaEnemigos = Constantes.VIDA_MINIMA_ENEMIGOS;
        proporcionVidaAgregada = Constantes.PROPORCION_VIDA_AGREGADA;
        danioBaseJugador = Constantes.DANIO_BASE_JUGADOR;
        probabilidadCritico = Constantes.PROBABILIDAD_CRITICO;
        probabilidadEscapar = Constantes.PROBABILIDAD_ESCAPAR;
        multiplicadorCritico = Constantes.MULTIPLICADOR_CRITICO;
        cantidadCarasDadoCritico = Constantes.CANTIDAD_CARAS_DADO_CRITICO;
        cantidadMaximaCartasInventario = Constantes.CANTIDAD_MAXIMA_CARTAS_INVENTARIO;
        curacionCarta = Constantes.CURACION_CARTA;
        efectoEscudo = Constantes.FACTOR_REDUCCION_DANIO_ESCUDO;
        danioAgua = Constantes.DANIO_AGUA;
        altoMapaFacil = Constantes.ALTO_MAPA_FACIL;
        anchoMapaFacil = Constantes.ANCHO_MAPA_FACIL;
        largoMapaFacil = Constantes.LARGO_MAPA_FACIL;
        cantidadEnemigosFacil = Constantes.CANTIDAD_ENEMIGOS_FACIL;
        danioEnemigoFacil = Constantes.DANIO_ENEMIGO_FACIL;
        iniciativaEnemigoFacil = Constantes.INICIATIVA_ENEMIGO_FACIL;
        altoMapaMedio = Constantes.ALTO_MAPA_MEDIO;
        anchoMapaMedio = Constantes.ANCHO_MAPA_MEDIO;
        largoMapaMedio = Constantes.LARGO_MAPA_MEDIO;
        cantidadEnemigosMedio = Constantes.CANTIDAD_ENEMIGOS_MEDIO;
        danioEnemigoMedio = Constantes.DANIO_ENEMIGO_MEDIO;
        iniciativaEnemigoMedio = Constantes.INICIATIVA_ENEMIGO_MEDIO;
        altoMapaDificil = Constantes.ALTO_MAPA_DIFICIL;
        anchoMapaDificil = Constantes.ANCHO_MAPA_DIFICIL;
        largoMapaDificil = Constantes.LARGO_MAPA_DIFICIL;
        cantidadEnemigosDificil = Constantes.CANTIDAD_ENEMIGOS_DIFICIL;
        danioEnemigoDificil = Constantes.DANIO_ENEMIGO_DIFICIL;
        iniciativaEnemigoDificil = Constantes.INICIATIVA_ENEMIGO_DIFICIL;
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------    
//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
    // Getters de controles
    /** @return Tecla configurada para mover hacia arriba */
    public static char getMoverArriba() { return moverArriba; }
    /** @return Tecla configurada para mover hacia abajo */
    public static char getMoverAbajo() { return moverAbajo; }
    /** @return Tecla configurada para mover hacia la derecha */
    public static char getMoverDerecha() { return moverDerecha; }
    /** @return Tecla configurada para mover hacia la izquierda */
    public static char getMoverIzquierda() { return moverIzquierda; }
    /** @return Tecla configurada para mover en diagonal arriba-izquierda */
    public static char getMoverArribaIzquierda() { return moverArribaIzquierda; }
    /** @return Tecla configurada para mover en diagonal arriba-derecha */
    public static char getMoverArribaDerecha() { return moverArribaDerecha; }
    /** @return Tecla configurada para mover en diagonal abajo-izquierda */
    public static char getMoverAbajoIzquierda() { return moverAbajoIzquierda; }
    /** @return Tecla configurada para mover en diagonal abajo-derecha */
    public static char getMoverAbajoDerecha() { return moverAbajoDerecha; }

    // Getters de representación visual
    /** @return Ruta de la imagen de fondo */
    public static String getImagenFondo() { return imagenFondo; }
    /** @return Ruta de la imagen de piedra */
    public static String getImagenPiedra() { return imagenPiedra; }
    /** @return Ruta de la imagen de rampa de subida */
    public static String getImagenRampaSubida() { return imagenRampaSubida; }
    /** @return Ruta de la imagen de rampa de bajada */
    public static String getImagenRampaBajada() { return imagenRampaBajada; }
    /** @return Ruta de la imagen de agua */
    public static String getImagenAgua() { return imagenAgua; }
    /** @return Ruta de la imagen de carta */
    public static String getImagenCarta() { return imagenCarta; }
    /** @return Ruta de la imagen del jugador */
    public static String getImagenJugador() { return imagenJugador; }
    /** @return Ruta de la imagen del enemigo */
    public static String getImagenEnemigo() { return imagenEnemigo; }

    // Getters de constantes generales
    public static int getVision() { return vision; }
    /** @return Maximo porcentaje de celdas ocupadas en el mapa */
    public static float getPorcentajeMaximaOcupacionMapa() { return porcentajeMaximaOcupacionMapa; }
    /** @return Probabilidad de generar piedra en el mapa */
    public static float getProbabilidadGenerarPiedra() { return probabilidadGenerarPiedra; }
    /** @return Probabilidad de generar agua en el mapa */
    public static float getProbabilidadGenerarAgua() { return probabilidadGenerarAgua; }
    /** @return Probabilidad de generar carta en el mapa */
    public static float getProbabilidadGenerarCarta() { return probabilidadGenerarCarta; }
    /** @return Vida inicial del jugador */
    public static float getVidaInicial() { return vidaInicial; }
    /** @return Vida máxima que pueden tener los enemigos */
    public static float getVidaMaximaEnemigos() { return vidaMaximaEnemigos; }
    /** @return Vida mínima que pueden tener los enemigos */
    public static float getVidaMinimaEnemigos() { return vidaMinimaEnemigos; }
    /** @return Proporción de vida recuperada cada turno */
    public static float getProporcionVidaAgregada() { return proporcionVidaAgregada; }
    /** @return Daño base del jugador */
    public static float getDanioBaseJugador() { return danioBaseJugador; }
    /** @return Probabilidad de golpe crítico */
    public static float getProbabilidadCritico() { return probabilidadCritico; }
    public static float getProbabilidadEscapar() { return probabilidadEscapar; }
    /** @return Multiplicador de daño para golpe crítico */
    public static float getMultiplicadorCritico() { return multiplicadorCritico; }
    /** @return Cantidad de caras del dado para críticos */
    public static int getCantidadCarasDadoCritico() { return cantidadCarasDadoCritico; }
    /** @return Cantidad máxima de cartas en inventario */
    public static int getCantidadMaximaCartasInventario() { return cantidadMaximaCartasInventario; }
    /** @return Puntos de vida recuperados por carta de curación */
    public static float getCuracionCarta() { return curacionCarta; }
    /** @return Factor de reducción de daño del escudo */
    public static float getEfectoEscudo() { return efectoEscudo; }
    /** @return Daño causado por el agua */
    public static float getDanioAgua() { return danioAgua; }
    
    // Getters de configuración dificultad FÁCIL
    /** @return Alto del mapa en dificultad fácil */
    public static int getAltoMapaFacil() { return altoMapaFacil; }
    /** @return Ancho del mapa en dificultad fácil */
    public static int getAnchoMapaFacil() { return anchoMapaFacil; }
    /** @return Largo del mapa en dificultad fácil */
    public static int getLargoMapaFacil() { return largoMapaFacil; }
    /** @return Cantidad de enemigos en dificultad fácil */
    public static int getCantidadEnemigosFacil() { return cantidadEnemigosFacil; }
    /** @return Daño de enemigos en dificultad fácil */
    public static float getDanioEnemigoFacil() { return danioEnemigoFacil; }
    /** @return Iniciativa de enemigos en dificultad fácil */
    public static int getIniciativaEnemigoFacil() { return iniciativaEnemigoFacil; }
    
    // Getters de configuración dificultad MEDIO
    /** @return Alto del mapa en dificultad medio */
    public static int getAltoMapaMedio() { return altoMapaMedio; }
    /** @return Ancho del mapa en dificultad medio */
    public static int getAnchoMapaMedio() { return anchoMapaMedio; }
    /** @return Largo del mapa en dificultad medio */
    public static int getLargoMapaMedio() { return largoMapaMedio; }
    /** @return Cantidad de enemigos en dificultad media */
    public static int getCantidadEnemigosMedio() { return cantidadEnemigosMedio; }
    /** @return Daño de enemigos en dificultad medio */
    public static float getDanioEnemigoMedio() { return danioEnemigoMedio; }
    /** @return Iniciativa de enemigos en dificultad media */
    public static int getIniciativaEnemigoMedio() { return iniciativaEnemigoMedio; }
    
    // Getters de configuración dificultad DIFÍCIL
    /** @return Alto del mapa en dificultad difícil */
    public static int getAltoMapaDificil() { return altoMapaDificil; }
    /** @return Ancho del mapa en dificultad difícil */
    public static int getAnchoMapaDificil() { return anchoMapaDificil; }
    /** @return Largo del mapa en dificultad difícil */
    public static int getLargoMapaDificil() { return largoMapaDificil; }
    /** @return Cantidad de enemigos en dificultad difícil */
    public static int getCantidadEnemigosDificil() { return cantidadEnemigosDificil; }
    /** @return Daño de enemigos en dificultad difícil */
    public static float getDanioEnemigoDificil() { return danioEnemigoDificil; }
    /** @return Iniciativa de enemigos en dificultad difícil */
    public static int getIniciativaEnemigoDificil() { return iniciativaEnemigoDificil; }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------    
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
