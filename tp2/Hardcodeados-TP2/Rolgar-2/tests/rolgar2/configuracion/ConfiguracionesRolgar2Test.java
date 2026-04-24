package tests.rolgar2.configuracion;

import org.junit.jupiter.api.Test;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase ConfiguracionesRolgar2.
 */
public class ConfiguracionesRolgar2Test {

    @Test
    public void testConfiguracionesRolgar2Existe() {
        assertNotNull(ConfiguracionesRolgar2.class);
    }

    @Test
    public void testInicializar() {
        assertDoesNotThrow(ConfiguracionesRolgar2::inicializar);
    }
}
