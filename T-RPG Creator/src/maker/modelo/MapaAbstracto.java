package maker.modelo;

import java.awt.Graphics2D;
import java.awt.Point;

import maker.recursos.SistemaCarga;
import maker.vista.editor.PanelMapa.Selección;

public abstract class MapaAbstracto {

	protected static int id = 0;
	protected int identificador;
	protected String nombre;

	public MapaAbstracto() {
		super();
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		MapaAbstracto.id = id;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public abstract void dibujar(Graphics2D g2, Point seleccionada, SistemaCarga carga, Selección sel);

}