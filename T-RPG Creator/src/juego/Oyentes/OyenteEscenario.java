package juego.Oyentes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import juego.Escenario;
import juego.Ventana;




/**
 * @author  David García
 */
public class OyenteEscenario implements KeyListener 
{

	private Ventana ven;
	private boolean activo;

	public OyenteEscenario(Ventana ven)
	{
		this.ven = ven;
		this.activo = true;
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
			case KeyEvent.VK_UP    : ((Escenario) ven.getPintor()).moverArriba(); break;
			case KeyEvent.VK_DOWN  : ((Escenario) ven.getPintor()).moverAbajo(); break;				
			case KeyEvent.VK_LEFT  : ((Escenario) ven.getPintor()).moverIzquierda(); break;	
			case KeyEvent.VK_RIGHT : ((Escenario) ven.getPintor()).moverDerecha(); break;	
			}
		}
		ven.repaint();
	}

}
