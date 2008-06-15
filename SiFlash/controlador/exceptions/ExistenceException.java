//Source file: C:\\GENERADO\\controlador\\exceptions\\ExistenceException.java

package controlador.exceptions;



/**
 * Se produce por problemas de 
 * existencia o inexistencia de datos, que no existen cuando esperabamos
 * que existiesen, o viceversa.
 */
public class ExistenceException extends Exception 
{
	/**
	 * Identificador de la clase.
	 */
	private static final long serialVersionUID = -8706245682868597189L;
	
	/**
	 * Indica excepcion de existencia de tipo indefinido.
	 */
	public static final int INDEFINIDA = 1;
	
	/**
	 * Indica que el dato que nos ha producido el fallo es un documento.
	 */
	public static final int DOCUMENTO = 2;
	
	/**
	 * Indica que el dato que nos ha producido el fallo es un usuario. 
	 */
	public static final int USUARIO = 4;
	
	/**
	 * Indica que el dato que nos ha producido el fallo es un proyecto.
	 */
	public static final int PROYECTO = 8;
	
	/**
	 * Indica que el dato que nos ha producido el fallo es un autor/editor.
	 */
	public static final int AUTOR = 16;
	
	/**
	 * Indica que el dato que nos ha producido el fallo es una relacion, por ejemplo
	 * una relación entre documento y proyecto.
	 */
	public static final int RELACION = 32;
	
	/**
	 * Listado con las posibles causas de los fallos.
	 */
	private static final int[] LISTATIPOS= {1,2,4,8,16,32};

	/**
	 * Almacena el tipo de excepcion de existencia (Documento, Usuario, Proyecto,....).
	 */
	private int tipo;

	/**
	 * Constructor por defecto de la clase. Almacenará que el error es de tipo indefinido.
	 */
	public ExistenceException() {
		this.tipo = INDEFINIDA;
	}

	/**
	 * Constructor de la clase indicandole el motivo que la ha provocado.
	 * @param tipo Indica el tipo concreto que ha producido el fallo de existencia.
	 */
	public ExistenceException(int tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * Comprueba si el tipo que nos proporcionan es uno de los tipos que pueden
	 * provocar la excepción.
	 * @param prueba Entero para comprobar si representa alguno de los posibles tipos.
	 * @return El resultado de la evaluación.
	 */
	private boolean existeTipo(int prueba){
		boolean continuar = true;
		for (int i=0;i< LISTATIPOS.length && continuar;i++){
			if (LISTATIPOS[i] == prueba) return true;
			continuar = (LISTATIPOS[i] < prueba);		   
		}
		return false;
	}

	/**
	 * @return El tipo que ha provocado la excepción.
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Cambia el valor del tipo que ha producido la excepción al que se le pasa por parámetro.
	 * @param tipo El tipo que ha provocado la excepción.
	 */
	public void setTipo(int tipo) {
		if (existeTipo(tipo))this.tipo = tipo;
		else this.tipo=INDEFINIDA;
	}


}
