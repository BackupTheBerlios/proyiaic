package parserFicherosBibtex;

/**
 * Contendrá el nombre y el valor de un campo durante el proceso
 * de conversión de un fichero BibTeX.
 */
public abstract class Campo 
{
	/**
	 * Nombre del campo.
	 */
	protected String nombre;

	public String getNombre()
	{
		return nombre;
	}
}
