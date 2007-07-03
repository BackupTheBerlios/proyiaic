package maker.modelo.recursos;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;


/**
 * @author  David García
 */
public class Textura extends Recurso implements Serializable
{	
	private static final long serialVersionUID = 1L;

	private String nombre;
	
	private boolean pasable;
	
	private String path_lateral_derecho;
	
	private String path_lateral_izquierdo;
	
	private String path_superficie;

	private Images texturas;
	
	public Textura(String nombre, boolean pasable, String path_lateral_derecho, String path_lateral_izquierdo, String path_superficie) {
		super();
		this.nombre = nombre;
		this.pasable = pasable;
		this.path_lateral_derecho = path_lateral_derecho;
		this.path_lateral_izquierdo = path_lateral_izquierdo;
		this.path_superficie = path_superficie;
		this.texturas = null;
	}

	/**
	 * @return  the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return  the path_lateral_derecho
	 */
	public String getPath_lateral_derecho() {
		return path_lateral_derecho;
	}

	/**
	 * @return  the path_lateral_izquierdo
	 */
	public String getPath_lateral_izquierdo() {
		return path_lateral_izquierdo;
	}

	/**
	 * @return  the path_superficie
	 */
	public String getPath_superficie() {
		return path_superficie;
	}

	/**
	 * @return  the texturas
	 */
	public Images getTexturas() {
		return texturas;
	}

	/**
	 * @return  the pasable
	 */
	public boolean isPasable() {
		return pasable;
	}

	public void precargar() 
	{
		if (texturas == null)
		{
			try 
			{
				texturas = new Images();
				texturas.cargarImagenIzq(new File(path_lateral_izquierdo));
				texturas.cargarImagenDer(new File(path_lateral_derecho));
				texturas.cargarImagenSup(new File(path_superficie));
			} 
			catch (IOException e) 
			{
				texturas = null;
			}
		}
	}

	/**
	 * @param nombre  the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @param pasable  the pasable to set
	 */
	public void setPasable(boolean pasable) {
		this.pasable = pasable;
	}

	/**
	 * @param path_lateral_derecho  the path_lateral_derecho to set
	 */
	public void setPath_lateral_derecho(String path_lateral_derecho) {
		this.path_lateral_derecho = path_lateral_derecho;
	}
	
	/**
	 * @param path_lateral_izquierdo  the path_lateral_izquierdo to set
	 */
	public void setPath_lateral_izquierdo(String path_lateral_izquierdo) {
		this.path_lateral_izquierdo = path_lateral_izquierdo;
	}

	/**
	 * @param path_superficie  the path_superficie to set
	 */
	public void setPath_superficie(String path_superficie) {
		this.path_superficie = path_superficie;
	}

	/**
	 * @param texturas  the texturas to set
	 */
	public void setTexturas(Images texturas) {
		this.texturas = texturas;
	}

	public String toString()
	{	
		return (nombre + " ");
	}
}
