//Source file: C:\\GENERADO\\controlador\\DataBaseControler.java

package controlador;

import java.util.Vector;

import personas.AutorEditor;
import personas.Usuario;
import publicaciones.Publication;
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
	 * @throws BDException 
	 * @roseuid 47C5A76F02DE
	 */
	public Vector<Publication> consultaDocumentos(int tipo_publicaciones, final Vector<String> autores, final Vector<String> editores, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException 
	{
		// Primero localizar a los autores.
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
		
		return consultor.getPublicaciones(tipo_publicaciones, v_authors, v_editors, title, parecido_title, publisher, parecido_publisher, journal, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, organization, parecido_organization, school, parecido_school, note, abstracts, bookTitle, parecido_bookTitle);
	}

	/**
	 * Método que devuelve una lista con los autores que coinciden total o parcialmente 
	 * con los datos parámetro.
	 * @param nombre - Nombre del autor.
	 * @param apellido - Apellidos del autor.
	 * @param web - URL de la web del autor.
	 * @return java.util.Vector Vector con los autores y/o editores consultados
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws UnimplementedException 
	 * @roseuid 47C5ACED0109
	 */
	public Vector<AutorEditor> consultaAutores(String nombre, String apellido, String web) throws ConnectionException, ConnectionNullException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
		return null;
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
		// Comprobamos la existencia del proyecto, si no existe -->    NonExistingElementException
		// Consultamos los documentos que pertenecen a ese proyecto. Construimos una publicacion para cada uno.  
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
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws controlador.exceptions.ExistingElementException
	 * @roseuid 47C5AE4A036B
	 */
	public void insertaDocumento(Publication publicacion) throws ConnectionNullException, ConnectionException, PermisssionException, ExistingElementException 
	{

	}

	/**
	 * Inserta el usuario pasado por parámetro en la base de datos.
	 * @param publicacion - Usuario a insertar.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws controlador.exceptions.ExistingElementException
	 * @roseuid 47C5B74C001F
	 */
	public void insertaUsuario(Usuario publicacion) throws ConnectionNullException, ConnectionException, PermisssionException, ExistingElementException 
	{

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
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws controlador.exceptions.ExistingElementException
	 * @throws UnimplementedException 
	 * @roseuid 47C5B7D10213
	 */
	public void asociaUsuarioProyecto(String proyecto, String Usuario) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, ExistingElementException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
	}

	/**
	 * Desasocia el usuario proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea desasociar al usuario.
	 * @param usuario - Usuario que se desea desasociar.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5B9A2034B
	 */
	public void desasociaUsuarioProyecto(String proyecto, int usuario) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
	}

	/**
	 * Desasocia el documento proporcionado al proyecto indicado.
	 * @param proyecto - Proyecto sobre el que se desea desasociar al usuario.
	 * @param documento - Documento que se desea desasociar.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5BBE2006D
	 */
	public void desasociaDocumentoProyecto(String proyecto, int documento) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();			
	}

	/**
	 * Elimina el usuario pasado por parámetro de la base de datos.
	 * @param user - Código del usuario a eliminar.
	 * @throws controlador.exceptions.ConnectionNullException
	 * @throws controlador.exceptions.ConnectionException
	 * @throws controlador.exceptions.NonExistingElementException
	 * @throws controlador.exceptions.PermisssionException
	 * @throws UnimplementedException 
	 * @roseuid 47C5BAEE0222
	 */
	public void eliminaUsuario(int user) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException 
	{
		if (true)throw new UnimplementedException();
	}
}
