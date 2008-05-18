//Source file: C:\\GENERADO\\controlador\\DataBaseControler.java

package controlador;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import personas.AutorEditor;
import personas.Usuario;
import publicaciones.Article;
import publicaciones.Book;
import publicaciones.Booklet;
import publicaciones.Conference;
import publicaciones.InBook;
import publicaciones.InCollection;
import publicaciones.InProceedings;
import publicaciones.Manual;
import publicaciones.MastersThesis;
import publicaciones.Misc;
import publicaciones.PhdThesis;
import publicaciones.Proceedings;
import publicaciones.Publication;
import publicaciones.TechReport;
import publicaciones.Unpublished;
import temporal.UnimplementedException;
import controlador.exceptions.ConnectionException;
import controlador.exceptions.ConnectionNullException;
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
	 * filtrado.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Strings que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param yearInicial - Primer año en que puede estar incluido. -1 Indica que no 
	 * hay límite.
	 * @param yearFinal - Ultimo año en el que puede estar incluida. -1 Indica que no 
	 * hay límite.
	 * @param monthInicial - Primer mes en el que se desea realizar la búsqueda. null 
	 * para no filtrar por este campo.
	 * @param monthFinal - Mes final en que se puede contener. null para no filtrar por 
	 * este campo.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se realiza o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param pagesMin - Longitud mínima en páginas. -1 para no filtrar por este campo.
	 * @param pagesMax - Maximo de páginas. -1 para no filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma a la que 
	 * pertenece. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param note - Vector con un conjunto de String que deberán estar contenidos en 
	 * el campo note de la publicación. null para no filtrar por este campo.
	 * @param abstracts - Vector con un conjunto de String que deberán estar contenidos 
	 * en el campo abstract de la publicación. null para no filtrar por este campo.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene la 
	 * publicación. null para no filtrar por este campo.
	 * @return vector construido con las publicaciones que cumplen los 
	 * requisitos@throws controlador.exceptions.ConnectionException
	 * @throws UnimplementedException 
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @roseuid 47C5A76F02DE
	 */
	public Vector<Publication> consultaDocumentos(int tipo_publicaciones, final Vector<AutorEditor> autores, final Vector<AutorEditor> editores, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, String bookTitle, Vector<String> keys, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, boolean parecido_keys) throws UnimplementedException, BDException 
	{
		this.abreConexion();
		// Primero localizar a los autores y editores.
		try{
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

			return consultor.getPublicaciones(tipo_publicaciones, v_authors, v_editors, title, parecido_title, publisher, parecido_publisher, journal, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, school, parecido_school,keys, bookTitle, parecido_bookTitle);
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
	 * @return java.util.Vector Vector con los autores y/o editores consultados
	 * @throws UnimplementedException 
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
	 * Devuelve un vector con los documentos asociados al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea realizar la consulta.
	 * @return java.util.Vector
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5EF62035B
	 */
	public Vector<Publication> consultaDocumentosProyecto(String proyecto) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
		return null;
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
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
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
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws UnimplementedException 
	 * @throws BDException 
	 * @throws ExistingElementException 
	 * @roseuid 47C5BBE2006D
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
	 * @param proyecto - Proyecto sobre el que se desea desasociar al proyecto.
	 * @param documento - Documento que se desea desasociar.
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws UnimplementedException 
	 * @throws BDException 
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

	public int consultaIdAutor() throws BDException 
	{
		Vector<Object[]> resultado = database.exeQuery("SELECT nextIdAut FROM id");
		int idAut = 0;
		if (resultado.size() != 0)
		{	
			Object[] array = resultado.get(0);
			idAut = ((Long) array[0]).intValue()-1;
		}
		return idAut;
	}

	public int consultaIdAutor(String nombre, String apellidos) throws BDException
	{
		return modif_autores.consultaIdAutor(nombre, apellidos);
	}

	public boolean consultaExistenciaKey(String key) throws BDException 
	{
		Vector<Object[]> resultado = database.exeQuery("SELECT clave FROM claves WHERE clave = '" + key + "'");
		if (resultado.size() == 0)
			return false;
		else
			return true;
	}

	public int consultaIdDoc() throws BDException 
	{
		Vector<Object[]> resultado = database.exeQuery("SELECT nextId FROM id");
		Object[] array = resultado.get(0);
		int idAut = ((Long) array[0]).intValue()-1;
		return idAut;
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

	public void ejecutaString(String str1) throws BDException 
	{
		database.exeUpdate(str1);
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
	
	
	public String obtenerListaAutoresEditoresYProyectosParaInserciones(String user) throws FileNotFoundException, BDException
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
		String consulta = "SELECT proyectos.nombre FROM proyectos, pertenecea";
		consulta += "WHERE proyectos.nombre = pertenecea.proyecto AND (proyectos.jefe = '" + user +"' OR )";
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
	
	
	public String obtenerListaUsuarios(String  user) throws FileNotFoundException, BDException
	{
		Element root = new Element("listaUsuarios");
		String consulta = "SELECT usuarios.nombre, usuarios.tipo FROM usuarios, participaen, proyectos ";
		consulta += "WHERE usuarios.nombre = participaen.usuario AND participaen.proyecto = proyectos.nombre ";
		consulta += "AND proyectos.jefe = '" + user + "' ORDER BY usuarios.nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
		int numAE = result.size();
		Object[] actual;
		String usuario, tipo;
		for (int i = 0; i < numAE; i++)
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
	
	public String obtenerListaProyectos(String  user) throws FileNotFoundException, BDException
	{
		Element root = new Element("listaProyectos");
		String consulta = "SELECT nombre FROM proyectos WHERE jefe='" + user + "' ORDER BY nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
		int numAE = result.size();
		Object[] actual;
		String nombre;
		for (int i = 0; i < numAE; i++)
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
	
	public String obtenerListaPublicaciones(String  user) throws FileNotFoundException, BDException, UnimplementedException
	{
		String cArticle = "SELECT article.*, proyectos.nombre FROM article, pertenecea, proyectos WHERE article.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cBook = "SELECT book.*, proyectos.nombre FROM book, pertenecea, proyectos WHERE book.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cBooklet = "SELECT booklet.*, proyectos.nombre FROM booklet, pertenecea, proyectos WHERE booklet.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cConference = "SELECT conference.*, proyectos.nombre FROM conference, pertenecea, proyectos WHERE conference.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cInBook = "SELECT inbook.*, proyectos.nombre FROM inbook, pertenecea, proyectos WHERE inbook.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cInCollection = "SELECT incollection.*, proyectos.nombre FROM incollection, pertenecea, proyectos WHERE incollection.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cInProceedings = "SELECT inproceedings.*, proyectos.nombre FROM inproceedings, pertenecea, proyectos WHERE inproceedings.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cManual = "SELECT manual.*, proyectos.nombre FROM manual, pertenecea, proyectos WHERE manual.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cMastersThesis = "SELECT mastersthesis.*, proyectos.nombre FROM mastersthesis, pertenecea, proyectos WHERE mastersthesis.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cMisc = "SELECT misc.*, proyectos.nombre FROM misc, pertenecea, proyectos WHERE misc.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cPhdThesis = "SELECT phdthesis.*, proyectos.nombre FROM phdthesis, pertenecea, proyectos WHERE phdthesis.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cProceedings = "SELECT proceedings.*, proyectos.nombre FROM proceedings, pertenecea, proyectos WHERE proceedings.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cTechReport = "SELECT techreport.*, proyectos.nombre FROM techreport, pertenecea, proyectos WHERE techreport.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		String cUnpublished = "SELECT unpublished.*, proyectos.nombre FROM unpublished, pertenecea, proyectos WHERE unpublished.idDoc = pertenecea.idDoc AND pertenecea.proyecto = proyectos.nombre AND proyectos.jefe ='" + user + "';";
		
		Vector<Object[]> rArticle = database.exeQuery(cArticle);
		Vector<Object[]> rBook = database.exeQuery(cBook);
		Vector<Object[]> rBooklet = database.exeQuery(cBooklet);
		Vector<Object[]> rConference = database.exeQuery(cConference);
		Vector<Object[]> rInBook = database.exeQuery(cInBook);
		Vector<Object[]> rInCollection = database.exeQuery(cInCollection);
		Vector<Object[]> rInProceedings = database.exeQuery(cInProceedings);
		Vector<Object[]> rManual = database.exeQuery(cManual);
		Vector<Object[]> rMastersThesis = database.exeQuery(cMastersThesis);
		Vector<Object[]> rMisc = database.exeQuery(cMisc);
		Vector<Object[]> rPhdThesis = database.exeQuery(cPhdThesis);
		Vector<Object[]> rProceedings = database.exeQuery(cProceedings);
		Vector<Object[]> rTechReport = database.exeQuery(cTechReport);
		Vector<Object[]> rUnpublished = database.exeQuery(cUnpublished);
		
		Vector<Publication> publicaciones = new Vector<Publication>();
		publicaciones.addAll(Article.generaPub(rArticle));
		publicaciones.addAll(Book.generaPub(rBook));
		publicaciones.addAll(Booklet.generaPub(rBooklet));
		publicaciones.addAll(Conference.generaPub(rConference));
		publicaciones.addAll(InBook.generaPub(rInBook));
		publicaciones.addAll(InCollection.generaPub(rInCollection));
		publicaciones.addAll(InProceedings.generaPub(rInProceedings));
		publicaciones.addAll(Manual.generaPub(rManual));
		publicaciones.addAll(MastersThesis.generaPub(rMastersThesis));
		publicaciones.addAll(Misc.generaPub(rMisc));
		publicaciones.addAll(PhdThesis.generaPub(rPhdThesis));
		publicaciones.addAll(Proceedings.generaPub(rProceedings));
		publicaciones.addAll(TechReport.generaPub(rTechReport));
		publicaciones.addAll(Unpublished.generaPub(rUnpublished));
		
		Vector<String> proyectosAsociados = new Vector<String>();
		Object[] actual;
		for (int i = 0; i < rArticle.size(); i++)
		{
			actual = rArticle.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rBook.size(); i++)
		{
			actual = rBook.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rBooklet.size(); i++)
		{
			actual = rBooklet.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rConference.size(); i++)
		{
			actual = rConference.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rInBook.size(); i++)
		{
			actual = rInBook.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rInCollection.size(); i++)
		{
			actual = rInCollection.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rInProceedings.size(); i++)
		{
			actual = rInProceedings.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rManual.size(); i++)
		{
			actual = rManual.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rMastersThesis.size(); i++)
		{
			actual = rMastersThesis.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rMisc.size(); i++)
		{
			actual = rMisc.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rPhdThesis.size(); i++)
		{
			actual = rPhdThesis.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rProceedings.size(); i++)
		{
			actual = rProceedings.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rTechReport.size(); i++)
		{
			actual = rTechReport.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		for (int i = 0; i < rUnpublished.size(); i++)
		{
			actual = rUnpublished.get(i);
			proyectosAsociados.add((String)actual[actual.length-1]);
		}
		
		
		Element root = new Element("listaPublicaciones");
		int numP = publicaciones.size();
		Publication pActual;
		for (int i = 0; i < numP; i++)
		{
			pActual = publicaciones.get(i);
			Element ePub = pActual.generarElementoXML();

			Element eProyecto = new Element("proyecto");
			eProyecto.addContent(proyectosAsociados.get(i));
			ePub.addContent(eProyecto);

			root.addContent(ePub);
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