package juego.Oyentes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import juego.Escenario;
import juego.Seleccion;
import juego.Ventana;
import maker.modelo.recursos.Textura;



/**
 * @author  David García
 */
public class OyenteTeclado implements KeyListener 
{
	private Ventana ven;
	private boolean activo;
	private Seleccion sel;
	private int[][] alturas;
	private int altura;
	private int anchura;
	private int[][] esc;

	public OyenteTeclado(Ventana ven, Seleccion sel, int alt, int anch, int[][] is2, int[][] alturas)
	{
		this.ven = ven;
		this.activo = true;
		this.sel = sel;
		this.altura = alt;
		this.anchura = anch;
		this.esc = is2;
		this.alturas = alturas;
	}

	public void keyReleased(KeyEvent arg0) 
	{

	}

	public void keyTyped(KeyEvent arg0) 
	{

	}


	public void keyPressed(KeyEvent arg0) 
	{
		if (activo)
		{
			switch (arg0.getKeyCode())
			{
			case KeyEvent.VK_UP    : sel.subir(); break;
			case KeyEvent.VK_DOWN  : sel.bajar(); break;
			case KeyEvent.VK_ENTER : 
			{
				if (sel.getseleccion() == 2)
					ven.dispose();
				if (sel.getseleccion() == 0)
				{
					sel.aniquilar();
					ven.setPintor(new Escenario(altura,anchura,esc,alturas));
					ven.repaint(); 
					activo = false;
					ven.addKeyListener(new OyenteEscenario(ven));
				}
			}
			}
		}
		ven.repaint();
	}
}
