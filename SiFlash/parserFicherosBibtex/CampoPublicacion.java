package parserFicherosBibtex;

/**
 * Clase que sirve para extraer un campo de una publicación 
 * mediante el ParserBibTeX.
 */
public class CampoPublicacion
{
	/**
	 * Nombre del campo.
	 */
	private String nombre;
	
	/**
	 * Valor del campo.
	 */
	private String valor;
	
	/**
	 * Indica si es el último campo del documento.
	 */
	private boolean esUltimo;
	
	
	public CampoPublicacion(String nombre, String valor, boolean ult)
	{
		this.nombre = nombre;
		this.valor = valor;
		this.esUltimo = ult;
	}
	
	public boolean getEsUltimo()
	{
		return esUltimo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getValor() {
		return valor;
	}
	/**
	 * Sustituye las cadenas de caracteres que representan las tildes,
	 * por la propia letra con tilde.
	 */
	public void sustituirTildes() 
	{
		if (valor != null)
		{
			valor = valor.replaceAll("\\\\\'a", "á");
			valor = valor.replaceAll("\\\\\'e", "é");
			valor = valor.replaceAll("\\\\\'\\\\i", "í"); //NO SE QUITAN LAS LLAVES!!
			valor = valor.replaceAll("\\\\'o", "ó");
			valor = valor.replaceAll("\\\\\'u", "ú");
		}
	}
}
