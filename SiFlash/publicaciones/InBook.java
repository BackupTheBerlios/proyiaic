//Source file: C:\\GENERADO\\publicaciones\\InBook.java

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
 * Representa una parte de un libro, que puede ser un capítulo (o sección 
 * o lo que fuere) o un rango de páginas.
 * Contiene todos sus posibles campos, así como los métodos necesarios
 * para su correcto manejo.
 */
public class InBook extends Publication 
{
	/**
	 * Contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private LinkedList<AutorEditor> editor;

	/**
	 * Capítulo en el que está contenido
	 */
	private String chapter;

	/**
	 * Paginas en las que está contenido.
	 */
	private String pages;

	/**
	 * Representa a la entidad que publica.
	 */
	private String publisher;

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
	 * Tipo del contenido.
	 */
	private String type;

	/**
	 * Edicion del mismo.
	 */
	private String edition;

	/**
	 * Lugar de publicación.
	 */
	private String address;

	/**
	 * Crea un InBook a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public InBook(LinkedList<Campo> campos)
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
		else if (nombreCampo.equalsIgnoreCase("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equalsIgnoreCase("title") && title == null)
			title = valorString;
		else if (nombreCampo.equalsIgnoreCase("chapter") && chapter == null)
			chapter = valorString;
		else if (nombreCampo.equalsIgnoreCase("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equalsIgnoreCase("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equalsIgnoreCase("series") && series == null)
			series = valorString;
		else if (nombreCampo.equalsIgnoreCase("type") && type == null)
			type = valorString;
		else if (nombreCampo.equalsIgnoreCase("address") && address == null)
			address = valorString;
		else if (nombreCampo.equalsIgnoreCase("edition") && edition == null)
			edition = valorString;
		else if (nombreCampo.equalsIgnoreCase("month") && month == null)
			month = valorString;
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
		elemento.setAttribute ("tipo", "InBook");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", quitarLlaves(referencia, quitarLlaves));

		Element eTitle = new Element("title");
		eTitle.addContent(quitarLlaves(title, quitarLlaves));
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML(true, quitarLlaves);
		elemento.addContent(eAuthor);

		Element eEditor = generarAutoresEditoresXML(false, quitarLlaves);
		elemento.addContent(eEditor);

		Element eChapter = new Element("chapter");
		eChapter.addContent(quitarLlaves(chapter, quitarLlaves));
		elemento.addContent(eChapter);

		Element ePages = new Element("pages");
		ePages.addContent(quitarLlaves(pages, quitarLlaves));
		elemento.addContent(ePages);

		Element ePublisher = new Element("publisher");
		ePublisher.addContent(quitarLlaves(publisher, quitarLlaves));
		elemento.addContent(ePublisher);

		Element eYear = new Element("year");
		eYear.addContent(quitarLlaves(year, quitarLlaves));
		elemento.addContent(eYear);

		Element eVolume = new Element("volume");
		eVolume.addContent(quitarLlaves(volume, quitarLlaves));
		elemento.addContent(eVolume);

		Element eNumber = new Element("number");
		eNumber.addContent(quitarLlaves(number, quitarLlaves));
		elemento.addContent(eNumber);

		Element eSeries = new Element("series");
		eSeries.addContent(quitarLlaves(series, quitarLlaves));
		elemento.addContent(eSeries);

		Element eType = new Element("type");
		eType.addContent(quitarLlaves(type, quitarLlaves));
		elemento.addContent(eType);

		Element eAddress = new Element("address");
		eAddress.addContent(quitarLlaves(address, quitarLlaves));
		elemento.addContent(eAddress);

		Element eEdition = new Element("edition");
		eEdition.addContent(quitarLlaves(edition, quitarLlaves));
		elemento.addContent(eEdition);

		Element eMonth = new Element("month");
		eMonth.addContent(quitarLlaves(month, quitarLlaves));
		elemento.addContent(eMonth);

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
	 * @param b Si es true, es un autor; si es false, un editor.
	 * @param quitarLlaves Indica si se deben quitar las llaves que aparezcan en alguno de los campos de la publicación.
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
		String bibtex = "@inbook{";
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
		if (editor != null)
			bibtex += "\teditor={" + convertirTextoBibtex(editor) + "},\n";
		if (chapter != null)
			bibtex += "\tchapter={" + convertirTextoBibtex(chapter) + "},\n";
		if (pages != null)
			bibtex += "\tpages={" + convertirTextoBibtex(pages) + "},\n";
		if (publisher != null)
			bibtex += "\tpublisher={" + convertirTextoBibtex(publisher) + "},\n";
		if (volume != null)
			bibtex += "\tvolume={" + convertirTextoBibtex(volume) + "},\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "},\n";
		if (series != null)
			bibtex += "\tseries={" + convertirTextoBibtex(series) + "},\n";
		if (type != null)
			bibtex += "\ttype={" + convertirTextoBibtex(type) + "},\n";
		if (edition != null)
			bibtex += "\tedition={" + convertirTextoBibtex(edition) + "},\n";
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "},\n";
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
	 * @return Los editores del documento.
	 */
	public LinkedList<AutorEditor> getEditor() {
		return editor;
	}

	/**
	 * @return Capítulo al que pertenece el documento.
	 */
	public String getChapter() {
		return chapter;
	}

	/**
	 * @return Paginas en las que se incluye el documento.
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * @return Publisher del documento.
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return Volumen del documento.
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return Numero del documento.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return Serie del documento.
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @return Tipo del documento.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return Edicion del documento.
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @return Adress del documento.
	 */
	public String getAddress() {
		return address;
	}

	@Override
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException 
	{
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO inbook VALUES (");
		str1 += Integer.toString(getIdDoc());
		
		if (getTitle()!= null)
		str1 += ",\"" + sustituirComillasSQL(getTitle()) + "\"";
		else str1+= ",null";

		if (getChapter()!=null)
		str1 += ",\"" + sustituirComillasSQL(getChapter()) + "\"";
		else str1+= ",null";
		
		if(getPages()!=null)
		str1 += ",\"" + sustituirComillasSQL(getPages()) + "\"";
		else str1+= ",null";
				
		if(getPublisher()!=null)
		str1 += ",\"" + sustituirComillasSQL(getPublisher()) + "\"";
		else str1+= ",null";
		
		if (getYear()!=null)
		str1 += ",\"" + sustituirComillasSQL(getYear()) + "\"";
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

		if(getType()!=null)
			str1 += ",\"" + sustituirComillasSQL(getType()) + "\"";
		else str1+= ",null";		

		if(getEdition()!=null)
			str1 += ",\"" + sustituirComillasSQL(getEdition()) + "\"";
		else str1+= ",null";			
		
		if(getAddress()!=null)
		str1 += ",\"" + sustituirComillasSQL(getAddress()) + "\"";
		else str1+= ",null";
		
		if(getMonth()!=null)
		str1 += ",\"" + sustituirComillasSQL(getMonth()) + "\"";
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
	 * Genera una conjunto de InBook a partir del resultado obtenido al realizar una consulta 
	 * en la base de datos.
	 * @param v Resultado obtenido por una consulta a la base de datos, cada array de Object 
	 * contenido en  representa una fila del resultado obtenido al consultar la base de datos.
	 * @return Vector de documentos resultante.
	 */		
	public static Vector<InBook> generaPub(Vector<Object[]> v) {
		Vector <InBook> vector = new Vector <InBook>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String title,volume,series,pages,address,year,number;
			String month,type,publisher, note, abstracts, URL,user, referencia,chapter,edition; 
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
			if (array[2] != null) edition = (String) array[2]; else edition = null;
			if (array[3] != null) year = ((String) array[3]); else year = null;
			if (array[4] != null) type = (String) array[4]; else type = null;
			if (array[5] != null) volume = (String) array[5]; else volume = null;
			if (array[6] != null) number = ((String) array[6]); else number = null;
			if (array[7] != null) series = (String) array[7]; else series = null;
			if (array[8] != null) pages = (String) array[8]; else pages = null;
			if (array[9] != null) address = (String) array[9]; else address = null;
			if (array[10] != null) month = (String) array[10]; else month = null;
			if (array[11] != null) chapter = (String) array[11]; else chapter = null;
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
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
//			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			if (autores.isEmpty()) autores = null;
			if (editores.isEmpty()) editores = null;
			InBook inb1 = new InBook(idDoc,referencia,title,year,month,URL,abstracts,note,claves,user,proyecto,autores,edition,type,editores,volume,number,series,pages,address,chapter,publisher);
			vector.add(inb1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != inb1.getIdDoc()) cambio_pub = true;
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
				if (escrito_edit == true) inb1.addAutor(autor1);
				else inb1.addEditor(autor1);


//				if (proyecto != null) inb1.addProyect(proyecto);
				if (clave != null) inb1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != inb1.getIdDoc()) cambio_pub = true;
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
	 * @param e Editor a añadir.
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
	 * @param edition Edicion del documento.
	 * @param type Tipo del documento.
	 * @param editor Editores del documento.
	 * @param volume Volumen del documento.
	 * @param number Number del documento.
	 * @param series Serie a la que pertenece el documento.
	 * @param pages Longitud en páginas del documento.
	 * @param address Dirección que le corresponde al documento.
	 * @param chapter Capítulo del documento.	 
	 * @param publisher Publisher del documento.
	 */	
	public InBook(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			String proyecto,LinkedList<AutorEditor> author, String edition,
			String type, LinkedList<AutorEditor> editor, String volume,
			String number, String series, String pages, String address,
			String chapter, String publisher) {
		this.author = author;
		this.edition = edition;
		this.type = type;
		this.editor = editor;
		this.volume = volume;
		this.number = number;
		this.series = series;
		this.pages = pages;
		this.address = address;
		this.chapter = chapter;
		this.publisher = publisher;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note, key,
				user, proyecto);
	}	
}
