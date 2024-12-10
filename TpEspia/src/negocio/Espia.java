package negocio;

public class Espia {

	private String nombre;
	private double latitud;
	private double longitud;

	public Espia(String nombre, double latitud, double longitud) {
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String darNombre() {
		return nombre;
	}

	public double darLatitud() {
		return latitud;
	}

	public double darLongitud() {
		return longitud;
	}

}
