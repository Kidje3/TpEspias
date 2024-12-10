package negocio;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AGMTest {

    private RedEspias redEspiasConexa;
    private RedEspias redEspiasNoConexa;
    private RedEspias redEspiasVacia;
    private RedEspias redEspiaUnico;

    @Before
    public void setUp() {
        redEspiasConexa = new RedEspias();
        redEspiasConexa.agregarEspia("Espia1", new Espia("Espia1", 1.0, 1.0));
        redEspiasConexa.agregarEspia("Espia2", new Espia("Espia2", 2.0, 2.0));
        redEspiasConexa.agregarEspia("Espia3", new Espia("Espia3", 3.0, 3.0));
        redEspiasConexa.agregarEncuentro("Espia1", "Espia2", 0.5);
        redEspiasConexa.agregarEncuentro("Espia2", "Espia3", 0.3);
        redEspiasConexa.agregarEncuentro("Espia1", "Espia3", 0.7);

        redEspiasNoConexa = new RedEspias();
        redEspiasNoConexa.agregarEspia("Espia1", new Espia("Espia1", 1.0, 1.0));
        redEspiasNoConexa.agregarEspia("Espia2", new Espia("Espia2", 2.0, 2.0));
        redEspiasNoConexa.agregarEspia("Espia3", new Espia("Espia3", 3.0, 3.0));
        redEspiasNoConexa.agregarEncuentro("Espia1", "Espia2", 0.4);

        redEspiasVacia = new RedEspias();

        redEspiaUnico = new RedEspias();
        redEspiaUnico.agregarEspia("Espia1", new Espia("Espia1", 1.0, 1.0));
    }

    @Test
    public void testGenerarAGMRedConexa() {

        RedEspias agm = AGM.generarAGM(redEspiasConexa);
        assertEquals(3, agm.tamano());
        assertEquals(2, agm.cantidadDeConexiones());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerarAGMRedNoConexa() {
 
        AGM.generarAGM(redEspiasNoConexa);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerarAGMRedVacia() {

        AGM.generarAGM(redEspiasVacia);
    }

    @Test
    public void testGenerarAGMUnSoloEspia() {

        RedEspias agm = AGM.generarAGM(redEspiaUnico);
         assertEquals(1, agm.tamano());
         assertEquals(0, agm.cantidadDeConexiones());
         
    }
}
