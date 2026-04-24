package tests.rolgar2.configuracion;

import org.junit.jupiter.api.Test;
import src.rolgar2.configuracion.Constantes;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Constantes.
 */
public class ConstantesTest {

    @Test
    public void testConstantesExisten() {
        assertTrue(Constantes.VISION > 0);
        assertTrue(Constantes.VIDA_INICIAL_JUGADOR > 0);
        assertTrue(Constantes.DANIO_BASE_JUGADOR > 0);
    }

    @Test
    public void testConstantesDificultades() {
        assertTrue(Constantes.ALTO_MAPA_FACIL > 0);
        assertTrue(Constantes.ALTO_MAPA_MEDIO > 0);
        assertTrue(Constantes.ALTO_MAPA_DIFICIL > 0);
    }
}
