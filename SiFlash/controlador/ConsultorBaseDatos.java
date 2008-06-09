//Source file: C:\\GENERADO\\database\\ConsultorBaseDatos.java

package controlador;

import java.sql.Connection;
import java.util.Vector;

import personas.AutorEditor;
import publicaciones.Article;
import publicaciones.Book;
import publicaciones.Booklet;
import publicaciones.CodigosDatos;
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
import controlador.exceptions.AuthenticationException;
import controlador.exceptions.ExistenceException;
import controlador.exceptions.NonExistingElementException;
import database.BDException;
import database.BaseDatos;




/**
 * Clase que se encarga de realizar las consultas más complejas que
 * necesarias sobre la base de datos.
 */
class ConsultorBaseDatos 
{   

	/**
	 * Base de datos sobre la que realiza las operaciones cuando no se le
	 * pasa un objeto Connection por parámetro.
	 */
	private BaseDatos database;

	/**
	 *  Constructor pasandole el parametro de la base de datos que posee.
	 * @param database - La base de datos sobre la que trabaja
	 */
	protected ConsultorBaseDatos(BaseDatos database) {
		this.database = database;
	}

	/**
	 * Cambia la base de datos a la que se le pasa por parámetro.
	 * @param database - La base de datos que fijará.
	 */
	protected void setDatabase(BaseDatos database) {
		this.database = database;
	}

	/**
	 * Metodo que realiza una consulta sobre la base de datos a la que se conecta el objeto
	 * Connection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return vector construido con las publicaciones que cumplen los 
	 * requisitos
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */	
	protected Vector<Publication> getPublicaciones(String proyecto, final int tipo_publicaciones, 
			final Vector<Integer> authors, final Vector<Integer> editors, final String title, 
			final boolean parecido_title, final String publisher, final boolean parecido_publisher, 
			final String journal, final boolean parecido_journal, Vector<String> years, final String volume, 
			final boolean parecido_volume, final String series, final boolean parecido_series, 
			final String address, final boolean parecido_address, final String organization, 
			final boolean parecido_organization, final String school, final boolean parecido_school, 
			final Vector<String> v_key, final String bookTitle, final boolean parecido_bookTitle, 
			final String user, Connection conn) throws BDException
	{
		Vector<Publication> vector= new Vector<Publication>();
		if ((tipo_publicaciones & CodigosDatos.codArticle)== CodigosDatos.codArticle)
			vector.addAll(this.getArticles(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codBook)== CodigosDatos.codBook)
			vector.addAll(this.getBooks(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codBookLet)== CodigosDatos.codBookLet)
			vector.addAll(this.getBooklets(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codConference) == CodigosDatos.codConference)
			vector.addAll(this.getConferences(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codInbook) == CodigosDatos.codInbook)
			vector.addAll(this.getInbooks(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codIncollection) == CodigosDatos.codIncollection)
			vector.addAll(this.getIncollection(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codInproceedings) == CodigosDatos.codInproceedings)
			vector.addAll(this.getInproceedings(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codManual) == CodigosDatos.codManual)
			vector.addAll(this.getManuals(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codMasterThesis) == CodigosDatos.codMasterThesis)
			vector.addAll(this.getMasterThesis(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codMisc) == CodigosDatos.codMisc)
			vector.addAll(this.getMiscs(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codphdThesis) == CodigosDatos.codphdThesis)
			vector.addAll(this.getPhdThesis(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codProceedings) == CodigosDatos.codProceedings)
			vector.addAll(this.getProceedings(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codTechReport) == CodigosDatos.codTechReport)
			vector.addAll(this.getTechReports(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));

		if ((tipo_publicaciones & CodigosDatos.codUnpublished) == CodigosDatos.codUnpublished)
			vector.addAll(this.getUnpublisheds(proyecto, authors, editors, title, parecido_title, publisher, journal, years, volume, series, address, organization, school, v_key, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization, user, conn));
		return vector;
	}

	/*
	 * Devuelve la publicación que corresponde a un idDoc determinado.
	 * 
	 * Excepcion si no corresponde con ninguna publicacion.
	 * @param id_doc - IdDoc para el que se desea buscar su correspondiente 
	 * publicación.
	 * @return publicacion
	 * @throws database.BDException
	 * @throws UnimplementedException 
	 * @roseuid 47C5961C0148
	 */
	/*protected Publication getPublicacionIddoc(int id_doc) throws BDException, UnimplementedException 
	{
		String c_filtro = new String ("SELECT DISTINCT tabla FROM tipopublication WHERE idDoc = " + id_doc +";");
		Vector <Object[]> res= database.exeQuery(c_filtro, null);
		if (res == null || res .size() == 0){
			return null;
		}
		String tabla = (String) res.firstElement()[0];
		String cs2 = new String ("SELECT DISTINCT * FROM " + tabla + " WHERE idDoc = " + id_doc + ";");

		res = database.exeQuery(cs2, null);
		if (res == null || res .size() == 0){
			return null;
		}

		return construyePub(tabla, res.firstElement());
	}*/

	/*private Publication construyePub(String tipo,Object[] datos) throws UnimplementedException{
		if (tipo == null) return null;
		if (tipo.equalsIgnoreCase("Article")) return new Article(datos);
		if (tipo.equalsIgnoreCase("Book")) return new Book(datos);
		if (tipo.equalsIgnoreCase("Booklet")) return new Booklet(datos);
		if (tipo.equalsIgnoreCase("Conference")) return new Conference(datos);
		if (tipo.equalsIgnoreCase("InBook")) return new InBook(datos);
		if (tipo.equalsIgnoreCase("InCollection")) return new InCollection(datos);
		if (tipo.equalsIgnoreCase("InProceedings")) return new InProceedings(datos);
		if (tipo.equalsIgnoreCase("Manual")) return new Manual(datos);
		if (tipo.equalsIgnoreCase("MastersThesis")) return new MastersThesis(datos);
		if (tipo.equalsIgnoreCase("Misc")) return new Misc(datos);
		if (tipo.equalsIgnoreCase("PhdThesis")) return new PhdThesis(datos);
		if (tipo.equalsIgnoreCase("Proceedings")) return new Proceedings(datos);
		if (tipo.equalsIgnoreCase("TechReport")) return new TechReport(datos);
		if (tipo.equalsIgnoreCase("Unpublished")) return new TechReport(datos);
		return null;
	}*/


	
	/**
	 * Metodo que realiza una busqueda de Articles sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Articles que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */		
	private Vector<Article> getArticles(final String proyecto, final Vector<Integer> authors, 
			final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, 
			String journal, Vector<String> years, String volume, String series, String address, 
			String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher,
			boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume,
			boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user,
			Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT ART1.idDoc, ART1.title, ART1.journal, ART1.year, ART1.volume, ART1.number, ART1.pages, ART1.month, ART1.note, ART1.abstract, ART1.URL, ART1.user, ART1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM article ART1 LEFT OUTER JOIN tienekey KEY1 ON ART1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON ART1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON ART1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "ART1", "article",authors, null, title, parecido_title, null, parecido_publisher, journal, parecido_journal, years, volume, parecido_volume, null, parecido_series, null, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);		
		consulta += " ORDER BY ART1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Article> vector = Article.generaPub(v);
		return vector;		
	}

	
	/**
	 * Metodo que realiza una busqueda de Books sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Books que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<Book> getBooks(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT BOOK1.idDoc, BOOK1.title, BOOK1.publisher, BOOK1.year, BOOK1.volume, BOOK1.number, BOOK1.series, BOOK1.address, BOOK1.edition, BOOK1.month, BOOK1.note, BOOK1.abstract, BOOK1.URL, BOOK1.user, BOOK1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM book BOOK1 LEFT OUTER JOIN tienekey KEY1 ON BOOK1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON BOOK1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON BOOK1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "BOOK1","book", authors, null, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY BOOK1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Book> vector = Book.generaPub(v);
		return vector;		
	}

	
	/**
	 * Metodo que realiza una busqueda de Booklets sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Booklets que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<Booklet> getBooklets(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT BOOK1.idDoc, BOOK1.title, BOOK1.howpublished, BOOK1.address, BOOK1.month, BOOK1.year, BOOK1.note, BOOK1.abstract, BOOK1.URL, BOOK1.user, BOOK1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM booklet BOOK1 LEFT OUTER JOIN tienekey KEY1 ON BOOK1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON BOOK1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON BOOK1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "BOOK1","booklet", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY BOOK1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Booklet> vector = Booklet.generaPub(v);		
		return vector;
	}

	
	/**
	 * Metodo que realiza una busqueda de Conferences sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Conferences que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<Conference> getConferences(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT CONF1.idDoc, CONF1.title, CONF1.booktitle, CONF1.year, CONF1.crossref, CONF1.volume, CONF1.number, CONF1.series, CONF1.pages, CONF1.address, CONF1.month, CONF1.organization, CONF1.publisher, CONF1.note, CONF1.abstract, CONF1.URL, CONF1.user, CONF1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM conference CONF1 LEFT OUTER JOIN tienekey KEY1 ON CONF1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON CONF1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON CONF1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "CONF1","conference", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);	   
		consulta += " ORDER BY CONF1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Conference> vector = Conference.generaPub(v);
		return vector;
	}

	
	/**
	 * Metodo que realiza una busqueda de InBooks sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Inbooks que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<InBook> getInbooks(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT INB1.idDoc, INB1.title, INB1.edition, INB1.year, INB1.type, INB1.volume, INB1.number, INB1.series, INB1.pages, INB1.address, INB1.month, INB1.chapter, INB1.publisher, INB1.note, INB1.abstract, INB1.URL, INB1.user, INB1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM inbook INB1 LEFT OUTER JOIN tienekey KEY1 ON INB1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON INB1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON INB1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "INB1","inbook", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY INB1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <InBook> vector = InBook.generaPub(v);
		return vector;
	}   

	
	/**
	 * Metodo que realiza una busqueda de Incollections sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Incollections que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<InCollection> getIncollection(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT INC1.idDoc, INC1.title, INC1.booktitle, INC1.year, INC1.crossref, INC1.volume, INC1.number, INC1.series, INC1.pages, INC1.address, INC1.month, INC1.publisher, INC1.note, INC1.abstract, INC1.URL, INC1.user, INC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave, INC1.chapter, INC1.edition FROM incollection INC1 LEFT OUTER JOIN tienekey KEY1 ON INC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON INC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON INC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "INC1","incollection", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);	   
		consulta += " ORDER BY INC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <InCollection> vector = InCollection.generaPub(v);
		return vector;
	}

	
	
	/**
	 * Metodo que realiza una busqueda de InCollections sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los InProceedings que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<InProceedings> getInproceedings(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT INP1.idDoc, INP1.title, INP1.booktitle, INP1.year, INP1.crossref, INP1.volume, INP1.number, INP1.series, INP1.pages, INP1.address, INP1.month, INP1.organization, INP1.publisher, INP1.note, INP1.abstract, INP1.URL, INP1.user, INP1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM inproceedings INP1 LEFT OUTER JOIN tienekey KEY1 ON INP1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON INP1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON INP1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "INP1","inproceedings", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);	   
		consulta += " ORDER BY INP1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <InProceedings> vector = InProceedings.generaPub(v);
		return vector;
	}

	
	
	/**
	 * Metodo que realiza una busqueda de Manuals sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Manuals que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<Manual> getManuals(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT MAN1.idDoc, MAN1.title, MAN1.organization, MAN1.edition, MAN1.address, MAN1.month, MAN1.year, MAN1.note, MAN1.abstract, MAN1.URL, MAN1.user, MAN1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM manual MAN1 LEFT OUTER JOIN tienekey KEY1 ON MAN1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON MAN1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON MAN1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "MAN1","manual", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY MAN1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Manual> vector = Manual.generaPub(v);		
		return vector;
	}   

	
	
	/**
	 * Metodo que realiza una busqueda de MastersThesis sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los MastersThesis que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<MastersThesis> getMasterThesis(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT MAS1.idDoc, MAS1.title, MAS1.type, MAS1.school, MAS1.address, MAS1.month, MAS1.year, MAS1.note, MAS1.abstract, MAS1.URL, MAS1.user, MAS1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM mastersthesis MAS1 LEFT OUTER JOIN tienekey KEY1 ON MAS1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON MAS1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON MAS1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "MAS1","mastersthesis", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, null, parecido_organization, school, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY MAS1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <MastersThesis> vector = MastersThesis.generaPub(v);		
		return vector;
	}   

	/**
	 * Metodo que realiza una busqueda de Miscs sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Miscs que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */		
	private Vector<Misc> getMiscs(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT MISC1.idDoc, MISC1.title, MISC1.howpublished, MISC1.month, MISC1.year, MISC1.note, MISC1.abstract, MISC1.URL, MISC1.user, MISC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM misc MISC1 LEFT OUTER JOIN tienekey KEY1 ON MISC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON MISC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON MISC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "MISC1","misc", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, null, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY MISC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Misc> vector = Misc.generaPub(v);		
		return vector;  
	}

	
	/**
	 * Metodo que realiza una busqueda de PhdThesis sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los PhdThesis que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */		
	private Vector<PhdThesis> getPhdThesis(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT PHD1.idDoc, PHD1.title, PHD1.type, PHD1.school, PHD1.address, PHD1.month, PHD1.year, PHD1.note, PHD1.abstract, PHD1.URL, PHD1.user, PHD1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM phdthesis PHD1 LEFT OUTER JOIN tienekey KEY1 ON PHD1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON PHD1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON PHD1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "PHD1","phdthesis", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, null, parecido_organization, school, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY PHD1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <PhdThesis> vector = PhdThesis.generaPub(v);		
		return vector; 	   
	}

	
	/**
	 * Metodo que realiza una busqueda de Proceedings sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Proceedings que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */		
	private Vector<Proceedings> getProceedings(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT PROC1.idDoc, PROC1.title, PROC1.booktitle, PROC1.year, PROC1.volume, PROC1.number, PROC1.series, PROC1.address, PROC1.month, PROC1.organization, PROC1.publisher, PROC1.note, PROC1.abstract, PROC1.URL, PROC1.user, PROC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM proceedings PROC1 LEFT OUTER JOIN tienekey KEY1 ON PROC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON PROC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON PROC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "PROC1","proceedings", null, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);
		consulta += " ORDER BY PROC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Proceedings> vector = Proceedings.generaPub(v);
		return vector; 	   
	}   

	
	
	/**
	 * Metodo que realiza una busqueda de TechReports sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los TechReports que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */		
	private Vector<TechReport> getTechReports(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT TEC1.idDoc, TEC1.title, TEC1.type, TEC1.institution, TEC1.address, TEC1.month, TEC1.year, TEC1.note, TEC1.abstract, TEC1.URL, TEC1.user, TEC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave, TEC1.number FROM techreport TEC1 LEFT OUTER JOIN tienekey KEY1 ON TEC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON TEC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON TEC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "TEC1","techreport", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY TEC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <TechReport> vector = TechReport.generaPub(v);		
		return vector;  
	}

	
	/**
	 * Metodo que realiza una busqueda de Unpublisheds sobre la base de datos a la que se conecta
	 * el objeto Conncection pasado por parámetro.
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 * @param conn - Objeto Connection sobre el que se va a realizar la busqueda.
	 * @return Vector construido con los Unpublished que cumplen los requisitos.
	 * @throws database.BDException - Diversos problemas en las operaciones con la base de datos,
	 * se puede concretar analizando la clase concrea de BDException, así como el mensaje que
	 * contenga.
	 */			
	private Vector<Unpublished> getUnpublisheds(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT UNP1.idDoc, UNP1.title, UNP1.month, UNP1.year, UNP1.note, UNP1.abstract, UNP1.URL, UNP1.user, UNP1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM unpublished UNP1 LEFT OUTER JOIN tienekey KEY1 ON UNP1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON UNP1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON UNP1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "UNP1","unpublished", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, null, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY UNP1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Unpublished> vector = Unpublished.generaPub(v);		
		return vector;   
	}   


	
	/**
	 * Metodo que construye el String correspondiente a la sentencia SQL que se debe realizar
	 * para consultar sobre la base de datos los documentos cuyas caracterísitcas coincidan con
	 * los datos que se pasan por parámetro.
	 * @param proyecto - Proyecto al que debe pertenecer documento,
	 *  el null para no filtrar por este campo.
	 * @param alias_pertenecea - Alias por el que hemos de referirnos a la tabla pertenecea para
	 * realizar la consulta.
	 * @param alias_tabla - Alias por el que debemos referirnos a la tabla del tipo de documento buscado.
	 * @param nombre_tabla - Nombre de la tabla del tipo de documento buscado.
	 * @param authors - Vector con un conjunto de Integers que representan los idAut de
	 * los autores por los que se desea filtrar. null para no filtrar por este campo.
	 * @param editors - Vector con un conjunto de Integers que representan los idAut de
	 * los editores por los que se desea filtrar. null para no filtrar por este campo.	
	 * @param title - Titulo o parte del título de la publicación sobre la que queremos 
	 * realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_title - Indica si basta con que el title del documento sea parecido (*title*), en
	 * caso que el valor sea true, o debe ser exactamente igual, en caso contrario.
	 * @param publisher - Editorial o parte de la editorial de la publicación sobre la	 
	 * que queremos realizar la búsqueda. null para no filtrar por este campo.
	 * @param parecido_publisher - Indica si basta con que el publisher del documento sea 
	 * parecido (*publisher*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param parecido_journal - Indica si basta con que el journal del documento 
	 * sea parecido (*journal*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param years - Vector de Integer, contiene los Años a los que puede pertencer la
	 * publicación, de esta forma permitimos por ejemplo, 1990 y 1992, pero no 1991.
	 * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
	 * por este campo.
	 * @param parecido_volume - Indica si basta con que el volume del documento 
	 * sea parecido (*volume*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param series - Serie o parte de su nombre en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param parecido_series - Indica si basta con que el series del documento 
	 * sea parecido (*series*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param address - Lugar en que se publica o parte de su nombre. null para no 
	 * filtrar por este campo.
	 * @param parecido_address - Indica si basta con que el address del documento 
	 * sea parecido (*address*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param organization - Organizacion o parte del nombre de la misma encargada del
	 * documento. null para no filtrar por este parámetro
	 * @param parecido_organization - Indica si basta con que el organization del documento 
	 * sea parecido (*organization*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param school - Escuela o parte del nombre de la misma en la que se ha 
	 * realizado. null para no filtrar por este campo.
	 * @param parecido_school - Indica si basta con que el school del documento 
	 * sea parecido (*school*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param v_key - Vector de String con las keywords que ha de contener el documento.
	 * @param bookTitle - Título o parte del título del libro en el que se contiene o se ha
	 * extraido el documento que deseamos buscar. null para no filtrar por este campo.
	 * @param parecido_bookTitle - Indica si basta con que el bookTitle del documento 
	 * sea parecido (*bookTitle*), en caso que el valor sea true, o debe ser exactamente igual, 
	 * en caso contrario.
	 * @param user - Usuario que ha añadido la publicación al sistema.
	 */		
	protected String creaConsulta (final String proyecto,
			final String alias_pertenecea, 
			final String alias_tabla,final String nombre_tabla, 
			final Vector<Integer> authors, final Vector<Integer> editors,
			final String title, final boolean parecido_title,
			final String publisher, final boolean parecido_publisher,
			final String journal, final boolean parecido_journal,		   
			final Vector<String> years,
			final String volume, final boolean parecido_volume,
			final String series, final boolean parecido_series,		   
			final String address, final boolean parecido_address,
			final String organization, final boolean parecido_organization,
			final String school, final boolean parecido_school,
			final Vector<String> v_key,  
			final String bookTitle, final boolean parecido_bookTitle, String user){



		String str = new String();

		if (proyecto != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_pertenecea,"pertenecea","proyecto",proyecto,false);
		}
			
		if (authors!= null && !authors.isEmpty()){
			str += (" AND ");
			str += creaConsultaAutores(alias_tabla,authors,CodigosDatos.codAutor);			
		}

		if (editors!= null && !editors.isEmpty()){
			str += (" AND ");
			str += creaConsultaAutores(alias_tabla,editors,CodigosDatos.codEditor);			
		}		

		if (title != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla,"title",title,parecido_title);
		}

		if (publisher != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla,"publisher",publisher,parecido_publisher);
		}

		if (journal != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "journal", journal, parecido_journal);
		}

		if (years!= null && years.size() != 0){
			str += " AND (";
			boolean alguno = false;
			for (int i=0;i<years.size();i++){
				if (i > 0 ) str += " OR ";
				str += creaConsultaSimple (alias_tabla,nombre_tabla,"year",years.get(i),true);
				alguno = true;
			}
			if (!alguno) str += "TRUE";
			str += (")");
		}

		if (volume != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "volume", volume, parecido_series);
		}		

		if (series != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "series", series, parecido_series);
		}		

		if (address != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "address", address, parecido_series);
		}

		if (organization != null){
			str += (" AND ");
			str+= creaConsultaSimple(alias_tabla,nombre_tabla, "organization", organization, parecido_organization);
		}

		if (school != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "school", school, parecido_school);
		}

		if (v_key != null){
			str += (" AND ");
			str += creaConsultaVectorial(alias_tabla, "tienekey", "clave", v_key, false, true);
		}

		if (bookTitle != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "booktitle", bookTitle, parecido_bookTitle);				
		}
		
		if (user != null){
			str += (" AND ");
			str += creaConsultaSimple(alias_tabla,nombre_tabla, "user", user, false);				
		}

		return str;
	}





	/**
	 * Método que genera el String con la parte de consulta relativa a los autores/editores
	 * de la publicaciones.
	 * La cadena generada debe tener el siguiente aspecto, ejemplo para búsqueda de un libro a
	 * los editores 1,2,8.
	 *  
	 * 	book.idDoc IN (
	 * 		(
	 * 			SELECT DISTINCT editadoPor.idDoc FROM editadoPor WHERE( 
	 * 				esctrito_editado_por.idPer IN (1,2,8)
	 * 				AND escrito_editado_por.escrito_o_editado = FALSE
	 * 			) 			
	 * 		)
	 * 	)
	 * 
	 * El mismo ejemplo pero nos valen tanto autores como editores.
	 * 	 book.idDoc IN (
	 * 		(
	 * 			SELECT DISTINCT editadoPor.idDoc FROM editadoPor WHERE( 
	 * 				esctrito_editado_por.idPer IN (1,2,8)
	 * 			) 			
	 * 		)
	 * 	)
	 * 
	 * @param tabla - Nombre de la tabla que representa el tipo de documento que estamos buscando.
	 * @param authors - Vector con todas los idPer de los autores/editores que deseamos filtrar 
	 * @return String con la parte de la consulta que genera el método.
	 */
	protected String creaConsultaAutores(String tabla, Vector<Integer> authors,int autor_editor){
		if (authors == null || authors.size()==0) return "TRUE";
		String cons = new String (tabla + ".idDoc IN (");
		cons += "SELECT DISTINCT escrito_editado_por.idDoc FROM escrito_editado_por WHERE (escrito_editado_por.idPer IN (";
		for (int i = 0; i < authors.size();i++){
			if (i > 0) cons += ",";
			if (authors.get(i)!= null) cons += authors.get(i).toString();						
		}
		cons += ")";		
		if (autor_editor == CodigosDatos.codEditor) 
			cons += " AND escrito_editado_por.escrito_o_editado = FALSE";
		if (autor_editor == CodigosDatos.codAutor) 
			cons += " AND escrito_editado_por.escrito_o_editado = TRUE";		
		cons += "))";	
		return cons;
	}

	
	/**
	 * Método que genera la parte de consulta relativa a los campos en los que deseamos filtrar según
	 * multiples valores, ya sea con la necesidad de contener todos ellos, o por contra, que basta con que
	 * tenga alguno de todos. Este tipo de búsqueda se utiliza por ejemplo en las keywords.
	 * 
	 * Ejemplo si deseamos que se busquen Books que tenga las keys teclado e inicio,
	 * bastando con que se le parezcan.
	 * Llamada --> creaConsultaVectorial("Books","tienekey","clave",{"teclado"-"inicio"},true,true)
	 * return -->
	 * (
	 * 		Book.idDoc IN (
	 * 			SELECT DISTINCT tienekeyA.idDoc FROM tienekey AS tienekeyA WHERE (
	 * 				tienekeyA.clave LIKE ('%teclado%')
	 * 			)
	 * 		)
	 *	)
	 *	AND
	 *	(
	 * 		Book.idDoc IN (
	 * 			SELECT DISTINCT tienekeyA.idDoc FROM tienekey AS tienekeyA WHERE (
	 * 				tienekeyA.clave LIKE ('%inicio%')
	 * 			)
	 * 		)
	 * 	)
	 *
	 * 		
	 * 
	 * 
	 * @param tabla_principal - Tabla que representa el tipo de documento que estamos buscando.
	 * @param tabla_datos - Tabla en la que se encuentra los datos por los que deseamos filtrar.
	 * @param campo_datos - Nombre del campo sobre el que se desea aplicar el filtro, dentro de 
	 * la tabla tabla_datos.
	 * @param v_datos - Vector con el conjunto de datos por el que deseamos realizar el filtrado.
	 * @param parecido - Booleano que indica si nos vale con que los datos filtrados sean parecidos,
	 * del tipo *dato*, o tiene que ser exactamente iguales.
	 * @param todos_o_alguno - Booleano que indica si se deben contener todos los datos del vector v_datos (true),
	 * o basta con que se tenga alguno (false).
	 * @return String con la parte de la consulta que se ha generado
	 */
	protected String creaConsultaVectorial(String tabla_principal, String tabla_datos,
			String campo_datos, Vector<String> v_datos, boolean parecido,
			boolean todos_o_alguno){
		String cons = null;		
		for (int i=0; i< v_datos.size();i++){
			String palabra;
			if (parecido){
				palabra = new String ("%");
				palabra += v_datos.get(i);
				palabra += ("%");
			}
			else palabra = v_datos.get(i);

			if (i > 0){
				if (todos_o_alguno)	cons += " AND ";
				else cons += "OR";
			}
			else cons = new String("(");



			cons += tabla_principal + ".idDoc IN (";

			cons += "SELECT DISTINCT " + tabla_datos +"A.idDoc " ;
			cons += "FROM " + tabla_datos + " AS " + tabla_datos +"A WHERE (" +tabla_datos + "A." + campo_datos;
			cons += " LIKE ('" + palabra + "')))";
		}
		if (cons == null ) cons = new String ("TRUE");
		else cons += ")";
		return cons;		
	}

	/**
	 * Método que genera la parte de una consulta correspondiente a una consulta simple dados
	 * unos parámetros.
	 * 
	 * Ejemplos de llamadas:<br>
	 * 	Llamada --> creaConsultaSimple ("publisher","Pearson",true);<br>
	 * 	Return	--> publisher LIKE ('%Pearson%')<br>
	 * 	Llamada	--> creaConsultaSimple ("publisher","Pearson",false);<br>
	 * 	Return	--> publisher = 'Pearson'<br>
	 * 
	 * 
	 * @param alias - Nombre por el que se conoce en el resto de la consulta a la tabla sobre la que deseamos
	 * aplicar el filtrado.
	 * @param tabla_data - Tabla sobre la que deseamos aplicar el filtrado.
	 * @param campo_de_busqueda - Campo por el que se desea filtrar
	 * @param token - Palabra que se desea hacer coincidir con el campo en el que se busca
	 * @param parecido - indica si deseamos que sea exactamente igual o contenida la palabra.
	 * @return String con la parte de la busqueda que se ha generado.
	 */
	protected String creaConsultaSimple(final String alias,final String tabla_data, final String campo_de_busqueda, 
			final String token, final boolean parecido){
		if (alias == null || tabla_data ==null || campo_de_busqueda == null || token == null) return new String("TRUE");
		String cons = new String();								
		String palabra;
		if (parecido){
			palabra = new String ("%");
			palabra += token;
			palabra += ("%");
		}
		else palabra = token;

		cons += alias + ".idDoc IN (";
		cons += "SELECT DISTINCT " + tabla_data +"A.idDoc ";
		cons += "FROM " + tabla_data + " AS " + tabla_data +"A WHERE (" +tabla_data + "A." + campo_de_busqueda;
		cons += " LIKE ('" + palabra + "')))";
		return cons;		

	}  
	/*
	protected String creaConsultaNumerica(final String tabla, final String campo_de_busqueda, 
			final int token_numero, final String comparador){
		if (tabla == null || comparador == null || token_numero == -1) return new String("TRUE");
		return new String (tabla + "." + campo_de_busqueda + " " + comparador + " " +  Integer.toString(token_numero));

	} 
	 */

	
	/**
	 * Método para realizar una búsqueda de autores o editores que coincidan con el nombre y los
	 * apellidos con los que se le pasan por parámetro.
	 * @param authors - Vector que contiene los objetoc, pero únicamente los nombre y/o apellidos de los
	 * autores/editores buscados.
	 * @param conn - Conexión sobre la que deseamos operar en la base de datos.
	 * @return Vector con los AutoresEditores buscados.
	 * @throws BDException - Cuando se produce algún tipo de error en las operaciones sobre la base de datos,
	 * se puede analizar por que se ha producido el fallo teniendo en cuenta el tipo de excepción concreto, así como
	 * su contenido.
	 */
	protected Vector <AutorEditor> buscaAutores (final Vector<AutorEditor> authors, Connection conn) throws BDException{
		Vector<AutorEditor> v_res = new Vector<AutorEditor>();
		if (authors== null || authors.size() == 0) return v_res;
		String cons = new String("SELECT * FROM autoreseditores WHERE (");		
		for (int i = 0; i<authors.size();i++){
			String palabra_nombre = new String ("'%" + authors.get(i).getNombre() + "%'");
			String palabra_apellidos = new String ("'%" + authors.get(i).getApellidos() + "%'");
			if (i > 0) cons += " OR ";
			if (authors.get(i).getNombre()!=null){
				cons += "nombre LIKE " + palabra_nombre;
				if (authors.get(i).getApellidos()!=null)
					cons += " AND apellidos LIKE " + palabra_apellidos;				
			}
			else {
				if (authors.get(i).getApellidos()!=null)
					cons += " apellidos LIKE " + palabra_apellidos;
				else { // Estamos buscando un autor sin nombre ni apellidos, eliminamos el OR
					if (i > 0) cons = cons.substring(0, cons.length()-4); // Para eliminar el " OR ";				
				}				
			}
		}
		cons += ");";	

		Vector<Object[]> v1 = database.exeQuery(cons, conn);
		for (int i=0; v1!=null && i<v1.size();i++){
			Object [] data = v1.get(i);
			AutorEditor at = new AutorEditor (data);
			v_res.add(at);
		}
		return v_res;
	}

	
	/**
	 * Método para analizar el tipo de usuario que corresponde al usuario cuyo nombre se pasa por parámetro.
	 * @param nombre - Nombre del usuario a comprobar.
	 * @param password - Contraseña del usuario a comprobar
	 * @param conn - Concexión con la base de datos sobre la que realizar la operación.
	 * @return String indicando el tipo concreto de usuario que representa: "user", "jefe" o "admin". En caso
	 * que no exista el usuario devuelve el mensaje "Error: El usuario no existe", si por contra no coinciden
	 * user y pass "Password incorrecto: inténtelo de nuevo.".
	 * @throws BDException - Cuando se produce algún tipo de error en las operaciones sobre la base de datos,
	 *  se puede analizar por que se ha producido el fallo  teniendo en cuenta el tipo de excepción concreto, así como su contenido. 
	 */
	public String getTipoUser(String nombre, String password, Connection conn) throws BDException
	{
		try
		{
			Vector<Object[]> result = database.exeQuery("SELECT password, tipo FROM usuarios WHERE nombre='" + nombre + "';", conn);
			if (result == null || result.size() == 0) throw new NonExistingElementException(ExistenceException.USUARIO);
			else
			{
				Object[] datos = result.get(0);
				String pass = (String)datos[0];
				String tipo = (String)datos[1];

				if (password.equals(pass))
					return tipo;
				else
					throw new AuthenticationException("Password incorrecto: inténtelo de nuevo.");
			}
		}
		catch(NonExistingElementException e)
		{
			return "Error: El usuario no existe.";
		}
		catch(AuthenticationException e)
		{
			return e.getMessage();
		}
	}
}
