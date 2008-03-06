package parserFicherosBibtex;

public class Campo 
{
	private String nombre, valorString;
	private int valorInt;
	
	private boolean esUltimo; //Indica si es el último campo del documento.
	
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
}
