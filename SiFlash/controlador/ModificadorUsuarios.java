//Source file: C:\\GENERADO\\database\\ModificadorUsuarios.java

package controlador;

import java.sql.Connection;
import java.util.Vector;

import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;

import personas.Usuario;
import database.BDException;
import database.BaseDatos;

/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a usuarios
 */
public class ModificadorUsuarios 
{
	/**
	 * Base de datos sobre la que realiza las operaciones.
	 */	
	public BaseDatos theBaseDatos;


	/**
	 *  Constructor pasandole el parametro de la base de datos que posee.
	 * @param database - Objeto base de datos sobre la que trabaja.
	 */		
	public ModificadorUsuarios(BaseDatos database) {
		this.theBaseDatos = database;
	}

	/**
	 * Inserta en la base de datos el usuario cuyos datos se han pasado por par�metro
	 * @param usuario - Usuario que se debe pasar por par�metro
	 * @param proyecto - Proyecto al que estar� unicialmente asociado el usuario.
	 * @param conn - Conexi�n a la base de datos que utilizar� de tal forma que se realizan m�s rapidamente
	 * las operaciones.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws ExistingElementException - Cuando ya existe ese usuario. 
	 * @throws NonExistingElementException - Cuando el proyecto no existe.
	 */
	public void creaUsuario(Usuario usuario, String proyecto, Connection conn) throws BDException, ExistingElementException, NonExistingElementException 
	{
		String consulta, insercion;
		consulta = new String ("SELECT * FROM usuarios WHERE nombre = '" + usuario.getNombre() +"';");
		insercion = new String ("INSERT INTO usuarios VALUES ('" + usuario.getNombre() +"','"
				+ usuario.getPassword() +"','" + usuario.getTipo()+"');");
		
		Vector<Object []> res = theBaseDatos.exeQuery(consulta, conn);
		if (res != null && res.size() > 0) throw new ExistingElementException(ExistenceException.USUARIO);
		theBaseDatos.exeUpdate(insercion, conn);
		
		if (proyecto != null)
		{
			consulta = new String ("SELECT * FROM proyectos WHERE nombre = '" + proyecto +"';");
			insercion = new String("INSERT INTO participaen VALUES('" + usuario.getNombre() + "', '" + proyecto + "');");
			
			res = theBaseDatos.exeQuery(consulta, conn);
			if (res == null || res.size() == 0) throw new NonExistingElementException(ExistenceException.PROYECTO);
			theBaseDatos.exeUpdate(insercion, conn);
		}
	}
	
	/**
	 * Devuelve un vector con los proyectos a los que pertenece el usuario indicado.
	 * @param usuario - Usuario sobre el que se desea realizar la consulta.
	 * @param conn - Conexi�n a la base de datos que utilizar� de tal forma que se realizan m�s rapidamente
	 * las operaciones.
	 * @return java.util.Vector - Conjunto de String con los nombres de los proyectos
	 * buscados
	 * @throws NonExistingElementException - En caso que el usuario no exista.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public Vector <String> consultaProyectos(String usuario, Connection conn) 
		throws NonExistingElementException,BDException{
		String cons1,cons2;
		Vector <Object[]> res1,res2;
		Vector <String> result;
		cons1 = new String ("SELECT nombre FROM usuario WHERE nombre = '" + usuario + "';");
		cons2 = new String ("SELECT proyecto FROM participaen WHERE usuario = '" + usuario + "';");
		result = new Vector<String>();
		
		res1 = theBaseDatos.exeQuery(cons1, conn);
		if (res1 == null || res1.size() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		res2 = theBaseDatos.exeQuery(cons2, conn);
		for (int i = 0; res2!=null && i<res2.size();i++){
			String str = (String)res2.get(i)[0];
			result.add(str);
		}
		
		return result;
	}

	/**
	 * A�ade un usuario a un proyecto.
	 * @param usuario - Nombre del usuario a a�adir.
	 * @param proyecto - Nombre del proyecto al cual se quiere a�adir.
	 * @param conn - Conexi�n a la base de datos que utilizar� de tal forma que se realizan m�s rapidamente
	 * las operaciones.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Cuando el usuario o el proyecto no existen. Se concreta en el contenido
	 * de la excepci�n.
	 * @throws ExistingElementException - Cuando ya est�n asociados usuario y proyecto. 
	 */
	public void asociaProyecto(String usuario, String proyecto, Connection conn) throws NonExistingElementException,BDException, ExistingElementException 
	{
		String consulta1, consulta2, consulta3, insercion;
		consulta1 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre = '" + usuario +"';");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");
		consulta3 = new String ("SELECT * FROM participaen WHERE usuario = '" + usuario +"' AND proyecto = '" + proyecto + "';");
		insercion = new String ("INSERT INTO participaen VALUES ('" + usuario + "','" + proyecto +
				"') ON DUPLICATE KEY UPDATE usuario=usuario;");
		
		Vector<Object []> res1, res2, res3;
		res1 = theBaseDatos.exeQuery(consulta1, conn);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		res2 = theBaseDatos.exeQuery(consulta2, conn);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res3 = theBaseDatos.exeQuery(consulta3, conn);
		if (res3.size() > 0) throw new ExistingElementException(ExistenceException.RELACION);
		
		theBaseDatos.exeUpdate(insercion, conn);
	}

	
	/**
	 * Elimina el usuario en cuestion del proyecto.
	 * @param usuario - Nombre del usuario a desvilcular.
	 * @param proyecto - Nombre del proyecto al cual se quiere de baja al usuario.
	 * @param conn - Conexi�n a la base de datos que utilizar� de tal forma que se realizan m�s rapidamente
	 * las operaciones.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Cuando no est� presente en la base de datos el usuario,
	 * o el proyecto o la relaci�n entre ambos, lo podemos concretar analizando el contenido de la excepci�n.
	 */
	public void desasociaProyecto(String usuario, String proyecto, Connection conn) throws NonExistingElementException,BDException 
	{
		String consulta1,consulta2,consulta3,borrado;
		consulta1 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre = '" + usuario +"';");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");		
		consulta3 = new String ("SELECT COUNT(*) FROM participaen WHERE usuario = '" + usuario +"'" +
				" AND proyecto = '" +  proyecto +"';");
		borrado = new String ("DELETE FROM participaen WHERE usuario ='" + usuario +"'" +
				" AND proyecto = '" +  proyecto +"';");
		
		Vector<Object []> res1,res2,res3;
		res1 = theBaseDatos.exeQuery(consulta1, conn);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		res2 = theBaseDatos.exeQuery(consulta2, conn);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res3 = theBaseDatos.exeQuery(consulta3, conn);
		if (res3 == null || ((Long)res3.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.RELACION);
		
		theBaseDatos.exeUpdate(borrado, conn);

	}

	/**
	 * Elimina al usuario de la aplicacion, y le proporciona las publicaciones que este hab�a subido al nuevo.
	 * 
	 * @param usuario - Nombre del usuario que se quiere eliminar.
	 * @param nuevoUserPublicaciones - Nombre del usuario al que se le asignar�n las publicaciones subidas por el usuario a eliminar.
	 * @param conn - Conexi�n a la base de datos que utilizar� de tal forma que se realizan m�s rapidamente
	 * las operaciones.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Cuando no existe alguno de los usuarios.
	 */
	public void eliminaUsuario(String usuario, String nuevoUserPublicaciones, Connection conn) throws NonExistingElementException,BDException 
	{
		String consulta1, consulta2;
		Vector<String> updates = new Vector <String>();
		consulta1 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre ='" + usuario +"';");
		consulta2 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre ='" + nuevoUserPublicaciones +"';");
		
		Vector<Object []> res1;
		res1 = theBaseDatos.exeQuery(consulta1, conn);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		Vector<Object []> res2;
		res2 = theBaseDatos.exeQuery(consulta2, conn);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		Vector<String> updatesPublicaciones = updatesPublicaciones(usuario, nuevoUserPublicaciones);
		updates.addAll(updatesPublicaciones);
		updates.add(new String ("DELETE FROM participaen WHERE usuario ='" + usuario +"';"));
		updates.add(new String ("DELETE FROM usuarios WHERE nombre ='" + usuario +"';"));
		
		theBaseDatos.exeUpdates(updates, conn);
	}

	
	/**
	 * M�todo auxiliar para generar un conjunto de sentencias SQL para traspasar los documentos que posee
	 * usuario a nuevoUsuarioPublicaciones.
	 * @param usuario - Actual propietario de los documentos.
	 * @param nuevoUserPublicaciones - Usuario al que se le pasar�n los documentos de usuario.
	 * @return Vector con el conjunto de clausulas para realizar las actualizaciones.
	 */
	private Vector<String> updatesPublicaciones(String usuario, String nuevoUserPublicaciones)
	{
		Vector<String> updatesPublicacion = new Vector<String>();
		String[] tiposPublicaciones = {"article", "book", "booklet", "conference", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished"};
		
		int numPublicaciones = tiposPublicaciones.length;
		for(int i = 0; i < numPublicaciones; i++)
		{
			String nuevo = "UPDATE " + tiposPublicaciones[i] + " SET user='" + nuevoUserPublicaciones + "' WHERE user='" + usuario + "';";
			updatesPublicacion.add(nuevo);
		}
		return updatesPublicacion;
	}
}
