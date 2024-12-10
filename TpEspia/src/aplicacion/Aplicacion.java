package aplicacion;

import cliente.MapaDeEspias;
import cliente.MenuEspias;
import negocio.RedEspias;

public class Aplicacion {

	MapaDeEspias miMapaDeEspias;
	MenuEspias miMenuEspias;
	
	RedEspias miRedEspias;

	public Aplicacion() {
		preparar();
		mostrarMenu();

	}

	private void mostrarMenu() {
		miMenuEspias.frame.setVisible(true);
	}

	private void preparar() {
		
		this.miRedEspias = new RedEspias();
		this.miMapaDeEspias = new MapaDeEspias(this.miRedEspias);
		this.miMenuEspias = new MenuEspias(this.miMapaDeEspias,  this.miRedEspias);

	}

}
