package controlador;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import personas.AutorEditor;
import personas.Usuario;
import publicaciones.CodigosDatos;
import publicaciones.Publication;
import temporal.UnimplementedException;
import controlador.exceptions.ConnectionException;
import controlador.exceptions.ConnectionNullException;
import controlador.exceptions.ExistenceException;
import controlador.exceptions.ExistingElementException;
import controlador.exceptions.NonExistingElementException;
import controlador.exceptions.PermisssionException;
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
	
	private ConsultorBaseDatos consultor;
	@SuppressWarnings("unused")
	private ModificadorAutores modif_autores;
	@SuppressWarnings("unused")
	private ModificadorProyectos modif_proyectos;
	@SuppressWarnings("unused")
	private ModificadorPublicaciones modif_pub;
	@SuppressWarnings("unused")
	private ModificadorUsuarios modif_user;
	
	/**
	 * Constructor por defecto de la clase.
	 * @roseuid 47C8A70F01C5
	 */
	public DataBaseControler() 
	{
		database = new BaseDatos();
		initSubcontrolers();
	}
	
	private void initSubcontrolers() {		
		consultor = new ConsultorBaseDatos (database);
		modif_autores = new ModificadorAutores (database);
		modif_proyectos = new ModificadorProyectos(database);
		modif_pub = new ModificadorPublicaciones (database);
		modif_user = new ModificadorUsuarios (database);
	}
	
	public DataBaseControler(BaseDatos base){
		database = base;
		initSubcontrolers();
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
	 * @return vector construido con las publicaciones que cumplen los 
	 * requisitos.
	 * 
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @roseuid 47C5A76F02DE
	 */
	public Vector<Publication> consultaDocumentos(final String proyecto,final int tipo_publicaciones, 
			final Vector<AutorEditor> autores, final Vector<AutorEditor> editores,
			final String title, final String publisher,
			final String journal,final Vector<String> years,final String volume,
			final String series,final String address,
			final String organization, final String school, final String bookTitle,
			final Vector<String> keys, final boolean parecido_publisher,
			final boolean parecido_title, final boolean parecido_series,
			final boolean parecido_address, final boolean parecido_journal,
			final boolean parecido_volume, final boolean parecido_school,
			final boolean parecido_bookTitle, final boolean parecido_organization,
			final boolean parecido_keys) throws UnimplementedException, BDException 
			{
		this.abreConexion();
		try{
			// Primero localizar a los autores y editores.
			Vector <AutorEditor> v_autores = consultor.buscaAutores(autores);
			Vector <AutorEditor> v_editores = consultor.buscaAutores(editores);
			Vector <Integer> v_authors= new Vector<Integer>();
			Vector <Integer> v_editors= new Vector<Integer>();
			for (int i = 0; i< v_autores.size();i++){
				v_authors.add(new Integer (v_autores.get(i).getId()));
			}
			
			for (int i = 0; i< v_editores.size();i++){
				v_editors.add(new Integer (v_editores.get(i).getId()));
			}
			
			return consultor.getPublicaciones(proyecto, tipo_publicaciones, v_authors, v_editors, title, parecido_title, publisher, parecido_publisher, journal, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, school, parecido_school,keys, bookTitle, parecido_bookTitle);
		}finally{
			this.cierraConexion();
		}
			}
	
	/**
	 * Método que devuelve una lista con los autores que coinciden total o parcialmente 
	 * con los datos parámetro.
	 * @param nombre - Nombre del autor.
	 * @param apellido - Apellidos del autor.
	 * @param web - URL de la web del autor.
	 * @return java.util.Vector Vector con los autores y/o editores que se ajusten a los parametros pasados.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public Vector<AutorEditor> consultaAutores(String nombre, String apellido, String web, boolean total_o_parcial) throws UnimplementedException, BDException 
	{
		this.abreConexion();
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
			
			if (web != null){
				if (cons == null) cons = new String();
				else cons+= " AND ";
				
				if (total_o_parcial) cons += "web LIKE '%" + web + "%'";
				else cons += "web = '" + web + "'";
			}
			
			if (cons == null) cons = new String ("TRUE");
			
			consulta+= cons + ";";
			
			
			Vector<Object[]> resultado = database.exeQuery(consulta);
			
			for (int i=0; resultado != null && i<resultado.size();i++){
				Object[] array = resultado.get(i);
				vector.add(new AutorEditor(array));			
			}
			return vector;	
		} finally {
			this.cierraConexion();
		}
	}
	
	/**
	 * Devuelve un vector con los usuarios que pertenecen al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea realizar la consulta.
	 * @return java.util.Vector
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5B5B8029F
	 */
	public Vector<String> consultaUsuariosProyecto(String proyecto) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
		return null;
	}
	
	/**
	 * Devuelve un vector con los proyectos a los que pertenece el usuario indicado.
	 * @param usuario - Usuario sobre el que se desea realizar la consulta.
	 * @return java.util.Vector
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5BB4C007D
	 */
	public Vector<String> consultaProyectosUsuario(int usuario) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
		return null;
	}
	
	/**
	 * Devuelve un vector con los proyectos sobre los que puede realizar modificaciones 
	 * el usuario asociado a la actual conexión.
	 * @return java.util.Vector
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5ABAE038A
	 */
	public Vector<String> getMyProyects() throws ConnectionNullException, ConnectionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();		
		return null;
	}
	
	/**
	 * Devuelve un vector con los documentos sobre los que puede realizar 
	 * modificaciones el usuario asociado a la actual conexión, es decir, los que el ha 
	 * subido y todos aquellos que pertenecen a un usuario de su proyecto.
	 * @return java.util.Vector
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5B28D030D
	 */
	public Vector<Publication> getMyDocuments() throws ConnectionNullException, ConnectionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();		
		return null;
	}
	
	/**
	 * Devuelve un vector con los documentos que ha subido el usuario.
	 * @return java.util.Vector
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5B5490290
	 */
	public Vector<Publication> getMyUploadDocuments() throws ConnectionNullException, ConnectionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();		
		return null;
	}
	
	/**
	 * Inserta la publicacion pasada por parámetro en la base de datos.
	 * @param publicacion - Publicacion a insertar.
	 * @throws BDException 
	 * @throws BDException 
	 * @roseuid 47C5AE4A036B
	 */
	public void insertaDocumento(Publication publicacion) throws BDException
	{
		modif_pub.insertaPublicación(publicacion);
	}
	
	/**
	 * Inserta el usuario pasado por parámetro en la base de datos.
	 * @param proyecto 
	 * @param publicacion - Usuario a insertar.
	 * @throws BDException 
	 * @throws ExistingElementException 
	 * @throws controlador.exceptions.ExistingElementException
	 * @throws BDException 
	 * @throws NonExistingElementException 
	 * @roseuid 47C5B74C001F
	 */
	public void insertaUsuario(Usuario usuario, String proyecto) throws ExistingElementException, BDException, NonExistingElementException
	{
		modif_user.creaUsuario(usuario, proyecto);
	}
	
	/**
	 * Modifica la publicacion pasada por parámetro en la base de datos.
	 * @param publicacion - Publicacion a modificar con sus nuevos datos.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @roseuid 47C5B3580196
	 */
	public void modificaDocumento(Publication publicacion) throws ConnectionNullException, ConnectionException, PermisssionException, NonExistingElementException 
	{
	}
	
	/**
	 * Método que modifica el autor correspondiente en la base de datos.
	 * @param id_autor - Identificador único de autor.
	 * @param nombre - Nombre que se le asignará al autor.
	 * @param apellidos - Apellidos que se le asignarán al autor.
	 * @param web - URL de la web que se le asigna al autor.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @roseuid 47C5B0360177
	 */
	public void modificaAutor(int id_autor, String nombre, String apellidos, String web) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException 
	{
		
	}
	
	/**
	 * Elimina el documento cuyo IdDoc se pasa por parámetro de la base de datos.
	 * @param id_doc - IdDoc del documento a eliminar.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5B46F008C
	 */
	public void eliminaDocumento(int id_doc) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
	}
	
	/**
	 * Asocia el usuario proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea asociar al usuario.
	 * @param Usuario - Usuario que se desea asociar.
	 * @throws NonExistingElementException - Si no existe el usuario o el proyecto, 
	 * se indica en parametro tipo de la excepcion cual es que no existe.   
	 * @throws BDException  - Diversos problemas operando con la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public void asociaUsuarioProyecto(String proyecto, String usuario) throws NonExistingElementException,BDException 
	{
		this.abreConexion();
		try {
			modif_user.asociaProyecto(usuario, proyecto);
		} 	finally {
			this.cierraConexion();
		}
	}
	
	/**
	 * Desasocia el usuario proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea desasociar al usuario.
	 * @param usuario - Usuario que se desea desasociar.
	 * @throws UnimplementedException 
	 * @throws NonExistingElementException - Si no existe el usuario, el proyecto o la relacion, 
	 * se indica en parametro tipo de la excepcion cual es que no existe.   
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public void desasociaUsuarioProyecto(String proyecto, String usuario) throws UnimplementedException, NonExistingElementException, BDException 
	{
		this.abreConexion();
		try {
			modif_user.desasociaProyecto(usuario, proyecto);
		} 	finally {
			this.cierraConexion();
		}		
	}
	
	/**
	 * Asocia el documento proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea asociar el documento.
	 * @param documento - Documento que se desea desasociar.
	 * @throws NonExistingElementException - Si no existe el proyecto o el documento, 
	 * se indica en parametro tipo de la excepcion cual es que no existe.   
	 * @throws ExistingElementException - Si ese documento ya pertenece a ese proyecto.
	 * @throws BDException - - Diversos problemas operando con la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public void asociaDocumentoProyecto(String proyecto, int documento) throws NonExistingElementException, UnimplementedException, ExistingElementException, BDException 
	{
		this.abreConexion();
		try {
			modif_pub.asociaPublicacion(documento, proyecto);
		} 	finally {
			this.cierraConexion();
		}			
	}
	
	/**
	 * Desasocia el documento proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea desasociar al documento.
	 * @param documento - Documento que se desea desasociar.
	 * @throws NonExistingElementException - Si no existe el documento, el proyecto o la relacion, 
	 * se indica en parametro tipo de la excepcion cual es que no existe.  
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @roseuid 47C5BBE2006D
	 */
	public void desasociaDocumentoProyecto(String proyecto, int documento) throws NonExistingElementException, UnimplementedException, BDException 
	{
		this.abreConexion();
		try {
			modif_pub.desasociaPublicacion(documento, proyecto);
		} 	finally {
			this.cierraConexion();
		}			
	}
	
	/**
	 * Elimina el usuario pasado por parámetro de la base de datos.
	 * @param usuario - Código del usuario a eliminar.
	 * @param nuevoUserPublicaciones 
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws UnimplementedException 
	 * @throws BDException 
	 * @roseuid 47C5BAEE0222
	 */
	public void eliminaUsuario(String usuario, String nuevoUserPublicaciones) throws NonExistingElementException, UnimplementedException, BDException 
	{
		modif_user.eliminaUsuario(usuario, nuevoUserPublicaciones);
	}
	
	
	public String verificaUsuario(String nombre, String password) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException, BDException 
	{
		return consultor.getTipoUser(nombre, password);
	}
	
	/**
	 * Método para consultar cual es el idAut que le ha correspondido al ultimo
	 * AutorEditor en ser insertado en la aplicación.
	 * @return int - Valor entero buscado.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public int consultaIdAutor() throws BDException 
	{
		this.abreConexion();
		try{
			Vector<Object[]> resultado = database.exeQuery("SELECT nextIdAut FROM id;");
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
	}
	
	/**
	 * Método que devuelve el id correspondiente al primer autor cuyo nombre y apellidos
	 * sean iguales a los pasados por parámetro. En caso que el parámetro sea null, no lo
	 * tendrá en cuenta. Si los dos parametros son null devolvera el primero.
	 * @param nombre - Nombre que debe corresponder a la persona buscada.
	 * @param apellidos - Apellidos de la persona buscada.
	 * @return - Entero correspondiente al idAut de la persona buscada. 0 en caso que
	 * no exista ningun AutorEditor que se ajuste a lo que se busca.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	private int consultaIdAutor(String nombre, String apellidos) throws BDException
	{
		this.abreConexion();
		try{
			return modif_autores.consultaIdAutor(nombre, apellidos);	
		}finally{
			this.cierraConexion();
		}
		
	}
	
	/**
	 * Método que se utiliza para consultar si la keyword que se pasa por parámetro se 
	 * encuentra ya introducida en la base de datos.
	 * @param key - Key que deseamos saber si se encuentra en la base de datos.
	 * @return boolean - True si la keyword se encuentra en la BBDD, false en caso contrario.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public boolean consultaExistenciaKey(String key) throws BDException 
	{
		this.abreConexion();
		try{		
			Vector<Object[]> resultado = database.exeQuery("SELECT clave FROM claves WHERE clave = '" + key + "';");
			if (resultado.size() == 0)
				return false;
			else
				return true;
		}finally{
			this.cierraConexion();
		}
	}
	
	/**
	 * Método para consultar cual es el idDoc que le ha correspondido al documento
	 * en ser insertado en la aplicación.
	 * @return int - Valor entero buscado.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public int consultaIdDoc() throws BDException 
	{
		this.abreConexion();
		try{
			Vector<Object[]> resultado = database.exeQuery("SELECT nextId FROM id;");
			Object[] array = resultado.get(0);
			int idAut = ((Long) array[0]).intValue()-1;
			return idAut;
		}finally{
			this.cierraConexion();
		}
	}		
	
	
	public void insertaAutorEditor(AutorEditor ae) throws BDException
	{
		String str = new String ("INSERT INTO autoreseditores VALUES(0");
		if(ae.getNombre() != null)
			str += ",\"" + ae.getNombre() + "\"";
		else str+= ",null";
		
		if(ae.getApellidos()!=null)
			str += ",\"" + ae.getApellidos() + "\"";
		else str+= ",null";
		
		if(ae.getWeb()!=null)
			str += ",\"" + ae.getWeb() + "\"";
		else str+= ",null";
		
		str+=");";
		database.exeUpdate(str);
	}
	
	/**
	 * Método para ejecutar la sentencia pasada por parámetro, ha de ser de tipo
	 * modificadora de la base de datos.
	 * @param sentence - Sentencia a ejecutar sobre la base de datos.
	 * @throws BDException  - Diversos problemas operando con la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public void ejecutaString(String sentence) throws BDException 
	{
		this.abreConexion();
		try{
			database.exeUpdate(sentence);
		}finally{
			this.cierraConexion();
		}
	}
	
	public String obtenerListaAutoresEditoresYProyectosParaBusquedas() throws FileNotFoundException, BDException
	{
		Element root = new Element("AutoresEditoresProyectos");
		Element eAutoresEditores = new Element("listaAutoresEditores");
		
		Vector<Object[]> result = database.exeQuery("SELECT idAut, nombre, apellidos FROM autoreseditores ORDER BY apellidos, nombre;");
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
		root.addContent(eAutoresEditores);
		
		Element eProyectos = new Element("listaProyectos");
		result = database.exeQuery("SELECT * FROM proyectos ORDER BY nombre");
		int numProy = result.size();
		String proyecto;
		for (int i = 0; i < numProy; i++)
		{
			actual = result.get(i);
			proyecto = (String)actual[0];
			
			Element eProyecto = new Element("proyecto");
			eProyecto.addContent(proyecto);
			
			eProyectos.addContent(eProyecto);
		}
		root.addContent(eProyectos);
		
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}
	
	
	public String obtenerListaAutoresEditoresYProyectosParaInserciones(String user) throws FileNotFoundException, BDException, NonExistingElementException
	{
		Element root = new Element("AutoresEditoresProyectos");
		Element eAutoresEditores = new Element("listaAutoresEditores");
		
		Vector<Object[]> result = database.exeQuery("SELECT idAut, nombre, apellidos FROM autoreseditores ORDER BY apellidos, nombre;");
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
		root.addContent(eAutoresEditores);
		
		result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';");
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
		result = database.exeQuery(consulta);
		
		int numProy = result.size();
		String proyecto;
		for (int i = 0; i < numProy; i++)
		{
			actual = result.get(i);
			proyecto = (String)actual[0];
			
			Element eProyecto = new Element("proyecto");
			eProyecto.addContent(proyecto);
			
			eProyectos.addContent(eProyecto);
		}
		root.addContent(eProyectos);
		
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}
	
	
	public String obtenerListaUsuarios(String  jefe) throws FileNotFoundException, BDException
	{
		Element root = new Element("listaUsuarios");
		String consulta = "SELECT usuarios.nombre, usuarios.tipo FROM usuarios, participaen, proyectos ";
		consulta += "WHERE usuarios.nombre = participaen.usuario AND participaen.proyecto = proyectos.nombre ";
		consulta += "AND proyectos.jefe = '" + jefe + "' ORDER BY usuarios.nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
		int numU = result.size();
		Object[] actual;
		String usuario, tipo;
		for (int i = 0; i < numU; i++)
		{
			actual = result.get(i);
			usuario = (String)actual[0];
			tipo = (String)actual[1];
			
			Element eUsuario = new Element("usuario");
			Element eNombre = new Element("nombre");
			eNombre.addContent(usuario);
			Element eTipo = new Element("tipo");
			eTipo.addContent(tipo);
			eUsuario.addContent(eNombre);
			eUsuario.addContent(eTipo);
			
			root.addContent(eUsuario);
		}
		
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}
	
	public String obtenerListaProyectosTotales() throws FileNotFoundException, BDException
	{
		Element root = new Element("listaProyectos");
		String consulta = "SELECT nombre FROM proyectos ORDER BY nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
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
		
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}
	
	public String obtenerListaProyectosDirigidos(String  user) throws FileNotFoundException, BDException
	{
		Element root = new Element("listaProyectos");
		String consulta = "SELECT nombre FROM proyectos WHERE jefe='" + user + "' ORDER BY nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
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
		
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}
	
	public String obtenerListaProyectosGestionables(String  user) throws FileNotFoundException, BDException, NonExistingElementException
	{
		Vector<Object[]> result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';");
		if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
		String tipoUser = (String)result.get(0)[0];
		if (tipoUser.equals("admin"))
			return obtenerListaProyectosTotales();
		else if (tipoUser.equals("jefe"))
			return obtenerListaProyectosDirigidos(user);
		else
			return null;
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
	
	public String obtenerListaPublicaciones(String  proyecto) throws BDException, UnimplementedException
	{
		Vector<Publication> publicaciones = consultaDocumentos(proyecto, CodigosDatos.codSumaTodasPublicaciones, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, false, false, false, false, false, false, false);
		
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
	
	/**
	 * Este método establece la conexión con la base de datos.
	 */
	private void abreConexion(){
		
	}
	
	/**
	 * Este método cierra la conexion con la base de datos.
	 */
	private void cierraConexion(){
		
	}
	
}