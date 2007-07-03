package maker.vista.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import maker.vista.editor.Acciones.AcciónDesplazar;
import maker.vista.editor.Acciones.AcciónLápiz;
import maker.vista.editor.Acciones.EscuchaRatón;


/**
 * @author  David García
 */
public class PanelMapa extends JPanel 
{
	public class Selección 
	{
		public int init_x;
		public int init_y;
		public int ancho;
		public int largo;
	}

	private static final long serialVersionUID = 1L;

	private Editor editor;

	private EscuchaRatón acción;

	private Point seleccionada;
	
	private Selección sel;

	public PanelMapa(final Editor editor)
	{					
		this.editor = editor;

		seleccionada = new Point(0,0);
		acción = new AcciónLápiz(editor);
		addMouseListener(acción);
		addMouseMotionListener(acción);

		addMouseMotionListener(new AcciónDesplazar(this));
		setBackground(Color.GRAY);
		
		sel = new Selección();
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

	public void cambiarEscucha(EscuchaRatón escucha) 
	{
		removeMouseListener(acción);
		removeMouseMotionListener(acción);
		acción = escucha;
		addMouseListener(escucha);
		addMouseMotionListener(escucha);
	}

	public void setAreaSeleccionada(int init_x, int init_y, int fin_x, int fin_y) 
	{
		sel = new Selección();
		sel.init_x = init_x;
		sel.init_y = init_y;
		sel.ancho = fin_x - init_x + 1;
		sel.largo = fin_y - init_y + 1;
	}
}
