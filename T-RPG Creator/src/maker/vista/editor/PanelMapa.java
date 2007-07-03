package maker.vista.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import maker.vista.editor.Acciones.Acci�nDesplazar;
import maker.vista.editor.Acciones.Acci�nL�piz;
import maker.vista.editor.Acciones.EscuchaRat�n;


/**
 * @author  David Garc�a
 */
public class PanelMapa extends JPanel 
{
	public class Selecci�n 
	{
		public int init_x;
		public int init_y;
		public int ancho;
		public int largo;
	}

	private static final long serialVersionUID = 1L;

	private Editor editor;

	private EscuchaRat�n acci�n;

	private Point seleccionada;
	
	private Selecci�n sel;

	public PanelMapa(final Editor editor)
	{					
		this.editor = editor;

		seleccionada = new Point(0,0);
		acci�n = new Acci�nL�piz(editor);
		addMouseListener(acci�n);
		addMouseMotionListener(acci�n);

		addMouseMotionListener(new Acci�nDesplazar(this));
		setBackground(Color.GRAY);
		
		sel = new Selecci�n();
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		if (editor.getController().getModelo().getMapa() != null)
			editor.getController().getModelo().getMapa().dibujar(g2, seleccionada, editor.getSistemacarga(), sel);
	}

	public void setSeleccion(int x, int y) 
	{
		seleccionada = new Point(x, y);
	}

	public void cambiarEscucha(EscuchaRat�n escucha) 
	{
		removeMouseListener(acci�n);
		removeMouseMotionListener(acci�n);
		acci�n = escucha;
		addMouseListener(escucha);
		addMouseMotionListener(escucha);
	}

	public void setAreaSeleccionada(int init_x, int init_y, int fin_x, int fin_y) 
	{
		sel = new Selecci�n();
		sel.init_x = init_x;
		sel.init_y = init_y;
		sel.ancho = fin_x - init_x + 1;
		sel.largo = fin_y - init_y + 1;
	}
}
