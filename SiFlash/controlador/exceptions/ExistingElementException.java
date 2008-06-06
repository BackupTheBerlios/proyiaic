//Source file: C:\\GENERADO\\controlador\\exceptions\\ExistingElementException.java

package controlador.exceptions;


/**
 * Excepcion hija de ExistenceException, esta concretamente se produce cuando intentamos
 * existe en la base de datos un dato/relación que no esperabamos encontrar, generalmente
 * al intentar insertar un dato que ya existe.
 */
public class ExistingElementException extends ExistenceException 
{

	/**
	 * Identificador de la clase.
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
	 * Constructor por defecto de la clase, llama al constructor de la clase padre.
	 */
	public ExistingElementException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor de la clase proporcionando el tipo de dato que la ha provocado.
	 * @param tipo Tipo de datos que la ha provocado.
	 */
	public ExistingElementException(int tipo) {
		super(tipo);
		// TODO Auto-generated constructor stub
	}


}
