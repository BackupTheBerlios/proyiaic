package parserFicherosBibtex;

public class Campo 
{
	private String nombre, valorString;
	private int valorInt;
	
	private boolean esUltimo; //Indica si es el �ltimo campo del documento.
	
	public Campo(String nombre, String valor, boolean ult)
	{
		this.nombre = nombre;
		this.valorString = valor;
		this.esUltimo = ult;
	}
	
	public Campo (String nombre, int valorInt, boolean ult)
	{
		this.nombre = nombre;
		this.valorInt = valorInt;
		this.valorString = null;
		this.esUltimo = ult;
	}
	
	public boolean getEsUltimo()
	{
		return esUltimo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getValorString() {
		return valorString;
	}

	public int getValorInt() {
		return valorInt;
	}

	public void sustituirTildes() 
	{
		if (valorString != null)
		{
			valorString = valorString.replaceAll("\\\\\'a", "�");
			valorString = valorString.replaceAll("\\\\\'e", "�");
			valorString = valorString.replaceAll("\\\\\'\\\\i", "�"); //NO SE QUITAN LAS LLAVES!!
			valorString = valorString.replaceAll("\\\\'o", "�");
			valorString = valorString.replaceAll("\\\\\'u", "�");
			
			omitirLlaves();
		}
	}

	private void omitirLlaves() 
	{
		char[] cadena = valorString.toCharArray();
		String sinLlaves = "";
		for (int i = 0; i < cadena.length; i++)
			if (cadena[i] != '{' && cadena[i] != '}')
				sinLlaves += cadena[i];
		
		valorString = sinLlaves.toString();
	}
}
