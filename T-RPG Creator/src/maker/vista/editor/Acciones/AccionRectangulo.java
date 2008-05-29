package maker.vista.editor.Acciones;

import java.awt.event.MouseEvent;

import maker.controlador.herramientas.mapa_escenario.HerramientaPintarRectángulo;
import maker.modelo.MapaEscenario;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class AccionRectangulo extends EscuchaRaton 
{
	private Editor editor;

	private int x;
	private int y;

	private int actx;
	private int acty;

	public AccionRectangulo(Editor editor) 
	{
		this.editor = editor;
		x = 0;
		y = 0;
	}

	public void mousePressed(MouseEvent evento)
	{
		x = evento.getX() / 32;
		y = evento.getY() / 32;
		actx = x;
		acty = y;
	}

	public void mouseReleased(MouseEvent evento)
	{
		MapaEscenario mapaEscenario = (MapaEscenario)editor.getController().getModelo().getMapa();

		int x2 = evento.getX() / 32;
		int y2 = evento.getY() / 32;

		if (x2 >= mapaEscenario.getFilas())
			x2 = mapaEscenario.getFilas() - 1;
		else 
			if (x2 < 0)
				x2 = 0;

		if (y2 >= mapaEscenario.getColumnas())
			y2 = mapaEscenario.getColumnas() - 1;
		else 
			if (y2 < 0)
				y2 = 0;

		int altura = (Integer) editor.getAltura();

		HerramientaPintarRectángulo hpr = new HerramientaPintarRectángulo(x > x2 ? x : x2,  y > y2 ? y : y2,  x > x2 ? x2 : x, y > y2 ? y2 : y, altura);
		editor.hacer(hpr);
		editor.setAreaSeleccionada(0,0,-1,-1);
		editor.repaint();
	}

	public void mouseDragged(MouseEvent evento)
	{
		int x2 = evento.getX() / 32;
		int y2 = evento.getY() / 32;
		boolean repaint = false;
		MapaEscenario mapaEscenario = (MapaEscenario)editor.getController().getModelo().getMapa();
		if (x2 != actx)
		{
			if (x2 >= mapaEscenario.getFilas())
				actx = mapaEscenario.getFilas() - 1;
			else 
				if (x2 < 0)
					actx = 0;
				else
					actx = x2;
			repaint = true;
		}
		if (y2 != acty)
		{
			if (y2 >= mapaEscenario.getColumnas())
				acty = mapaEscenario.getColumnas() - 1;
			else
				if (y2 < 0)
					acty = 0;
				else
					acty = y2;
			repaint = true;
		}
		if (repaint)
		{
			editor.setAreaSeleccionada(actx < x ? actx : x, 
					acty < y ? acty : y, 
							actx < x ? x : actx, 
									acty < y ? y : acty);
			editor.repaint();
		}
	}
}













