package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.Render;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Render.
 */
public class RenderTest {

    @Test
    public void testRenderExiste() {
        assertNotNull(Render.class);
    }
}
