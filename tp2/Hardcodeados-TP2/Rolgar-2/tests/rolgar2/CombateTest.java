package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Combate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Combate.
 */
public class CombateTest {

    @Test
    public void testCombateExiste() {
        assertNotNull(Combate.class);
    }
}
