package controlador.exceptions;



/**
 * Excepcion que se produce al verificar incorrectamente a un usuario.
 */
public class AuthenticationException extends Exception 
{
	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = 8138653528902143248L;

	/**
	 * Constructor por defecto de la clase.
	 */
	public AuthenticationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor de la clase dado el mensaje que ha de contener.
	 * @param message - String con el mensaje que ha de contener la excepcion.
	 */
	public AuthenticationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}

