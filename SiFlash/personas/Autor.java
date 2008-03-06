package personas;

public class Autor 
{
	/**
	 * Nombre del autor.
	 */
	private String nombre;
	
	/**
	 * Apellidos del autor.
	 */
	private String apellidos;
	
	/**
	 * Web del autor.
	 */
	private String web;
	
	public Autor(String nom, String ap, String w)
	{
		nombre = nom;
		apellidos = ap;
		web = w;
	}
}
