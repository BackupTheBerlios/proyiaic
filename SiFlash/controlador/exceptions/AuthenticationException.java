package controlador.exceptions;



/**
 * Excepcion que se produce al verificar incorrectamente a un usuario.
 */
public class AuthenticationException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8138653528902143248L;

	/**
	 * 
	 */
	public AuthenticationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public AuthenticationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}

