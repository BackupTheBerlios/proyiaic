//Source file: C:\\GENERADO\\controlador\\exceptions\\PermisssionException.java

package controlador.exceptions;

import database.BDException;


/**
 * Excepcion que se produce cuando se quiere realizar una operaci�n para la que no 
 * se tienen permisos.
 */
public class PermissionException extends BDException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2972861761470064332L;

	/**
	 * 
	 */
	public PermissionException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public PermissionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}



}