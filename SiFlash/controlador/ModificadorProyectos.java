//Source file: C:\\GENERADO\\database\\ModificadorProyectos.java

package controlador;

import database.BDException;
import database.BaseDatos;


/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a proyectos.
 */
public class ModificadorProyectos 
{

	public BaseDatos theBaseDatos;

	public ModificadorProyectos(BaseDatos database) {
		this.theBaseDatos = database;
	}

	/**
	 * Método para añadir un proyecto a la aplicación.
	 * 
	 * Excepcion si no se cuentan con permisos.
	 * @param nombre_proyecto - Nombre del proyecto a añadir.
	 * @param nombre_administrador - Nombre del administrador del proyecto.
	 * @throws database.BDException
	 * @roseuid 47C5A0FC005D
	 */
	public static void insertaProyecto(BaseDatos base,String nombre_proyecto, String nombre_administrador) throws BDException 
	{

	}

	/**
	 * Cambia el administrador del proyecto al nuevo pasado por parámetro.
	 * 
	 * @param nombre_proyecto - Nombre del proyecto a modificar.
	 * @param nuevo_administrador - Nuevo administrador del proyecto.
	 * @return nombre del antiguo administrador.@throws database.BDException
	 * @roseuid 47C5A19C03D8
	 */
	public String setAdmin(String nombre_proyecto, String nuevo_administrador) throws BDException 
	{
		return null;
	}

	/**
	 * Borra el proyecto de la aplicación.
	 * @param proyecto - Nombre del proyecto a borrar
	 * @roseuid 47C5A25B0290
	 */
	public static void borraProyecto(BaseDatos base,String proyecto) 
	{
		//

	}
}
