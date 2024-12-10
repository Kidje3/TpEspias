package negocio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class AGM {

	private static String primerEspia;
	private static Set<String> espiasMarcados;
	private static LinkedList<String> listaDeEspias;
	private static Map<String, Map<String, Double>> mapaDeConexiones;
	private static RedEspias redEspiasAGM;

	public static RedEspias generarAGM(RedEspias redEspias) {

		verificarRedEspias(redEspias);
		inicializar(redEspias);

		while (espiasMarcados.size() < listaDeEspias.size()) {

			Double probabilidadMinima = Double.MAX_VALUE;
			String espiaOrigenMin = "";
			String espiaDestinoMin = "";

			for (String espiaOrigen : espiasMarcados) {
				Map<String, Double> conexiones = mapaDeConexiones.get(espiaOrigen);

				for (String posibleEspiaDestino : conexiones.keySet()) {
					Double probabilidadActual = conexiones.get(posibleEspiaDestino);

					if (!espiasMarcados.contains(posibleEspiaDestino) && probabilidadActual < probabilidadMinima) {
						probabilidadMinima = probabilidadActual;
						espiaOrigenMin = espiaOrigen;
						espiaDestinoMin = posibleEspiaDestino;
					}
				}
			}

			if (!espiaDestinoMin.isEmpty()) {
				redEspiasAGM.agregarEncuentro(espiaOrigenMin, espiaDestinoMin, probabilidadMinima);
				espiasMarcados.add(espiaDestinoMin);
			}
		}

		return redEspiasAGM;
	}

	private static void inicializar(RedEspias redEspias) {

		redEspiasAGM = new RedEspias();

		for (String nombreEspia : redEspias.setDeEspias()) {
			redEspiasAGM.agregarEspia(nombreEspia, redEspias.verEspia(nombreEspia));
		}

		listaDeEspias = new LinkedList<>();
		mapaDeConexiones = redEspias.verMapaDeConexiones();

		for (String nombreEspia : redEspias.setDeEspias()) {
			listaDeEspias.add(nombreEspia);
		}

		primerEspia = listaDeEspias.getFirst();
		espiasMarcados = new HashSet<>();
		espiasMarcados.add(primerEspia);
	}

	private static void verificarRedEspias(RedEspias redEspias) {
		if (!BFS.esConexo(redEspias)) {
			throw new IllegalArgumentException("El grafo no es conexo. No se puede construir un AGM.");
		}
		if (redEspias == null || redEspias.tamano() == 0) {
			throw new IllegalArgumentException("La red de espías no puede ser nula o vacía.");
		}
	}
}
