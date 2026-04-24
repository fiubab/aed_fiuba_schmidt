package src.rolgar2.configuracion;

/**
 * Clase que gestiona las configuraciones del juego.
 *
 * <p>Almacena los controles del jugador y la representación visual
 * de los elementos del juego. Se carga desde un archivo JSON.</p>
 */
public class Configuraciones {
    // Controles del juego
    private char moverArriba;
    private char moverIzquierda;
    private char moverAbajo;
    private char moverDerecha;

    private char moverArribaIzquierda;
    private char moverArribaDerecha;
    private char moverAbajoIzquierda;
    private char moverAbajoDerecha;

    // Representación visual
    private String imagenFondo;
    private String imagenPiedra;
    private String imagenRampaSubida;
    private String imagenRampaBajada;
    private String imagenAgua;
    private String imagenCarta;
    private String imagenJugador;
    private String imagenEnemigo;

    // Getters
    /**
     * Obtiene la tecla para mover hacia arriba.
     * @return carácter de la tecla
     */
    public char getMoverArriba() { return moverArriba; }
    
    /**
     * Obtiene la tecla para mover hacia abajo.
     * @return carácter de la tecla
     */
    public char getMoverAbajo() { return moverAbajo; }
    
    /**
     * Obtiene la tecla para mover hacia la derecha.
     * @return carácter de la tecla
     */
    public char getMoverDerecha() { return moverDerecha; }
    
    /**
     * Obtiene la tecla para mover hacia la izquierda.
     * @return carácter de la tecla
     */
    public char getMoverIzquierda() { return moverIzquierda; }
    
    /**
     * Obtiene la tecla para mover en diagonal arriba-izquierda.
     * @return carácter de la tecla
     */
    public char getMoverArribaIzquierda() { return moverArribaIzquierda; }
    
    /**
     * Obtiene la tecla para mover en diagonal arriba-derecha.
     * @return carácter de la tecla
     */
    public char getMoverArribaDerecha() { return moverArribaDerecha; }
    
    /**
     * Obtiene la tecla para mover en diagonal abajo-izquierda.
     * @return carácter de la tecla
     */
    public char getMoverAbajoIzquierda() { return moverAbajoIzquierda; }
    
    /**
     * Obtiene la tecla para mover en diagonal abajo-derecha.
     * @return carácter de la tecla
     */
    public char getMoverAbajoDerecha() { return moverAbajoDerecha; }

    /**
     * Obtiene la ruta de la imagen de fondo.
     * @return ruta de la imagen
     */
    public String getImagenFondo() { return imagenFondo; }
    
    /**
     * Obtiene la ruta de la imagen de piedra.
     * @return ruta de la imagen
     */
    public String getImagenPiedra() { return imagenPiedra; }
    
    /**
     * Obtiene la ruta de la imagen de rampa de subida.
     * @return ruta de la imagen
     */
    public String getImagenRampaSubida() { return imagenRampaSubida; }
    
    /**
     * Obtiene la ruta de la imagen de rampa de bajada.
     * @return ruta de la imagen
     */
    public String getImagenRampaBajada() { return imagenRampaBajada; }
    
    /**
     * Obtiene la ruta de la imagen de agua.
     * @return ruta de la imagen
     */
    public String getImagenAgua() { return imagenAgua; }
    
    /**
     * Obtiene la ruta de la imagen de carta.
     * @return ruta de la imagen
     */
    public String getImagenCarta() { return imagenCarta; }
    
    /**
     * Obtiene la ruta de la imagen del jugador.
     * @return ruta de la imagen
     */
    public String getImagenJugador() { return imagenJugador; }
    
    /**
     * Obtiene la ruta de la imagen del enemigo.
     * @return ruta de la imagen
     */
    public String getImagenEnemigo() { return imagenEnemigo; }

    // Setters
    /**
     * Establece la tecla para mover hacia arriba.
     * @param valor carácter de la tecla
     */
    public void setMoverArriba(char valor) { this.moverArriba = valor; }
    
    /**
     * Establece la tecla para mover hacia abajo.
     * @param valor carácter de la tecla
     */
    public void setMoverAbajo(char valor) { this.moverAbajo = valor; }
    
    /**
     * Establece la tecla para mover hacia la derecha.
     * @param valor carácter de la tecla
     */
    public void setMoverDerecha(char valor) { this.moverDerecha = valor; }
    
    /**
     * Establece la tecla para mover hacia la izquierda.
     * @param valor carácter de la tecla
     */
    public void setMoverIzquierda(char valor) { this.moverIzquierda = valor; }
    
    /**
     * Establece la tecla para mover en diagonal arriba-izquierda.
     * @param valor carácter de la tecla
     */
    public void setMoverArribaIzquierda(char valor) { this.moverArribaIzquierda = valor; }
    
    /**
     * Establece la tecla para mover en diagonal arriba-derecha.
     * @param valor carácter de la tecla
     */
    public void setMoverArribaDerecha(char valor) { this.moverArribaDerecha = valor; }
    
    /**
     * Establece la tecla para mover en diagonal abajo-izquierda.
     * @param valor carácter de la tecla
     */
    public void setMoverAbajoIzquierda(char valor) { this.moverAbajoIzquierda = valor; }
    
    /**
     * Establece la tecla para mover en diagonal abajo-derecha.
     * @param valor carácter de la tecla
     */
    public void setMoverAbajoDerecha(char valor) { this.moverAbajoDerecha = valor; }

    /**
     * Establece la ruta de la imagen de fondo.
     * @param valor ruta de la imagen
     */
    public void setImagenFondo(String valor) { this.imagenFondo = valor; }
    
    /**
     * Establece la ruta de la imagen de piedra.
     * @param valor ruta de la imagen
     */
    public void setImagenPiedra(String valor) { this.imagenPiedra = valor; }
    
    /**
     * Establece la ruta de la imagen de rampa de subida.
     * @param valor ruta de la imagen
     */
    public void setImagenRampaSubida(String valor) { this.imagenRampaSubida = valor; }
    
    /**
     * Establece la ruta de la imagen de rampa de bajada.
     * @param valor ruta de la imagen
     */
    public void setImagenRampaBajada(String valor) { this.imagenRampaBajada = valor; }
    
    /**
     * Establece la ruta de la imagen de agua.
     * @param valor ruta de la imagen
     */
    public void setImagenAgua(String valor) { this.imagenAgua = valor; }
    
    /**
     * Establece la ruta de la imagen de carta.
     * @param valor ruta de la imagen
     */
    public void setImagenCarta(String valor) { this.imagenCarta = valor; }
    
    /**
     * Establece la ruta de la imagen del jugador.
     * @param valor ruta de la imagen
     */
    public void setImagenJugador(String valor) { this.imagenJugador = valor; }
    
    /**
     * Establece la ruta de la imagen del enemigo.
     * @param valor ruta de la imagen
     */
    public void setImagenEnemigo(String valor) { this.imagenEnemigo = valor; }
}
