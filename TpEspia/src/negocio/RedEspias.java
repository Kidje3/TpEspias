package negocio;

import java.util.*;

public class RedEspias {

	private Map<String, Espia> espias;
	private Map<String, Map<String, Double>> mapaDeConexiones;

	public RedEspias() {
		this.espias = new HashMap<String, Espia>();
		this.mapaDeConexiones = new HashMap<String, Map<String, Double>>();
	}

	public void agregarEspia(String nombre, Espia espia) {
		espias.put(nombre, espia);
		mapaDeConexiones.put(nombre, new HashMap<String, Double>());
	}

	public void agregarEncuentro(String esp1, String esp2, double prob) {
		verificarProbabilidad(prob);
		verificarEspia(esp1);
		verificarEspia(esp2);
		verificarLoop(esp1, esp2);

		mapaDeConexiones.get(esp1).put(esp2, prob);
		mapaDeConexiones.get(esp2).put(esp1, prob);

	}

	public Map<String, Map<String, Double>> verMapaDeConexiones() {
		return getMapaDeConexiones();
	}

	private Map<String, Map<String, Double>> getMapaDeConexiones() {
		return this.mapaDeConexiones;
	}

	public Map<String, Double> verConexionesDe(String nombreEspia) {
		verificarEspia(nombreEspia);
		return mapaDeConexiones.get(nombreEspia);
	}

	public Double verProbabilidad(String esp1, String esp2) {
		verificarEspia(esp1);
		verificarEspia(esp2);

		if (mapaDeConexiones.get(esp1).containsKey(esp2)) {
			return mapaDeConexiones.get(esp1).get(esp2);
		} else {
			throw new IllegalArgumentException("No existe encuentro entre " + esp1 + " y " + esp2);
		}
	}

	public Set<String> setDeEspias() {

		return this.espias.keySet();
	}

	public Espia verEspia(String nombreEspia) {
		verificarEspia(nombreEspia);
		return espias.get(nombreEspia);
	}

	public boolean estaEspia(String nombreEspia) {

		return this.espias.containsKey(nombreEspia);
	}
	public int cantidadDeConexiones() {
		int contador = 0;
		for(String espia : this.mapaDeConexiones.keySet()) {
			contador += this.mapaDeConexiones.get(espia).keySet().size();
		}
		return contador/2;
	}

	public int tamano() {
		return this.espias.size();
	}

	public boolean existeArista(String esp1, String esp2) {
		verificarEspia(esp1);
		verificarEspia(esp2);

		return this.mapaDeConexiones.get(esp1).containsKey(esp2);
	}

	private void verificarEspia(String nombreEspia) {
		if (!this.espias.containsKey(nombreEspia)) {
			throw new IllegalArgumentException("El espia " + nombreEspia + " no existe.");
		}
	}

	private void verificarProbabilidad(double prob) {
		if (prob < 0 || prob > 1) {
			throw new IllegalArgumentException("El peso debe ser mayor a 0 y menor o igual a 1");
		}
	}

	private void verificarLoop(String esp1, String esp2) {
		if (esp1.equals(esp2)) {
			throw new IllegalArgumentException("no se permiten loops");
		}

	}

}
