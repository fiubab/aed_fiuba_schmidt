package tests.modelo;

import src.modelo.Celda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CeldaTest {

    private Celda<String> celda;
    private int[] posicion;

    @BeforeEach
    void setUp() {
        posicion = new int[]{1, 2, 3};
        celda = new Celda<>("contenido", posicion);
    }

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    @Test
    void constructorConContenidoDeberiaCrearCeldaCorrectamente() {
        Celda<String> nuevaCelda = new Celda<>("test", new int[]{0, 0, 0});
        
        assertEquals("test", nuevaCelda.getContenido());
        assertArrayEquals(new int[]{0, 0, 0}, nuevaCelda.getPosicion());
        assertFalse(nuevaCelda.estaLibre());
        assertTrue(nuevaCelda.estaOcupado());
    }

    @Test
    void constructorSinContenidoDeberiaCrearCeldaVacia() {
        Celda<String> celdaVacia = new Celda<>(new int[]{1, 1, 1});
        
        assertNull(celdaVacia.getContenido());
        assertArrayEquals(new int[]{1, 1, 1}, celdaVacia.getPosicion());
        assertTrue(celdaVacia.estaLibre());
        assertFalse(celdaVacia.estaOcupado());
    }

    // -------------------------------------------------------------------------
    // Estado de la celda
    // -------------------------------------------------------------------------

    @Test
    void celdaConContenidoDeberiaEstarOcupada() {
        assertFalse(celda.estaLibre());
        assertTrue(celda.estaOcupado());
    }

    @Test
    void celdaVaciaDeberiaEstarLibre() {
        Celda<String> celdaVacia = new Celda<>(new int[]{0, 0, 0});
        
        assertTrue(celdaVacia.estaLibre());
        assertFalse(celdaVacia.estaOcupado());
    }

    // -------------------------------------------------------------------------
    // Modificar contenido
    // -------------------------------------------------------------------------

    @Test
    void setContenidoDeberiaModificarContenido() {
        celda.setContenido("nuevo contenido");
        
        assertEquals("nuevo contenido", celda.getContenido());
        assertFalse(celda.estaLibre());
    }

    @Test
    void setContenidoNullDeberiaDejarCeldaLibre() {
        celda.setContenido(null);
        
        assertNull(celda.getContenido());
        assertTrue(celda.estaLibre());
    }

    // -------------------------------------------------------------------------
    // Vecinos
    // -------------------------------------------------------------------------

    @Test
    void setVecinosDeberiaEstablecerVecinos() {
        @SuppressWarnings("unchecked")
        Celda<String>[][][] vecinos = new Celda[3][3][3];
        vecinos[1][1][1] = new Celda<>("vecino", new int[]{2, 2, 2});
        
        celda.setVecinos(vecinos);
        
        assertNotNull(celda.getVecinos());
        assertEquals("vecino", celda.getVecinos()[1][1][1].getContenido());
    }

    @Test
    void getVecinosDeberiaRetornarMatriz3x3x3() {
        @SuppressWarnings("unchecked")
        Celda<String>[][][] vecinos = new Celda[3][3][3];
        celda.setVecinos(vecinos);
        
        Celda<String>[][][] vecinosObtenidos = celda.getVecinos();
        
        assertNotNull(vecinosObtenidos);
        assertEquals(3, vecinosObtenidos.length);
        assertEquals(3, vecinosObtenidos[0].length);
        assertEquals(3, vecinosObtenidos[0][0].length);
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    @Test
    void getContenidoDeberiaRetornarContenidoCorrecto() {
        assertEquals("contenido", celda.getContenido());
    }

    @Test
    void getPosicionDeberiaRetornarPosicionCorrecta() {
        assertArrayEquals(posicion, celda.getPosicion());
    }

    // -------------------------------------------------------------------------
    // Métodos especiales (equals, hashCode, toString)
    // -------------------------------------------------------------------------

    @Test
    void equalsDeberiaSerVerdaderoParaCeldasConMismoContenido() {
        Celda<String> otraCelda = new Celda<>("contenido", new int[]{5, 5, 5});
        
        assertEquals(celda, otraCelda);
    }

    @Test
    void equalsDeberiaSerFalsoParaCeldasConDistintoContenido() {
        Celda<String> otraCelda = new Celda<>("otro contenido", new int[]{1, 2, 3});
        
        assertNotEquals(celda, otraCelda);
    }

    @Test
    void equalsDeberiaSerFalsoParaNull() {
        assertNotEquals(null, celda);
    }

    @Test
    void equalsDeberiaSerFalsoParaObjetoDeOtraClase() {
        assertNotEquals("string", celda);
    }

    @Test
    void equalsDeberiaSerVerdaderoParaMismaInstancia() {
        assertEquals(celda, celda);
    }

    @Test
    void hashCodeDeberiaSerConsistenteConEquals() {
        Celda<String> otraCelda = new Celda<>("contenido", new int[]{5, 5, 5});
        
        assertEquals(celda.hashCode(), otraCelda.hashCode());
    }

    @Test
    void toStringDeberiaContenerContenido() {
        String texto = celda.toString();
        
        assertTrue(texto.contains("contenido"));
        assertTrue(texto.contains("Celda"));
    }
}
