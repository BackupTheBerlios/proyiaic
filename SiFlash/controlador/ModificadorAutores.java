//Source file: C:\\GENERADO\\database\\ModificadorAutores.java

package controlador;

import java.sql.Connection;
import java.util.Vector;

import publicaciones.Publication;
import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;
import database.BDException;
import database.BaseDatos;


/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base de datos
 * relativas a autores/editores.
 */
public class ModificadorAutores 
{

	/**
	 * Base de datos sobre la que realiza las operaciones.
	 */	
	public BaseDatos theBaseDatos;


	/**
	 *  Constructor pasandole el parametro de la base de datos que posee.
	 * @param database - Objeto base de datos sobre la que trabaja.
	 */	
	public ModificadorAutores(BaseDatos database)
	{
		theBaseDatos = database;
	}

	/**
	 * Inserta el autor cuyos datos se le pasan por parámetro en la base de datos.
	 * @param nombre - Nombre del autor o editor.
	 * @param apellidos - Apellidos del autor o editor.
	 * @param conn - Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException, así como su mensaje.
	 * @throws ExistingElementException - Si ya se encuentra en la base de datos el AutorEditor
	 * cuyos datos se han pasado.
	 */
	public void insertaAutorEditor(String nombre, String apellidos, Connection conn) throws BDException, ExistingElementException 
	{
		int idAut = consultaIdAutor(nombre, apellidos, conn);		
		if (idAut > 0) throw new ExistingElementException(ExistenceException.AUTOR);
		
		String insercion = "INSERT INTO autoreseditores VALUES (0,'" + Publication.sustituirComillasSQL(nombre) + "','" + Publication.sustituirComillasSQL(apellidos) + "');";
		theBaseDatos.exeUpdate(insercion, conn);
	}

	/**
	 * Método que consulta el identificador que le corresponde al AutorEditor cuyos datos se le pasan
	 * por parámetro.
	 * @param nombre - Nombre del autor o editor.
	 * @param apellidos - Apellidos del autor o editor.
	 * @param conn - Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @return Entero con el idPer del autor que coincida con los datos que se han pasado. 0 en caso que 
	 * ninguno coincida.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException, así como su mensaje.
	 */
	protected int consultaIdAutor(String nombre, String apellidos, Connection conn) throws BDException 
	{		
		String consulta = "SELECT idAut FROM AutoresEditores WHERE ";
		if (nombre != null) consulta += "nombre = '" + nombre + "' AND ";
		if (apellidos != null) consulta += "apellidos = '" + apellidos + "' AND ";
		consulta += "TRUE;";


		Vector<Object[]> resultado = theBaseDatos.exeQuery(consulta, conn);
		if (resultado.size() != 0)
		{
			Object[] array = resultado.get(0);
			int idAut = ((Long) array[0]).intValue();
			return idAut;
		}
		else
			return 0;
	}


	/**
	 * Borra el autor cuyos datos se le pasan por parámetro de la base de datos, 
	 * desvinculandolo previamente de todas sus operaciones.
	 * 
	 * @param nombre - Nombre del autor o editor.
	 * @param apellidos - Apellidos del autor o editor.
	 * @param conn - Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws NonExistingElementException - Si no existe ningún autor que coincida con los datos pasados
	 * por parámetro.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException, así como su mensaje.
	 */
	public void borraAutor(String nombre, String apellidos, Connection conn) throws NonExistingElementException,BDException  
	{			
		Vector <String> borrados = new Vector<String>();
		int idAut = consultaIdAutor(nombre, apellidos, conn);
		if (idAut == 0) throw new NonExistingElementException(ExistenceException.AUTOR);

		borrados.add(new String ("DELETE FROM escrito_editado_por WHERE idPer = " + idAut + ";"));
		borrados.add(new String ("DELETE FROM autoreseditores WHERE idAut = " + idAut + ";"));
		theBaseDatos.exeUpdates(borrados, conn);   
	}

	/** 
	 * Modifica los datos del autor cuyo nombre y apellidos se han pasado como parámetro, sustituyendolos por
	 * los nuevos datos que se obtienen del resto de parámetros.
	 * @param nombreActual Nombre actual del autor.
	 * @param apellidosActual Apellidos actuales del autor.
	 * @param nombreNuevo Nuevo nombre que se quiere almacenar en la base de datos.
	 * @param apellidosNuevos Apellidos nuevos del autor que se quieren almacenar en la base de datos.
	 * @param conn - Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws NonExistingElementException - Si no existe ningún autor que coincida con los datos pasados
	 * por parámetro.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException, así como su mensaje.
	 */
	public void modificaAutor(String nombreActual, String apellidosActual, String nombreNuevo, String apellidosNuevos, Connection conn) throws NonExistingElementException,BDException 
	{
		int idAut = consultaIdAutor(nombreActual, apellidosActual, conn);
		if (idAut == 0) throw new NonExistingElementException(ExistenceException.AUTOR);

		String consulta = new String ("UPDATE Autores SET nombre = '" + nombreNuevo + "', apellidos = '" + apellidosNuevos + "' WHERE idAut = "+ idAut + ";") ;
		theBaseDatos.exeUpdate(consulta, conn);    
	}

	/** 
	 * Modifica los datos del autor cuyo idAutor se ha pasado como parámetro, sustituyendolos por
	 * los nuevos datos que se obtienen como parámetro.
	 * @param idAutor - Identificador único de Autor del autor que se desea modificar.
	 * @param nombreNuevo -  Nuevo nombre que se quiere almacenar en la base de datos.
	 * @param apellidosNuevos -  Apellidos nuevos del autor que se quieren almacenar en la base de datos.
	 * @param conn - Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws NonExistingElementException - Si no existe ningún autor que coincida con los datos pasados
	 * por parámetro.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException, así como su mensaje.
	 */
	public void modificaAutor(int idAutor, String nombreNuevo, String apellidosNuevos, Connection conn) throws NonExistingElementException,BDException 
	{		
		String cons1 = new String ("SELECT * FROM autoreseditores WHERE idAut = " + idAutor +";");
		Vector <Object[]> res1 = theBaseDatos.exeQuery(cons1, conn);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue()!=1) throw new NonExistingElementException(ExistenceException.AUTOR);

		String consulta = new String ("UPDATE autoreseditores SET nombre = '" + nombreNuevo + "', apellidos = '" + apellidosNuevos + "' WHERE idAut = "+ idAutor + ";") ;
		theBaseDatos.exeUpdate(consulta, conn);    
	}	
}
