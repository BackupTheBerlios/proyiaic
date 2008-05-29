package maker.vista.editor.Acciones;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import maker.controlador.herramientas.mapa_escenario.HerramientaBorrarCasilla;
import maker.vista.editor.Editor;

public class AccionGoma extends EscuchaRaton 
{
	private Editor editor;
	private LinkedList<Point> visitados;

	public AccionGoma(Editor editor) 
	{
		this.editor = editor;
		visitados = new LinkedList<Point>();
	}

	public void mouseClicked(MouseEvent evento)
	{
		int x = evento.getX() / 32;
		int y = evento.getY() / 32;
		HerramientaBorrarCasilla herr = new HerramientaBorrarCasilla(x, y);
		editor.hacer(herr);
		editor.repaint();
	}

	public void mouseReleased(MouseEvent evento)
	{
		visitados = new LinkedList<Point>();
	}

	public void mouseDragged(MouseEvent evento)
	{
		int x = evento.getX() / 32;
		int y = evento.getY() / 32;
		actuar(x,y);
	}

	public void mouseMoved(MouseEvent e) 
	{
		
	}

	private void actuar(int x, int y)
	{
		if (!visitado(x,y))
		{	
			visitar(x,y);
			HerramientaBorrarCasilla herr = new HerramientaBorrarCasilla(x, y);
			editor.hacer(herr);
			editor.repaint();
		}
	}

	private void visitar(int x, int y) 
	{
		visitados.add(new Point(x,y));
	}

	private boolean visitado(int x, int y) 
	{
		Iterator<Point> iter = visitados.iterator();
		boolean encontrado = false;
		while (!encontrado && iter.hasNext())
		{
			Point p = iter.next();
			if (p.x == x && p.y == y)
				encontrado = true;
		}
		return encontrado;
	}
}
