package maker.modelo.mapa_caminos;

import java.awt.Point;
import java.util.LinkedList;



public class Nodo 
{
	private static int id_nodo = 0;
	
	private int id;
	
	private String nombre;
	
	private Point posicion_central;
	
	private Object tipo;
	
	private LinkedList<Tramo> tramos;

	public Nodo(String nombre, Point posicion_central, Object tipo) 
	{
		id = id_nodo++;
		this.nombre = nombre;
		this.posicion_central = posicion_central;
		this.tipo = tipo;
		tramos = new LinkedList<Tramo>();
	}

	public Nodo(Point posicion_central) 
	{
		id = id_nodo++;
		this.posicion_central = posicion_central;
		tramos = new LinkedList<Tramo>();
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Point getPosicion_central() {
		return posicion_central;
	}

	public void setPosicion_central(Point posicion_central) {
		this.posicion_central = posicion_central;
	}

	public Object getTipo() {
		return tipo;
	}

	public void setTipo(Object tipo) {
		this.tipo = tipo;
	}

	public LinkedList<Tramo> getTramos() {
		return tramos;
	}

	public void setTramos(LinkedList<Tramo> tramos) {
		this.tramos = tramos;
	}

	public static int getId_nodo() {
		return id_nodo;
	}

	public static void setId_nodo(int id_nodo) {
		Nodo.id_nodo = id_nodo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
