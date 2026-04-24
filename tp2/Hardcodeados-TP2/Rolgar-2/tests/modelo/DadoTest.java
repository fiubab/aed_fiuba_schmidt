package tests.modelo;

import src.modelo.Dado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DadoTest {

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    @Test
    void dadoPorDefectoDeberiaSerDe6Caras() {
        Dado dado = new Dado();
        
        assertEquals(6, dado.getCaras());
    }

    @Test
    void dadoConCarasPersonalizadasDeberiaCrearseCorrectamente() {
        Dado dado = new Dado(20);
        
        assertEquals(20, dado.getCaras());
    }

    // -------------------------------------------------------------------------
    // Tirar dado
    // -------------------------------------------------------------------------

    @Test
    void tirarDeberiaRetornarValorDentroDelRango() {
        Dado dado = new Dado(6);
        
        for (int i = 0; i < 100; i++) {
            int resultado = dado.tirar();
            assertTrue(resultado >= 1 && resultado <= 6);
        }
    }

    @Test
    void tirarDadoDe20DeberiaRetornarValorEntre1Y20() {
        Dado dado = new Dado(20);
        
        for (int i = 0; i < 50; i++) {
            int resultado = dado.tirar();
            assertTrue(resultado >= 1 && resultado <= 20);
        }
    }

    @Test
    void tirarDadoDe1CaraDeberiaRetornarSiempre1() {
        Dado dado = new Dado(1);
        
        for (int i = 0; i < 10; i++) {
            assertEquals(1, dado.tirar());
        }
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    @Test
    void getCarasDeberiaRetornarCantidadCorrecta() {
        Dado dado = new Dado(12);
        
        assertEquals(12, dado.getCaras());
    }

    // -------------------------------------------------------------------------
    // Métodos especiales (equals, hashCode, toString)
    // -------------------------------------------------------------------------

    @Test
    void equalsDeberiaSerVerdaderoParaDadosConMismasCaras() {
        Dado dado1 = new Dado(6);
        Dado dado2 = new Dado(6);
        
        assertEquals(dado1, dado2);
    }

    @Test
    void equalsDeberiaSerFalsoParaDadosConCarasDistintas() {
        Dado dado1 = new Dado(6);
        Dado dado2 = new Dado(20);
        
        assertNotEquals(dado1, dado2);
    }

    @Test
    void equalsDeberiaSerFalsoParaNull() {
        Dado dado = new Dado(6);
        
        assertNotEquals(null, dado);
    }

    @Test
    void equalsDeberiaSerVerdaderoParaMismaInstancia() {
        Dado dado = new Dado(6);
        
        assertEquals(dado, dado);
    }

    @Test
    void hashCodeDeberiaSerConsistenteConEquals() {
        Dado dado1 = new Dado(6);
        Dado dado2 = new Dado(6);
        
        assertEquals(dado1.hashCode(), dado2.hashCode());
    }

    @Test
    void hashCodeDeberiaSerDistintoParaDadosDiferentes() {
        Dado dado1 = new Dado(6);
        Dado dado2 = new Dado(20);
        
        assertNotEquals(dado1.hashCode(), dado2.hashCode());
    }

    @Test
    void toStringDeberiaContenerCaras() {
        Dado dado = new Dado(6);
        String texto = dado.toString();
        
        assertTrue(texto.contains("6"));
        assertTrue(texto.contains("Dado"));
        assertTrue(texto.contains("caras"));
    }
}
