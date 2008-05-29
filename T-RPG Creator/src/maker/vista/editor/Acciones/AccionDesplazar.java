package maker.vista.editor.Acciones;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import maker.vista.editor.PanelMapa;

/**
 * @author  David García
 */
public class AccionDesplazar implements MouseMotionListener {

	private PanelMapa mapa;

	public AccionDesplazar(PanelMapa mapa) 
	{
		this.mapa = mapa;
	}

	public void mouseDragged(MouseEvent evento) 
	{
		int x = evento.getX() / 32;
		int y = evento.getY() / 32;
		try
		{
			mapa.setSeleccion(x,y);
			mapa.repaint();
		}
		catch (ArrayIndexOutOfBoundsException e) {};
	}

	public void mouseMoved(MouseEvent evento) 
	{
		int x = evento.getX() / 32;
		int y = evento.getY() / 32;
		try
		{
			mapa.setSeleccion(x,y);
			mapa.repaint();
		}
		catch (ArrayIndexOutOfBoundsException e) {};
	}

}
