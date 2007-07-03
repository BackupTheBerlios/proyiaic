package maker.vista.baseDatos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * @author  David García
 */
public class BotonTextura extends JButton 
{
	private static final long serialVersionUID = 1L;

	private File path; 
	private Image textura;
	private BufferedImage buffer;
	private boolean preparado;

	public BotonTextura()
	{
		super();
		preparado = false;
	}

	public void setTextura(File f)
	{
		path = f;
		try 
		{
			textura = ImageIO.read(path);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//Toolkit.getDefaultToolkit().getImage(path.getAbsolutePath());
		preparado = true;
	}

	public void paint(Graphics g)
	{
		if (preparado)
		{
			int width, height;
			super.paint(g);
			if (textura.getWidth(this) < 0)
				width = 0;
			else
				width = textura.getWidth(this);
			if (textura.getHeight(this) < 0)
				height = 0;
			else
				height = textura.getHeight(this);

			buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D gdos  = buffer.createGraphics();
			gdos.drawImage(textura, 0, 0, null);
			Graphics2D g2 = (Graphics2D)g;
			g2.setPaint(new TexturePaint(buffer, new Rectangle(textura.getWidth(this), textura.getHeight(this))));
			g2.fillRect(5,5,this.getWidth() -10 ,this.getHeight() - 10);
		}
		else
			super.paint(g);
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	/**
	 * @param preparado  the preparado to set
	 */
	public void setPreparado(boolean b) 
	{
		preparado = b;
	}
}
