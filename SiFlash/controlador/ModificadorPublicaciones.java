//Source file: C:\\GENERADO\\database\\ModificadorPublicaciones.java

package controlador;

import java.sql.Connection;
import java.util.Vector;

import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;

import publicaciones.Publication;
import database.BDException;
import database.BaseDatos;

/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a usuarios
 */
public class ModificadorPublicaciones {

	public BaseDatos theBaseDatos;

	public ModificadorPublicaciones(BaseDatos database) {
		this.theBaseDatos = database;
	}

	/**
	 * Método que se encarga de insertar la publicación pasada por parámetro en la base 
	 * de datos.
	 * 
	 * Excepcion si ya existe o no hay permisos.
	 * @param publicacion - Publicación a insertar.
	 * @param conn 
	 * @throws BDException 
	 * @throws ExistingElementException 
	 * @throws ExistingElementException 
	 */
	public void insertaPublicación(Publication publicacion, Connection conn) throws BDException, ExistingElementException
	{
		Vector<String> inserciones = publicacion.generaInserciones(conn);		
		theBaseDatos.exeUpdates(inserciones, conn);
	}

	/**
	 * Método que se encarga de modificar la publicación pasada por parámetro en la 
	 * base de datos, cambia los antiguos datos que contenía al respecto de la misma 
	 * por los que contiene el objeto. Para ello se basa en el idDoc, y asigna uno nuevo.
	 * @param publicacion - Nuevos datos de la publicación.
	 * @param conn 
	 * @return int - Nuevo idDoc asignado al documento.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Si la publicacion ( el idDoc) no se encuentra en
	 * la base de datos.
	 * @throws ExistingElementException 
	 */
	public int modificaPublicación(Publication publicacion, Connection conn) throws NonExistingElementException,BDException, ExistingElementException 
	{	
		int id_doc = publicacion.getIdDoc();
		String consulta = new String ("SELECT tipo FROM tipopopublicacion WHERE idDoc = " + id_doc + ";");
		Vector<Object []> res = theBaseDatos.exeQuery(consulta, conn);
		if (res == null || res.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);		
		borraPublicación(publicacion.getIdDoc(), conn);
		insertaPublicación(publicacion, conn);
		return publicacion.getIdDoc();
	}

	/**
	 * Elimina la publicación cuyo id se le pasa por parámetro.
	 * Para ello elimina previamente todos los vinculos que tenga, tanto con autores,
	 * keywords, proyectos, etc. 
	 * @param id_doc - IdDoc de la publicación que se desea borrar.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - En caso que el documento no exista.
	 */
	public void borraPublicación(int id_doc, Connection conn) throws BDException, NonExistingElementException 
	{
		String consulta = new String ("SELECT tipo FROM tipopopublicacion WHERE idDoc = " + id_doc + ";");
		Vector <String> borrados = new Vector <String>();
		Vector<Object []> res = theBaseDatos.exeQuery(consulta, conn);
		if (res == null || res.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		String tabla = (String) res.firstElement()[0];
		borrados.add(new String ("DELETE FROM tienekey WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM pertenecea WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM escrito_editado_por WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM tipopublicacion WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM " + tabla + " WHERE idDoc = " + id_doc + ";"));
		theBaseDatos.exeUpdates(borrados, conn);
	}

	/**
	 * Asocia una publicación a un proyecto.
	 * @param publicacion - Publicación a asociar.
	 * @param proyecto - Proyecto del que asociar.
	 * @throws database.BDException
	 * @throws NonExistingElementException 
	 * @throws ExistingElementException 
	 * @roseuid 47C59B0E0213
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
	 * @param publicacion - Publicación a desasociar.
	 * @param proyecto - Proyecto del que desasociar.
	 * @throws database.BDException
	 * @throws NonExistingElementException 
	 * @roseuid 47C59B0E0213
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
