//Source file: C:\\GENERADO\\controlador\\exceptions\\ExistingElementException.java

package controlador.exceptions;


/**
 * Excepcion que se produce cuando intentamos insertar en la base de datos un 
 * elemento que ya existe.
 */
public class ExistingElementException extends ExistenceException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 430603106713792544L;

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

	/* (non-Javadoc)
	 * @see database.BDException#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	/**
	 * 
	 */
	public ExistingElementException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tipo
	 */
	public ExistingElementException(int tipo) {
		super(tipo);
		// TODO Auto-generated constructor stub
	}


}
