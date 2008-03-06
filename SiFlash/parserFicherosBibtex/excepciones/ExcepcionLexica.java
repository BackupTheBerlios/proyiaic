package parserFicherosBibtex.excepciones;

public class ExcepcionLexica extends Exception 
{
	String message;
	
	public ExcepcionLexica(String s)
	{
		message = s;
	}
	
	public void imprimirError()
	{
		System.out.println(message);
	}
}
