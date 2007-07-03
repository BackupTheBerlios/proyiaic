package maker.modelo.recursos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author  David García
 */
public class Images 
{
	BufferedImage sup;
	BufferedImage der;
	BufferedImage izq;
	
	public void cargarImagenSup(File f) throws IOException
	{
		sup = ImageIO.read(f);
	}
	
	public void cargarImagenDer(File f) throws IOException
	{
		der = ImageIO.read(f);
	}
	
	public void cargarImagenIzq(File f) throws IOException
	{
		izq = ImageIO.read(f);
	}

	/**
	 * @return  the der
	 */
	public BufferedImage getDer() {
		return der;
	}

	/**
	 * @param der  the der to set
	 */
	public void setDer(BufferedImage der) {
		this.der = der;
	}

	/**
	 * @return  the izq
	 */
	public BufferedImage getIzq() {
		return izq;
	}

	/**
	 * @param izq  the izq to set
	 */
	public void setIzq(BufferedImage izq) {
		this.izq = izq;
	}

	/**
	 * @return  the sup
	 */
	public BufferedImage getSup() {
		return sup;
	}

	/**
	 * @param sup  the sup to set
	 */
	public void setSup(BufferedImage sup) {
		this.sup = sup;
	}
}
