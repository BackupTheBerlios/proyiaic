package juego;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
import javax.swing.JFrame;
import juego.Oyentes.OyenteTeclado;
import maker.modelo.recursos.Textura;




/**
 * @author  David García
 */
public class Ventana extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Seleccion thread;

	private Painter pintor;

	private AudioClip clip;

	public Ventana(final int[][] is2, int[][] is, int alt, int anch)
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		thread = new Seleccion(this);
		setSize(1024,768);
		setTitle("Guado Games Tactics");
		setVisible(true);
		addKeyListener(new OyenteTeclado(this, thread, alt, anch, is2, is));
		pintor = new Menú(thread);
		try 
		{
			clip = JApplet.newAudioClip(new URL("file:src\\061-Slow04.mid"));
			clip.play();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		thread.start();
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent ev)
			{
				clip.stop();
			}
		});
	}

	public void paint(Graphics g)
	{
		pintor.dibujar(g);
	}

	/**
	 * @param pintor  the pintor to set
	 */
	public void setPintor(Painter pintor) 
	{
		this.pintor = pintor;
	}

	/**
	 * @return  the pintor
	 */
	public Painter getPintor() {
		return pintor;
	}

	/**
	 * @return  the thread
	 */
	public Seleccion getThread() {
		return thread;
	}

	/**
	 * @param thread  the thread to set
	 */
	public void setThread(Seleccion thread) {
		this.thread = thread;
	}
}
