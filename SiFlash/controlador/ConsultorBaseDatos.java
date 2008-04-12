//Source file: C:\\GENERADO\\database\\ConsultorBaseDatos.java

package controlador;

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
//import temporal.UnimplementedException;
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
	 * Se le pasan por parámetro los campos sobre los que se puede realizar el 
	 * filtrado.
	 * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
	 * códigos correspondientes a cada tipo de publicación que deseamos consultar.
	 * @param authors - Vector con un conjunto de Strings que deben estar contenidos 
	 * entre los autores. null para no filtrar por este campo.
	 * @param escrito_editado - Booleano que indica si vale con que el autor/editor sea parecido
	 * a los proporcionados o debe ser igual.
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
	 * requisitos@throws database.BDException
	 * @throws UnimplementedException 
	 * @roseuid 47C49FBE0280
	 */
	protected Vector<Publication> getPublicaciones(final int tipo_publicaciones, final Vector<Integer> authors, final Vector<Integer> editors, final String title, final boolean parecido_title, final String publisher, final boolean parecido_publisher, final String journal, final boolean parecido_journal, final int yearInicial, final int yearFinal, final String monthInicial, final String monthFinal, final String volume, final boolean parecido_volume, final String series, final boolean parecido_series, final String address, final boolean parecido_address, final int pagesMin, final int pagesMax, final String organization, final boolean parecido_organization, final String school, final boolean parecido_school, final Vector<String> v_key, final Vector<String> abstracts, final String bookTitle, final boolean parecido_bookTitle) throws BDException, UnimplementedException 
	{
		Vector<Publication> vector= new Vector<Publication>();
		if ((tipo_publicaciones & CodigosDatos.codArticle)== CodigosDatos.codArticle)
			vector.addAll(this.getArticles(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codBook)== CodigosDatos.codBook)
			vector.addAll(this.getBooks(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codBookLet)== CodigosDatos.codBookLet)
			vector.addAll(this.getBooklets(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codConference) == CodigosDatos.codConference)
			vector.addAll(this.getConferences(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codInbook) == CodigosDatos.codInbook)
			vector.addAll(this.getInbooks(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codIncollection) == CodigosDatos.codIncollection)
			vector.addAll(this.getIncollection(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codInproceedings) == CodigosDatos.codInproceedings)
			vector.addAll(this.getInproceedings(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codManual) == CodigosDatos.codManual)
			vector.addAll(this.getManuals(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codMasterThesis) == CodigosDatos.codMasterThesis)
			vector.addAll(this.getMasterThesis(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codMisc) == CodigosDatos.codMisc)
			vector.addAll(this.getMiscs(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codphdThesis) == CodigosDatos.codphdThesis)
			vector.addAll(this.getPhdThesis(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codProceedings) == CodigosDatos.codProceedings)
			vector.addAll(this.getProceedings(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codTechReport) == CodigosDatos.codTechReport)
			vector.addAll(this.getTechReports(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codUnpublished) == CodigosDatos.codUnpublished)
			vector.addAll(this.getUnpublisheds(authors, editors, title, parecido_title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, v_key, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		return vector;
	}

	/**
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
	protected Publication getPublicacionIddoc(int id_doc) throws BDException, UnimplementedException 
	{
		String c_filtro = new String ("SELECT DISTINCT tabla FROM tipopublication WHERE idDoc = " + id_doc +";");
		Vector <Object[]> res= database.exeQuery(c_filtro);
		if (res == null || res .size() == 0){
			return null;
		}
		String tabla = (String) res.firstElement()[0];
		String cs2 = new String ("SELECT DISTINCT * FROM " + tabla + " WHERE idDoc = " + id_doc + ";");

		res = database.exeQuery(cs2);
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

	private Vector<Article> getArticles(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM article");	 
		consulta += this.creaConsulta("article", authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, null, parecido_series, null, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector<Article> vector = new Vector<Article>();
		for (int i=0; i< v.size();i++){
			Article articulo = new Article(v.get(i));
			vector.add(articulo);
		}
		return vector;
	}

	private Vector<Book> getBooks(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM book");	 
		consulta += this.creaConsulta("book", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector<Book> vector = new Vector<Book>();
		for (int i=0; i< v.size();i++){
			Book libro = new Book(v.get(i));
			vector.add(libro);
		}
		return vector;
	}

	private Vector<Booklet> getBooklets(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM booklet");
		consulta += this.creaConsulta("booklet", authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector<Booklet> vector = new Vector<Booklet>();
		for (int i=0; i < v.size(); i++){
			Booklet booklet = new Booklet(v.get(i));
			vector.add(booklet);
		}
		return vector;
	}

	private Vector<Conference> getConferences(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM conference");
		consulta += this.creaConsulta("conference", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, organization, parecido_organization, null, parecido_school, v_key, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector <Conference> vector = new Vector <Conference>();
		for (int i = 0; i< v.size();i++){
			Conference conference = new Conference (v.get(i));
			vector.add(conference);
		}
		return vector;
	}

	private Vector<InBook> getInbooks(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM inbook");
		consulta += this.creaConsulta("inbook", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, null, parecido_organization, null, parecido_school, v_key, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector <InBook> vector = new Vector <InBook>();
		for (int i = 0; i< v.size();i++){
			InBook inbook = new InBook (v.get(i));
			vector.add(inbook);
		}
		return vector;
	}   

	private Vector<InCollection> getIncollection(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM incollection");
		consulta += this.creaConsulta("incollection", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, null, parecido_organization, null, parecido_school, v_key, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector<InCollection> vector = new Vector<InCollection>();
		for (int i= 0; i< v.size();i++){
			InCollection incollection = new InCollection (v.get(i));
			vector.add(incollection);
		}
		return vector;
	}

	private Vector<InProceedings> getInproceedings(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String ("SELECT DISTINCT INP1.idDoc, INP1.title, INP1.booktitle, INP1.year, INP1.crossref, INP1.volume, INP1.number, INP1.series, INP1.pages, INP1.address, INP1.month, INP1.organization, INP1.publisher, INP1.note, INP1.abstract, INP1.URL, INP1.user, INP1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave FROM inproceedings AS INP1, pertenecea AS PRY1, autoreseditores AS AUT1, escrito_editado_por AS EEX1, tienekey AS KEY1 WHERE PRY1.idDoc = INP1.idDoc AND EEX1.idDoc = INP1.idDoc AND AUT1.idAut = EEX1.idPer AND KEY1.idDoc = INP1.idDoc");
		consulta += this.creaConsulta("INP1", authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, organization, parecido_organization, null, parecido_school, v_key, abstracts, bookTitle, parecido_bookTitle);	   
		consulta += " ORDER BY INP1.idDoc,AUT1.idAut;";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <InProceedings> vector = InProceedings.generaPub(v);
		return vector;
	}

	private Vector<Manual> getManuals(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String ("SELECT * FROM manual");
		consulta += this.creaConsulta("manual", authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, organization, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector<Manual> vector = new Vector<Manual>();
		for (int i= 0; i< v.size();i++){
			Manual manual = new Manual(v.get(i));
			vector.add (manual);
		}
		return vector;
	}   

	private Vector<MastersThesis> getMasterThesis(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String ("SELECT * FROM mastersthesis");
		consulta += this.creaConsulta("mastersthesis", authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, school, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <MastersThesis> vector = new Vector<MastersThesis>();
		for (int i= 0; i< v.size();i++){
			MastersThesis mastersthesis = new MastersThesis(v.get(i));
			vector.add(mastersthesis);
		}
		return vector;
	}   

	private Vector<Misc> getMiscs(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "misc";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, null, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <Misc> vector = new Vector<Misc>();
		for (int i= 0; i< v.size();i++){
			Misc publicacion = new Misc(v.get(i));
			vector.add(publicacion);
		}
		return vector;	   
	}

	private Vector<PhdThesis> getPhdThesis(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "phdthesis";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, school, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <PhdThesis> vector = new Vector<PhdThesis>();
		for (int i= 0; i< v.size();i++){
			PhdThesis publicacion = new PhdThesis(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}

	private Vector<Proceedings> getProceedings(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "proceedings";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, editors, title, parecido_title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, -1, -1, organization, parecido_organization, null, parecido_school, v_key, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <Proceedings> vector = new Vector<Proceedings>();
		for (int i= 0; i< v.size();i++){
			Proceedings publicacion = new Proceedings(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}   

	private Vector<TechReport> getTechReports(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "techreport";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, series, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <TechReport> vector = new Vector<TechReport>();
		for (int i= 0; i< v.size();i++){
			TechReport publicacion = new TechReport(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}

	private Vector<Unpublished> getUnpublisheds(final Vector<Integer> authors, final Vector<Integer> editors, String title, final boolean parecido_title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> v_key, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "unpublished";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, editors, title, parecido_title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, null, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, v_key, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <Unpublished> vector = new Vector<Unpublished>();
		for (int i= 0; i< v.size();i++){
			Unpublished publicacion = new Unpublished(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}   


	protected String creaConsulta (final String tabla, 
			final Vector<Integer> authors, final Vector<Integer> editors,
			final String title, final boolean parecido_title,
			final String publisher, final boolean parecido_publisher,
			final String journal, final boolean parecido_journal,		   
			final int year_inicial, final int year_final, final String month_inicial, 
			final String month_final, final String volume, final boolean parecido_volume,
			final String series, final boolean parecido_series,		   
			final String address, final boolean parecido_address,
			final int pages_min, final int pages_max, 
			final String organization, final boolean parecido_organization,
			final String school, final boolean parecido_school,
			final Vector<String> v_key, final Vector<String> abstracts, 
			final String bookTitle, final boolean parecido_bookTitle) throws UnimplementedException{



		String str = new String();

		if (authors!= null && !authors.isEmpty()){
			str += (" AND ");
			str += creaConsultaAutores(tabla,authors,CodigosDatos.codAutor);			
		}

		if (editors!= null && !editors.isEmpty()){
			str += (" AND ");
			str += creaConsultaAutores(tabla,editors,CodigosDatos.codEditor);			
		}		
		
		if (title != null){
			str += (" AND ");
			str += creaConsultaSimple(tabla,"title",title,parecido_title);
		}

		if (publisher != null){
			str += (" AND ");
			str += creaConsultaSimple(tabla,"publisher",publisher,parecido_publisher);
		}

		if (journal != null){
			str += (" AND ");
			str += creaConsultaSimple(tabla, "journal", journal, parecido_journal);
		}

		if (year_inicial != -1){
			str += (" AND ");
			str += creaConsultaNumerica(tabla, "year", year_inicial, ">=");
		}

		if (year_final != -1){
			str += (" AND ");
			str += creaConsultaNumerica(tabla, "year", year_inicial, "<=");
		}

		if (month_inicial != null){
			throw new UnimplementedException("No implementada busqueda por mes");
		}

		if (month_final != null){
			throw new UnimplementedException("No implementada busqueda por mes");
		}

		if (series != null){
			str += (" AND ");
			str += creaConsultaSimple(tabla, "series", series, parecido_series);
		}

		if (pages_min != -1){
			str += (" AND ");
			str += creaConsultaNumerica(tabla, "pages", pages_min, ">=");			
		}

		if (pages_max != -1){
			str += (" AND ");
			str += creaConsultaNumerica(tabla, "pages", pages_max, "<=");
		}

		if (organization != null){
			str += (" AND ");
			str+= creaConsultaSimple(tabla, "organization", organization, parecido_organization);
		}

		if (school != null){
			str += (" AND ");
			str += creaConsultaSimple(tabla, "school", school, parecido_school);
		}

		if (v_key != null){
			str += (" AND ");
			str += creaConsultaVectorial(tabla, "tienekey", "clave", v_key, false, true);
		}

		if (abstracts != null){
			for (int i = 0; i< abstracts.size();i++){
				str += (" AND ");
				str += creaConsultaSimple(tabla, "abstract", abstracts.get(i), true);
			}
		}

		if (bookTitle != null){
			str += (" AND ");
			str += creaConsultaSimple(tabla, "booktitle", bookTitle, parecido_bookTitle);				
		}
		
		return str;
	}





	/**
	 * Método que genera el String con la parte de consulta relativa a los autores/editores
	 * de la publicaciones.
	 * La cadena generada debe tener el siguiente aspecto, ejemplo para búsqueda de un libro
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
			// La palabra de busqueda de patron de autor/editor ya está buscada.

			if (i > 0){
				if (todos_o_alguno)	cons += " AND ";
				else cons += "OR";
			}
			else cons = new String("(");



			cons += tabla_principal + ".idDoc IN (";

			cons += "SELECT DISTINCT " + tabla_datos +".idDoc" + campo_datos;
			cons += "FROM " + tabla_datos + " WHERE (" +tabla_datos + "." + campo_datos;
			cons += "LIKE ('" + palabra + "')))";
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
	 * @param campo_de_busqueda Campo por el que se desea filtrar
	 * @param token Palabra que se desea hacer coincidir con el campo en el que se busca
	 * @param parecido indica si deseamos que sea exactamente igual o contenida la palabra.
	 * @return
	 */
	protected String creaConsultaSimple(final String tabla, final String campo_de_busqueda, 
			final String token, final boolean parecido){
		if (tabla == null || campo_de_busqueda == null || token == null) return new String("TRUE");
		if (!parecido) return new String (tabla + "." + campo_de_busqueda + " = '" + token + "'");
		else return new String (tabla + "." + campo_de_busqueda + " LIKE ('%" + token + "%')");

	}  

	protected String creaConsultaNumerica(final String tabla, final String campo_de_busqueda, 
			final int token_numero, final String comparador){
		if (tabla == null || comparador == null || token_numero == -1) return new String("TRUE");
		return new String (tabla + "." + campo_de_busqueda + " " + comparador + " " +  Integer.toString(token_numero));

	} 

	protected Vector <AutorEditor> buscaAutores (final Vector<String> authors) throws BDException{
		Vector<AutorEditor> v_res = new Vector<AutorEditor>();
		if (authors== null || authors.size() == 0) return v_res;
		String cons = new String("SELECT * FROM autoreseditores WHERE (");		
		for (int i = 0; i<authors.size();i++){
			String palabra = new String ("'%" + authors.get(i) + "%'");  
			if (i > 0) cons += " OR ";
			cons += "nombre LIKE " + palabra + " OR apellidos LIKE " + palabra;
		}
		cons += ");";
		
		Vector<Object[]> v1 = database.exeQuery(cons);
		for (int i=0; v1!=null && i<v1.size();i++){
			Object [] data = v1.get(i);
			AutorEditor at = new AutorEditor (data);
			v_res.add(at);
		}
		return v_res;
	}
}
