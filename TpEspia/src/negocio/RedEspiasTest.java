package negocio;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class RedEspiasTest {

    private RedEspias redEspias;

    @Before
    public void iniciar() {
        redEspias = new RedEspias();
        redEspias.agregarEspia("Espia1", new Espia("Espia1", 1.0, 1.0));
        redEspias.agregarEspia("Espia2", new Espia("Espia2", 2.0, 2.0));
        redEspias.agregarEspia("Espia3", new Espia("Espia3", 3.0, 3.0));
    }

    @Test
    public void testAgregarEspia() {
        assertTrue(redEspias.estaEspia("Espia1"));
        assertTrue(redEspias.estaEspia("Espia2"));
        assertTrue(redEspias.estaEspia("Espia3"));
    }

    @Test
    public void testAgregarEncuentro() {
        redEspias.agregarEncuentro("Espia1", "Espia2", 0.5);
        assertTrue(redEspias.existeArista("Espia1", "Espia2"));
        assertEquals(0.5, redEspias.verProbabilidad("Espia1", "Espia2"), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarEncuentroProbabilidadInvalida() {
        redEspias.agregarEncuentro("Espia1", "Espia2", 1.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarEncuentroEspiaNoExistente() {
        redEspias.agregarEncuentro("Espia1", "EspiaNoExiste", 0.5);
    }

    @Test
    public void testVerConexionesDe() {
        redEspias.agregarEncuentro("Espia1", "Espia2", 0.5);
        redEspias.agregarEncuentro("Espia1", "Espia3", 0.7);

        Map<String, Double> conexiones = redEspias.verConexionesDe("Espia1");
        assertEquals(2, conexiones.size());
        assertTrue(conexiones.containsKey("Espia2"));
        assertTrue(conexiones.containsKey("Espia3"));
    }

    @Test
    public void testCantidadDeConexiones() {
        redEspias.agregarEncuentro("Espia1", "Espia2", 0.5);
        redEspias.agregarEncuentro("Espia1", "Espia3", 0.7);

        assertEquals(2, redEspias.cantidadDeConexiones());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerProbabilidadSinEncuentro() {
        redEspias.verProbabilidad("Espia1", "Espia2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarLoop() {
        redEspias.agregarEncuentro("Espia1", "Espia1", 0.5); 
    }

    @Test
    public void testListaEspias() {
        Set<String> espias = redEspias.setDeEspias();
        assertEquals(3, espias.size());
        assertTrue(espias.contains("Espia1"));
        assertTrue(espias.contains("Espia2"));
        assertTrue(espias.contains("Espia3"));
    }

    @Test
    public void testTamano() {
        assertEquals(3, redEspias.tamano());
    }
}