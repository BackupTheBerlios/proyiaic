//Source file: C:\\GENERADO\\database\\ModificadorPublicaciones.java

package controlador;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import publicaciones.Publication;
import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;
import database.BDException;
import database.BaseDatos;

/**
 * Se encarga de realizar las modificaciones necesarias en la base relativas a publicaciones.
 */
public class ModificadorPublicaciones {

	
	/**
	 * Base de datos sobre la que realiza las operaciones.
	 */	
	public BaseDatos theBaseDatos;


	/**
	 *  Constructor especificando la base de datos.
	 * @param database Base de datos sobre la que trabaja.
	 */		
	public ModificadorPublicaciones(BaseDatos database) {
		this.theBaseDatos = database;
	}

	/**
	 * Se encarga de insertar la publicación pasada por parámetro en la base de datos.
	 * @param publicacion Publicación a insertar.
	 * @param conn Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws database.BDException Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException, así como su mensaje.
	 * @throws ExistingElementException Si ya existe esa publicación en la base de datos.
	 */
	public void insertaPublicacion(Publication publicacion, Connection conn) throws BDException, ExistingElementException
	{
		Vector<String> inserciones = publicacion.generaInserciones(conn);		
		theBaseDatos.exeUpdates(inserciones, conn);
	}

	/**
	 * Se encarga de modificar una publicación pasada por parámetro, cambiando los antiguos datos que contenía 
	 * por los que contiene el objeto.
	 * @param publicacion Nuevos datos de la publicación.
	 * @param conn Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws BDException Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException Si la publicacion (el idDoc) no se encuentra en
	 * la base de datos.
	 * @throws ExistingElementException Si ocurre un error inesperado durante la modificación.
	 */
	public void modificaPublicacion(Publication publicacion, Connection conn) throws NonExistingElementException,BDException, ExistingElementException 
	{	
		int id_doc = publicacion.getIdDoc();
		String consulta = new String ("SELECT tipo FROM tipopublicacion WHERE idDoc = " + id_doc + ";");
		Vector<Object []> res = theBaseDatos.exeQuery(consulta, conn);
		if (res == null || res.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		
		consulta = "SELECT proyecto FROM pertenecea WHERE idDoc=" + id_doc + ";";
		res = theBaseDatos.exeQuery(consulta, conn);
		LinkedList<String> proyectos = new LinkedList<String>();
		for (int i = 0; i < res.size(); i++)
			proyectos.add((String)res.get(i)[0]);
		
		borraPublicacion(publicacion.getIdDoc(), conn);
		insertaPublicacion(publicacion, conn);
		
		Iterator<String> it = proyectos.iterator();
		String insercion;
		while (it.hasNext())
		{
			insercion = "INSERT INTO pertenecea VALUES (" + id_doc + ", '" + it.next() + "');";
			theBaseDatos.exeUpdate(insercion, conn);
		}		
	}

	/**
	 * Elimina una publicación de la base de datos. Elimina previamente todos los vinculos que tenga, tanto con autores,
	 * keywords, proyectos, etc. 
	 * @param id_doc IdDoc de la publicación que se desea borrar.
	 * @param conn Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws database.BDException Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException En caso que el documento no exista.
	 */
	public void borraPublicacion(int id_doc, Connection conn) throws BDException, NonExistingElementException 
	{
		String consulta = new String ("SELECT tipo FROM tipopublicacion WHERE idDoc = " + id_doc + ";");
		Vector<Object []> res = theBaseDatos.exeQuery(consulta, conn);
		if (res == null || res.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		
		Vector<Object[]> guardaAutoresEditores = theBaseDatos.exeQuery("SELECT idPer FROM escrito_editado_por WHERE idDoc = '" + id_doc + "';", conn);
		Vector<Object[]> guardaClaves = theBaseDatos.exeQuery("SELECT clave FROM tienekey WHERE idDoc = '" + id_doc + "';", conn);
		
		String tabla = (String) res.firstElement()[0];
		theBaseDatos.exeUpdate("DELETE FROM " + tabla + " WHERE idDoc = " + id_doc + ";", conn);
		
		int numAE = guardaAutoresEditores.size();
		String idAut;
		for (int i = 0; i < numAE; i++)
		{
			idAut = ((Long)guardaAutoresEditores.get(i)[0]).toString();
			res = theBaseDatos.exeQuery("SELECT * FROM escrito_editado_por WHERE idPer = '" + idAut + "';", conn);
			if (res.size() == 0)
				theBaseDatos.exeUpdate("DELETE FROM autoreseditores WHERE idAut = '" + idAut + "';", conn);
		}
		
		int numClaves = guardaClaves.size();
		String nombreClave;
		for (int i = 0; i < numClaves; i++)
		{
			nombreClave = (String)guardaClaves.get(i)[0];
			res = theBaseDatos.exeQuery("SELECT * FROM tienekey WHERE clave = '" + nombreClave + "';", conn);
			if (res.size() == 0)
				theBaseDatos.exeUpdate("DELETE FROM claves WHERE clave = '" + nombreClave + "';", conn);
		}
	}

	/**
	 * Asocia una publicación a un proyecto.
	 * @param publicacion idDoc de la publicación a asociar.
	 * @param proyecto Proyecto al que deseamos asociar la publicación.
	 * @param conn Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws database.BDException Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException Cuando no exite el proyecto o la la publicación, lo podemos concretar
	 * analizando el contenido de la excepción.
	 * @throws ExistingElementException Si el proyecto y la publicación ya están asociados.
	 */
	public void asociaPublicacion(int publicacion, String proyecto, Connection conn) throws BDException, NonExistingElementException, ExistingElementException 
	{
		String consulta1,consulta2,consulta3;
		Vector<Object []> res1,res2,res3;
		consulta1 = new String ("SELECT tipo FROM tipopublicacion WHERE idDoc = " + publicacion + ";");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");
		consulta3 = new String ("SELECT * FROM pertenecea WHERE idDoc = " + publicacion +" AND proyecto = '" + proyecto + "';");
		String asociacion = new String ("INSERT INTO pertenecea VALUES('" + publicacion + "', '" + proyecto + "');");
		
		res1 = theBaseDatos.exeQuery(consulta1, conn);
		if (res1 == null || res1.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		
		res2 = theBaseDatos.exeQuery(consulta2, conn);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res3 = theBaseDatos.exeQuery(consulta3, conn);
		if (res3 != null && res3.size() > 0) throw new ExistingElementException(ExistenceException.RELACION);
		
		theBaseDatos.exeUpdate(asociacion, conn);
	}
	
	/**
	 * Desasocia la publicacion del proyecto.
	 * @param publicacion idDoc de la publicación a asociar.
	 * @param proyecto Proyecto al que deseamos asociar la publicación.
	 * @param conn Conexión a la base de datos que utilizará de tal forma que se realizan más rapidamente
	 * las operaciones.
	 * @throws database.BDException Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException Cuando no está presente en la base de datos el idDoc de la publicación,
	 * o el proyecto o la relación entre ambos, lo podemos concretar analizando el contenido de la excepción.
	 */
	public void desasociaPublicacion(int publicacion, String proyecto, Connection conn) throws BDException, NonExistingElementException 
	{
		String consulta1,consulta2,consulta3;
		Vector<Object []> res1,res2,res3;
		consulta1 = new String ("SELECT tipo FROM tipopublicacion WHERE idDoc = " + publicacion + ";");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");
		consulta3 = new String ("SELECT COUNT(*) FROM pertenecea WHERE idDoc = " + publicacion +" AND proyecto = '" + proyecto + "';");
		String borrado = new String ("DELETE FROM pertenecea WHERE idDoc = " + publicacion +" AND proyecto = '" + proyecto + "';");
		
		res1 = theBaseDatos.exeQuery(consulta1, conn);
		if (res1 == null || res1.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		
		res2 = theBaseDatos.exeQuery(consulta2, conn);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res3 = theBaseDatos.exeQuery(consulta3, conn);
		if (res3 == null || ((Long)res3.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.RELACION);
		
		theBaseDatos.exeUpdate(borrado, conn);
	}
}
