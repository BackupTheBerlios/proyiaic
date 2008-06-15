package controlador.exceptions;

import database.BDException;


/**
 * Representa un error a la hora de consultar en la base de datos.
 */
public class ConsultaException extends BDException 
{

	/**
	 * Identificador de la clase. 
	 */
	private static final long serialVersionUID = 6915164595673183927L;

	/**
	 * Constructor por defecto,
	 */
	public ConsultaException() 
	{
		super();
	}
}