package src.rolgar2.configuracion;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Clase utilitaria para la gestión de archivos de configuración en formato JSON.
 * 
 * <p>Proporciona métodos para crear archivos de configuración por defecto y
 * leer configuraciones desde archivos JSON usando Jackson.</p>
 */
public class Json {
    /** Ruta donde se encuentra el archivo json a leer */
    private static final String RUTA_CONFIG = "config.json";

    /**
     * Crea un archivo de configuración por defecto con los valores predeterminados, si no existe.
     * 
     * @throws IOException sí ocurre un error al leer o escribir el archivo de configuración
     */
    private static void crearJsonPorDefecto() throws IOException {
        File archivo = new File(RUTA_CONFIG);

        if (archivo.exists()) {
            System.out.println("El archivo de configuración ya existe, no se sobrescribirá.");
            return;
        } else {
            System.out.println("Creando archivo de configuración por defecto.");
        }

        Configuraciones config = crearConfiguracionPorDefecto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(archivo, config);
    }

    /**
     * Lee el archivo de configuración y devuelve una instancia de Configuraciones.
     * Si no existe el archivo o está vacío, se crea uno nuevo con las configuraciones por defecto.
     *
     * @return Instancia de Configuraciones con los valores leídos del JSON
     * @throws IOException sí ocurre un error al leer o escribir el archivo de configuración.
     */
    public static Configuraciones leerConfiguraciones() throws IOException {
        File archivo = new File(RUTA_CONFIG);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No se encontró el archivo de configuración o está vacío, creando uno nuevo...");
            crearJsonPorDefecto();
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(archivo, Configuraciones.class);
    }

    /**
     * Crea y devuelve una instancia de Configuraciones con valores por defecto.
     * 
     * @return Instancia de Configuraciones con valores por defecto
     */
    private static Configuraciones crearConfiguracionPorDefecto() {
        Configuraciones config = new Configuraciones();
        
        // Configurar controles usando las constantes definidas
        config.setMoverArriba(Constantes.MOVER_ARRIBA);
        config.setMoverAbajo(Constantes.MOVER_ABAJO);
        config.setMoverIzquierda(Constantes.MOVER_IZQUIERDA);
        config.setMoverDerecha(Constantes.MOVER_DERECHA);
        config.setMoverArribaIzquierda(Constantes.MOVER_ARRIBA_IZQUIERDA);
        config.setMoverArribaDerecha(Constantes.MOVER_ARRIBA_DERECHA);
        config.setMoverAbajoIzquierda(Constantes.MOVER_ABAJO_IZQUIERDA);
        config.setMoverAbajoDerecha(Constantes.MOVER_ABAJO_DERECHA);

        // Configurar representación visual usando las constantes definidas
        config.setImagenFondo(Constantes.IMAGEN_FONDO);
        config.setImagenPiedra(Constantes.IMAGEN_PIEDRA);
        config.setImagenRampaSubida(Constantes.IMAGEN_RAMPA_SUBIDA);
        config.setImagenRampaBajada(Constantes.IMAGEN_RAMPA_BAJADA);
        config.setImagenAgua(Constantes.IMAGEN_AGUA);
        config.setImagenCarta(Constantes.IMAGEN_CARTA);
        config.setImagenJugador(Constantes.IMAGEN_JUGADOR);
        config.setImagenEnemigo(Constantes.IMAGEN_ENEMIGO);
        
        return config;
    }
}
