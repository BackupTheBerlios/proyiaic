package maker.modelo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Iterator;
import java.util.LinkedList;

import maker.modelo.mapa_caminos.Nodo;
import maker.modelo.mapa_caminos.Tramo;
import maker.recursos.SistemaCarga;
import maker.vista.editor.PanelMapa.Selección;

public class MapaCaminos extends MapaAbstracto 
{
	public static final int RADIO = 10; 
	
	private LinkedList<Nodo> lista_nodos;
	
	private LinkedList<Tramo> lista_tramos;
	
	public MapaCaminos()
	{
		lista_nodos = new LinkedList<Nodo>();
		lista_tramos = new LinkedList<Tramo>();
	}
	
	public boolean insertarNodo(Nodo n)
	{
		Iterator<Nodo> iter = lista_nodos.iterator();
		while (iter.hasNext())
		{
			Nodo siguiente = iter.next();
			int norma = norma(siguiente, n);
			if (norma < RADIO)
				return false;
		}
		lista_nodos.add(n);
		return true;
	}
	
	private int norma(Nodo siguiente, Nodo n) 
	{
		int diferencia_X = siguiente.getPosicion_central().x - n.getPosicion_central().x;
		int diferencia_Y = siguiente.getPosicion_central().y - n.getPosicion_central().y;
		int raiz = (int) (Math.pow(diferencia_X, 2) + Math.pow(diferencia_Y, 2));
		return (int) Math.pow(raiz, 0.5);
	}

	public void dibujar(Graphics2D g2, Point seleccionada, SistemaCarga carga, Selección sel) 
	{
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.RED);
		Iterator<Nodo> iter = lista_nodos.iterator();
		while (iter.hasNext())
		{
			Nodo siguiente = iter.next();
			g2.fillOval(siguiente.getPosicion_central().x - RADIO, siguiente.getPosicion_central().y - RADIO, RADIO * 2, RADIO * 2);
		}
	}
}
