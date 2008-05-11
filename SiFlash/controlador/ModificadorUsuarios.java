//Source file: C:\\GENERADO\\database\\ModificadorUsuarios.java

package controlador;

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
	public BaseDatos theBaseDatos;

	public ModificadorUsuarios(BaseDatos database) {
		this.theBaseDatos = database;
	}

	/**
	 * Inserta en la base de datos es usuario pasado por parámetro.
	 * @param usuario - Usuario que se debe pasar por parámetro
	 * @throws database.BDException
	 * @throws ExistingElementException 
	 * @roseuid 47C5913A0271
	 */
	public void creaUsuario(Usuario usuario) throws BDException, ExistingElementException 
	{
		String consulta, insercion;
		consulta = new String ("SELECT * FROM usuarios WHERE nombre = '" + usuario.getNombre() +"';");
		insercion = new String ("INSERT INTO usuarios VALUES ('" + usuario.getNombre() +"','"
				+ usuario.getPassword() +"','" + usuario.getTipo()+"');");
		
		Vector<Object []> res = theBaseDatos.exeQuery(consulta);
		if (res != null && res.size() > 0) throw new ExistingElementException(ExistenceException.USUARIO);
		theBaseDatos.exeUpdate(insercion);
	}

	/**
	 * Añade un usuario a un proyecto, en caso de que ya estén asociados no ocurre nada.
	 * @param String - Nombre del usuario a añadir.
	 * @param proyecto - Nombre del proyecto al cual se quiere añadir.
	 * @throws database.BDException
	 * @roseuid 47C5979A01F4
	 */
	public void asociaProyecto(String usuario, String proyecto) throws NonExistingElementException,BDException 
	{
		String consulta1,consulta2,insercion;
		consulta1 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre = '" + usuario +"';");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");
		insercion = new String ("INSERT INTO participa_en VALUES ('" + usuario + "','" + proyecto +
				"') ON DUPLICATE KEY UPDATE usuario=usuario;");
		
		Vector<Object []> res1,res2;
		res1 = theBaseDatos.exeQuery(consulta1);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		res2 = theBaseDatos.exeQuery(consulta2);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		theBaseDatos.exeUpdate(insercion);
	}

	
	/**
	 * Elimina el usuario en cuestion del proyecto.
	 * @param String - Nombre del usuario a desvilcular.
	 * @param proyecto - Nombre del proyecto al cual se quiere de baja al usuario.
	 * @throws database.BDException
	 * @roseuid 47C599770186
	 */
	public void desasociaProyecto(String usuario, String proyecto) throws NonExistingElementException,BDException 
	{
		String consulta1,consulta2,consulta3,borrado;
		consulta1 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre = '" + usuario +"';");
		consulta2 = new String ("SELECT COUNT(*) FROM proyectos WHERE nombre = '" + proyecto +"';");		
		consulta3 = new String ("SELECT COUNT(*) FROM participaen WHERE usuario = '" + usuario +"'" +
				" AND proyecto = '" +  proyecto +"';");
		borrado = new String ("DELETE FROM participaen WHERE usuario ='" + usuario +"'" +
				" AND proyecto = '" +  proyecto +"';");
		
		Vector<Object []> res1,res2,res3;
		res1 = theBaseDatos.exeQuery(consulta1);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		res2 = theBaseDatos.exeQuery(consulta2);
		if (res2 == null || ((Long)res2.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.PROYECTO);
		
		res3 = theBaseDatos.exeQuery(consulta3);
		if (res3 == null || ((Long)res3.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.RELACION);
		
		theBaseDatos.exeUpdate(borrado);

	}

	/**
	 * Elimina al usuario de la aplicacion.
	 * 
	 * Excepcion si no existe o no se tienen permisos.
	 * @param usuario - Nombre del usuario que se quiere eliminar.
	 * @throws database.BDException
	 * @roseuid 47C599E803C8
	 */
	public void eliminaUsuario(String usuario) throws NonExistingElementException,BDException 
	{
		String consulta1;
		Vector<String> borrados = new Vector <String>();
		consulta1 = new String ("SELECT COUNT(*) FROM usuarios WHERE nombre ='" + usuario +"';");
		borrados.add(new String ("DELETE FROM participaen WHERE usuario ='" + usuario +"';"));
		borrados.add(new String ("DELETE FROM usuarios WHERE nombre ='" + usuario +"';"));
		
		Vector<Object []> res1;
		res1 = theBaseDatos.exeQuery(consulta1);
		if (res1 == null || ((Long)res1.firstElement()[0]).intValue() < 1) throw new NonExistingElementException(ExistenceException.USUARIO);
		
		theBaseDatos.exeUpdates(borrados);
	}
}
