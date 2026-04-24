package tests.rolgar2.configuracion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.rolgar2.configuracion.Configuraciones;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Configuraciones.
 */
public class ConfiguracionesTest {
    private Configuraciones config;

    @BeforeEach
    public void setUp() {
        config = new Configuraciones();
    }

    @Test
    public void testCrearConfiguraciones() {
        assertNotNull(config);
    }

    @Test
    public void testSetGetMoverArriba() {
        config.setMoverArriba('W');
        assertEquals('W', config.getMoverArriba());
    }

    @Test
    public void testSetGetImagenFondo() {
        config.setImagenFondo("ruta/imagen.bmp");
        assertEquals("ruta/imagen.bmp", config.getImagenFondo());
    }
}
