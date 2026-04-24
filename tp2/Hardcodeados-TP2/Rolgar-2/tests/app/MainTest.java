package tests.app;

import org.junit.jupiter.api.Test;
import src.app.Main;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Main.
 */
public class MainTest {

    @Test
    public void testMainExiste() {
        assertNotNull(Main.class);
    }
}
