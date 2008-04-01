package controlador.exceptions;

import database.BDException;


/**
 * Excepcion que se puede producir a la hora de consultar en la base de datos.
 */
public class ConsultaException extends BDException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6915164595673183927L;

	/**
	 * 
	 */
	public ConsultaException() 
	{
		super();

	}
}