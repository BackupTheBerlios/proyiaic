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
import temporal.UnimplementedException;
import controlador.exceptions.AuthenticationException;
import controlador.exceptions.ExistenceException;
import controlador.exceptions.NonExistingElementException;
import database.BDException;
import database.BaseDatos;




/**
 * Clase que se encarga de realizar las consultas necesarias sobre la base de 
 * datos.
 */
class ConsultorBaseDatos 
{   

	private BaseDatos database;

	/**
	 *  Constructor pasandole el parametro de la base de datos que posee.
	 * @param database - La base de datos sobre la que trabaja
	 */
	protected ConsultorBaseDatos(BaseDatos database) {
		this.database = database;
	}

	/**
	 * @param database the database to set
	 */
	protected void setDatabase(BaseDatos database) {
		this.database = database;
	}

	/**
	 * Metodo que realiza una consulta sobre la base de datos.
	 * Se le pasan por par�metro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param proyecto 
	 * @param tipo_publicaciones - Representa la AND l�gica a nivel de bits de los 
	 * c�digos correspondientes a cada tipo de publicaci�n que deseamos consultar.
	 * @param authors - Vector con un conjunto de Strings que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param escrito_editado - Booleano que indica si vale con que el autor/editor sea parecido
	 * a los proporcionados o debe ser igual.
	 * @param title - Titulo o parte del t�tulo de la publicaci�n sobre la que queremos 
	 * realizar la b�squeda. null para no filtrar por este campo.
	 * @param publisher - Editorial o parte de la editorial de la publicaci�n sobre la 
	 * que queremos realizar la b�squeda. null para no filtrar por este campo.
	 * @param journal - Journal o parte del mismo en que se incluye. null para no 
	 * filtrar por este campo.
	 * @param yearInicial - Primer a�o en que puede estar incluido. -1 Indica que no 
	 * hay l�mite.
	 * @param yearFinal - Ultimo a�o en el que puede estar incluida. -1 Indica que no 
	 * hay l�mite.
	 * @param monthInicial - Primer mes en el que se desea realizar la b�squeda. null 
	 * para no filtrar por este campo.
	 * @param monthFinal - Mes final en que se puede contener. null para no filtrar por 
	 * este campo.
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
	 * @param note - Vector con un conjunto de String que deber�n estar contenidos en 
	 * el campo note de la publicaci�n. null para no filtrar por este campo.
	 * @param abstracts - Vector con un conjunto de String que deber�n estar contenidos 
	 * en el campo abstract de la publicaci�n. null para no filtrar por este campo.
	 * @param bookTitle - T�tulo o parte del t�tulo del libro en el que se contiene la 
	 * publicaci�n. null para no filtrar por este campo.
	 * @param user 
	 * @return vector construido con las publicaciones que cumplen los 
	 * requisitos@throws database.BDException
	 * @throws UnimplementedException 
	 * @roseuid 47C49FBE0280
	 */
	protected Vector<Publication> getPublicaciones(String proyecto, final int tipo_publicaciones, final Vector<Integer> authors, final Vector<Integer> editors, final String title, final boolean parecido_title, final String publisher, final boolean parecido_publisher, final String journal, final boolean parecido_journal, Vector<String> years, final String volume, final boolean parecido_volume, final String series, final boolean parecido_series, final String address, final boolean parecido_address, final String organization, final boolean parecido_organization, final String school, final boolean parecido_school, final Vector<String> v_key, final String bookTitle, final boolean parecido_bookTitle, String user, Connection conn) throws BDException
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

	/**
	 * Devuelve la publicaci�n que corresponde a un idDoc determinado.
	 * 
	 * Excepcion si no corresponde con ninguna publicacion.
	 * @param id_doc - IdDoc para el que se desea buscar su correspondiente 
	 * publicaci�n.
	 * @return publicacion
	 * @throws database.BDException
	 * @throws UnimplementedException 
	 * @roseuid 47C5961C0148
	 */
	protected Publication getPublicacionIddoc(int id_doc) throws BDException, UnimplementedException 
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
	}

	private Publication construyePub(String tipo,Object[] datos) throws UnimplementedException{
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
	}

	private Vector<Article> getArticles(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT ART1.idDoc, ART1.title, ART1.journal, ART1.year, ART1.volume, ART1.number, ART1.pages, ART1.month, ART1.note, ART1.abstract, ART1.URL, ART1.user, ART1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM article ART1 LEFT OUTER JOIN tienekey KEY1 ON ART1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON ART1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON ART1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "ART1", "article",authors, null, title, parecido_title, null, parecido_publisher, journal, parecido_journal, years, volume, parecido_volume, null, parecido_series, null, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);		
		consulta += " ORDER BY ART1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Article> vector = Article.generaPub(v);
		return vector;		
	}

	private Vector<Book> getBooks(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT BOOK1.idDoc, BOOK1.title, BOOK1.publisher, BOOK1.year, BOOK1.volume, BOOK1.number, BOOK1.series, BOOK1.address, BOOK1.edition, BOOK1.month, BOOK1.note, BOOK1.abstract, BOOK1.URL, BOOK1.user, BOOK1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM book BOOK1 LEFT OUTER JOIN tienekey KEY1 ON BOOK1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON BOOK1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON BOOK1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "BOOK1","book", authors, null, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY BOOK1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Book> vector = Book.generaPub(v);
		return vector;		
	}

	private Vector<Booklet> getBooklets(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT BOOK1.idDoc, BOOK1.title, BOOK1.howpublished, BOOK1.address, BOOK1.month, BOOK1.year, BOOK1.note, BOOK1.abstract, BOOK1.URL, BOOK1.user, BOOK1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM booklet BOOK1 LEFT OUTER JOIN tienekey KEY1 ON BOOK1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON BOOK1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON BOOK1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "BOOK1","booklet", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY BOOK1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Booklet> vector = Booklet.generaPub(v);		
		return vector;
	}

	private Vector<Conference> getConferences(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT CONF1.idDoc, CONF1.title, CONF1.booktitle, CONF1.year, CONF1.crossref, CONF1.volume, CONF1.number, CONF1.series, CONF1.pages, CONF1.address, CONF1.month, CONF1.organization, CONF1.publisher, CONF1.note, CONF1.abstract, CONF1.URL, CONF1.user, CONF1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM conference CONF1 LEFT OUTER JOIN tienekey KEY1 ON CONF1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON CONF1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON CONF1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "CONF1","conference", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);	   
		consulta += " ORDER BY CONF1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Conference> vector = Conference.generaPub(v);
		return vector;
	}

	private Vector<InBook> getInbooks(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT INB1.idDoc, INB1.title, INB1.edition, INB1.year, INB1.type, INB1.volume, INB1.number, INB1.series, INB1.pages, INB1.address, INB1.month, INB1.chapter, INB1.publisher, INB1.note, INB1.abstract, INB1.URL, INB1.user, INB1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM inbook INB1 LEFT OUTER JOIN tienekey KEY1 ON INB1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON INB1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON INB1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "INB1","inbook", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY INB1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <InBook> vector = InBook.generaPub(v);
		return vector;
	}   

	private Vector<InCollection> getIncollection(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT INC1.idDoc, INC1.title, INC1.booktitle, INC1.year, INC1.crossref, INC1.volume, INC1.number, INC1.series, INC1.pages, INC1.address, INC1.month, INC1.publisher, INC1.note, INC1.abstract, INC1.URL, INC1.user, INC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave, INC1.chapter, INC1.edition FROM incollection INC1 LEFT OUTER JOIN tienekey KEY1 ON INC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON INC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON INC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "INC1","incollection", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, null, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);	   
		consulta += " ORDER BY INC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <InCollection> vector = InCollection.generaPub(v);
		return vector;
	}

	private Vector<InProceedings> getInproceedings(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT INP1.idDoc, INP1.title, INP1.booktitle, INP1.year, INP1.crossref, INP1.volume, INP1.number, INP1.series, INP1.pages, INP1.address, INP1.month, INP1.organization, INP1.publisher, INP1.note, INP1.abstract, INP1.URL, INP1.user, INP1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM inproceedings INP1 LEFT OUTER JOIN tienekey KEY1 ON INP1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON INP1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON INP1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "INP1","inproceedings", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);	   
		consulta += " ORDER BY INP1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <InProceedings> vector = InProceedings.generaPub(v);
		return vector;
	}

	private Vector<Manual> getManuals(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT MAN1.idDoc, MAN1.title, MAN1.organization, MAN1.edition, MAN1.address, MAN1.month, MAN1.year, MAN1.note, MAN1.abstract, MAN1.URL, MAN1.user, MAN1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM manual MAN1 LEFT OUTER JOIN tienekey KEY1 ON MAN1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON MAN1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON MAN1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "MAN1","manual", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY MAN1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Manual> vector = Manual.generaPub(v);		
		return vector;
	}   

	private Vector<MastersThesis> getMasterThesis(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT MAS1.idDoc, MAS1.title, MAS1.type, MAS1.school, MAS1.address, MAS1.month, MAS1.year, MAS1.note, MAS1.abstract, MAS1.URL, MAS1.user, MAS1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM mastersthesis MAS1 LEFT OUTER JOIN tienekey KEY1 ON MAS1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON MAS1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON MAS1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "MAS1","mastersthesis", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, null, parecido_organization, school, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY MAS1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <MastersThesis> vector = MastersThesis.generaPub(v);		
		return vector;
	}   

	private Vector<Misc> getMiscs(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT MISC1.idDoc, MISC1.title, MISC1.howpublished, MISC1.month, MISC1.year, MISC1.note, MISC1.abstract, MISC1.URL, MISC1.user, MISC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM misc MISC1 LEFT OUTER JOIN tienekey KEY1 ON MISC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON MISC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON MISC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "MISC1","misc", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, null, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY MISC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Misc> vector = Misc.generaPub(v);		
		return vector;  
	}

	private Vector<PhdThesis> getPhdThesis(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT PHD1.idDoc, PHD1.title, PHD1.type, PHD1.school, PHD1.address, PHD1.month, PHD1.year, PHD1.note, PHD1.abstract, PHD1.URL, PHD1.user, PHD1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM phdthesis PHD1 LEFT OUTER JOIN tienekey KEY1 ON PHD1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON PHD1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON PHD1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "PHD1","phdthesis", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, null, parecido_organization, school, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY PHD1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <PhdThesis> vector = PhdThesis.generaPub(v);		
		return vector; 	   
	}

	private Vector<Proceedings> getProceedings(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT PROC1.idDoc, PROC1.title, PROC1.booktitle, PROC1.year, PROC1.volume, PROC1.number, PROC1.series, PROC1.address, PROC1.month, PROC1.organization, PROC1.publisher, PROC1.note, PROC1.abstract, PROC1.URL, PROC1.user, PROC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM proceedings PROC1 LEFT OUTER JOIN tienekey KEY1 ON PROC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON PROC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON PROC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "PROC1","proceedings", null, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, years, volume, parecido_volume, series, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, bookTitle, parecido_bookTitle, user);
		consulta += " ORDER BY PROC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Proceedings> vector = Proceedings.generaPub(v);
		return vector; 	   
	}   

	private Vector<TechReport> getTechReports(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT TEC1.idDoc, TEC1.title, TEC1.type, TEC1.institution, TEC1.address, TEC1.month, TEC1.year, TEC1.note, TEC1.abstract, TEC1.URL, TEC1.user, TEC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave, TEC1.number FROM techreport TEC1 LEFT OUTER JOIN tienekey KEY1 ON TEC1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON TEC1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON TEC1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "TEC1","techreport", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, address, parecido_address, organization, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY TEC1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <TechReport> vector = TechReport.generaPub(v);		
		return vector;  
	}

	private Vector<Unpublished> getUnpublisheds(final String proyecto, final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, Vector<String> years, String volume, String series, String address, String organization, String school, Vector<String> v_key, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization, String user, Connection conn) throws BDException{
		String consulta = new String ("SELECT DISTINCT UNP1.idDoc, UNP1.title, UNP1.month, UNP1.year, UNP1.note, UNP1.abstract, UNP1.URL, UNP1.user, UNP1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM unpublished UNP1 LEFT OUTER JOIN tienekey KEY1 ON UNP1.idDoc = KEY1.idDoc LEFT OUTER JOIN pertenecea PRY1 ON UNP1.idDoc = PRY1.idDoc LEFT OUTER JOIN escrito_editado_por EEX1 ON UNP1.idDoc = EEX1.idDoc LEFT OUTER JOIN autoreseditores AUT1 ON AUT1.idAut = EEX1.idPer WHERE TRUE");
		consulta += this.creaConsulta(proyecto, "PRY1", "UNP1","unpublished", authors, null, title, parecido_title, null, parecido_publisher, null, parecido_journal, years, null, parecido_volume, null, parecido_series, null, parecido_address, null, parecido_organization, null, parecido_school, v_key, null, parecido_bookTitle, user);
		consulta += " ORDER BY UNP1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta, conn);
		Vector <Unpublished> vector = Unpublished.generaPub(v);		
		return vector;   
	}   


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
	 * M�todo que genera el String con la parte de consulta relativa a los autores/editores
	 * de la publicaciones.
	 * La cadena generada debe tener el siguiente aspecto, ejemplo para b�squeda de un libro
	 * 
	 * 
	 * 	book.idDoc IN (
	 * 		(
	 * 			SELECT DISTINCT editadoPor.idDoc FROM editadoPor WHERE( 
	 * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
	 * 			)
	 * 			UNION
	 * 			SELECT DISTINCT escritoPor.idDoc FROM escritoPor WHERE(
	 * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
	 * 			)
	 * 		)
	 * 	)
	 * 
	 *  AND
	 *  
	 * 	book.idDoc IN (
	 * 		(
	 * 			SELECT DISTINCT editadoPor.idDoc FROM editadoPor WHERE( 
	 * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
	 * 			)
	 * 			UNION
	 * 			SELECT DISTINCT escritoPor.idDoc FROM escritoPor WHERE(
	 * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
	 * 			)
	 * 		)
	 * 	)
	 * 
	 * 
	 * @param authors Vector con todas las palabras que deben aparecer en autores o editores.
	 * @param parecido Booleano 
	 * @return String con la parte de la consulta que genera el m�todo.
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
			// La palabra de busqueda de patron de autor/editor ya est� buscada.

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
	 * M�todo que genera la parte de una consulta correspondiente a una consulta simple dados
	 * unos par�metros.
	 * 
	 * Ejemplos de llamadas:<br>
	 * 	Llamada --> creaConsultaSimple ("publisher","Pearson",true);<br>
	 * 	Return	--> publisher LIKE ('%Pearson%')<br>
	 * 	Llamada	--> creaConsultaSimple ("publisher","Pearson",false);<br>
	 * 	Return	--> publisher = 'Pearson'<br>
	 * 
	 * 
	 * @param campo_de_busqueda Campo por el que se desea filtrar
	 * @param token Palabra que se desea hacer coincidir con el campo en el que se busca
	 * @param parecido indica si deseamos que sea exactamente igual o contenida la palabra.
	 * @return
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
					throw new AuthenticationException("Password incorrecto: int�ntelo de nuevo.");
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
