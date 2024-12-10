package cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import negocio.AGM;
import negocio.RedEspias;
import negocio.Espia;

public class MapaDeEspias {

	public JFrame frame;
	private JMapViewer mapa;
	private RedEspias redEspias;
	private RedEspias redEspiasAGM;

	public MapaDeEspias(RedEspias rEspias) {
		this.redEspias = rEspias;
		initialize();
	}
	private void initialize() {
		frame = new JFrame("Espionaje");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(860, 600));

		mapa = new JMapViewer();
		mapa.setBounds(10,20 , 700, 530);
		mapa.setDisplayPosition(new Coordinate(0, 0), 2);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(mapa);

		frame.pack();
		frame.setLocationRelativeTo(null);

		this.crearAGM();
	}
	public void generarRedDeEspionaje() {
		this.marcarEspias();
		this.dibujarRedes();
	}
	private void marcarEspias() {

		for (String nombreEspia : this.redEspias.setDeEspias()) {
			Espia espiaSeleccionado = this.redEspias.verEspia(nombreEspia);
			double latitud = espiaSeleccionado.darLatitud();
			double longitud = espiaSeleccionado.darLongitud();
			Coordinate coordenada = new Coordinate(latitud, longitud);
			MapMarkerDot espiaMarker = new MapMarkerDot(nombreEspia, coordenada);
			espiaMarker.setColor(Color.black);
			espiaMarker.setBackColor(Color.red);
			mapa.addMapMarker(espiaMarker);
		}
	}
	private void dibujarRedes() {
		Set<String> espiasRecorridos = new HashSet<String>();

		for (String nombreEspia1 : this.redEspias.setDeEspias()) {

			LinkedList<Coordinate> arista = new LinkedList<Coordinate>();

			Espia espia1 = this.redEspias.verEspia(nombreEspia1);

			arista.add(new Coordinate(espia1.darLatitud(), espia1.darLongitud()));

			Map<String, Double> vecinos = this.redEspias.verConexionesDe(nombreEspia1);

			for (String nombreEspia2 : vecinos.keySet()) {
				if (!espiasRecorridos.contains(nombreEspia2)) {
					Espia espia2 = this.redEspias.verEspia(nombreEspia2);
					arista.add(new Coordinate(espia2.darLatitud(), espia2.darLongitud()));
					arista.add(new Coordinate(espia1.darLatitud(), espia1.darLongitud()));
					mapa.addMapPolygon(new MapPolygonImpl(arista));
				}
			}
			espiasRecorridos.add(nombreEspia1);
		}
	}
	private void crearAGM() {
		JButton agm = new JButton("Dar AGM");
		agm.setBounds(720, 20, 100, 50);
		frame.getContentPane().add(agm);
		agm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dibujarAGM();
			}
		});
	}

	private void dibujarAGM() {
		this.redEspiasAGM = AGM.generarAGM(this.redEspias);
		mapa.removeAllMapPolygons();
		int y = 100;
		Set<String> espiasRecorridos = new HashSet<String>();

		for (String nombreEspia1 : this.redEspiasAGM.setDeEspias()) {

			LinkedList<Coordinate> arista = new LinkedList<Coordinate>();

			Espia espia1 = this.redEspiasAGM.verEspia(nombreEspia1);

			arista.add(new Coordinate(espia1.darLatitud(), espia1.darLongitud()));

			Map<String, Double> vecinos = this.redEspiasAGM.verConexionesDe(nombreEspia1);

			for (String nombreEspia2 : vecinos.keySet()) {
				if (!espiasRecorridos.contains(nombreEspia2)) {
					Espia espia2 = this.redEspiasAGM.verEspia(nombreEspia2);
					arista.add(new Coordinate(espia2.darLatitud(), espia2.darLongitud()));
					arista.add(new Coordinate(espia1.darLatitud(), espia1.darLongitud()));
					mapa.addMapPolygon(new MapPolygonImpl(arista));

					JLabel espias = new JLabel(
							"<html>" + espia2.darNombre() + " y " + espia1.darNombre() + " <br>probabilidad:  "
									+ redEspiasAGM.verProbabilidad(espia1.darNombre(), espia2.darNombre()) + "</html>");
					frame.getContentPane().add(espias);
					espias.setBounds(720, y, 100, 60);
					y = y + 90;
				}
			}
			espiasRecorridos.add(nombreEspia1);
		}
	}
}
