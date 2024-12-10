package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import negocio.Espia;
import negocio.RedEspias;
import javax.swing.JTextField;

public class MenuEspias {

	public JFrame frame;
	private JComboBox<String> comboEspias1;
	private JComboBox<String> comboEspias2;
	private JComboBox<Double> probabilidades;
	private MapaDeEspias mapa;
	private RedEspias redEspias;
	private JTextField textFieldNombreEspiaNuevo;
	private JTextField textFieldLatitudEspiaNuevo;
	private JTextField textFieldLongitudEspiaNuevo;

	public MenuEspias(MapaDeEspias map, RedEspias rEspias) {
		this.mapa = map;

		this.redEspias = rEspias;
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(500, 250, 633, 380);
		frame.setTitle("Seleccion de Espias");
		frame.getContentPane().setLayout(null);

		comboEspias1 = new JComboBox<>();
		comboEspias1.setBounds(57, 183, 122, 31);
		frame.getContentPane().add(comboEspias1);

		probabilidades = new JComboBox<>();
		probabilidades.setBounds(254, 183, 90, 31);
		frame.getContentPane().add(probabilidades);

		comboEspias2 = new JComboBox<>();
		comboEspias2.setBounds(410, 183, 122, 31);
		frame.getContentPane().add(comboEspias2);

		JLabel lblAgregarPosiblesEncuentros = new JLabel("Crear Red Espias:");
		lblAgregarPosiblesEncuentros.setBounds(10, 123, 122, 31);
		frame.getContentPane().add(lblAgregarPosiblesEncuentros);

		JLabel lblEspia = new JLabel("Espia 1");
		lblEspia.setBounds(100, 146, 46, 31);
		frame.getContentPane().add(lblEspia);

		JLabel lblProbabilidad = new JLabel("Probabilidad de intercepción");
		lblProbabilidad.setBounds(227, 151, 166, 21);
		frame.getContentPane().add(lblProbabilidad);

		JLabel lblEspia_1 = new JLabel("Espia 2");
		lblEspia_1.setBounds(448, 141, 46, 40);
		frame.getContentPane().add(lblEspia_1);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(254, 225, 90, 21);
		frame.getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarEncuentro();

			}

		});

		JButton btnMostrarMapa = new JButton("Mostrar Mapa");
		btnMostrarMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mapa.generarRedDeEspionaje();
				mapa.frame.setVisible(true);
				frame.setVisible(false);

			}
		});
		btnMostrarMapa.setBounds(227, 288, 145, 21);
		frame.getContentPane().add(btnMostrarMapa);

		JSeparator separator = new JSeparator();
		separator.setBounds(-47, 257, 702, 12);
		frame.getContentPane().add(separator);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		setComboProbabilidades(probabilidades);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-47, 123, 702, 12);
		frame.getContentPane().add(separator_1);

		JLabel lblCrearEspia = new JLabel("Crear  Espia:");
		lblCrearEspia.setBounds(10, 0, 90, 31);
		frame.getContentPane().add(lblCrearEspia);

		JLabel lblNombreEspiaNuevo = new JLabel("Nombre:");
		lblNombreEspiaNuevo.setBounds(39, 42, 122, 21);
		frame.getContentPane().add(lblNombreEspiaNuevo);

		JLabel lblLatitudEspiaNuevo = new JLabel("Latidud:");
		lblLatitudEspiaNuevo.setBounds(222, 42, 122, 21);
		frame.getContentPane().add(lblLatitudEspiaNuevo);

		JLabel lblLongitudEspiaNuevo = new JLabel("Longitud:");
		lblLongitudEspiaNuevo.setBounds(410, 42, 122, 21);
		frame.getContentPane().add(lblLongitudEspiaNuevo);

		textFieldNombreEspiaNuevo = new JTextField();
		textFieldNombreEspiaNuevo.setBounds(39, 67, 122, 20);
		frame.getContentPane().add(textFieldNombreEspiaNuevo);
		textFieldNombreEspiaNuevo.setColumns(10);

		textFieldLatitudEspiaNuevo = new JTextField();
		textFieldLatitudEspiaNuevo.setColumns(10);
		textFieldLatitudEspiaNuevo.setBounds(222, 67, 122, 20);
		frame.getContentPane().add(textFieldLatitudEspiaNuevo);

		textFieldLongitudEspiaNuevo = new JTextField();
		textFieldLongitudEspiaNuevo.setColumns(10);
		textFieldLongitudEspiaNuevo.setBounds(410, 67, 122, 20);
		frame.getContentPane().add(textFieldLongitudEspiaNuevo);

		JButton btnCrearEspia = new JButton("Crear");
		btnCrearEspia.setBounds(254, 98, 90, 21);
		frame.getContentPane().add(btnCrearEspia);
		btnCrearEspia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearEspia();

			}

		});

	}

	private void setComboProbabilidades(JComboBox<Double> probabilidades) {
		for (int i = 0; i <= 10; i++)
			probabilidades.addItem((double) i / 10);
	}

	private void setComboEspias(String nombreEspia) {

		comboEspias1.addItem(nombreEspia);
		comboEspias2.addItem(nombreEspia);

	}

	private void crearEspia() {
		String nombreEspiaNuevo = (String) this.textFieldNombreEspiaNuevo.getText();
		Double LatitudEspiaNuevo = Double.parseDouble((String) this.textFieldLatitudEspiaNuevo.getText());
		Double LongitudEspiaNuevo = Double.parseDouble((String) this.textFieldLongitudEspiaNuevo.getText());
		Espia nuevoEspia = new Espia(nombreEspiaNuevo, LatitudEspiaNuevo, LongitudEspiaNuevo);
		this.redEspias.agregarEspia(nombreEspiaNuevo, nuevoEspia);
		limpiarTextField();
		setComboEspias(nombreEspiaNuevo);

	}

	private void limpiarTextField() {
		this.textFieldNombreEspiaNuevo.setText("");
		this.textFieldLatitudEspiaNuevo.setText("");
		this.textFieldLongitudEspiaNuevo.setText("");

	}

	private void agregarEncuentro() {

		String espiaSeleccionado1 = (String) this.comboEspias1.getSelectedItem();
		String espiaSeleccionado2 = (String) this.comboEspias2.getSelectedItem();
		int probabilidadSelec = probabilidades.getSelectedIndex();
		double probabilidadValue = probabilidades.getItemAt(probabilidadSelec);

		redEspias.agregarEncuentro(espiaSeleccionado1, espiaSeleccionado2, probabilidadValue);

	}
}