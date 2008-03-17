package parserFicherosBibtex.excepciones;

/**
 * Excepci�n que salta cuando se lee de un fichero algo que no se esperaba.
 */
public class ExcepcionLexica extends Exception 
{
	/**
	 * Mensaje que se mostrar� si se da la excepci�n.
	 */
	String message;
	
	public ExcepcionLexica(String s)
	{
		message = s;
	}
	
	/**
	 * Imprime el error por consola.
	 */
	public void imprimirError()
	{
		System.out.println(message);
	}
}
