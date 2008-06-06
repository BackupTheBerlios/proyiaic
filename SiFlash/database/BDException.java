//Source file: C:\\GENERADO\\database\\BDException.java

package database;


/**
 * Clase genérica que representa las excepciones que se pueden producir tratando 
 * con la base de datos.
 */
public class BDException extends Exception 
{

	/**
	 * Identificador de Exception.
	 */
	private static final long serialVersionUID = 1700034899133845429L;

	/**
	 * Constructor por defecto de la clase
	 */
	public BDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor proporcionandole un mensaje.
	 * @param message - Mensaje que guardará entre sus atributos.
	 */
	public BDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}




}
