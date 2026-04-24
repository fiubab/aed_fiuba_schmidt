package tests.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.utils.Teclado;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TecladoTest {

    @BeforeEach
    void setUp() {
        Teclado.inicializar();
    }

    @AfterEach
    void tearDown() {
        if (Teclado.teclado != null) {
            Teclado.finalizar();
        }
    }

    @Test
    void testInicializar() {
        assertNotNull(Teclado.teclado, "El scanner debería estar inicializado");
        assertInstanceOf(Scanner.class, Teclado.teclado, "Debe ser un objeto Scanner");
    }

    @Test
    void testFinalizar() {
        Teclado.finalizar();
        assertThrows(IllegalStateException.class, () -> {
            Teclado.teclado.nextLine(); // Intentar usar el scanner cerrado lanza excepción
        }, "El scanner debería estar cerrado y lanzar IllegalStateException");
    }
}
