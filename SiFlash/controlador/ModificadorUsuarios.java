//Source file: C:\\GENERADO\\database\\ModificadorUsuarios.java

package controlador;

import personas.Usuario;
import database.BDException;
import database.BaseDatos;

/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a usuarios
 */
public class ModificadorUsuarios 
{
	public BaseDatos theBaseDatos;

	public ModificadorUsuarios(BaseDatos database) {
		this.theBaseDatos = database;
	}

	/**
	 * Inserta en la base de datos es usuario pasado por parámetro.
	 * @param usuario - Usuario que se debe pasar por parámetro
	 * @throws database.BDException
	 * @roseuid 47C5913A0271
	 */
	public void creaUsuario(Usuario usuario) throws BDException 
	{

	}

	/**
	 * Añade un usuario a un proyecto.
	 * @param String - Nombre del usuario a añadir.
	 * @param proyecto - Nombre del proyecto al cual se quiere añadir.
	 * @throws database.BDException
	 * @roseuid 47C5979A01F4
	 */
	public void asociaProyecto(Usuario String, String proyecto) throws BDException 
	{

	}

	/**
	 * Elimina el usuario en cuestion del proyecto.
	 * @param String - Nombre del usuario a desvilcular.
	 * @param proyecto - Nombre del proyecto al cual se quiere de baja al usuario.
	 * @throws database.BDException
	 * @roseuid 47C599770186
	 */
	public void desasociaProyecto(Usuario String, String proyecto) throws BDException 
	{

	}

	/**
	 * Elimina al usuario de la aplicacion.
	 * 
	 * Excepcion si no existe o no se tienen permisos.
	 * @param usuario - Nombre del usuario que se quiere eliminar.
	 * @throws database.BDException
	 * @roseuid 47C599E803C8
	 */
	public void eliminaUsuario(String usuario) throws BDException 
	{

	}
}
