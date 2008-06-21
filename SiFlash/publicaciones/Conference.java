//Source file: C:\\GENERADO\\publicaciones\\Conference.java

package publicaciones;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.jdom.Element;

import parserFicherosBibtex.Campo;
import parserFicherosBibtex.CampoPublicacion;
import parserFicherosBibtex.CampoPublicacionAutorEditor;
import personas.AutorEditor;
import controlador.DataBaseControler;
import controlador.exceptions.ExistingElementException;
import database.BDException;


/**
 * Clase que representa los Conference, dicho tipo de documento equivale a un inproceedings,
 * incluido para compatibilidad con el lenguaje de markup Scribe en:Scribe (markup language).
 * Contiene todos sus posibles campos, así como los métodos necesarios
 * para su correcto manejo.
 */
public class Conference extends Publication 
{
	/**
	 * Contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Libro al que hace referencia.
	 */
	private String booktitle;

	/**
	 * Ayuda a generar referecias cruzadas internas.
	 */
	private String crossref;

	/**
	 * Contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private LinkedList<AutorEditor> editor;

	/**
	 * Volumen en el que está contenido.
	 */
	private String volume;

	/**
	 * Numero de volumen.
	 */
	private String number;

	/**
	 * Serie en la que se encuadra la publicacion.
	 */
	private String series;

	/**
	 * Longitud en páginas.
	 */
	private String pages;

	/**
	 * Lugar de publicación.
	 */
	private String address;

	/**
	 * Organización que se encarga de la gestión de la misma.
	 */
	private String organization;

	/**
	 * Representa a la entidad que publica.
	 */
	private String publisher;

	/**
	 * Crea un Conference a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Conference(LinkedList<Campo> campos)
	{
		Campo campo;
		Iterator<Campo> it = campos.iterator();
		while (it.hasNext())
		{
			campo = it.next();
			String nombreCampo = campo.getNombre();
			if (campo instanceof CampoPublicacion)
			{
				String valor = ((CampoPublicacion)campo).getValor();
				insertar(nombreCampo, valor);
			}
			else
			{
				LinkedList<AutorEditor> valor = ((CampoPublicacionAutorEditor)campo).getValor();
				insertar(nombreCampo, valor);
			}
				
		}
	}

	/**
	 * Establece el valor de un atributo del documento.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valorString Valor del campo que se quiere insertar.
	 */	
	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equalsIgnoreCase("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equalsIgnoreCase("idDoc") && idDoc == 0)
			idDoc = Integer.parseInt(valorString);
		else if (nombreCampo.equalsIgnoreCase("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equalsIgnoreCase("title") && title == null)
			title = valorString;
		else if (nombreCampo.equalsIgnoreCase("booktitle") && booktitle == null)
			booktitle = valorString;
		else if (nombreCampo.equalsIgnoreCase("crossref") && crossref == null)
			crossref = valorString;
		else if (nombreCampo.equalsIgnoreCase("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equalsIgnoreCase("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equalsIgnoreCase("series") && series == null)
			series = valorString;
		else if (nombreCampo.equalsIgnoreCase("address") && address == null)
			address = valorString;
		else if (nombreCampo.equalsIgnoreCase("month") && month == null)
			month = valorString;
		else if (nombreCampo.equalsIgnoreCase("organization") && organization == null)
			organization = valorString;
		else if (nombreCampo.equalsIgnoreCase("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equalsIgnoreCase("note") && note == null)
			note = valorString;
		else if (nombreCampo.equalsIgnoreCase("abstract") && _abstract == null)
			_abstract = valorString;
		else if (nombreCampo.equalsIgnoreCase("key") && key == null)
			key = separarKeys(valorString);
		else if (nombreCampo.equalsIgnoreCase("number") && number == null)
			number = valorString;
		else if (nombreCampo.equalsIgnoreCase("pages") && pages == null)
			pages = valorString;
		else if (nombreCampo.equalsIgnoreCase("year") && year == null)
			year = valorString;
		else if (nombreCampo.equalsIgnoreCase("user") && user == null)
			user = valorString;
		else if (nombreCampo.equalsIgnoreCase("url") && URL == null)
			URL = valorString;
		else if (nombreCampo.equalsIgnoreCase("proyecto") && proyecto == null)
			proyecto = valorString;
		else if (nombreCampo.equalsIgnoreCase("DOI") && DOI == null)
			DOI = valorString;
	}

	/**
	 * Establece el valor de un atributo(lista) del documento.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valor Lista con los valores del campo que se quiere insertar.
	 */		
	private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("authors") && author == null)
			author = valor;
		else if (nombreCampo.equalsIgnoreCase("editors") && editor == null)
			editor = valor;
	}
	
	@Override
	public Element generarElementoXML(boolean quitarLlaves)
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Conference");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", quitarLlaves(referencia, quitarLlaves));
		if (DOI != null)
			elemento.setAttribute("DOI", quitarLlaves(DOI, quitarLlaves));

		Element eTitle = new Element("title");
		eTitle.addContent(quitarLlaves(title, quitarLlaves));
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML(true, quitarLlaves);
		elemento.addContent(eAuthor);

		Element eBooktitle = new Element("booktitle");
		eBooktitle.addContent(quitarLlaves(booktitle, quitarLlaves));
		elemento.addContent(eBooktitle);

		Element eYear = new Element("year");
		eYear.addContent(quitarLlaves(year, quitarLlaves));
		elemento.addContent(eYear);

		Element eCrossref = new Element("crossref");
		eCrossref.addContent(quitarLlaves(crossref, quitarLlaves));
		elemento.addContent(eCrossref);

		Element eEditor = generarAutoresEditoresXML(false, quitarLlaves);
		elemento.addContent(eEditor);

		Element eVolume = new Element("volume");
		eVolume.addContent(quitarLlaves(volume, quitarLlaves));
		elemento.addContent(eVolume);

		Element eNumber = new Element("number");
		eNumber.addContent(quitarLlaves(number, quitarLlaves));
		elemento.addContent(eNumber);

		Element eSeries = new Element("series");
		eSeries.addContent(quitarLlaves(series, quitarLlaves));
		elemento.addContent(eSeries);

		Element ePages = new Element("pages");
		ePages.addContent(quitarLlaves(pages, quitarLlaves));
		elemento.addContent(ePages);

		Element eAddress = new Element("address");
		eAddress.addContent(quitarLlaves(address, quitarLlaves));
		elemento.addContent(eAddress);

		Element eMonth = new Element("month");
		eMonth.addContent(quitarLlaves(month, quitarLlaves));
		elemento.addContent(eMonth);

		Element eOrganization = new Element("organization");
		eOrganization.addContent(quitarLlaves(organization, quitarLlaves));
		elemento.addContent(eOrganization);

		Element ePublisher = new Element("publisher");
		ePublisher.addContent(quitarLlaves(publisher, quitarLlaves));
		elemento.addContent(ePublisher);

		Element eNote = new Element("note");
		eNote.addContent(quitarLlaves(note, quitarLlaves));
		elemento.addContent(eNote);

		Element eAbstract = new Element("abstract");
		eAbstract.addContent(quitarLlaves(_abstract, quitarLlaves));
		elemento.addContent(eAbstract);

		Element eKey = new Element("key");
		eKey.addContent(quitarLlaves(convertirTextoBibtexKeys(key), quitarLlaves));
		elemento.addContent(eKey);
		
		Element eURL = new Element("URL");
		eURL.addContent(URL);
		elemento.addContent(eURL);

		return elemento;
	}

	/**
	 * Genera un elemento XML con todos los autores/editores.
	 * @param b Si es true, se trata de un autor; si es false, es un editor.
	 * @param quitarLlaves Indica si se quieren quitar las llaves que pueden aparecer en alguno de los campos de la publicación.
	 * @return El elemento generado.
	 */
	private Element generarAutoresEditoresXML(boolean b, boolean quitarLlaves) 
	{
		Element eAuthorEditor;
		if (b) //eAuthor
		{
			eAuthorEditor = new Element("authors");
			if (author != null)
			{
				Iterator<AutorEditor> it = author.iterator();
				while (it.hasNext())
					eAuthorEditor.addContent(it.next().generarAuthorXML(quitarLlaves));
			}
		}
		else //eEditor
		{
			eAuthorEditor = new Element("editors");
			if (editor != null)
			{
				Iterator<AutorEditor> it = editor.iterator();
				while (it.hasNext())
					eAuthorEditor.addContent(it.next().generarEditorXML(quitarLlaves));
			}
		}
		return eAuthorEditor;
	}

	@Override
	public String getBibTeX()
	{
		String bibtex = "@conference{";
		if (referencia != null)
			bibtex += referencia;
		bibtex += "\n";
		if (title != null)
			bibtex += "\ttitle={" + convertirTextoBibtex(title) + "},\n";
		if (year != null)
			bibtex += "\tyear={" + convertirTextoBibtex(year) + "},\n";
		if (month != null)
			bibtex += "\tmonth={" + convertirTextoBibtex(month) + "},\n";
		if (author != null)
			bibtex += "\tauthor={" + convertirTextoBibtex(author) + "},\n";
		if (booktitle != null)
			bibtex += "\tbooktitle={" + convertirTextoBibtex(booktitle) + "},\n";
		if (crossref != null)
			bibtex += "\tcrossref={" + convertirTextoBibtex(crossref) + "},\n";
		if (editor != null)
			bibtex += "\teditor={" + convertirTextoBibtex(editor) + "},\n";
		if (volume != null)
			bibtex += "\tvolume={" + convertirTextoBibtex(volume) + "},\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "},\n";
		if (series != null)
			bibtex += "\tseries={" + convertirTextoBibtex(series) + "},\n";
		if (pages != null)
			bibtex += "\tpages={" + convertirTextoBibtex(pages) + "},\n";
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "},\n";
		if (organization != null)
			bibtex += "\torganization={" + convertirTextoBibtex(organization) + "},\n";
		if (publisher != null)
			bibtex += "\tpublisher={" + convertirTextoBibtex(publisher) + "},\n";
		if (_abstract != null)
			bibtex += "\tabstract={" + convertirTextoBibtex(_abstract) + "},\n";
		if (note != null)
			bibtex += "\tnote={" + convertirTextoBibtex(note) + "},\n";
		if (key != null)
			bibtex += "\tkey={" + convertirTextoBibtexKeys(key) + "},\n";
		if (bibtex.charAt(bibtex.length()-2) == ',') //Sobra la última coma.
			bibtex = bibtex.substring(0, bibtex.length()-2);
		bibtex += "}";
		
		return bibtex;
	}

	/**
	 * @return Los autores del documento.
	 */
	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	/**
	 * @return El titulo del libro del documento.
	 */
	public String getBooktitle() {
		return booktitle;
	}

	/**
	 * @return El crossref del documento.
	 */
	public String getCrossref() {
		return crossref;
	}

	/**
	 * @return Los editores del documento.
	 */
	public LinkedList<AutorEditor> getEditor() {
		return editor;
	}

	/**
	 * @return El volumen del documento.
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return El number del documento.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return La serie a la que pertenece el documento.
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @return Las páginas del documento.
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * @return El address del documento.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return La organización del documento.
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @return El publisher del documento.
	 */
	public String getPublisher() {
		return publisher;
	}

	@Override
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException 
	{
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO conference VALUES (");
		str1 += Integer.toString(getIdDoc());
		
		if (getTitle()!= null)
		str1 += ",\"" + sustituirComillasSQL(getTitle()) + "\"";
		else str1+= ",null";
		
		if (getBooktitle()!=null)
		str1 += ",\"" + sustituirComillasSQL(getBooktitle()) + "\"";
		else str1+= ",null";
		
		if (getYear()!=null)
		str1 += ",\"" + sustituirComillasSQL(getYear()) + "\"";
		else str1+= ",null";
		
		if (getCrossref()!=null)
		str1 += ",\"" + sustituirComillasSQL(getCrossref()) + "\"";
		else str1+= ",null";
		
		if(getVolume()!=null)
		str1 += ",\"" + sustituirComillasSQL(getVolume()) + "\"";
		else str1+= ",null";
		
		if(getNumber()!=null)
		str1 += ",\"" + sustituirComillasSQL(getNumber()) + "\"";
		else str1+= ",null";
		
		if(getSeries()!=null)
		str1 += ",\"" + sustituirComillasSQL(getSeries()) + "\"";
		else str1+= ",null";
		
		if(getPages()!=null)
		str1 += ",\"" + sustituirComillasSQL(getPages()) + "\"";
		else str1+= ",null";
		
		if(getAddress()!=null)
		str1 += ",\"" + sustituirComillasSQL(getAddress()) + "\"";
		else str1+= ",null";
		
		if(getMonth()!=null)
		str1 += ",\"" + sustituirComillasSQL(getMonth()) + "\"";
		else str1+= ",null";
		
		if(getOrganization()!=null)
		str1 += ",\"" + sustituirComillasSQL(getOrganization()) + "\"";
		else str1+= ",null";
		
		if(getPublisher()!=null)
		str1 += ",\"" + sustituirComillasSQL(getPublisher()) + "\"";
		else str1+= ",null";
		
		if(getNote()!=null)
		str1 += ",\"" + sustituirComillasSQL(getNote()) + "\"";
		else str1+= ",null";
		
		if(get_abstract()!=null)
		str1 += ",\"" + sustituirComillasSQL(get_abstract()) + "\"";
		else str1+= ",null";
		
		if(getURL()!=null)
		str1 += ",\"" + sustituirComillasSQL(getURL()) + "\"";
		else str1+= ",null";
		
		if(getUser()!=null)
		str1 += ",\"" + sustituirComillasSQL(getUser()) + "\"";
		else str1+= ",null";
		
		if(getReferencia()!=null)
		str1 += ",\"" + sustituirComillasSQL(getReferencia()) + "\"";
		else str1+= ",null";
		
		if(getDOI()!=null)
			str1 += ",\"" + sustituirComillasSQL(getDOI()) + "\"";
			else str1+= ",null";
		
		str1+=");";
			
		DataBaseControler dbc = new DataBaseControler();
		dbc.ejecutaString(str1, conn);
		if (idDoc == 0)
			idDoc = dbc.consultaIdDoc(conn);

		if (author != null)		
			for (int i=0;i<this.author.size();i++){
				int idAutor = author.get(i).getId();
				String str;
				if (idAutor == 0) //Hay que insertarlo
				{
					idAutor = dbc.consultaIdAutor(author.get(i).getNombre(), author.get(i).getApellidos(), conn);
					if (idAutor == 0)
					{
						dbc.insertaAutorEditor(author.get(i), conn);
						idAutor = dbc.consultaIdAutor(author.get(i).getNombre(), author.get(i).getApellidos(), conn);
					}
				}
				str = new String ("INSERT INTO escrito_editado_por VALUES(" + getIdDoc());
				str += "," + idAutor + ",TRUE);";
				vector.add(str);
			}

		if (editor != null)
			for (int i=0;i<this.editor.size();i++){
				int idEditor = editor.get(i).getId();
				String str;
				if (idEditor == 0) //Hay que insertarlo
				{
					idEditor = dbc.consultaIdAutor(editor.get(i).getNombre(), editor.get(i).getApellidos(), conn);
					if (idEditor == 0)
					{
						dbc.insertaAutorEditor(editor.get(i), conn);
						idEditor = dbc.consultaIdAutor(editor.get(i).getNombre(), editor.get(i).getApellidos(), conn);
					}
				}
				str = new String ("INSERT INTO escrito_editado_por VALUES(" + getIdDoc());
				str += "," + idEditor + ",FALSE);";
				vector.add(str);
			}

		
		vector.addAll(super.generaInserciones(conn));
			
		return vector; 
	}

	/**
	 * Genera una conjunto de Conference a partir del resultado obtenido al realizar una consulta 
	 * en la base de datos.
	 * @param v Resultado obtenido por una consulta a la base de datos, cada array de Object 
	 * contenido en  representa una fila del resultado obtenido al consultar la base de datos.
	 * @return Vector de documentos resultante.
	 */	
	public static Vector<Conference> generaPub(Vector<Object[]> v)  {
		Vector <Conference> vector = new Vector <Conference>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String doi, title,booktitle,crossref,volume,series,pages,address,number;
			String month,year,organization,publisher, note, abstracts, URL,user, referencia; 
			String proyecto,n_aut,ap_aut,clave;
			LinkedList<AutorEditor> autores,editores;
//			Vector<String> proyectos = new Vector<String>();
			Vector<String> claves = new Vector<String>();
			autores = new LinkedList<AutorEditor>();
			editores = new LinkedList<AutorEditor>();
			boolean cambio_pub,escrito_edit;
			cambio_pub = false;			
			idDoc = ((Long) array[0]).intValue();
			if (array[1] != null) title = (String) array[1]; else title = null;
			if (array[2] != null) booktitle = (String) array[2]; else booktitle = null;
			if (array[3] != null) year = ((String) array[3]); else year = null;
			if (array[4] != null) crossref = (String) array[4]; else crossref = null;
			if (array[5] != null) volume = (String) array[5]; else volume = null;
			if (array[6] != null) number = ((String) array[6]); else number = null;
			if (array[7] != null) series = (String) array[7]; else series = null;
			if (array[8] != null) pages = (String) array[8]; else pages = null;
			if (array[9] != null) address = (String) array[9]; else address = null;
			if (array[10] != null) month = (String) array[10]; else month = null;
			if (array[11] != null) organization = (String) array[11]; else organization = null;
			if (array[12] != null) publisher = (String) array[12]; else publisher = null;
			if (array[13] != null) note = (String) array[13]; else note = null;
			if (array[14] != null) abstracts = (String) array[14]; else abstracts = null;
			if (array[15] != null) URL = (String) array[15]; else URL = null;
			if (array[16] != null) user = (String) array[16]; else user = null;
			if (array[17] != null) referencia = (String) array[17]; else referencia = null;
			if (array[18] != null) proyecto = (String) array[18]; else proyecto = null;
			id_aut = ((Long) array[19]).intValue();			
			if (array[20] != null) n_aut = (String) array[20]; else n_aut = null;
			if (array[21] != null) ap_aut = (String) array[21]; else ap_aut = null;
			escrito_edit = ((Boolean) array[22]).booleanValue();
			if (array[23] != null) clave = (String) array[23]; else clave = null;
			if (array[24] != null) doi = (String) array[24]; else doi = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
//			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			if (autores.isEmpty()) autores = null;
			if (editores.isEmpty()) editores = null;
			Conference conf1 = new Conference(idDoc,referencia,doi,title,year,month,URL,abstracts,note,claves,user,proyecto,autores,booktitle,crossref,editores,volume,number,series,pages,address,organization,publisher);
			vector.add(conf1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != conf1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[18] != null) proyecto = (String) array[18]; else proyecto = null;
				id_aut = ((Long) array[19]).intValue();			
				if (array[20] != null) n_aut = (String) array[20]; else n_aut = null;
				if (array[21] != null) ap_aut = (String) array[21]; else ap_aut = null;
				escrito_edit = ((Boolean) array[22]).booleanValue();
				if (array[23] != null) clave = (String) array[23]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut);				
				if (escrito_edit == true) conf1.addAutor(autor1);
				else conf1.addEditor(autor1);


//				if (proyecto != null) conf1.addProyect(proyecto);
				if (clave != null) conf1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != conf1.getIdDoc()) cambio_pub = true;
					else cambio_pub = false;
				}					
			}							
		}
		return vector;
	}
	
	/**
	 * Añade un autor al documento.
	 * @param e Autor a añadir.
	 */
	public void addAutor(AutorEditor e){
		if (author == null)
			author = new LinkedList<AutorEditor>();
		if (!author.contains(e)) author.add(e);
	}

	/**
	 * Añade un editor al documento.
	 * @param e Editor a añadir
	 */
	public void addEditor(AutorEditor e){
		if (editor == null)
			editor = new LinkedList<AutorEditor>();
		if (!editor.contains(e)) editor.add(e);
	}
	
	/**
	 * Constructor de la clase dados sus atributos.
	 * @param idDoc Identificador del documento.
	 * @param referencia Referencia del documento.
	 * @param doi DOI del documento.
	 * @param title Título del documento.
	 * @param year Año del documento.
	 * @param month Mes del documento.
	 * @param url Dirección URL del documento.
	 * @param _abstract Abstract del documento.
	 * @param note Nota del documento.
	 * @param key Conjunto de keywords del documento.
	 * @param user Usuario que ha subido el documento.
	 * @param proyecto Proyectos a los que pertenece el documento.
	 * @param author Autores del documento.
	 * @param booktitle Booktitle para el documento.
	 * @param crossref Crossref del documento.
	 * @param editor Editores del documento.
	 * @param volume Volumen del documento.
	 * @param number Number del documento.
	 * @param series Serie a la que pertenece el documento.
	 * @param pages Longitud en páginas del documento. 
	 * @param address Dirección que le corresponde al documento.
	 * @param organization Organization del documento.
	 * @param publisher Publisher del documento.
	 */	
	public Conference(int idDoc, String referencia, String doi, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			String proyecto,LinkedList<AutorEditor> author, String booktitle,
			String crossref, LinkedList<AutorEditor> editor, String volume,
			String number, String series, String pages, String address,
			String organization, String publisher) {
		this.author = author;
		this.booktitle = booktitle;
		this.crossref = crossref;
		this.editor = editor;
		this.volume = volume;
		this.number = number;
		this.series = series;
		this.pages = pages;
		this.address = address;
		this.organization = organization;
		this.publisher = publisher;
		super.SetAll(idDoc, referencia, doi, title, year, month, url, _abstract, note, key,
				user, proyecto);
	}	
	
	@Override
	public String obligatoriosRellenos()
	{
		if (title == null)
			return "Error: 'title' es un campo obligatorio.";
		else if (booktitle == null)
			return "Error: 'booktitle' es un campo obligatorio.";
		else if (year == null)
			return "Error: 'year' es un campo obligatorio.";
		else if (author == null)
			return "Error: Debe especificar al menos un autor.";
		else if (URL == null)
			return "Error: 'URL' es un campo obligatorio.";
		else 
			return null;
	}
}
