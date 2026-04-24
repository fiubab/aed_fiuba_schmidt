package tests.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.utils.Validaciones;

class ValidacionesTest {

    // validarMayorQueCero
    @Test
    void testValidarMayorQueCeroValido() {
        assertDoesNotThrow(() -> Validaciones.validarMayorQueCero(5, "edad"));
    }

    @Test
    void testValidarMayorQueCeroInvalido() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validaciones.validarMayorQueCero(0, "edad");
        });
        assertEquals("El valor edad debe ser mayor a 0", exception.getMessage());
    }

    // validarMayorIgual
    @Test
    void testValidarMayorIgualValido() {
        assertDoesNotThrow(() -> Validaciones.validarMayorIgual(10, 5, "edad", "mínimo"));
    }

    @Test
    void testValidarMayorIgualInvalido() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validaciones.validarMayorIgual(3, 5, "edad", "mínimo");
        });
        assertEquals("El valor edad debe ser mayor o igual que mínimo", exception.getMessage());
    }

    // validarDistintoDeNull
    @Test
    void testValidarDistintoDeNullValido() {
        assertDoesNotThrow(() -> Validaciones.validarDistintoDeNull("valor", "nombre"));
    }

    @Test
    void testValidarDistintoDeNullInvalido() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validaciones.validarDistintoDeNull(null, "nombre");
        });
        assertEquals("El valor nombre no puede ser null", exception.getMessage());
    }

    // validarCaracteresAlfabeticos
    @Test
    void testValidarCaracteresAlfabeticosValido() {
        assertDoesNotThrow(() -> Validaciones.validarCaracteresAlfabeticos("Juan Pérez", "nombre"));
    }

    @Test
    void testValidarCaracteresAlfabeticosInvalido() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validaciones.validarCaracteresAlfabeticos("Juan123", "nombre");
        });
        assertEquals("El nombre debe tener solo letras y espacios. Se ingreso Juan123", exception.getMessage());
    }

    @Test
    void testValidarFalse() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validaciones.validarFalse(true, "valor");
        });
        assertEquals("El valor debe ser false", exception.getMessage());
    }

    @Test
    void testValidarTrue() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Validaciones.validarTrue(false, "valor");
        });
        assertEquals("El valor debe ser true", exception.getMessage());
    }
}
