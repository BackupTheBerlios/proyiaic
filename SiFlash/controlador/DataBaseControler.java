package controlador;

import java.sql.Connection;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import personas.AutorEditor;
import personas.Usuario;
import publicaciones.CodigosDatos;
import publicaciones.Publication;
import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;
import controlador.exceptions.PermissionException;
import database.BDException;
import database.BaseDatos;



/**
 * Clase que implementa una operación por cada funcionalidad claramente definida 
 * que tiene que tener la aplicación respecto a la base de datos.
 */
public class DataBaseControler
{

	/**
	 * Base de datos sobre la que se realizarán las operaciones.
	 */
	private BaseDatos database;

	/**
	 * Objeto de la clase ConsultorBaseDatos que nos ayudará a realizar las operaciones
	 * necesarias.
	 */
	private ConsultorBaseDatos consultor;
	
	/**
	 * Objeto de la clase ModificadorAutores que nos ayudará a realizar las operaciones
	 * más complejas relativas a Autores/Editores.
	 */
	private ModificadorAutores modif_autores;
	
	/**
	 * Objeto de la clase ModificadorProyectos que nos ayudará a realizar las operaciones
	 * más complejas relativas a los proyectos.
	 */
	private ModificadorProyectos modif_proyectos;
	
	/**
	 * Objeto de la clase ModificadorUsuarios que nos ayudará a realizar las operaciones
	 * más complejas relativas a los usuarios de la aplicación.
	 */
	private ModificadorUsuarios modif_user;
	
	/**
	 * Objeto de la clase ModificadorPublicaciones que nos ayudará a realizar las operaciones
	 * más complejas relativas a las publicaciones.
	 */
	private ModificadorPublicaciones modif_pub;

	/**
	 * Constructor por defecto de la clase, iniciará todos sus atributos en relaciones
	 * a la base de datos prefijada por defecto.
	 */
	public DataBaseControler() 
	{
		database = new BaseDatos();
		initSubcontrolers();
	}
	
	/**
	 * Constructor de la clase proporcionandole la base de datos sobre la que debe operar.
	 * Iniciará los subcontroladores en base a ella.
	 * @param base Base de datos sobre la que debe operar.
	 */
	public DataBaseControler(BaseDatos base){
		database = base;
		initSubcontrolers();
	}	

	/**
	 * Método para iniciar los distintos subcontrolares, es decir, tanto el ConsultorBaseDatos
	 * como los distintos modificadores.
	 */
	private void initSubcontrolers() {		
		consultor = new ConsultorBaseDatos (database);
		modif_autores = new ModificadorAutores (database);
		modif_proyectos = new ModificadorProyectos(database);
		modif_pub = new ModificadorPublicaciones (database);
		modif_user = new ModificadorUsuarios (database);
	}

	/**
	 * Método que realiza una consulta sobre la base de datos.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado. Como situación general, si no queremos filtrar por ese campo simplemente hay que ponerlo a null.
	 * A la hora de realizar la subconsulta correspondiente para cada tipo de documento 
	 * buscado solo se tendrán en cuenta aquellos parámetros que le afecten. 
	 * 
	 * @param proyecto - Proyecto al que deseamos que pertenezca el documento.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param autores - Vector con un conjunto de AutorEditor que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param editores - Vector con un conjunto de Strings que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector con un conjunto de String que indica los años a los que puede pertenecer
	 * el proyecto, de esta manera podríamos admitir por ejemplo proyectos de 1996 y 1998 sin necesidad
	 * de admitir aquellos pertenecientes a 1997. null para no filtrar por este parámetro.  
	 * hay límite.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se realiza o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma a la que 
	 * pertenece. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene la 
	 * publicación. null para no filtrar por este campo.
	 * @param keys - Vector con un conjunto de String que representa keywords que han de describir
	 * la publicación buscada.
	 * @param parecido_** - Indica si deseamos buscar exactamente el parametro pasado, o por
	 * el contrario cualquier palabra que lo contenga. Por Ejemplo: si pasamos como parametro publisher "marc"
	 * en caso que parecido_publisher == false, buscaía únicamente documentos cuyo publisher sea "marc",
	 * por contra, si parecido_publisher == true, buscaría documentos cuyo publisher sea del tipo "*marc*",
	 * representando el * cualquier cadena de caracteres, incluyendo la cadena vacia.
	 * @param user - Indica el usuario que debe haber subido la publicación.
	 * @return vector construido con las publicaciones que cumplen los 
	 * requisitos.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Esperabamos encontrar un dato que no se encuentra.
	 */
	public Vector<Publication> consultaDocumentos(String proyecto, int tipo_publicaciones, final Vector<AutorEditor> autores, final Vector<AutorEditor> editores, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, String bookTitle, Vector<String> keys, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, boolean parecido_keys, String user) throws BDException, NonExistingElementException 
	{
		Connection conn = database.abreConexion();
		// Primero localizar a los autores y editores.
		try{
			Vector<Object[]> result;
			if (user != null)
			{
				result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';", conn);
				if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
			}
			if (proyecto != null)
			{
				result = database.exeQuery("SELECT nombre FROM proyectos WHERE nombre = '" + proyecto + "';", conn);
				if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.PROYECTO);
			}
			Vector <AutorEditor> v_autores = consultor.buscaAutores(autores, conn);
			Vector <AutorEditor> v_editores = consultor.buscaAutores(editores, conn);
			Vector <Integer> v_authors= new Vector<Integer>();
			Vector <Integer> v_editors= new Vector<Integer>();
			for (int i = 0; i< v_autores.size();i++){
				v_authors.add(new Integer (v_autores.get(i).getId()));
			}

			for (int i = 0; i< v_editores.size();i++){
				v_editors.add(new Integer (v_editores.get(i).getId()));
			}

			return consultor.getPublicaciones(proyecto, tipo_publicaciones, v_authors, v_editors, title, parecido_title, publisher, parecido_publisher, journal, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, school, parecido_school,keys, bookTitle, parecido_bookTitle, user, conn);
		}finally{
			database.cierraConexion(conn);
		}
	}

	/**
	 * Método que devuelve una lista con los autores que coinciden total o parcialmente 
	 * con los datos parámetro.
	 * @param nombre - Nombre del autor.
	 * @param apellido - Apellidos del autor.
	 * @return java.util.Vector Vector con los autores y/o editores consultados
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public Vector<AutorEditor> consultaAutores(String nombre, String apellido, boolean total_o_parcial) throws BDException 
	{
		Connection conn = database.abreConexion();
		try{
			Vector<AutorEditor> vector = new Vector<AutorEditor>();
			String consulta = new String("SELECT * FROM AutoresEditores WHERE ");
			String cons = null;
			if (nombre != null){
				if (cons == null) cons = new String();
				else cons += " AND ";

				if (total_o_parcial) cons+= "nombre LIKE '%" + nombre + "%'";
				else cons += "nombre = '" + nombre + "'"; 			
			}

			if (apellido != null) {
				if (cons == null) cons = new String();
				else cons += " AND ";

				if (total_o_parcial) cons += "apellidos LIKE '%" + apellido + "%'";
				else cons += "apellido = '" + apellido + "'";
			}

			if (cons == null) cons = new String ("TRUE");

			consulta+= cons + ";";


			Vector<Object[]> resultado = database.exeQuery(consulta, conn);

			for (int i=0; resultado != null && i<resultado.size();i++){
				Object[] array = resultado.get(i);
				vector.add(new AutorEditor(array));			
			}
			return vector;	
		} finally {
			database.cierraConexion(conn);
		}
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
	public Vector<String> consultaUsuariosProyecto(String proyecto) 
	throws NonExistingElementException,BDException 
	{
		Connection conn = database.abreConexion();
		try{
			return modif_proyectos.consultaUsuarios(proyecto, conn);
		}finally{
			database.cierraConexion(conn);
		}
	}

	/**
	 * Devuelve un vector con los proyectos a los que pertenece el usuario indicado.
	 * @param usuario - Usuario sobre el que se desea realizar la consulta.
	 * @return java.util.Vector - Vector con los nombres de los proyectos a los que está
	 * relacionado el usuario consultado.
	 * @throws NonExistingElementException - En caso que el usuario no exista.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public Vector<String> consultaProyectosUsuario(String usuario) throws BDException,NonExistingElementException
	{
		Connection conn = database.abreConexion();
		try{
			return modif_user.consultaProyectos(usuario, conn);
		}finally{
			database.cierraConexion(conn);
		}
	}

	/**
	 * Inserta la publicacion pasada por parámetro en la base de datos.
	 * @param publicacion - Publicacion a insertar.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws ExistingElementException - Si ya existe el documento.
	 */
	public String insertaDocumento(Publication publicacion) throws BDException
	{		
		Connection conn = database.abreConexion();
		try
		{
			ejecutaString("BEGIN;", conn);
			modif_pub.insertaPublicacion(publicacion, conn);
			ejecutaString("COMMIT;", conn);
			return "La publicación se ha insertado correctamente.";
		}
		catch (ExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.AUTOR)
				return "Se ha producido un error: un autor/editor nuevo ya existe";
			else
				return "Error desconocido al insertar documento.";
		}
		catch(BDException e)
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 
		finally{
			database.cierraConexion(conn);
		}

	}

	/**
	 * Inserta el usuario pasado por parámetro en la base de datos, y lo relaciona con el
	 * proyecto cuyo nombre se pasa por parámetro.
	 * @param usuario - Usuario a insertar.
	 * @param proyecto - Proyecto con el que se encontrará relacionado. 
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Si el proyecto al que se desea asociar no existe.
	 * @throws ExistingElementException - Si el Usuario ya existe.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public String insertaUsuario(Usuario usuario, String proyecto) throws BDException
	{
		Connection conn = database.abreConexion();
		try 
		{
			ejecutaString("BEGIN;", conn);
			modif_user.creaUsuario(usuario, proyecto, conn);
			ejecutaString("COMMIT;", conn);
			return "El nuevo usuario ha sido creado con éxito.";
		} 
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 
		catch (ExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.USUARIO)
				return "Error al crear el nuevo usuario: el nick indicado ya está dado de alta.";
			else
				return "Error desconocido al insertar usuario.";
		} 
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al crear el nuevo usuario: el proyecto indicado no existe.";
			else
				return "Error desconocido al insertar usuario.";
		}
		finally{
			database.cierraConexion(conn);
		}

	}

	/**
	 * Método que se encarga de modificar la publicación pasada por parámetro en la 
	 * base de datos, cambia los antiguos datos que contenía al respecto de la misma 
	 * por los que contiene el objeto. Para ello se basa en el idDoc, y asigna uno nuevo.
	 * <br> <b> Es muy importante tener en cuenta que asigna un nuevo idDoc </b>
	 * @param publicacion - Nuevos datos de la publicación.
	 * @return Un String indicando el resultado de la modificación.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Si la publicacion ( el idDoc) no se encuentra en
	 * la base de datos. 
	 */
	public String modificaDocumento(Publication publicacion) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			ejecutaString("BEGIN;", conn);
			modif_pub.modificaPublicacion(publicacion, conn);
			ejecutaString("COMMIT;", conn);
			return "La modificación del documento se ha realizado correctamente.";
		} 
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.DOCUMENTO)
				return "Error al realizar la modificación: el documento especificado no existe.";
			else
				return "Error desconocido al modificar el documento.";
		} 
		catch (ExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.AUTOR)
				return "Se ha producido un error: un autor/editor nuevo ya existe"; //No debería dar nunca!!!
			else
				return "Error desconocido al modificar documento.";
		}
		finally{
			database.cierraConexion(conn);
		}
	}

	/** Modifica los datos del autor cuyo idAutor se ha pasado como parámetro, sustituyendolos por
	 *  los nuevos datos que se obtienen como parámetro.
	 *  @param id_autor - Identificador único de Autor del autor que se desea modificar.
	 *  @param nombre -  Nuevo nombre que se quiere almacenar en la base de datos.
	 *  @param apellidos -  Apellidos nuevos del autor que se quieren almacenar en la base de datos.
	 * 	@throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 *	 analizando la clase concreta de BDException.
	 * 	@throws NonExistingElementException - Si el AutorEditor no se encuentra en la base de datos. 
	 */
	public String modificaAutor(int id_autor, String nombre, String apellidos) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			ejecutaString("BEGIN;", conn);
			modif_autores.modificaAutor(id_autor, nombre, apellidos, conn);
			ejecutaString("COMMIT;", conn);
			return "La modificación del autor/editor se ha realizado correctamente.";
		}
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		}
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.AUTOR)
				return "Error al realizar la modificación: el autor/editor especificado no existe.";
			else
				return "Error desconocido al modificar el autor/editors.";
			
		}
		finally
		{
			database.cierraConexion(conn);
		}
	}

	/**
	 * Elimina la publicación cuyo id se le pasa por parámetro.
	 * Para ello elimina previamente todos los vinculos que tenga, tanto con autores,
	 * keywords, proyectos, etc. 
	 * @param idDoc - IdDoc del documento que se desea borrar.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - En caso que el documento no exista.
	 */
	public String eliminaDocumento(String idDoc) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			int id_doc = Integer.parseInt(idDoc);
			ejecutaString("BEGIN;", conn);
			modif_pub.borraPublicacion(id_doc, conn);
			ejecutaString("COMMIT;", conn);
			return "La eliminación del documento se ha realizado correctamente.";
		}
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		}
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.DOCUMENTO)
				return "Error al realizar la eliminación: el documento especificado no existe.";
			else
				return "Error al eliminar: no se ha encontrado el documento especificado.";
		}
		finally
		{
			database.cierraConexion(conn);
		}
	}

	/**
	 * Asocia el usuario proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea asociar al usuario.
	 * @param usuario - Usuario que se desea asociar.
	 * @throws NonExistingElementException - Si no existe el usuario o el proyecto, 
	 * se indica en parametro tipo de la excepcion cual es que no existe.   
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public String asociaUsuarioProyecto(String proyecto, String usuario) throws BDException
	{
		Connection conn = database.abreConexion();
		try {
			ejecutaString("BEGIN;", conn);
			modif_user.asociaProyecto(usuario, proyecto, conn);
			ejecutaString("COMMIT;", conn);
			return "Vinculación realizada correctamente.";
		} 
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.USUARIO)
				return "Error al vincular: el usuario no existe.";
			else if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al vincular: el proyecto no existe.";
			else
				return "Error desconocido al vincular.";
		} 
		catch (ExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.RELACION)
				return "Error al vincular: la relación ya existe.";
			else
				return "Error desconocido al vincular.";
		}
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 	
		finally 
		{
			database.cierraConexion(conn);
		}
	}

	/**
	 * Desasocia el usuario proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea desasociar al usuario.
	 * @param usuario - Usuario que se desea desasociar.
	 * @throws NonExistingElementException - Si no existe el usuario, el proyecto o la relacion, 
	 * se indica en parametro tipo de la excepcion cual es que no existe.   
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public String desasociaUsuarioProyecto(String proyecto, String usuario) throws BDException
	{
		Connection conn = database.abreConexion();
		try 
		{
			ejecutaString("BEGIN;", conn);
			modif_user.desasociaProyecto(usuario, proyecto, conn);
			ejecutaString("COMMIT;", conn);
			return "Desvinculación realizada correctamente.";
		} 
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.USUARIO)
				return "Error al desvincular: el usuario no existe.";
			else if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al desvincular: el proyecto no existe.";
			else if (e.getTipo() == ExistenceException.RELACION)
				return "Error al desvincular: la relación no existe.";
			else
				return "Error desconocido al desvincular.";
		}	
		finally 
		{
			database.cierraConexion(conn);
		}		
	}

	/**
	 * Asocia el documento proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea asociar el documento.
	 * @param documento - Documento que se desea desasociar.
	 * @throws controlador.exceptions.NonExistingElementException - Cuando no existe el 
	 * proyecto o el documento, podremos ver cual de ellos concretamente
	 * es el que no existe consultando el campo tipo de la excepción.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws ExistingElementException - Cuando el proyecto y el documento ya se encuentran
	 * asociados.  
	 */
	public String asociaDocumentoProyecto(String proyecto, int documento) throws BDException
	{
		Connection conn = database.abreConexion();
		try 
		{
			ejecutaString("BEGIN;", conn);
			modif_pub.asociaPublicacion(documento, proyecto, conn);
			ejecutaString("COMMIT;", conn);
			return "Vinculación realizada correctamente.";
		} 
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.DOCUMENTO)
				return "Error al vincular: el documento no existe.";
			else if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al vincular: el proyecto no existe.";
			else
				return "Error desconocido al vincular.";
		} 
		catch (ExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.RELACION)
				return "Error al vincular: la relación ya existe.";
			else
				return "Error desconocido al vincular.";
		} 	
		finally 
		{
			database.cierraConexion(conn);
		}			
	}
	
	/**
	 * Desasocia el documento proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea desasociar al proyecto.
	 * @param documento - Documento que se desea desasociar.
	 * @throws controlador.exceptions.NonExistingElementException - Cuando no existe el 
	 * proyecto, el documento o la relación, podremos ver cual de ellos concretamente
	 * es el que no existe consultando el campo tipo de la excepción.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public String desasociaDocumentoProyecto(String proyecto, int documento) throws BDException
	{
		Connection conn = database.abreConexion();
		try 
		{
			ejecutaString("BEGIN;", conn);
			modif_pub.desasociaPublicacion(documento, proyecto, conn);
			ejecutaString("COMMIT;", conn);
			return "Desvinculación realizada correctamente.";
		} 
		catch (NonExistingElementException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.DOCUMENTO)
				return "Error al desvincular: el documento no existe.";
			else if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al desvincular: el proyecto no existe.";
			else if (e.getTipo() == ExistenceException.RELACION)
				return "Error al desvincular: la relación no existe.";
			else
				return "Error desconocido al desvincular.";
		} 
		catch (BDException e) 
		{
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		} 	
		finally
		{
			database.cierraConexion(conn);
		}			
	}

	/**
	 * Elimina al usuario de la aplicacion, y asigna las publicaciones que ha subido
	 * este al usuario pasado como segundo parámetro.
	 * @param usuario - Nombre del usuario que se quiere eliminar.
	 * @param nuevoUserPublicaciones - Nombre del usuario al que se le asignarán 
	 * las publicaciones subidas por el usuario a eliminar.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - En caso de que no existan alguno de los dos usuarios.
	 * Si no existe el usuario a eliminar su campo int llevará el valor USUARIO, mientras
	 * que si el que no existe es el nuevoUserPublicaciones este campo tomará el valor
	 * INDEFINIDA.
	 */
	public String eliminaUsuario(String usuario, String nuevoUserPublicaciones) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			ejecutaString("BEGIN;", conn);
			modif_user.eliminaUsuario(usuario, nuevoUserPublicaciones, conn);
			ejecutaString("COMMIT;", conn);
			return "Eliminación realizada correctamente.";
		} 
		catch (NonExistingElementException e) {
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.USUARIO)
				return "Error al eliminar: el usuario a eliminar o el que hereda las publicaciones no existe.";
			else
				return "Error desconocido al eliminar.";
		} 
		catch (BDException e) {
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		}
		finally
		{
			database.cierraConexion(conn);
		}

	}


	/**
	 * Método para comprobar si el usuario cuyo nombre se pasa por parámetro se encuentra
	 * en la base de datos, y le corresponde esa password.
	 * @param nombre - Nombre del usuario.
	 * @param password - Password del usuario.
	 * @return String - Si coinciden usuario y contraseña devolvera "user", "jefe" o "admin"
	 * dependiendo del tipo de usuario que sea.
	 * @throws BDException - El usuario y la pass no coinciden o se ha procucido algún problema en la conexión,
	 * es necesario consultar el tipo concreto de excepción así como su mensaje.
	 */
	public String verificaUsuario(String nombre, String password) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			return consultor.getTipoUser(nombre, password, conn);
		} 
		catch (BDException e) 
		{
			return null;
		}
		finally
		{
			database.cierraConexion(conn);
		}
	}

	/*
	 * Método para consultar cual es el idAut que le ha correspondido al ultimo
	 * AutorEditor en ser insertado en la aplicación.
	 * @return int - Valor entero buscado.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	/*public int consultaIdAutor() throws BDException 
	{
		Connection conn = database.abreConexion();
		try{
			Vector<Object[]> resultado = database.exeQuery("SELECT nextIdAut FROM id;", conn);
			int idAut = 0;
			if (resultado.size() != 0)
			{	
				Object[] array = resultado.get(0);
				idAut = ((Long) array[0]).intValue()-1;
			}
			return idAut;
		}finally{
			this.cierraConexion();
		}
	}*/

	/**
	 * Método que devuelve el id correspondiente al primer autor cuyo nombre y apellidos
	 * sean iguales a los pasados por parámetro. En caso que el parámetro sea null, no lo
	 * tendrá en cuenta. Si los dos parametros son null devolvera el primero.
	 * @param nombre - Nombre que debe corresponder a la persona buscada.
	 * @param apellidos - Apellidos de la persona buscada.
	 * @param conn - Conexión que se utilizará para operar.
	 * @return - Entero correspondiente al idAut de la persona buscada. 0 en caso que
	 * no exista ningun AutorEditor que se ajuste a lo que se busca.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public int consultaIdAutor(String nombre, String apellidos, Connection conn) throws BDException
	{
		//this.abreConexion();
//		try{
			return modif_autores.consultaIdAutor(nombre, apellidos, conn);	
//		}finally{
//			this.cierraConexion();
//		}

	}

	/**
	 * Método que se utiliza para consultar si la keyword que se pasa por parámetro se 
	 * encuentra ya introducida en la base de datos.
	 * @param key - Key que deseamos saber si se encuentra en la base de datos.
	 * @param conn - Conexión que se utilizará para operar.
	 * @return boolean - True si la keyword se encuentra en la BBDD, false en caso contrario.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public boolean consultaExistenciaKey(String key, Connection conn) throws BDException 
	{
		//this.abreConexion();
//		try{		
			Vector<Object[]> resultado = database.exeQuery("SELECT clave FROM claves WHERE clave = '" + key + "';", conn);
			if (resultado.size() == 0)
				return false;
			else
				return true;
//		}finally{
//			this.cierraConexion();
//		}
	}

	/**
	 * Método para consultar cual es el idDoc que le ha correspondido al documento
	 * en ser insertado en la aplicación.
	 * @param conn - Conexión que se utilizará para operar.
	 * @return int - Valor entero buscado.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public int consultaIdDoc(Connection conn) throws BDException 
	{
		//this.abreConexion();
//		try{
			Vector<Object[]> resultado = database.exeQuery("SELECT nextId FROM id;", conn);
			Object[] array = resultado.get(0);
			int idAut = ((Long) array[0]).intValue()-1;
			return idAut;
//		}
//		finally{
//			this.cierraConexion();
//		}
	}


	/**
	 * Inserta en la base de datos el AutorEditor que se corresponde con los valores
	 * que contiene el objeto pasado por parámetro.
	 * @param ae - Objeto que contiene los datos del AutorEditor pasado por parámetro
	 * @param conn - Conexión que se utilizará para operar.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws ExistingElementException - Si un autorEditor con los 3 campos iguales 
	 * ya se encuentra en la base de datos.
	 */
	public void insertaAutorEditor(AutorEditor ae, Connection conn) throws BDException, ExistingElementException
	{
		modif_autores.insertaAutorEditor(ae.getNombre(), ae.getApellidos(), conn);
	}

	/**
	 * Método para ejecutar la sentencia pasada por parámetro, ha de ser de tipo
	 * modificadora de la base de datos.
	 * @param sentence - Sentencia a ejecutar sobre la base de datos.
	 * @param conn - Conexión que se utilizará para operar.
	 * @throws BDException  - Diversos problemas operando con la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public void ejecutaString(String sentence, Connection conn) throws BDException 
	{
		boolean cerrarDespues = (conn == null);
		if (cerrarDespues)
			conn = database.abreConexion();
		try{
			database.exeUpdate(sentence, conn);
		}finally{
			if (cerrarDespues)
				database.cierraConexion(conn);
		}
	}

	
	
	private Element generaElementoAutoresEditores(Connection conn) throws BDException
	{
		Element eAutoresEditores = new Element("listaAutoresEditores");

		Vector<Object[]> result = database.exeQuery("SELECT idAut, nombre, apellidos FROM autoreseditores ORDER BY apellidos, nombre;", conn);
		int numAE = result.size();
		Object[] actual;
		int idAut;
		String nombre, apellidos;
		for (int i = 0; i < numAE; i++)
		{
			actual = result.get(i);
			idAut = ((Long)actual[0]).intValue();
			nombre = (String)actual[1];
			if (nombre==null) nombre = "-";
			apellidos = (String)actual[2];
			if (apellidos==null) apellidos = "-";


			Element eAutorEditor = new Element("AutorEditor");
			eAutorEditor.setAttribute("idAut", ((Integer)idAut).toString());
			Element eNombre = new Element("nombre");
			eNombre.addContent(nombre);
			Element eApellidos = new Element("apellidos");
			eApellidos.addContent(apellidos);
			eAutorEditor.addContent(eNombre);
			eAutorEditor.addContent(eApellidos);

			eAutoresEditores.addContent(eAutorEditor);
		}
		return eAutoresEditores;
	}
	
	private Element generaElementoProyectos(Connection conn) throws BDException
	{
		Element eProyectos = new Element("listaProyectos");
		Vector<Object[]> result = database.exeQuery("SELECT * FROM proyectos ORDER BY nombre", conn);
		int numProy = result.size();
		String proyecto;
		Object[] actual;
		for (int i = 0; i < numProy; i++)
		{
			actual = result.get(i);
			proyecto = (String)actual[0];

			Element eProyecto = new Element("proyecto");
			eProyecto.addContent(proyecto);

			eProyectos.addContent(eProyecto);
		}
		return eProyectos;
	}
	
	private Element generaElementoProyectos(String user, Connection conn) throws BDException, NonExistingElementException
	{
		Vector<Object[]> result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';", conn);
		if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
		String tipoUser = (String)result.get(0)[0];
		String consulta;
		if (tipoUser.equals("admin"))
			consulta = "SELECT * FROM proyectos ORDER BY nombre";
		else if (tipoUser.equals("jefe"))
		{
			consulta = "SELECT proyecto FROM ProyectosAccesiblesJefe WHERE jefe='" + user + "' ORDER BY proyecto;";
		}
		else //Usuario normal (user).
		{
			consulta = "SELECT proyectos.nombre FROM proyectos, participaen ";
			consulta += "WHERE proyectos.nombre = participaen.proyecto AND participaen.usuario = '" + user + "' ";
			consulta += "ORDER BY proyectos.nombre";
		}
		
		Element eProyectos = new Element("listaProyectos");
		result = database.exeQuery(consulta, conn);

		int numProy = result.size();
		String proyecto;
		Object[] actual;
		for (int i = 0; i < numProy; i++)
		{
			actual = result.get(i);
			proyecto = (String)actual[0];

			Element eProyecto = new Element("proyecto");
			eProyecto.addContent(proyecto);

			eProyectos.addContent(eProyecto);
		}
		return eProyectos;
	}
	
	public String obtenerListaAutoresEditoresYProyectosParaBusquedas()
	{
		try
		{
			Connection conn = database.abreConexion();
			Element root = new Element("AutoresEditoresProyectos");
			Element eAutoresEditores = generaElementoAutoresEditores(conn);
			root.addContent(eAutoresEditores);
			
			Element eProyectos = generaElementoProyectos(conn);
			root.addContent(eProyectos);
			
			database.cierraConexion(conn);

			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch (BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	
	public String obtenerListaAutoresEditoresYProyectosParaInserciones(String user)
	{
		try
		{
			Connection conn = database.abreConexion();
			Element root = new Element("AutoresEditoresProyectos");
			Element eAutoresEditores = generaElementoAutoresEditores(conn);
			root.addContent(eAutoresEditores);

			Element eProyectos = generaElementoProyectos(user, conn);
			root.addContent(eProyectos);

			database.cierraConexion(conn);
			
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.USUARIO)
				root.addContent("Error: el usuario no existe.");
			else
				root.addContent("Error desconocido al obtener lista de autores/editores y proyectos.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	
	private Element generaElementoPublicacionesProyecto(String proyecto) throws NonExistingElementException, BDException
	{
		Vector<Publication> publicaciones = consultaDocumentos(proyecto, CodigosDatos.codSumaTodasPublicaciones, null, null, null, false, null, null, null, null, null, null, null, null, null, null, false, false, false, false, false, false, false, false, false, null);
		Element ePublicaciones = new Element("listaPublicaciones");
		int numP = publicaciones.size();
		for (int i = 0;  i < numP; i++)
		{
			Element ePublication = publicaciones.get(i).generarElementoXML();
			ePublicaciones.addContent(ePublication);
		}
		return ePublicaciones;
	}
	
	public String obtenerUsuariosYPublicacionesProyecto(String  proyecto)
	{
		try
		{
			Element root = new Element("listaUsuariosYPublicaciones");
			Element pertenecen = new Element("pertenecen");

			Connection conn = database.abreConexion();
			Vector<Object[]> result = database.exeQuery("SELECT * FROM proyectos WHERE nombre='" + proyecto + "';", conn);
			if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.PROYECTO);
			String consulta = "SELECT usuarios.nombre FROM usuarios, participaen WHERE usuarios.nombre = participaen.usuario AND participaen.proyecto = '" + proyecto +"' ORDER BY usuarios.nombre;";
			result = database.exeQuery(consulta, conn);
			int numU = result.size();
			Object[] actual;
			String usuario;
			for (int i = 0; i < numU; i++)
			{
				actual = result.get(i);
				usuario = (String)actual[0];

				Element eUsuario = new Element("usuario");
				eUsuario.addContent(usuario);

				pertenecen.addContent(eUsuario);
			}
			root.addContent(pertenecen);

			Element noPertenecen = new Element("noPertenecen");
			consulta = "SELECT usuarios.nombre FROM usuarios WHERE NOT EXISTS(SELECT * FROM participaen WHERE usuarios.nombre = participaen.usuario AND participaen.proyecto = '" + proyecto +"') ORDER BY usuarios.nombre;";
			result = database.exeQuery(consulta, conn);
			numU = result.size();
			for (int i = 0; i < numU; i++)
			{
				actual = result.get(i);
				usuario = (String)actual[0];

				Element eUsuario = new Element("usuario");
				eUsuario.addContent(usuario);

				noPertenecen.addContent(eUsuario);
			}
			root.addContent(noPertenecen);
			
			Element ePublicaciones = generaElementoPublicacionesProyecto(proyecto);
			root.addContent(ePublicaciones);
			
			database.cierraConexion(conn);
			
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.PROYECTO)
				root.addContent("Error: el proyecto especificado no existe.");
			else
				root.addContent("Error desconocido al obtener lista de usuarios de un proyecto.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	private Element generaElementoUsuarios(Connection conn) throws BDException
	{
		Element root = new Element("listaUsuarios");
		String consulta = "SELECT nombre FROM usuarios ORDER BY nombre";

		Vector<Object[]> result = database.exeQuery(consulta, conn);
		int numU = result.size();
		Object[] actual;
		String usuario;
		for (int i = 0; i < numU; i++)
		{
			actual = result.get(i);
			usuario = (String)actual[0];

			Element eUsuario = new Element("usuario");
			eUsuario.addContent(usuario);

			root.addContent(eUsuario);
		}
		
		return root;
	}
	
	public String obtenerListaTotalUsuarios()
	{
		try
		{
			Connection conn = database.abreConexion();
			Element root = generaElementoUsuarios(conn);
			database.cierraConexion(conn);

			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String obtenerListaProyectosTotales()
	{
		try
		{
			Connection conn = database.abreConexion();
			Element proyectos = generaElementoProyectos(conn);
			database.cierraConexion(conn);
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(proyectos));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String obtenerListaProyectosDirigidos(String  user)
	{
		try
		{
			Element root = new Element("listaProyectos");
			String consulta = "SELECT nombre FROM proyectos WHERE jefe='" + user + "' ORDER BY nombre;";

			Connection conn = database.abreConexion();
			Vector<Object[]> result = database.exeQuery(consulta, conn);
			int numP = result.size();
			Object[] actual;
			String nombre;
			for (int i = 0; i < numP; i++)
			{
				actual = result.get(i);
				nombre = (String)actual[0];

				Element eProyecto = new Element("proyecto");
				eProyecto.addContent(nombre);

				root.addContent(eProyecto);
			}
			
			database.cierraConexion(conn);

			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String obtenerListaProyectosGestionables(String  user)
	{
		try
		{
			Connection conn = database.abreConexion();
			Vector<Object[]> result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';", conn);
			database.cierraConexion(conn);
			if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
			String tipoUser = (String)result.get(0)[0];
			if (tipoUser.equals("admin"))
				return obtenerListaProyectosTotales();
			else if (tipoUser.equals("jefe"))
				return obtenerListaProyectosDirigidos(user);
			else
				throw new PermissionException("Error: el usuario no es jefe ni administrador.");
		} 
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.USUARIO)
				root.addContent("Error: el usuario no existe.");
			else
				root.addContent("Error desconocido al obtener lista de proyectos gestionables.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	/*public String obtenerListaPublicaciones(String  user) throws BDException, UnimplementedException
	{
		String consulta = "SELECT nombre FROM proyectos WHERE jefe='" + user + "' ORDER BY nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
		int numP = result.size();
		Object[] actual;
		String nombre;
		Vector<Publication> publicaciones = new Vector<Publication>();
		for (int i = 0; i < numP; i++)
		{
			actual = result.get(i);
			nombre = (String)actual[0];

			publicaciones.addAll(consultaDocumentos(nombre, CodigosDatos.codSumaTodasPublicaciones, null, null, null, false, null, null, null, null, null, null, null, null, null, null, false, false, false, false, false, false, false, false, false));
		}

		Element root = new Element("listaPublicaciones");
		numP = publicaciones.size();
		for (int i = 0;  i < numP; i++)
		{
			Element ePublication = publicaciones.get(i).generarElementoXML();
			root.addContent(ePublication);
		}
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}*/
	
	public String obtenerListaPublicacionesProyecto(String  proyecto)
	{
		try
		{
			Element root = generaElementoPublicacionesProyecto(proyecto);
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.PROYECTO)
				root.addContent("Error: El proyecto no existe.");
			else
				root.addContent("Error desconocido al obtener la lista de publicaciones de un proyecto.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
	}
	
	public String obtenerListaPublicacionesUsuario(String  user)
	{
		try
		{
			Vector<Publication> publicaciones = consultaDocumentos(null, CodigosDatos.codSumaTodasPublicaciones, null, null, null, false, null, null, null, null, null, null, null, null, null, null, false, false, false, false, false, false, false, false, false, user);

			Element root = new Element("listaPublicaciones");
			int numP = publicaciones.size();
			for (int i = 0;  i < numP; i++)
			{
				Element ePublication = publicaciones.get(i).generarElementoXML();
				root.addContent(ePublication);
			}
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.USUARIO)
				root.addContent("Error: El usuario no existe.");
			else
				root.addContent("Error desconocido al obtener la lista de publicaciones de un usuario.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
	}
	
	
	public String obtenerListaVincularDesvincular(String  user, String idDoc)
	{
		try
		{
			Connection conn = database.abreConexion();
			Vector<Object[]> result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';", conn);
			if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
			Vector<Object[]> result2 = database.exeQuery("SELECT * FROM tipopublicacion WHERE idDoc = '" + idDoc + "';", conn);
			if (result2 == null || result2.size() == 0) throw new NonExistingElementException(ExistenceException.DOCUMENTO);
			String tipoUser = (String)result.get(0)[0];
			String consultaVincular, consultaDesvincular;
			if (tipoUser.equals("admin"))
			{
				consultaVincular = "SELECT proyectos.nombre FROM proyectos WHERE NOT EXISTS (SELECT * FROM pertenecea WHERE pertenecea.idDoc = " + idDoc + " AND pertenecea.proyecto = proyectos.nombre) ORDER BY proyectos.nombre";
				consultaDesvincular = "SELECT proyectos.nombre FROM proyectos, pertenecea WHERE proyectos.nombre = pertenecea.proyecto AND pertenecea.idDoc = " + idDoc + " ORDER BY proyectos.nombre;";
			}
			else if (tipoUser.equals("jefe"))
			{
				consultaVincular = "SELECT PAJ.proyecto FROM proyectosaccesiblesjefe PAJ WHERE PAJ.jefe = '" + user + "' AND NOT EXISTS (SELECT * FROM pertenecea WHERE pertenecea.idDoc = " + idDoc + " AND pertenecea.proyecto = PAJ.proyecto) ORDER BY PAJ.proyecto";
				consultaDesvincular = "SELECT PAJ.proyecto FROM proyectosaccesiblesjefe PAJ, pertenecea WHERE PAJ.jefe = '" + user + "' AND PAJ.proyecto = pertenecea.proyecto AND pertenecea.idDoc = " + idDoc + " ORDER BY PAJ.proyecto;";
			}
			else //Usuario normal (user).
			{
				consultaVincular = "SELECT participaen.proyecto FROM participaen WHERE participaen.usuario = '" + user + "' AND NOT EXISTS (SELECT * FROM pertenecea WHERE pertenecea.idDoc = " + idDoc + " AND pertenecea.proyecto = participaen.proyecto) ORDER BY participaen.proyecto;";
				consultaDesvincular = "SELECT participaen.proyecto FROM participaen, pertenecea WHERE participaen.usuario = '" + user + "' AND participaen.proyecto = pertenecea.proyecto AND pertenecea.idDoc = " + idDoc + " ORDER BY participaen.proyecto;";
			}

			Element root = new Element("listaVincularDesvincularProyectos");
			Element vincular = new Element("vincular");
			result = database.exeQuery(consultaVincular, conn);
			int numP = result.size();
			Object[] actual;
			String nombre;
			for (int i = 0; i < numP; i++)
			{
				actual = result.get(i);
				nombre = (String)actual[0];

				Element eProyecto = new Element("proyecto");
				eProyecto.addContent(nombre);

				vincular.addContent(eProyecto);
			}
			root.addContent(vincular);

			Element desvincular = new Element("desvincular");
			result = database.exeQuery(consultaDesvincular, conn);
			numP = result.size();
			for (int i = 0; i < numP; i++)
			{
				actual = result.get(i);
				nombre = (String)actual[0];

				Element eProyecto = new Element("proyecto");
				eProyecto.addContent(nombre);

				desvincular.addContent(eProyecto);
			}
			root.addContent(desvincular);

			database.cierraConexion(conn);
			
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		} 
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.USUARIO)
				root.addContent("Error: el usuario especificado no existe.");
			else if (e.getTipo() == ExistenceException.DOCUMENTO)
				root.addContent("Error: el documento especificado no existe.");
			else
				root.addContent("Error desconocido al obtener lista de proyectos para vincular/desvincular.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}

	
	public String insertaProyecto(String proyecto, String jefe) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			ejecutaString("BEGIN;", conn); //database.exeQuery("BEGIN;", conn);
			modif_proyectos.insertaProyecto(proyecto, jefe, conn);
			ejecutaString("COMMIT;", conn); //database.exeQuery("COMMIT;", conn);
			return "El proyecto ha sido creado satisfactoriamente.";
		}
		catch (ExistingElementException e) {
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al crear el proyecto: ya existe un proyecto con ese nombre.";
			else
				return "Error desconocido al crear un proyecto.";
		} 
		catch (BDException e) {
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		}
		finally{
			database.cierraConexion(conn);
		}
	}
	
	public String eliminaProyecto(String proyecto) throws BDException
	{
		Connection conn = database.abreConexion();
		try
		{
			ejecutaString("BEGIN;", conn); //database.exeQuery("BEGIN;", conn);
			modif_proyectos.borraProyecto(proyecto, conn);
			ejecutaString("COMMIT;", conn); //database.exeQuery("COMMIT;", conn);
			return "El proyecto ha sido eliminado satisfactoriamente.";
		}
		catch (NonExistingElementException e) {
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.PROYECTO)
				return "Error al eliminar: el proyecto no existe.";
			else
				return "Error desconocido al eliminar un proyecto.";
		} 
		catch (BDException e) {
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		}
		finally{
			database.cierraConexion(conn);
		}
	}
	
	
	public String obtenerListaProyectosUsuariosYAutores()
	{
		try
		{
			Connection conn = database.abreConexion();
			Element root = new Element("listaProyectosUsuariosYAutores");
			Element eProyectos = generaElementoProyectos(conn);
			root.addContent(eProyectos);
			
			Element eUsuarios = generaElementoUsuarios(conn);
			root.addContent(eUsuarios);
			
			Element eAutores = generaElementoAutoresEditores(conn);
			root.addContent(eAutores);
			
			database.cierraConexion(conn);
			
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}

	//Sustituye autor1 por autor2.
	public String fusionarAutoresEditores(String autor1, String autor2) throws BDException 
	{
		Connection conn = database.abreConexion();
		
		try
		{
			Vector<Object[]> result1 = database.exeQuery("SELECT * FROM autoreseditores WHERE idAut=" + autor1 + ";", conn);
			if (result1 == null || result1.size() < 1)
				throw new NonExistingElementException(ExistenceException.AUTOR);
			Vector<Object[]> result2 = database.exeQuery("SELECT * FROM autoreseditores WHERE idAut=" + autor2 + ";", conn);
			if (result2 == null || result2.size() < 1)
				throw new NonExistingElementException(ExistenceException.AUTOR);
			
			long idAut1 = (Long)result1.get(0)[0];
			long idAut2 = (Long)result2.get(0)[0];
			Vector<String> updates = new Vector<String>();
			updates.add("UPDATE escrito_editado_por SET idPer = " + idAut2 + " WHERE idPer = " + idAut1 + ";");
			updates.add("DELETE FROM autoreseditores WHERE idAut = " + idAut1 + ";");
			ejecutaString("BEGIN;", conn);
			database.exeUpdates(updates, conn);
			ejecutaString("COMMIT;", conn);
			return "Los autores/editores han sido fusionados correctamente.";
		}
		catch (NonExistingElementException e) {
			ejecutaString("ROLLBACK;", conn);
			if (e.getTipo() == ExistenceException.AUTOR)
				return "Error al fusionar: uno de los autores/editores no existe.";
			else
				return "Error desconocido al fusionar autores/editores.";
		} 
		catch (BDException e) {
			ejecutaString("ROLLBACK;", conn);
			return e.getMessage();
		}
		finally{
			database.cierraConexion(conn);
		}
	}

}