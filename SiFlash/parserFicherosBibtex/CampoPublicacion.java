package parserFicherosBibtex;

public class CampoPublicacion
{
	private String nombre, valor;
	
	private boolean esUltimo; //Indica si es el último campo del documento.
	
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

	public void sustituirTildes() 
	{
		if (valor != null)
		{
			valor = valor.replaceAll("\\\\\'a", "á");
			valor = valor.replaceAll("\\\\\'e", "é");
			valor = valor.replaceAll("\\\\\'\\\\i", "í"); //NO SE QUITAN LAS LLAVES!!
			valor = valor.replaceAll("\\\\'o", "ó");
			valor = valor.replaceAll("\\\\\'u", "ú");
			
			//omitirLlaves();
		}
	}

	private void omitirLlaves() 
	{
		char[] cadena = valor.toCharArray();
		String sinLlaves = "";
		for (int i = 0; i < cadena.length; i++)
			if (cadena[i] != '{' && cadena[i] != '}')
				sinLlaves += cadena[i];
		
		valor = sinLlaves.toString();
	}
}
