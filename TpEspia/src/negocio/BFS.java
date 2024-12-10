package negocio;

import java.util.*;

public class BFS {
	private static LinkedList<String> colaBfs;
	private static Set<String> espiasMarcados;
	private static String primerEspia;

	public static boolean esConexo(RedEspias redEspias) {

		verificarRedEspiasNula(redEspias);
		inicializar(redEspias);
		alcanzables(redEspias);

		return espiasMarcados.size() == redEspias.tamano();
	}

	public static Set<String> alcanzables(RedEspias redEspias) {
		Set<String> vecinosDeEspia;
		Set<String> espiasAlcanzados = new HashSet<String>();
		String nombreEspia;

		while (!colaBfs.isEmpty()) {

			nombreEspia = colaBfs.removeFirst();
			espiasAlcanzados.add(nombreEspia);
			espiasMarcados.add(nombreEspia);
			vecinosDeEspia = redEspias.verConexionesDe(nombreEspia).keySet();

			for (String nombreEspiaVecino : vecinosDeEspia) {

				if (!espiasAlcanzados.contains(nombreEspiaVecino) && !colaBfs.contains(nombreEspiaVecino)) {
					colaBfs.addLast(nombreEspiaVecino);
				}
			}
		}
		return espiasAlcanzados;
	}

	private static void verificarRedEspiasNula(RedEspias redEspias) {
		if (redEspias == null)
			throw new IllegalArgumentException("La red de espías no puede ser nula");
	}

	private static void inicializar(RedEspias redEspias) {

		primerEspia = redEspias.setDeEspias().stream().findFirst().orElse(null);
		if (primerEspia == null) {
			throw new IllegalArgumentException("La red de espías está vacía");
		}
		colaBfs = new LinkedList<String>();
		colaBfs.add(primerEspia);
		espiasMarcados = new HashSet<String>();
	}

}
