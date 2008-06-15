package parserFicherosBibtex;

/**
 * Clase que sirve para extraer un "@STRING".
 */
public class CampoString 
{
	/**
	 * Abreviatura usada.
	 */
	private String abrev;
	
	/**
	 * Texto correspondiente a la abreviatura.
	 */
	private String texto;

	/**
	 * Constructor especificando los parámetros.
	 * @param ab Abreviatura.
	 * @param tex Texto al que sustituye la abreviatura.
	 */
	public CampoString(String ab, String tex)
	{
		abrev = ab;
		texto = tex;
	}
	
	public String getAbrev() {
		return abrev;
	}

	public String getTexto() {
		return texto;
	}
	
	
}
