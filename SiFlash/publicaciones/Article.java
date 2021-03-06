//Source file: C:\\GENERADO\\publicaciones\\Article.java

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
 * Clase que representa un art�culo publicado en una revista.
 * Contiene todos sus posibles campos, as� como los m�todos necesarios
 * para su correcto manejo.
 */
public class Article extends Publication 
{
	/**
	 * Contiene los autores que han colaborado en la creaci�n del art�culo.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Journal del art�culo.
	 */
	private String journal;

	/**
	 * Volumen del art�culo.
	 */
	private String volume;

	/**
	 * N�mero del art�culo.
	 */
	private String number;

	/**
	 * Longitud en p�ginas del art�culo.
	 */
	private String pages;


	/**
	 * Constructor de la clase dados sus atributos.
	 * @param idDoc Identificador del documento.
	 * @param referencia Referencia del documento.
	 * @param doi DOI del documento.
	 * @param title T�tulo del documento.
	 * @param year A�o del documento.
	 * @param month Mes del documento.
	 * @param url Direcci�n URL del documento.
	 * @param _abstract Abstract del documento.
	 * @param note Nota del documento.
	 * @param key Conjunto de keywords del documento.
	 * @param user Usuario que ha subido el documento.
	 * @param proyectos Proyectos a los que pertenece el documento.
	 * @param author Autores del documento.
	 * @param journal Journal del documento.
	 * @param volume Volumen del documento.
	 * @param number Number del documento.
	 * @param pages Longitud en p�ginas del documento.
	 */
	public Article(int idDoc, String referencia, String doi, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user, String proyectos,
			LinkedList<AutorEditor> author, String journal, String volume,
			String number, String pages)
	{
		this.author = author;
		this.journal = journal;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		super.SetAll(idDoc, referencia, doi, title, year, month, url, _abstract, note,
				key, user, proyecto);	
	}

	/**
	 * Crea un Article a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Article(LinkedList<Campo> campos)
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
	 * Establece el valor de un atributo del art�culo.
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
		else if (nombreCampo.equalsIgnoreCase("journal") && journal == null)
			journal = valorString;
		else if (nombreCampo.equalsIgnoreCase("volume") && volume == null)
			volume = valorString;
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
		else if (nombreCampo.equalsIgnoreCase("doi") && DOI == null)
			DOI = valorString;
	}

	/**
	 * Establece el valor de un atributo(lista) del art�culo.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valor Lista con los valores del campo que se quiere insertar.
	 */
	private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("authors") && author == null)
			author = valor;
	}

	@Override
	public Element generarElementoXML(boolean quitarLlaves)
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Article");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", quitarLlaves(referencia, quitarLlaves));
		if (DOI != null)
			elemento.setAttribute("DOI", quitarLlaves(DOI, quitarLlaves));

		Element eTitle = new Element("title");
		eTitle.addContent(quitarLlaves(title, quitarLlaves));
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML(quitarLlaves);
		elemento.addContent(eAuthor);

		Element eJournal = new Element("journal");
		eJournal.addContent(quitarLlaves(journal, quitarLlaves));
		elemento.addContent(eJournal);

		Element eYear = new Element("year");
		eYear.addContent(quitarLlaves(year, quitarLlaves));
		elemento.addContent(eYear);

		Element eVolume = new Element("volume");
		eVolume.addContent(quitarLlaves(volume, quitarLlaves));
		elemento.addContent(eVolume);

		Element eNumber = new Element("number");
		eNumber.addContent(quitarLlaves(number, quitarLlaves));
		elemento.addContent(eNumber);

		Element ePages = new Element("pages");
		ePages.addContent(quitarLlaves(pages, quitarLlaves));
		elemento.addContent(ePages);

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
	 * Genera un elemento XML con todos los autores.
	 * @param quitarLlaves Indica si se quieren quitar las llaves que aparezcan en el nombre y/o apellidos.
	 * @return El elemento generado.
	 */
	private Element generarAutoresEditoresXML(boolean quitarLlaves) 
	{	
		Element eAuthor = new Element("authors");
		if (author != null)
		{
			Iterator<AutorEditor> it = author.iterator();
			while (it.hasNext())
				eAuthor.addContent(it.next().generarAuthorXML(quitarLlaves));
		}
		return eAuthor;
	}

	@Override
	public String getBibTeX() 
	{
		String bibtex = "@article{";
		if (referencia != null)
			bibtex += referencia + ",";
		bibtex += "\n";
		if (title != null)
			bibtex += "\ttitle={" + convertirTextoBibtex(title) + "},\n";
		if (year != null)
			bibtex += "\tyear={" + convertirTextoBibtex(year) + "},\n";
		if (month != null)
			bibtex += "\tmonth={" + convertirTextoBibtex(month) + "},\n";
		if (author != null)
			bibtex += "\tauthor={" + convertirTextoBibtex(author) + "},\n";
		if (journal != null)
			bibtex += "\tjournal={" + convertirTextoBibtex(journal) + "},\n";
		if (volume != null)
			bibtex += "\tvolume={" + convertirTextoBibtex(volume) + "},\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "},\n";
		if (pages != null)
			bibtex += "\tpages={" + convertirTextoBibtex(pages) + "},\n";
		if (_abstract != null)
			bibtex += "\tabstract={" + convertirTextoBibtex(_abstract) + "},\n";
		if (note != null)
			bibtex += "\tnote={" + convertirTextoBibtex(note) + "},\n";
		if (key != null)
			bibtex += "\tkey={" + convertirTextoBibtexKeys(key) + "},\n";
		if (bibtex.charAt(bibtex.length()-2) == ',') //Sobra la �ltima coma.
			bibtex = bibtex.substring(0, bibtex.length()-2);
		bibtex += "}";

		return bibtex;
	}

	/**
	 * @return La lista de autores del art�culo.
	 */
	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	/**
	 * @return El Journal del art�culo.
	 */
	public String getJournal() {
		return journal;
	}

	/**
	 * @return El volumen del art�culo.
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @return El n�mero del art�culo.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return Las p�ginas del art�culo.
	 */
	public String getPages() {
		return pages;
	}

	@Override
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException
	{
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO article VALUES (");
		str1 += Integer.toString(getIdDoc());

		if (getTitle()!= null)
			str1 += ",\"" + sustituirComillasSQL(getTitle()) + "\"";
		else str1+= ",null";

		if (getJournal()!=null)
			str1 += ",\"" + sustituirComillasSQL(getJournal()) + "\"";
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

		if(getPages()!=null)
			str1 += ",\"" + sustituirComillasSQL(getPages()) + "\"";
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
				String str;
				int idAutor = author.get(i).getId();
				if (idAutor == 0) //Hay que insertarlo (o as� lo ha especificado el usuario)
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

		vector.addAll(super.generaInserciones(conn));

		return vector; 
	}	

	/**
	 * Genera una serie de art�culos a partir del resultado obtenido al realizar una consulta 
	 * en la base de datos.
	 * @param v Resultado obtenido por una consulta a la base de datos, cada array de object representa una fila
	 * del resultado obtenido al consultar la base de datos.
	 * @return Vector de art�culos resultante.
	 */
	public static Vector<Article> generaPub(Vector<Object[]> v)
	{
		Vector <Article> vector = new Vector <Article>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String doi, title,journal,volume,pages,number,year;
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
			if (array[2] != null) journal = (String) array[2]; else journal = null;
			if (array[3] != null) year = ((String) array[3]); else year = null;			
			if (array[4] != null) volume = (String) array[4]; else volume = null;
			if (array[5] != null) number = ((String) array[5]); else number = null;			
			if (array[6] != null) pages = ((String) array[6]); else pages = null;
			if (array[7] != null) month = (String) array[7]; else month = null;				
			if (array[8] != null) note = (String) array[8]; else note = null;
			if (array[9] != null) abstracts = (String) array[9]; else abstracts = null;
			if (array[10] != null) URL = (String) array[10]; else URL = null;
			if (array[11] != null) user = (String) array[11]; else user = null;
			if (array[12] != null) referencia = (String) array[12]; else referencia = null;
			if (array[13] != null) proyecto = (String) array[13]; else proyecto = null;
			id_aut = ((Long) array[14]).intValue();
			if (array[15] != null) n_aut = (String) array[15]; else n_aut = null;
			if (array[16] != null) ap_aut = (String) array[16]; else ap_aut = null;
			escrito_edit = ((Boolean) array[17]).booleanValue();
			if (array[18] != null) clave = (String) array[18]; else clave = null;
			if (array[19] != null) doi = (String) array[19]; else doi = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
			//if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			if (autores.isEmpty()) autores = null;
			if (editores.isEmpty()) editores = null;
			Article	art1 = new Article(idDoc,referencia, doi, title,year,month,URL,abstracts,note,claves,user,proyecto,autores,journal,volume,number,pages); 
			vector.add(art1);		


			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != art1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[13] != null) proyecto = (String) array[13]; else proyecto = null;
				id_aut = ((Long) array[14]).intValue();			
				if (array[15] != null) n_aut = (String) array[15]; else n_aut = null;
				if (array[16] != null) ap_aut = (String) array[16]; else ap_aut = null;
				escrito_edit = ((Boolean) array[17]).booleanValue();
				if (array[18] != null) clave = (String) array[18]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut);				
				if (escrito_edit == true) art1.addAutor(autor1);


//				if (proyecto != null) art1.addProyect(proyecto);
				if (clave != null) art1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != art1.getIdDoc()) cambio_pub = true;
					else cambio_pub = false;
				}					
			}							
		}
		return vector;
	}

	/**
	 * A�ade un autor nuevo al art�culo.
	 * @param e Autor nuevo a a�adir.
	 */
	public void addAutor(AutorEditor e){
		if (author == null)
			author = new LinkedList<AutorEditor>();
		if (!author.contains(e)) author.add(e);
	}
	
	@Override
	public String obligatoriosRellenos()
	{
		if (title == null)
			return "Error: 'title' es un campo obligatorio.";
		else if (journal == null)
			return "Error: 'journal' es un campo obligatorio.";
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
