package controlador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * Clase que implementa una operaci�n por cada funcionalidad claramente definida 
 * que tiene que tener la aplicaci�n respecto a la base de datos.
 */
public class DataBaseControler
{

	/**
	 * Base de datos sobre la que se realizar�n las operaciones.
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
	 * M�todo que realiza una consulta sobre la base de datos.
	 * Se le pasan por par�metro los campos sobre los que se puede realizar el 
	 * filtrado. Como situaci�n general, si no queremos filtrar por ese campo simplemente hay que ponerlo a null.
	 * A la hora de realizar la subconsulta correspondiente para cada tipo de documento 
	 * buscado solo se tendr�n en cuenta aquellos par�metros que le afecten. 
	 * 
	 * @param proyecto - Proyecto al que deseamos que pertenezca el documento.
	 * @param tipo_publicaciones - Representa la AND l�gica a nivel de bits de los 
	 * c�digos correspondientes a cada tipo de publicaci�n que deseamos consultar.
	 * @param autores - Vector con un conjunto de AutorEditor que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param editores - Vector con un conjunto de Strings que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param title - Titulo o parte del t�tulo de la publicaci�n sobre la que queremos 
	 * realizar la b�squeda. null para no filtrar por este campo.
	 * @param publisher - Editorial o parte de la editorial de la publicaci�n sobre la 
	 * que queremos realizar la b�squeda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector con un conjunto de String que indica los a�os a los que puede pertenecer
	 * el proyecto, de esta manera podr�amos admitir por ejemplo proyectos de 1996 y 1998 sin necesidad
	 * de admitir aquellos pertenecientes a 1997. null para no filtrar por este par�metro.  
	 * hay l�mite.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se realiza o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma a la que 
	 * pertenece. null para no filtrar por este par�metro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param bookTitle - T�tulo o parte del t�tulo del libro en el que se contiene la 
	 * publicaci�n. null para no filtrar por este campo.
	 * @param keys - Vector con un conjunto de String que representa keywords que han de describir
	 * la publicaci�n buscada.
	 * @param parecido_** - Indica si deseamos buscar exactamente el parametro pasado, o por
	 * el contrario cualquier palabra que lo contenga. Por Ejemplo: si pasamos como parametro publisher "marc"
	 * en caso que parecido_publisher == false, busca�a �nicamente documentos cuyo publisher sea "marc",
	 * por contra, si parecido_publisher == true, buscar�a documentos cuyo publisher sea del tipo "*marc*",
	 * representando el * cualquier cadena de caracteres, incluyendo la cadena vacia.
	 * @param user - Indica el usuario que debe haber subido la publicaci�n.
	 * @return vector construido con las publicaciones que cumplen los 
	 * requisitos.
	 * 
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws UnimplementedException 
	 */
	public Vector<Publication> consultaDocumentos(String proyecto, int tipo_publicaciones, final Vector<AutorEditor> autores, final Vector<AutorEditor> editores, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, String bookTitle, Vector<String> keys, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, boolean parecido_keys, String user) throws UnimplementedException, BDException 
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

			return consultor.getPublicaciones(proyecto, tipo_publicaciones, v_authors, v_editors, title, parecido_title, publisher, parecido_publisher, journal, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, school, parecido_school,keys, bookTitle, parecido_bookTitle, user);
		}finally{
			this.cierraConexion();
		}
	}

	/**
	 * M�todo que devuelve una lista con los autores que coinciden total o parcialmente 
	 * con los datos par�metro.
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
	 * Devuelve un vector con los usuario que participan en el proyecto pasado por
	 * par�metro.
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
		this.abreConexion();
		try{
			return modif_proyectos.consultaUsuarios(proyecto);
		}finally{
			this.cierraConexion();
		}
	}

	/**
	 * Devuelve un vector con los proyectos a los que pertenece el usuario indicado.
	 * @param usuario - Usuario sobre el que se desea realizar la consulta.
	 * @return java.util.Vector 
	 * @throws NonExistingElementException - En caso que el usuario no exista.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public Vector<String> consultaProyectosUsuario(String usuario) throws BDException,NonExistingElementException
	{
		this.abreConexion();
		try{
			return modif_user.consultaProyectos(usuario);
		}finally{
			this.cierraConexion();
		}
	}

	/**
	 * Inserta la publicacion pasada por par�metro en la base de datos.
	 * @param publicacion - Publicacion a insertar.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws ExistingElementException - Si ya existe el documento 
	 */
	public void insertaDocumento(Publication publicacion) throws BDException
	{		
		this.abreConexion();
		try{
			modif_pub.insertaPublicaci�n(publicacion);
		}finally{
			this.cierraConexion();
		}

	}

	/**
	 * Inserta el usuario pasado por par�metro en la base de datos, y lo relaciona con el
	 * proyecto cuyo nombre se pasa por par�metro.
	 * @param publicacion - Usuario a insertar.
	 * @param proyecto - Proyecto con el que se encontrar� relacionado. 
	 * @throws NonExistingElementException - Si el proyecto al que se desea asociar no existe.
	 * @throws ExistingElementException - Si el Usuario ya existe.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public void insertaUsuario(Usuario usuario, String proyecto) throws ExistingElementException, BDException, NonExistingElementException
	{
		this.abreConexion();
		try {
			modif_user.creaUsuario(usuario, proyecto);
		}finally{
			this.cierraConexion();
		}

	}

	/**
	 * M�todo que se encarga de modificar la publicaci�n pasada por par�metro en la 
	 * base de datos, cambia los antiguos datos que conten�a al respecto de la misma 
	 * por los que contiene el objeto. Para ello se basa en el idDoc, y asigna uno nuevo.
	 * <br> <b> Es muy importante tener en cuenta que asigna un nuevo idDoc </b>
	 * @param publicacion - Nuevos datos de la publicaci�n.
	 * @return int - Nuevo idDoc asignado al documento.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Si la publicacion ( el idDoc) no se encuentra en
	 * la base de datos. 
	 */
	public int modificaDocumento(Publication publicacion) throws NonExistingElementException, BDException, ExistingElementException 
	{
		this.abreConexion();
		try{
			return modif_pub.modificaPublicaci�n(publicacion);
		}finally{
			this.cierraConexion();
		}
	}

	/** Modifica los datos del autor cuyo idAutor se ha pasado como par�metro, sustituyendolos por
	 *  los nuevos datos que se obtienen como par�metro.
	 *  @param idAutor - Identificador �nico de Autor del autor que se desea modificar.
	 *  @param nombreNuevo -  Nuevo nombre que se quiere almacenar en la base de datos.
	 *  @param apellidosNuevos -  Apellidos nuevos del autor que se quieren almacenar en la base de datos.
	 *  @param urlNueva Nueva - direcci�n Web del autor que se quiere almacenar en la base de datos.
	 * 	@throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - Si el AutorEditor no se encuentra en la base de datos. 
	 */
	public void modificaAutor(int id_autor, String nombre, String apellidos, String web) throws NonExistingElementException, BDException 
	{
		this.abreConexion();
		try{
			modif_autores.modificaAutor(id_autor, nombre, apellidos, web);
		}finally{
			this.cierraConexion();
		}
	}

	/**
	 * Elimina la publicaci�n cuyo id se le pasa por par�metro.
	 * Para ello elimina previamente todos los vinculos que tenga, tanto con autores,
	 * keywords, proyectos, etc. 
	 * @param id_doc - IdDoc del documento que se desea borrar.
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - En caso que el documento no exista.
	 */
	public void eliminaDocumento(String idDoc) throws NonExistingElementException, UnimplementedException, BDException 
	{
		this.abreConexion();
		try
		{
			int id_doc = Integer.parseInt(idDoc);
			modif_pub.borraPublicaci�n(id_doc);
		}finally{
			this.cierraConexion();
		}
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
	 * Elimina al usuario de la aplicacion, y asigna las publicaciones que ha subido
	 * este al usuario pasado como segundo par�metro.
	 * 
	 * @param usuario - Nombre del usuario que se quiere eliminar.
	 * @param nuevoUserPublicaciones - Nombre del usuario al que se le asignar�n 
	 * las publicaciones subidas por el usuario a eliminar.
	 * @throws database.BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws NonExistingElementException - En caso de que no existan alguno de los dos usuarios.
	 * Si no existe el usuario a eliminar su campo int llevar� el valor USUARIO, mientras
	 * que si el que no existe es el nuevoUserPublicaciones este campo tomar� el valor
	 * INDEFINIDA.
	 */
	public void eliminaUsuario(String usuario, String nuevoUserPublicaciones) throws NonExistingElementException, UnimplementedException, BDException 
	{
		this.abreConexion();
		try{
			modif_user.eliminaUsuario(usuario, nuevoUserPublicaciones);
		}finally{
			this.cierraConexion();
		}

	}


	public String verificaUsuario(String nombre, String password) throws ConnectionNullException, ConnectionException, NonExistingElementException, PermisssionException, UnimplementedException, BDException 
	{
		return consultor.getTipoUser(nombre, password);
	}

	/**
	 * M�todo para consultar cual es el idAut que le ha correspondido al ultimo
	 * AutorEditor en ser insertado en la aplicaci�n.
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
	 * M�todo que devuelve el id correspondiente al primer autor cuyo nombre y apellidos
	 * sean iguales a los pasados por par�metro. En caso que el par�metro sea null, no lo
	 * tendr� en cuenta. Si los dos parametros son null devolvera el primero.
	 * @param nombre - Nombre que debe corresponder a la persona buscada.
	 * @param apellidos - Apellidos de la persona buscada.
	 * @return - Entero correspondiente al idAut de la persona buscada. 0 en caso que
	 * no exista ningun AutorEditor que se ajuste a lo que se busca.
	 * @throws BDException  - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 */
	public int consultaIdAutor(String nombre, String apellidos) throws BDException
	{
		this.abreConexion();
		try{
			return modif_autores.consultaIdAutor(nombre, apellidos);	
		}finally{
			this.cierraConexion();
		}

	}

	/**
	 * M�todo que se utiliza para consultar si la keyword que se pasa por par�metro se 
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
	 * M�todo para consultar cual es el idDoc que le ha correspondido al documento
	 * en ser insertado en la aplicaci�n.
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


	/**
	 * Inserta en la base de datos el AutorEditor que se corresponde con los valores
	 * que contiene el objeto pasado por par�metro.
	 * @param ae - Objeto que contiene los datos del AutorEditor pasado por par�metro
	 * @throws BDException - Diversos problemas con la conexion a la base de datos, se puede deducir
	 * analizando la clase concreta de BDException.
	 * @throws ExistingElementException - Si un autorEditor con los 3 campos iguales 
	 * ya se encuentra en la base de datos.
	 */
	public void insertaAutorEditor(AutorEditor ae) throws BDException
	{
		this.abreConexion();
		try{							
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
		}finally{
			this.cierraConexion();
		}
	}

	/**
	 * M�todo para ejecutar la sentencia pasada por par�metro, ha de ser de tipo
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
	
	
	public String obtenerUsuariosProyecto(String  proyecto) throws FileNotFoundException, BDException
	{
		Element root = new Element("listaUsuarios");
		
		Element pertenecen = new Element("pertenecen");
		String consulta = "SELECT usuarios.nombre FROM usuarios, participaen WHERE usuarios.nombre = participaen.usuario AND participaen.proyecto = '" + proyecto +"' ORDER BY usuarios.nombre;";
		Vector<Object[]> result = database.exeQuery(consulta);
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
		result = database.exeQuery(consulta);
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

		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}
	
	public String obtenerListaTotalUsuarios() throws FileNotFoundException, BDException
	{
		Element root = new Element("listaUsuarios");
		String consulta = "SELECT nombre FROM usuarios ORDER BY nombre";
		Vector<Object[]> result = database.exeQuery(consulta);
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
	
	public String obtenerListaPublicacionesProyecto(String  proyecto) throws BDException, UnimplementedException
	{
		Vector<Publication> publicaciones = consultaDocumentos(proyecto, CodigosDatos.codSumaTodasPublicaciones, null, null, null, false, null, null, null, null, null, null, null, null, null, null, false, false, false, false, false, false, false, false, false, null);

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
	
	public String obtenerListaPublicacionesUsuario(String  user) throws BDException, UnimplementedException
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
	
	
	public String obtenerListaVincularDesvincular(String  user, String idDoc) throws BDException, UnimplementedException, NonExistingElementException
	{
		Vector<Object[]> result = database.exeQuery("SELECT tipo FROM usuarios WHERE nombre = '" + user + "';");
		if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
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
		result = database.exeQuery(consultaVincular);
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
		result = database.exeQuery(consultaDesvincular);
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
		
		XMLOutputter outputter = new XMLOutputter();
		return outputter.outputString (new Document(root));
	}

	
	/**
	 * Este m�todo establece la conexi�n con la base de datos.
	 */
	private void abreConexion(){

	}

	/**
	 * Este m�todo cierra la conexion con la base de datos.
	 */
	private void cierraConexion(){

	}

	public void insertaProyecto(String proyecto, String jefe) throws ExistingElementException, BDException 
	{
		modif_proyectos.insertaProyecto(proyecto, jefe);
	}

}