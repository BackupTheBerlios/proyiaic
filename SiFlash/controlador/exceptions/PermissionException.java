//Source file: C:\\GENERADO\\controlador\\exceptions\\PermisssionException.java

package controlador.exceptions;

import database.BDException;


/**
 * Excepcion que se produce cuando se quiere realizar una operación para la que no 
 * se tienen permisos.
 */
public class PermissionException extends BDException 
{

	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = -2972861761470064332L;

	/**
	 * Constructor por defecto de la clase.
	 */
	public PermissionException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor de la clase proporcionandole un mensaje.
	 * @param message String con el mensaje que almacenará.
	 */
	public PermissionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



}
