//Source file: C:\\GENERADO\\database\\ModificadorProyectos.java

package controlador;

import java.sql.Connection;
import java.util.Vector;

import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;

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
	public void insertaProyecto(String nombre_proyecto, String nombre_administrador, Connection conn) throws ExistingElementException,BDException 
	{
		String consulta1 = "SELECT jefe FROM proyectos WHERE nombre = '" + nombre_proyecto + "';";
		Vector<Object[]> res = theBaseDatos.exeQuery(consulta1, conn);
		if (res.size() > 0) throw new ExistingElementException(ExistenceException.PROYECTO);
		String consulta2 = "INSERT INTO proyectos VALUES ('" +nombre_proyecto + "','";
		consulta2+= nombre_administrador + "');";
		theBaseDatos.exeUpdate(consulta2, conn);
	}

	/**
	 * Cambia el administrador del proyecto al nuevo pasado por parámetro.
	 * 
	 * @param nombre_proyecto - Nombre del proyecto a modificar.
	 * @param nuevo_administrador - Nuevo administrador del proyecto.
	 * @return nombre del antiguo administrador.@throws database.BDException
	 * @roseuid 47C5A19C03D8
	 */
	public String setAdmin(String nombre_proyecto, String nuevo_administrador, Connection conn) throws NonExistingElementException,BDException 
	{
		String consulta1 = "SELECT jefe FROM proyectos WHERE nombre = '" + nombre_proyecto + "';";
		Vector<Object[]> res = theBaseDatos.exeQuery(consulta1, conn);
		if (res == null || res.size() <= 0) throw new NonExistingElementException(ExistenceException.PROYECTO);
		String consulta2 = "UPDATE proyectos SET nombre = " + nombre_proyecto + ", jefe = " + nuevo_administrador +
		 "WHERE nombre = " + nombre_proyecto + ";" ;
		theBaseDatos.exeUpdate(consulta2, conn);
		return (String)res.firstElement()[0];
	}

	/**
	 * Borra el proyecto de la aplicación.
	 * @param proyecto - Nombre del proyecto a borrar
	 * @throws BDException 
	 * @roseuid 47C5A25B0290
	 */
	public void borraProyecto(String proyecto, Connection conn) throws NonExistingElementException,BDException 
	{
		String consulta1 = "SELECT jefe FROM proyectos WHERE nombre = '" + proyecto + "';";
		Vector<Object[]> res = theBaseDatos.exeQuery(consulta1, conn);
		if (res == null || res.size() <= 0) throw new NonExistingElementException(ExistenceException.PROYECTO);
		String consulta2 = "DELETE FROM proyectos WHERE nombre = '" + proyecto + "';";
		theBaseDatos.exeUpdate(consulta2, conn);

	}
	
	/**
	 * Devuelve un vector con los usuario que participan en el proyecto pasado por
	 * parámetro.
	 * @param proyecto - Proyecto sobre el que se desea realizar la consulta.
	 * @return java.util.Vector - Conjunto de String con los nombres de los usuarios
	 * buscados.
	 * @throws NonExistingElementException - En caso que el proyecto no exista.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public Vector <String> consultaUsuarios(String proyecto, Connection conn) 
		throws NonExistingElementException,BDException{
		String cons1,cons2;
		Vector <Object[]> res1,res2;
		Vector <String> result;
		cons1 = new String ("SELECT nombre FROM proyectos WHERE nombre = '" + proyecto + "';");
		cons2 = new String ("SELECT usuario FROM participaen WHERE proyecto = '" + proyecto + "';");
		result = new Vector<String>();
		
		res1 = theBaseDatos.exeQuery(cons1, conn);
		if (res1 == null || res1.size() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res2 = theBaseDatos.exeQuery(cons2, conn);
		for (int i = 0; res2!=null && i<res2.size();i++){
			String str = (String)res2.get(i)[0];
			result.add(str);
		}
		
		return result;
	}	
}
