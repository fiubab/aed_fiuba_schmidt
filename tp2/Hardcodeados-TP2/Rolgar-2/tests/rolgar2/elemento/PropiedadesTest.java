package tests.rolgar2.elemento;

import org.junit.jupiter.api.Test;
import src.rolgar2.elemento.Propiedades;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para el enum Propiedades.
 */
public class PropiedadesTest {

    @Test
    public void testPropiedadesExisten() {
        assertNotNull(Propiedades.TRASPASABLE);
        assertNotNull(Propiedades.NO_TRASPASABLE);
        assertNotNull(Propiedades.PERMITE_SUBIR);
        assertNotNull(Propiedades.PERMITE_BAJAR);
    }

    @Test
    public void testPropiedadesValores() {
        assertEquals(4, Propiedades.values().length);
    }
}
