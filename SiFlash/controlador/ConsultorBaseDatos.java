//Source file: C:\\GENERADO\\database\\ConsultorBaseDatos.java

package controlador;

import java.util.Vector;

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
import database.BDException;
import database.BaseDatos;




/**
 * Clase que se encarga de realizar las consultas necesarias sobre la base de 
 * datos.
 */
public class ConsultorBaseDatos 
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
	 * @param parecido_authors - Booleano que indica si vale con que el autor/editor sea parecido
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
	public Vector<Publication> getPublicaciones(int tipo_publicaciones, final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException 
	{
		Vector<Publication> vector= new Vector<Publication>();
		if ((tipo_publicaciones & CodigosDatos.codArticle)== CodigosDatos.codArticle)
			vector.addAll(this.getArticles(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));

		if ((tipo_publicaciones & CodigosDatos.codBook)== CodigosDatos.codBook)
			vector.addAll(this.getBooks(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codBookLet)== CodigosDatos.codBookLet)
			vector.addAll(this.getBooklets(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codConference) == CodigosDatos.codConference)
			vector.addAll(this.getConferences(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codInbook) == CodigosDatos.codInbook)
			vector.addAll(this.getInbooks(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codIncollection) == CodigosDatos.codIncollection)
			vector.addAll(this.getIncollection(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codInproceedings) == CodigosDatos.codInproceedings)
			vector.addAll(this.getInproceedings(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codManual) == CodigosDatos.codManual)
			vector.addAll(this.getManuals(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codMasterThesis) == CodigosDatos.codMasterThesis)
			vector.addAll(this.getMasterThesis(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codMisc) == CodigosDatos.codMisc)
			vector.addAll(this.getMiscs(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codphdThesis) == CodigosDatos.codphdThesis)
			vector.addAll(this.getPhdThesis(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codProceedings) == CodigosDatos.codProceedings)
			vector.addAll(this.getProceedings(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codTechReport) == CodigosDatos.codTechReport)
			vector.addAll(this.getTechReports(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
		
		if ((tipo_publicaciones & CodigosDatos.codUnpublished) == CodigosDatos.codUnpublished)
			vector.addAll(this.getUnpublisheds(authors, parecido_authors, title, publisher, journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, series, address, pagesMin, pagesMax, organization, school, note, abstracts, bookTitle, parecido_publisher, parecido_series, parecido_address, parecido_journal, parecido_volume, parecido_school, parecido_bookTitle, parecido_organization));
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
	 * @roseuid 47C5961C0148
	 */
	public Publication getPublicacionIddoc(int id_doc) throws BDException 
	{
		return null;
	}


	private Vector<Article> getArticles(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM article");	 
		consulta += this.creaConsulta("article", authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, null, parecido_series, null, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector<Article> vector = new Vector<Article>();
		for (int i=0; i< v.size();i++){
			Article articulo = new Article(v.get(i));
			vector.add(articulo);
		}
		return vector;
	}

	private Vector<Book> getBooks(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM book");	 
		consulta += this.creaConsulta("book", authors, parecido_authors, title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector<Book> vector = new Vector<Book>();
		for (int i=0; i< v.size();i++){
			Book libro = new Book(v.get(i));
			vector.add(libro);
		}
		return vector;
	}

	private Vector<Booklet> getBooklets(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM booklet");
		consulta += this.creaConsulta("booklet", authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector<Booklet> vector = new Vector<Booklet>();
		for (int i=0; i < v.size(); i++){
			Booklet booklet = new Booklet(v.get(i));
			vector.add(booklet);
		}
		return vector;
	}

	private Vector<Conference> getConferences(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM conference");
		consulta += this.creaConsulta("conference", authors, parecido_authors, title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, organization, parecido_organization, null, parecido_school, note, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector <Conference> vector = new Vector <Conference>();
		for (int i = 0; i< v.size();i++){
			Conference conference = new Conference (v.get(i));
			vector.add(conference);
		}
		return vector;
	}

	private Vector<InBook> getInbooks(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM inbook");
		consulta += this.creaConsulta("inbook", authors, parecido_authors, title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, null, parecido_organization, null, parecido_school, note, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector<Object[]> v = database.exeQuery(consulta);
		Vector <InBook> vector = new Vector <InBook>();
		for (int i = 0; i< v.size();i++){
			InBook inbook = new InBook (v.get(i));
			vector.add(inbook);
		}
		return vector;
	}   

	private Vector<InCollection> getIncollection(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String("SELECT * FROM incollection");
		consulta += this.creaConsulta("incollection", authors, parecido_authors, title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, null, parecido_organization, null, parecido_school, note, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector<InCollection> vector = new Vector<InCollection>();
		for (int i= 0; i< v.size();i++){
			InCollection incollection = new InCollection (v.get(i));
			vector.add(incollection);
		}
		return vector;
	}

	private Vector<InProceedings> getInproceedings(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String ("SELECT * FROM inproceedings");
		consulta += this.creaConsulta("inproceedings", authors, parecido_authors, title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, pagesMin, pagesMax, organization, parecido_organization, null, parecido_school, note, abstracts, bookTitle, parecido_bookTitle);	   
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <InProceedings> vector = new Vector<InProceedings>();
		for (int i = 0; i < v.size() ; i++){
			InProceedings inproceeding = new InProceedings(v.get(i));
			vector.add(inproceeding);
		}
		return vector;
	}

	private Vector<Manual> getManuals(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String ("SELECT * FROM manual");
		consulta += this.creaConsulta("manual", authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, organization, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector<Manual> vector = new Vector<Manual>();
		for (int i= 0; i< v.size();i++){
			Manual manual = new Manual(v.get(i));
			vector.add (manual);
		}
		return vector;
	}   

	private Vector<MastersThesis> getMasterThesis(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String consulta = new String ("SELECT * FROM mastersthesis");
		consulta += this.creaConsulta("mastersthesis", authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, school, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <MastersThesis> vector = new Vector<MastersThesis>();
		for (int i= 0; i< v.size();i++){
			MastersThesis mastersthesis = new MastersThesis(v.get(i));
			vector.add(mastersthesis);
		}
		return vector;
	}   

	private Vector<Misc> getMiscs(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "misc";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, null, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <Misc> vector = new Vector<Misc>();
		for (int i= 0; i< v.size();i++){
			Misc publicacion = new Misc(v.get(i));
			vector.add(publicacion);
		}
		return vector;	   
	}

	private Vector<PhdThesis> getPhdThesis(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "phdthesis";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, school, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <PhdThesis> vector = new Vector<PhdThesis>();
		for (int i= 0; i< v.size();i++){
			PhdThesis publicacion = new PhdThesis(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}

	private Vector<Proceedings> getProceedings(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "proceedings";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, parecido_authors, title, publisher, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, volume, parecido_volume, series, parecido_series, address, parecido_address, -1, -1, organization, parecido_organization, null, parecido_school, note, abstracts, bookTitle, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <Proceedings> vector = new Vector<Proceedings>();
		for (int i= 0; i< v.size();i++){
			Proceedings publicacion = new Proceedings(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}   

	private Vector<TechReport> getTechReports(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "techreport";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, series, parecido_series, address, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <TechReport> vector = new Vector<TechReport>();
		for (int i= 0; i< v.size();i++){
			TechReport publicacion = new TechReport(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}

	private Vector<Unpublished> getUnpublisheds(final Vector<String> authors, final boolean parecido_authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle, boolean parecido_publisher, boolean parecido_series, boolean parecido_address, boolean parecido_journal, boolean parecido_volume, boolean parecido_school, boolean parecido_bookTitle, boolean parecido_organization) throws BDException, UnimplementedException{
		String tabla = "unpublished";
		String consulta = new String ("SELECT * FROM "+ tabla);
		consulta += this.creaConsulta(tabla, authors, parecido_authors, title, null, parecido_publisher, null, parecido_journal, yearInicial, yearFinal, monthInicial, monthFinal, null, parecido_volume, null, parecido_series, null, parecido_address, -1, -1, null, parecido_organization, null, parecido_school, note, abstracts, null, parecido_bookTitle);
		consulta += ";";
		Vector <Object[]> v = database.exeQuery(consulta);
		Vector <Unpublished> vector = new Vector<Unpublished>();
		for (int i= 0; i< v.size();i++){
			Unpublished publicacion = new Unpublished(v.get(i));
			vector.add(publicacion);
		}
		return vector;	 	   
	}   

	private String creaConsulta (final String tabla, 
			final Vector<String> authors, final boolean parecido_authors,
			final String title, 
			final String publisher, final boolean parecido_publisher,
			final String journal, final boolean parecido_journal,		   
			final int yearInicial, final int yearFinal, final String monthInicial, 
			final String monthFinal, final String volume, final boolean parecido_volume,
			final String series, final boolean parecido_series,		   
			final String address, final boolean parecido_address,
			final int pagesMin, final int pagesMax, 
			final String organization, final boolean parecido_organization,
			final String school, final boolean parecido_school,
			final Vector<String> note, final Vector<String> abstracts, 
			final String bookTitle, final boolean parecido_bookTitle) throws UnimplementedException{



		String str = null;
		boolean iniciado = false;
		if (authors!= null && !authors.isEmpty()){
			if (!iniciado){
				iniciado = true;
				str = new String (" WHERE( ");
			}				   
			str += creaConsultaAutores(tabla,authors,parecido_authors);			   
		}

		if (title != null){
			if (!iniciado){
				iniciado = true;
				str = new String (" WHERE( ");
			}
		}

		if (iniciado) str += ")"; 
		if (true) throw new UnimplementedException();
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
	 * 			SELECT editadoPor.idDoc FROM editadoPor WHERE( 
	 * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
	 * 			)
	 * 			UNION
	 * 			SELECT escritoPor.idDoc FROM escritoPor WHERE(
	 * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
	 * 			)
	 * 		)
	 * 	)
	 * 
	 *  AND
	 *  
	 * 	book.idDoc IN (
	 * 		(
	 * 			SELECT editadoPor.idDoc FROM editadoPor WHERE( 
	 * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
	 * 			)
	 * 			UNION
	 * 			SELECT escritoPor.idDoc FROM escritoPor WHERE(
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
	private String creaConsultaAutores(String tabla, Vector<String> authors,boolean parecido){
		String cons = new String();	  
		for (int i=0; i< authors.size();i++){
			String palabra;
			if (parecido){
				palabra = new String ("%");
				palabra += authors.get(i);
				palabra += ("%");
			}
			else palabra = authors.get(i);
			// La palabra de busqueda de patron de autor/editor ya está buscada.

			if (i > 0) cons += " AND ";	

			cons += tabla + ".idDoc IN (";

			cons += "SELECT  editadoPor.idDoc FROM editadoPor WHERE( editadoPor.nombre LIKE ('" + palabra;
			cons += "') OR editadoPor.apellido LIKE('" + palabra +"'))";

			cons += " UNION ";

			cons += "SELECT escritoPor.idDoc FROM escritoPor WHERE( escritoPor.nombre LIKE ('" + palabra; 
			cons += "') OR escritoPor.apellido LIKE('" + palabra +"'))";

			cons += ")";
		}

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
	@SuppressWarnings("unused")
	private String creaConsultaSimple(final String campo_de_busqueda, 
			final String token, final boolean parecido){	   		  	   
		if (parecido) return new String (campo_de_busqueda + " = '" + token + "'");
		else return new String (campo_de_busqueda + " LIKE ('%" + token + "%')");

	}  
}
