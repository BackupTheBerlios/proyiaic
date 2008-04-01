//Source file: C:\\GENERADO\\controlador\\exceptions\\NonExistingElementException.java

package controlador.exceptions;


/**
 * Excepcion que se produce cuando queremos eliminar o modificar un elemento de la 
 * base de datos y no existe.
 */
public class NonExistingElementException extends ExistenceException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3939393614705232677L;

	/* (non-Javadoc)
	 * @see controlador.exceptions.ExistenceException#getTipo()
	 */
	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return super.getTipo();
	}

	/* (non-Javadoc)
	 * @see controlador.exceptions.ExistenceException#setTipo(int)
	 */
	@Override
	public void setTipo(int tipo) {
		// TODO Auto-generated method stub
		super.setTipo(tipo);
	}

	/**
	 * 
	 */
	public NonExistingElementException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tipo
	 */
	public NonExistingElementException(int tipo) {
		super(tipo);
		// TODO Auto-generated constructor stub
	}

}
