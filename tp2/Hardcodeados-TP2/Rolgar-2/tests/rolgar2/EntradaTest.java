package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Entrada;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Entrada.
 */
public class EntradaTest {

    @Test
    public void testEntradaExiste() {
        assertNotNull(Entrada.class);
    }
}
