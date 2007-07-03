package sonido;

import java.applet.AudioClip;
import java.net.URL;

import javax.swing.JApplet;

public class Reproductor 
{
	private AudioClip sonido_actual;

	public Reproductor()
	{
		sonido_actual = null;
	}
	
	public void reproducir(URL url)
	{
		if (sonido_actual != null)
			sonido_actual.stop();
		sonido_actual = JApplet.newAudioClip(url);
		sonido_actual.play();
	}
	
	public void repetir(URL url)
	{
		if (sonido_actual != null)
			sonido_actual.stop();
		sonido_actual = JApplet.newAudioClip(url);
		sonido_actual.loop();
	}
	
	public void detener()
	{
		if (sonido_actual != null)
			sonido_actual.stop();
	}
}
