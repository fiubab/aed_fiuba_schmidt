package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Salida;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Salida.
 */
public class SalidaTest {

    @Test
    public void testSalidaExiste() {
        assertNotNull(Salida.class);
    }
}
