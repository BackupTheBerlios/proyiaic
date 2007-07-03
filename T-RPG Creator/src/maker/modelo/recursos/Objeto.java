package maker.modelo.recursos;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * @author  David Garc�a
 */
public class Objeto extends Recurso implements Serializable
{
	private static final long serialVersionUID = 807845185815043554L;
	
	private String nombre;
	private String path;
	private String pasable;
	
	private boolean atravesable;
	private boolean golpeable;
	
	private Image imagen;
	
	private int da�o;

	public Objeto(String nombre, String path, String pasable, boolean atravesable, boolean golpeable, int da�o) {
		super();
		this.nombre = nombre;
		this.path = path;
		this.pasable = pasable;
		this.atravesable = atravesable;
		this.golpeable = golpeable;
		this.da�o = da�o;
		this.imagen = null;
	}

	/**
	 * @return  the atravesable
	 */
	public boolean isAtravesable() {
		return atravesable;
	}

	/**
	 * @param atravesable  the atravesable to set
	 */
	public void setAtravesable(boolean atravesable) {
		this.atravesable = atravesable;
	}

	/**
	 * @return  the da�o
	 */
	public int getDa�o() {
		return da�o;
	}

	/**
	 * @param da�o  the da�o to set
	 */
	public void setDa�o(int da�o) {
		this.da�o = da�o;
	}

	/**
	 * @return  the golpeable
	 */
	public boolean isGolpeable() {
		return golpeable;
	}

	/**
	 * @param golpeable  the golpeable to set
	 */
	public void setGolpeable(boolean golpeable) {
		this.golpeable = golpeable;
	}

	/**
	 * @return  the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre  the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return  the pasable
	 */
	public String getPasable() {
		return pasable;
	}

	/**
	 * @param pasable  the pasable to set
	 */
	public void setPasable(String pasable) {
		this.pasable = pasable;
	}

	/**
	 * @return  the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path  the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString()
	{
		return (nombre + " ");
	}

	public void precargar() 
	{
		if (imagen == null)
		{
			try 
			{
				imagen = ImageIO.read(new File(path));
			} 
			catch (IOException e) 
			{
				imagen = null;
			}
		}
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
}
