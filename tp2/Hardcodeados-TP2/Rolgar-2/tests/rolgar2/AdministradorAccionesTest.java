package tests.rolgar2;

import org.junit.jupiter.api.Test;
import src.rolgar2.AdministradorAcciones;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase AdministradorAcciones.
 */
public class AdministradorAccionesTest {

    @Test
    public void testAdministradorAccionesExiste() {
        assertNotNull(AdministradorAcciones.class);
    }
}
