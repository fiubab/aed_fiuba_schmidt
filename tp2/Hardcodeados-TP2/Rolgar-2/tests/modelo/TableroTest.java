package tests.modelo;

import src.modelo.Tablero;
import src.modelo.Celda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    private Tablero<String> tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero<>(3, 3, 3);
    }

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    @Test
    void constructorDeberiaCrearTableroCorrecto() {
        Tablero<String> nuevoTablero = new Tablero<>(5, 4, 3);
        
        assertEquals(5, nuevoTablero.getAncho());
        assertEquals(4, nuevoTablero.getAlto());
        assertEquals(3, nuevoTablero.getProfundo());
        assertEquals(60, nuevoTablero.getCantidadDeCeldas());
    }

    @Test
    void constructorConValorPorDefectoDeberiaInicializarCeldas() {
        Tablero<String> tableroConValor = new Tablero<>(2, 2, 2, "default");
        
        assertEquals("default", tableroConValor.getCelda(1, 1, 1).getContenido());
        assertEquals("default", tableroConValor.getCelda(2, 2, 2).getContenido());
    }

    @Test
    void constructorSinValorPorDefectoDeberiaCrearCeldasVacias() {
        assertNull(tablero.getCelda(1, 1, 1).getContenido());
        assertNull(tablero.getCelda(2, 2, 2).getContenido());
    }

    // -------------------------------------------------------------------------
    // Obtener celdas
    // -------------------------------------------------------------------------

    @Test
    void getCeldaDeberiaRetornarCeldaCorrecta() {
        Celda<String> celda = tablero.getCelda(2, 2, 2);
        
        assertNotNull(celda);
        assertArrayEquals(new int[]{2, 2, 2}, celda.getPosicion());
    }

    @Test
    void getCeldaConCoordenadasInvalidasDeberiaLanzarExcepcion() {
        assertThrows(RuntimeException.class, () -> tablero.getCelda(0, 1, 1));
        assertThrows(RuntimeException.class, () -> tablero.getCelda(1, 0, 1));
        assertThrows(RuntimeException.class, () -> tablero.getCelda(1, 1, 0));
    }

    // -------------------------------------------------------------------------
    // Vecinos
    // -------------------------------------------------------------------------

    @Test
    void getVecinosDeCeldaDeberiaRetornarMatriz3x3x3() {
        Celda<String>[][][] vecinos = tablero.getVecinosDeCelda(2, 2, 2);
        
        assertNotNull(vecinos);
        assertEquals(3, vecinos.length);
        assertEquals(3, vecinos[0].length);
        assertEquals(3, vecinos[0][0].length);
    }

    @Test
    void vecinosFueraDelTableroDeberianSerNull() {
        Celda<String>[][][] vecinos = tablero.getVecinosDeCelda(1, 1, 1);
        
        // Esquina superior izquierda frontal debería tener vecinos null
        assertNull(vecinos[0][0][0]);
    }

    @Test
    void vecinosDentroDelTableroNoDeberianSerNull() {
        Celda<String>[][][] vecinos = tablero.getVecinosDeCelda(2, 2, 2);
        
        // Centro debería tener todos los vecinos
        assertNotNull(vecinos[1][1][1]); // La celda misma
        assertNotNull(vecinos[0][1][1]); // Vecino a la izquierda
        assertNotNull(vecinos[2][1][1]); // Vecino a la derecha
    }

    // -------------------------------------------------------------------------
    // Dimensiones
    // -------------------------------------------------------------------------

    @Test
    void getAnchoDeberiaRetornarValorCorrecto() {
        assertEquals(3, tablero.getAncho());
    }

    @Test
    void getAltoDeberiaRetornarValorCorrecto() {
        assertEquals(3, tablero.getAlto());
    }

    @Test
    void getProfundoDeberiaRetornarValorCorrecto() {
        assertEquals(3, tablero.getProfundo());
    }

    @Test
    void getCantidadDeCeldasDeberiaCalcularCorrectamente() {
        assertEquals(27, tablero.getCantidadDeCeldas());
        
        Tablero<String> tableroGrande = new Tablero<>(10, 5, 2);
        assertEquals(100, tableroGrande.getCantidadDeCeldas());
    }

    @Test
    void getCantidadDeCeldasLibresDeberiaContarCorrectamente() {
        assertEquals(27, tablero.getCantidadDeCeldasLibres());
        
        tablero.getCelda(1, 1, 1).setContenido("ocupado");
        assertEquals(26, tablero.getCantidadDeCeldasLibres());
        
        tablero.getCelda(2, 2, 2).setContenido("ocupado");
        assertEquals(25, tablero.getCantidadDeCeldasLibres());
    }

    // -------------------------------------------------------------------------
    // Métodos especiales (equals, hashCode, toString)
    // -------------------------------------------------------------------------

    @Test
    void equalsDeberiaSerVerdaderoParaTablerosIguales() {
        Tablero<String> otroTablero = new Tablero<>(3, 3, 3);
        
        assertEquals(tablero, otroTablero);
    }

    @Test
    void equalsDeberiaSerFalsoParaTablerosConDimensionesDistintas() {
        Tablero<String> otroTablero = new Tablero<>(4, 3, 3);
        
        assertNotEquals(tablero, otroTablero);
    }

    @Test
    void equalsDeberiaSerFalsoParaTablerosConContenidoDistinto() {
        Tablero<String> otroTablero = new Tablero<>(3, 3, 3);
        tablero.getCelda(1, 1, 1).setContenido("diferente");
        
        assertNotEquals(tablero, otroTablero);
    }

    @Test
    void equalsDeberiaSerFalsoParaNull() {
        assertNotEquals(null, tablero);
    }

    @Test
    void equalsDeberiaSerVerdaderoParaMismaInstancia() {
        assertEquals(tablero, tablero);
    }

    @Test
    void hashCodeDeberiaSerConsistenteConEquals() {
        Tablero<String> otroTablero = new Tablero<>(3, 3, 3);
        
        assertEquals(tablero.hashCode(), otroTablero.hashCode());
    }

    @Test
    void toStringDeberiaContenerDimensiones() {
        String texto = tablero.toString();
        
        assertTrue(texto.contains("3"));
        assertTrue(texto.contains("Tablero"));
    }
}
