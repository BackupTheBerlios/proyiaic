package parserFicherosBibtex;

public class CampoPublicacion
{
	private String nombre, valor;
	
	private boolean esUltimo; //Indica si es el �ltimo campo del documento.
	
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
			valor = valor.replaceAll("\\\\\'a", "�");
			valor = valor.replaceAll("\\\\\'e", "�");
			valor = valor.replaceAll("\\\\\'\\\\i", "�"); //NO SE QUITAN LAS LLAVES!!
			valor = valor.replaceAll("\\\\'o", "�");
			valor = valor.replaceAll("\\\\\'u", "�");
			
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
