package javazoom.jl;

import java.io.IOException;
import java.net.URL;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * @author  David García
 */
public class HiloMusical extends Thread 
{
	private Player reproductor;
	
	public HiloMusical(URL url)
	{
		try
		{
			reproductor = new Player(url.openStream());
		}
		catch (JavaLayerException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		try 
		{
			reproductor.play();
		} 
		catch (JavaLayerException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void stopping()
	{
		reproductor.close();
	}
}
