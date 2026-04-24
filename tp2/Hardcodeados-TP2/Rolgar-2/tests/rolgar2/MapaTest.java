package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Mapa;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Mapa.
 */
public class MapaTest {

    @Test
    public void testMapaExiste() {
        assertNotNull(Mapa.class);
    }
}
