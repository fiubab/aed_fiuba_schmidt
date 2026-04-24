package tests.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Personaje;
import src.modelo.Turno;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Turno.
 */
public class TurnoTest {
    private Turno<Personaje> turno;
    private List<Personaje> personajes;

    @BeforeEach
    public void setUp() {
        personajes = new ListaSimplementeEnlazada<>();
        personajes.add(new Personaje("PersonajeUno"));
        personajes.add(new Personaje("PersonajeDos"));
        personajes.add(new Personaje("PersonajeTres"));
        turno = new Turno<>(personajes);
    }

    @Test
    public void testCrearTurno() {
        assertNotNull(turno);
        assertEquals(3, turno.getPersonajes().size());
    }

    @Test
    public void testGetPersonajeTurnoActual() {
        Personaje actual = turno.getPersonajeTurnoActual();
        assertNotNull(actual);
        assertEquals("PersonajeUno", actual.getNombre());
    }

    @Test
    public void testSiguienteTurno() {
        Personaje primero = turno.getPersonajeTurnoActual();
        turno.siguienteTurno();
        Personaje segundo = turno.getPersonajeTurnoActual();
        assertNotEquals(primero.getNombre(), segundo.getNombre());
    }

    @Test
    public void testRemoverPersonaje() {
        Personaje personaje = personajes.get(0);
        turno.removerPersonaje(personaje);
        assertEquals(2, turno.getPersonajes().size());
    }
}
