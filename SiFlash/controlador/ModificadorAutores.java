//Source file: C:\\GENERADO\\database\\ModificadorAutores.java

package controlador;

import database.BDException;
import database.BaseDatos;


/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a autores/editores.
 */
public class ModificadorAutores 
{
	public BaseDatos theBaseDatos;

	public ModificadorAutores(BaseDatos database)
	{
		theBaseDatos = database;
	}

	/**
	 * Inserta el a utor cuyos datos se le pasan por parámetro en la base de datos.
	 * 
	 * Excepción si ya existe o no hay permisos.
	 * @param nombre - Nombre del autor.
	 * @param apellidos - Apellidos del autor.
	 * @param web - Dirección de la página web del autor.
	 * @throws database.BDException
	 * @roseuid 47C5A3B1036B
	 */
	public void insertaAutor(String nombre, String apellidos, String web) throws BDException 
	{
		String consulta = "INSERT INTO Autores VALUES (" + nombre + ", " + apellidos + ", " + web + ");";
		theBaseDatos.exeUpdate(consulta);
	}

	/**
	 * Borra el autor cuyos datos se le pasan por parámetro de la base de datos, 
	 * desvinculandolo previamente de todas sus operaciones.
	 * 
	 * Excepcion si no se tienen permisos.
	 * @param nombre - Nombre del autor.
	 * @param apellidos - Apellidos del autor.
	 * @throws BDException 
	 * @throws UnimplementedException 
	 * @roseuid 47C5A4F702CE
	 */
	public void borraAutor(String nombre, String apellidos) throws BDException  
	{	
		String consulta = "DELETE FROM Autores WHERE nombre = " + nombre + " and apellidos = " + apellidos + ";";
		theBaseDatos.exeUpdate(consulta);   
	}

	/** Modifica los datos del autor cuyo nombre y apellidos se han pasado como parámetro, sustituyendolos por
	 *  los nuevos datos que se obtienen como parámetro.
	 *  @param nombreActual Nombre actual del autor.
	 *  @param apellidosActual Apellidos actuales del autor.
	 *  @param nombreNuevo Nuevo nombre que se quiere almacenar en la base de datos.
	 *  @param apellidosNuevos Apellidos nuevos del autor que se quieren almacenar en la base de datos.
	 *  @param urlNueva Nueva dirección Web del autor que se quiere almacenar en la base de datos.
	 * @throws BDException 
	 * @roseuid 47C5A5EA034B
	 */
	public void modificaAutor(String nombreActual, String apellidosActual, String nombreNuevo, String apellidosNuevos, String urlNueva) throws BDException 
	{
		String consulta = "UPDATE Autores SET nombre = " + nombreNuevo + ", apellidos = " + apellidosNuevos + ", url = " + urlNueva +
		"WHERE nombre = " + nombreActual + " and apellidos = " + apellidosActual + ";" ;
		theBaseDatos.exeUpdate(consulta);    
	}
}
