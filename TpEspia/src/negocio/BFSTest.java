package negocio;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BFSTest {

	private RedEspias redEspiasConexa;
	private RedEspias redEspiasNoConexa;
	private RedEspias redEspiasVacia;

	@Before
	public void iniciar() {

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
	}

	@Test
	public void testEsConexoRedConexa() {

		assertTrue(BFS.esConexo(redEspiasConexa));
	}

	@Test
	public void testEsConexoRedNoConexa() {

		assertFalse(BFS.esConexo(redEspiasNoConexa));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRedNula() {

		BFS.esConexo(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRedVacia() {

		BFS.esConexo(redEspiasVacia);
	}
}