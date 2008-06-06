//Source file: C:\\GENERADO\\controlador\\exceptions\\NonExistingElementException.java

package controlador.exceptions;


/**
 * Excepcion hija de ExistenceException, esta concretamente se produce cuando intentamos
 * acceder a un dato/relación que no encontrar y no está presente, generalmente
 * al intentar eliminar/modificar un dato que no existe.
 */
public class NonExistingElementException extends ExistenceException 
{

	/**
	 * Identificador de la clase.
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
	 * Constructor por defecto de la clase, unicamente llama al de la clase padre, por
	 * tanto se construirá con un origen indefinido.
	 */
	public NonExistingElementException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor de la clase proporcionando el tipo de dato que la ha provocado.
	 * @param tipo Tipo de datos que la ha provocado.
	 */
	public NonExistingElementException(int tipo) {
		super(tipo);
		// TODO Auto-generated constructor stub
	}

}
