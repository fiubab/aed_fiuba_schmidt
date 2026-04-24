package tests.rolgar2.configuracion;

import org.junit.jupiter.api.Test;
import src.rolgar2.configuracion.Json;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Json.
 */
public class JsonTest {

    @Test
    public void testJsonExiste() {
        assertNotNull(Json.class);
    }

    @Test
    public void testLeerConfiguraciones() {
        assertDoesNotThrow(Json::leerConfiguraciones);
    }
}
