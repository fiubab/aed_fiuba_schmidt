package tests.modelo;

import src.modelo.Inventario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    private Inventario<String> inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario<>(3);
    }

    // -------------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------------

    @Test
    void constructorDeberiaCrearInventarioConCapacidadCorrecta() {
        Inventario<String> nuevoInventario = new Inventario<>(5);
        
        assertEquals(5, nuevoInventario.getCapacidad());
        assertEquals(0, nuevoInventario.getCantidad());
        assertTrue(nuevoInventario.estaVacio());
    }

    // -------------------------------------------------------------------------
    // Agregar elementos
    // -------------------------------------------------------------------------

    @Test
    void agregarElementoDeberiaIncrementarCantidad() {
        inventario.agregar("Manzana");
        
        assertEquals(1, inventario.getCantidad());
        assertFalse(inventario.estaVacio());
    }

    @Test
    void agregarVariosElementosDeberiaAlmacenarEnOrden() {
        inventario.agregar("A");
        inventario.agregar("B");
        inventario.agregar("C");

        assertEquals("A", inventario.obtener(1));
        assertEquals("B", inventario.obtener(2));
        assertEquals("C", inventario.obtener(3));
    }

    @Test
    void agregarEnInventarioLlenoDeberiaLanzarExcepcion() {
        inventario.agregar("A");
        inventario.agregar("B");
        inventario.agregar("C");

        assertThrows(RuntimeException.class, () -> inventario.agregar("D"));
    }

    // -------------------------------------------------------------------------
    // Obtener elementos
    // -------------------------------------------------------------------------

    @Test
    void obtenerElementoValidoDeberiaRetornarCorrecto() {
        inventario.agregar("Uva");
        
        assertEquals("Uva", inventario.obtener(1));
    }

    @Test
    void obtenerElementoEnPosicionMediaDeberiaFuncionar() {
        inventario.agregar("Primero");
        inventario.agregar("Segundo");
        inventario.agregar("Tercero");
        
        assertEquals("Segundo", inventario.obtener(2));
    }

    // -------------------------------------------------------------------------
    // Remover elementos
    // -------------------------------------------------------------------------

    @Test
    void removerElementoDeberiaReducirCantidad() {
        inventario.agregar("Queso");
        inventario.remover(1);

        assertEquals(0, inventario.getCantidad());
        assertTrue(inventario.estaVacio());
    }

    @Test
    void removerElementoDeberiaDejarPosicionVacia() {
        inventario.agregar("Elemento");
        inventario.remover(1);
        
        assertTrue(inventario.posicionVacia(1));
    }

    @Test
    void removerDeInventarioVacioDeberiaLanzarExcepcion() {
        assertThrows(RuntimeException.class, () -> inventario.remover(1));
    }

    // -------------------------------------------------------------------------
    // Estado del inventario
    // -------------------------------------------------------------------------

    @Test
    void inventarioVacioDeberiaEstarVacio() {
        assertTrue(inventario.estaVacio());
        assertFalse(inventario.estaLleno());
    }

    @Test
    void inventarioLlenoDeberiaEstarLleno() {
        inventario.agregar("1");
        inventario.agregar("2");
        inventario.agregar("3");

        assertTrue(inventario.estaLleno());
        assertFalse(inventario.estaVacio());
    }

    @Test
    void inventarioParcialmenteOcupadoNoDeberiaEstarVacioNiLleno() {
        inventario.agregar("Elemento");
        
        assertFalse(inventario.estaVacio());
        assertFalse(inventario.estaLleno());
    }

    // -------------------------------------------------------------------------
    // Método contiene
    // -------------------------------------------------------------------------

    @Test
    void contieneDeberiaRetornarTrueSiExisteElemento() {
        inventario.agregar("Pan");
        
        assertTrue(inventario.contiene("Pan"));
    }

    @Test
    void contieneDeberiaRetornarFalseSiNoExisteElemento() {
        inventario.agregar("Agua");
        
        assertFalse(inventario.contiene("Leche"));
    }

    @Test
    void contieneDeberiaRetornarFalseCuandoInventarioEstaVacio() {
        assertFalse(inventario.contiene("Nada"));
    }

    @Test
    void contieneDeberiaIgnorarPosicionesVacias() {
        inventario.agregar("A");
        inventario.agregar("B");
        inventario.remover(1);
        
        assertFalse(inventario.contiene("A"));
        assertTrue(inventario.contiene("B"));
    }

    // -------------------------------------------------------------------------
    // Posición vacía
    // -------------------------------------------------------------------------

    @Test
    void posicionVaciaDeberiaRetornarTrueParaPosicionSinElemento() {
        assertTrue(inventario.posicionVacia(1));
    }

    @Test
    void posicionVaciaDeberiaRetornarFalseParaPosicionConElemento() {
        inventario.agregar("Elemento");
        
        assertFalse(inventario.posicionVacia(1));
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    @Test
    void getCapacidadDeberiaRetornarValorCorrecto() {
        assertEquals(3, inventario.getCapacidad());
    }

    @Test
    void getCantidadDeberiaRetornarCantidadCorrecta() {
        assertEquals(0, inventario.getCantidad());
        
        inventario.agregar("Cosa");
        assertEquals(1, inventario.getCantidad());
        
        inventario.agregar("Otra");
        assertEquals(2, inventario.getCantidad());
    }

    // -------------------------------------------------------------------------
    // Métodos especiales (equals, hashCode, toString)
    // -------------------------------------------------------------------------

    @Test
    void equalsDeberiaSerVerdaderoParaInventariosIguales() {
        Inventario<String> otro = new Inventario<>(3);
        inventario.agregar("A");
        otro.agregar("A");

        assertEquals(inventario, otro);
    }

    @Test
    void equalsDeberiaSerFalsoParaInventariosConElementosDistintos() {
        Inventario<String> otro = new Inventario<>(3);
        inventario.agregar("A");
        otro.agregar("B");

        assertNotEquals(inventario, otro);
    }

    @Test
    void equalsDeberiaSerFalsoParaInventariosConCapacidadDistinta() {
        Inventario<String> otro = new Inventario<>(5);

        assertNotEquals(inventario, otro);
    }

    @Test
    void equalsDeberiaSerFalsoParaNull() {
        assertNotEquals(null, inventario);
    }

    @Test
    void equalsDeberiaSerVerdaderoParaMismaInstancia() {
        assertEquals(inventario, inventario);
    }

    @Test
    void hashCodeDeberiaSerConsistenteConEquals() {
        Inventario<String> otro = new Inventario<>(3);
        inventario.agregar("A");
        otro.agregar("A");

        assertEquals(inventario.hashCode(), otro.hashCode());
    }

    @Test
    void toStringDeberiaContenerCapacidadYCantidad() {
        inventario.agregar("Dato");
        String texto = inventario.toString();

        assertTrue(texto.contains("capacidad"));
        assertTrue(texto.contains("elementos"));
        assertTrue(texto.contains("3"));
        assertTrue(texto.contains("1"));
    }
}
