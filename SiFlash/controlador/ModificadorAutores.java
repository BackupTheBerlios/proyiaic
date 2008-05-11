//Source file: C:\\GENERADO\\database\\ModificadorAutores.java

package controlador;

import java.util.Vector;

import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;

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
	public void insertaAutor(String nombre, String apellidos, String web) throws ExistingElementException,BDException 
	{
		int idAut = consultaIdAutor(nombre, apellidos);		
		if (idAut > 0) throw new ExistingElementException(ExistenceException.AUTOR);
		
		String insercion = "INSERT INTO Autores VALUES (0,'" + nombre + "','" + apellidos + "','" + web + "');";
		theBaseDatos.exeUpdate(insercion);
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
	public void borraAutor(String nombre, String apellidos) throws NonExistingElementException,BDException  
	{			
		Vector <String> borrados = new Vector<String>();
		int idAut = consultaIdAutor(nombre, apellidos);
		if (idAut == 0) throw new NonExistingElementException(ExistenceException.AUTOR);
		
		borrados.add(new String ("DELETE FROM escrito_editado_por WHERE idPer = " + idAut + ";"));
		borrados.add(new String ("DELETE FROM autoreseditores WHERE idAut = " + idAut + ";"));
		theBaseDatos.exeUpdates(borrados);   
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
	public void modificaAutor(String nombreActual, String apellidosActual, String nombreNuevo, String apellidosNuevos, String urlNueva) throws NonExistingElementException,BDException 
	{
		int idAut = consultaIdAutor(nombreActual, apellidosActual);
		if (idAut == 0) throw new NonExistingElementException(ExistenceException.AUTOR);
		
		String consulta = new String ("UPDATE Autores SET nombre = '" + nombreNuevo + "', apellidos = '" + apellidosNuevos + "', url = '" + urlNueva +
		"' WHERE idAut = "+ idAut + ";") ;
		theBaseDatos.exeUpdate(consulta);    
	}
	
	/**
	 * 
	 * @param nombre
	 * @param apellidos
	 * @return
	 * @throws BDException
	 */
	public int consultaIdAutor(String nombre, String apellidos) throws BDException 
	{
		Vector<Object[]> resultado = theBaseDatos.exeQuery("SELECT idAut FROM AutoresEditores WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'");
		if (resultado.size() != 0)
		{
			Object[] array = resultado.get(0);
			int idAut = ((Long) array[0]).intValue();
			return idAut;
		}
		else
			return 0;
	}	
}
