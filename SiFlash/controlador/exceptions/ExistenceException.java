//Source file: C:\\GENERADO\\controlador\\exceptions\\ExistenceException.java

package controlador.exceptions;

import database.BDException;


/**
 * Excepcion que se produce al manipular la base de datos por problemas de 
 * existencia o inexistencia de los objetos a tratar.
 */
public class ExistenceException extends BDException 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8706245682868597189L;
	public static final int INDEFINIDA = 1;
	public static final int DOCUMENTO = 2;
	public static final int USUARIO = 4;
	public static final int PROYECTO = 8;
	private static final int[] LISTATIPOS= {1,2,4,8};

	/**
	 * Indica el tipo de elemento que ha producido la excepcion de existencia ( 
	 * Documento, Usuario, Proyecto,....).
	 */
	private int tipo;

	/**
	 * @roseuid 47C8A7100119
	 */
	public ExistenceException() {
		this.tipo = INDEFINIDA;
	}

	public ExistenceException(int tipo){

	}

	private boolean existeTipo(int prueba){
		boolean continuar = true;
		for (int i=0;i< LISTATIPOS.length && continuar;i++){
			if (LISTATIPOS[i] == prueba) return true;
			continuar = (LISTATIPOS[i] < prueba);		   
		}
		return false;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		if (existeTipo(tipo))this.tipo = tipo;
		else this.tipo=INDEFINIDA;
	}


}
