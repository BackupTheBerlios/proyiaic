//Source file: C:\\GENERADO\\database\\ModificadorPublicaciones.java

package controlador;

import java.util.Vector;

import controlador.exceptions.ExistenceException;
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
	 * @throws BDException 
	 * @roseuid 47C5927F006D
	 */
	public void insertaPublicación(Publication publicacion) throws BDException 
	{
		Vector<String> inserciones = publicacion.generaInserciones();		
		theBaseDatos.exeUpdates(inserciones);
	}

	/**
	 * Método que se encarga de modificar la publicación pasada por parámetro en la 
	 * base de datos, cambia los antiguos datos que contenía al respecto de la misma 
	 * por los que contiene el objeto. Para ello se basa en el idDoc, que no puede 
	 * cambiar.
	 * 
	 * Excepcion si no existe o no hay permisos.
	 * @param publicacion - Nuevos datos de la publicación.
	 * @throws database.BDException
	 * @roseuid 47C59446001F
	 */
	public void modificaPublicación(Publication publicacion) throws NonExistingElementException,BDException 
	{	
		borraPublicación(publicacion.getIdDoc());
		insertaPublicación(publicacion);
	}

	/**
	 * Elimina la publicación cuyo id se le pasa por parámetro.
	 * Para ello la desvincula previamente de todos los proyectos a los que esté 
	 * asociada.
	 * 
	 * Excepcion si no hay permisos.
	 * @param id_doc - IdDoc de la publicación que se desea borrar.
	 * @throws database.BDException
	 * @roseuid 47C59A7002BF
	 */
	public void borraPublicación(int id_doc) throws BDException 
	{
		String consulta = new String ("SELECT tipo FROM tipopopublicacion WHERE idDoc = " + id_doc + ";");
		Vector <String> borrados = new Vector <String>();
		Vector<Object []> res = theBaseDatos.exeQuery(consulta);
		if (res == null || res.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		String tabla = (String) res.firstElement()[0];
		borrados.add(new String ("DELETE FROM tienekey WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM pertenecea WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM escrito_editado_por WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM tipopublicacion WHERE idDoc = " + id_doc + ";"));
		borrados.add(new String ("DELETE FROM " + tabla + " WHERE idDoc = " + id_doc + ";"));
		theBaseDatos.exeUpdates(borrados);
	}

	/**
	 * Desasocia la publicacion del proyecto.
	 * @param publicacion - Publicación a desasociar.
	 * @param proyecto - Proyecto del que desasociar.
	 * @throws database.BDException
	 * @roseuid 47C59B0E0213
	 */
	public void desasociaPublicacion(int publicacion, String proyecto) throws BDException 
	{
		String consulta1,consulta2,consulta3;
		Vector<Object []> res1,res2,res3;
		consulta1 = new String ("SELECT tipo FROM tipopopublicacion WHERE idDoc = " + publicacion + ";");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");
		consulta3 = new String ("SELECT COUNT(*) FROM pertenecea WHERE idDoc = " + publicacion +" AND proyecto = '" + proyecto + "';");
		String borrado = new String ("DELETE FROM pertenecea WHERE idDoc = " + publicacion +" AND proyecto = '" + proyecto + "';");
		
		res1 = theBaseDatos.exeQuery(consulta1);
		if (res1 == null || res1.size() <1 ) throw new NonExistingElementException (ExistenceException.DOCUMENTO);
		
		res2 = theBaseDatos.exeQuery(consulta2);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res3 = theBaseDatos.exeQuery(consulta3);
		if (res3 == null || ((Long)res3.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.RELACION);
		
		theBaseDatos.exeUpdate(borrado);
	}
}
