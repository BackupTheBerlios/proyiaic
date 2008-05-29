package maker.vista.editor.Acciones;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import maker.controlador.herramientas.mapa_escenario.HerramientaDeshacerLinea;
import maker.controlador.herramientas.mapa_escenario.HerramientaPintarCasilla;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class AccionLapiz extends EscuchaRaton
{
	private Editor editor;
	private LinkedList<Point> visitados;
	private int pintados;

	public AccionLapiz(Editor editor) 
	{
		this.editor = editor;
		visitados = new LinkedList<Point>();
		pintados = 0;
	}

	public void mouseClicked(MouseEvent evento)
	{
		int x = evento.getX() / 32;
		int y = evento.getY() / 32;
		int altura = (Integer) editor.getAltura();
		HerramientaPintarCasilla herr = new HerramientaPintarCasilla(x, y, altura);
		editor.hacer(herr);
		editor.repaint();
	}

	public void mouseReleased(MouseEvent evento)
	{
		HerramientaDeshacerLinea herr = new HerramientaDeshacerLinea(pintados);
		editor.hacer(herr);
		editor.repaint();
		visitados = new LinkedList<Point>();
		pintados = 0;
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
			int altura = (Integer) editor.getAltura();
			HerramientaPintarCasilla herr = new HerramientaPintarCasilla(x, y, altura);
			editor.hacer(herr);
			editor.repaint();
			pintados++;
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
