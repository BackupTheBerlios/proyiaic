//Source file: C:\\GENERADO\\publicaciones\\Book.java

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
 * Clase que representa un libro publicado.
 * Contiene todos sus posibles campos, así como los métodos necesarios
 * para su correcto manejo.
 */
public class Book extends Publication 
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
	 * Representa a la entidad que publica.
	 */
	private String publisher;

	/**
	 * Volumen en el que está contenido.
	 */
	private String volume;

	/**
	 * Número del libro.
	 */
	private String number;

	/**
	 * Serie en la que se encuadra la publicación.
	 */
	private String series;

	/**
	 * Lugar de publicación.
	 */
	private String address;

	/**
	 * Edicion del mismo.
	 */
	private String edition;
	
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
	 * @param editor Editores del documento.
	 * @param publisher Publisher del documento.
	 * @param volume Volumen del documento.
	 * @param number Number del documento.
	 * @param series Serie a la que pertenece el documento.
	 * @param address Dirección que le corresponde al documento.
	 * @param edition Edicion del documento.
	 */
	public Book(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user, String proyecto,
			LinkedList<AutorEditor> author, LinkedList<AutorEditor> editor,
			String publisher, String volume, String number, String series,
			String address, String edition)  {		
		this.author = author;
		this.editor = editor;
		this.publisher = publisher;
		this.volume = volume;
		this.number = number;
		this.series = series;
		this.address = address;
		this.edition = edition;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note,
				key, user, proyecto);
	}

	/**
	 * Crea un Book a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Book(LinkedList<Campo> campos)
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
		else if (nombreCampo.equalsIgnoreCase("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equalsIgnoreCase("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equalsIgnoreCase("number") && number == null)
			number = valorString;
		else if (nombreCampo.equalsIgnoreCase("series") && series == null)
			series = valorString;
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
		elemento.setAttribute ("tipo", "Book");
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
		eVolume.addContent(quitarLlaves(number, quitarLlaves));
		elemento.addContent(eNumber);

		Element eSeries = new Element("series");
		eSeries.addContent(quitarLlaves(series, quitarLlaves));
		elemento.addContent(eSeries);

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
	 * @param b Si es true, se trata de un autor; si es false, se trata de un editor.
	 * @param quitarLlaves Indica si se quieren quitar las llaves que aparezcan en alguno de los campos de la publicación.
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
		String bibtex = "@book{";
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
		if (publisher != null)
			bibtex += "\tpublisher={" + convertirTextoBibtex(publisher) + "},\n";
		if (volume != null)
			bibtex += "\tvolume={" + convertirTextoBibtex(volume) + "},\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "},\n";
		if (series != null)
			bibtex += "\tseries={" + convertirTextoBibtex(series) + "},\n";
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "},\n";
		if (edition != null)
			bibtex += "\tedition={" + convertirTextoBibtex(edition) + "},\n";
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
	 * @return La lista de autores del documento.
	 */
	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	/**
	 * @return El editor del documento.
	 */
	public LinkedList<AutorEditor> getEditor() {
		return editor;
	}

	/**
	 * @return El publisher del documento.
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return El volumen del documento.
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return El numero del documento.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return La serie del documento.
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @return El address del documento.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return La edicion del documento.
	 */
	public String getEdition() {
		return edition;
	}

	@Override
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException 
	{
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO book VALUES (");
		str1 += Integer.toString(getIdDoc());

		if (getTitle()!= null)
			str1 += ",\"" + sustituirComillasSQL(getTitle()) + "\"";
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

		if(getAddress()!=null)
			str1 += ",\"" + sustituirComillasSQL(getAddress()) + "\"";
		else str1+= ",null";

		if(getEdition()!=null)
			str1 += ",\"" + sustituirComillasSQL(getEdition())+ "\"";
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
				if (idAutor == 0) //Hay que insertarlo (o eso ha especificado el usuario)
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
	 * Genera una conjunto de Book a partir del resultado obtenido al realizar una consulta 
	 * en la base de datos.
	 * @param v Resultado obtenido por una consulta a la base de datos, cada array de Object 
	 * contenido en  representa una fila del resultado obtenido al consultar la base de datos.
	 * @return Vector de documentos resultante.
	 */
	public static Vector<Book> generaPub(Vector<Object[]> v) {		
		Vector <Book> vector = new Vector <Book>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String title,publisher,volume,number,year,series, address, edition;
			String month, note, abstracts, URL,user, referencia; 
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
			if (array[2] != null) publisher = (String) array[2]; else publisher = null;
			if (array[3] != null) year = ((String) array[3]); else year = null;			
			if (array[4] != null) volume = (String) array[4]; else volume = null;
			if (array[5] != null) number = ((String) array[5]); else number = null;
			if (array[6] != null) series = (String) array[6]; else series = null;
			if (array[7] != null) address = (String) array[7]; else address = null;
			if (array[8] != null) edition = (String) array[8]; else edition = null;
			if (array[9] != null) month = (String) array[9]; else month = null;				
			if (array[10] != null) note = (String) array[10]; else note = null;
			if (array[11] != null) abstracts = (String) array[11]; else abstracts = null;
			if (array[12] != null) URL = (String) array[12]; else URL = null;
			if (array[13] != null) user = (String) array[13]; else user = null;
			if (array[14] != null) referencia = (String) array[14]; else referencia = null;
			if (array[15] != null) proyecto = (String) array[15]; else proyecto = null;
			id_aut = ((Long) array[16]).intValue();			
			if (array[17] != null) n_aut = (String) array[17]; else n_aut = null;
			if (array[18] != null) ap_aut = (String) array[18]; else ap_aut = null;
			escrito_edit = ((Boolean) array[19]).booleanValue();
			if (array[20] != null) clave = (String) array[20]; else clave = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
//			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			if (autores.isEmpty()) autores = null;
			if (editores.isEmpty()) editores = null;
			Book book1 = new Book(idDoc,referencia,title,year,month,URL,abstracts,note,claves,user,proyecto,autores,editores,publisher,volume,number,series,address,edition);
			vector.add(book1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != book1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[15] != null) proyecto = (String) array[15]; else proyecto = null;
				id_aut = ((Long) array[16]).intValue();			
				if (array[17] != null) n_aut = (String) array[17]; else n_aut = null;
				if (array[18] != null) ap_aut = (String) array[18]; else ap_aut = null;
				escrito_edit = ((Boolean) array[19]).booleanValue();
				if (array[20] != null) clave = (String) array[21]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut);				
				if (escrito_edit == true) book1.addAutor(autor1);
				else book1.addEditor(autor1);

//				if (proyecto != null) book1.addProyect(proyecto);
				if (clave != null) book1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != book1.getIdDoc()) cambio_pub = true;
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
}
