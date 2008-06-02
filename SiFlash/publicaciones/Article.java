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
	 * Crea un art�culo a partir de una serie de atributos.
	 * @param idDoc Identificador del art�culo.
	 * @param referencia Referencia del art�culo.
	 * @param title T�tulo del art�culo.
	 * @param year A�o del art�culo.
	 * @param month Mes del art�culo.
	 * @param url Direcci�n URL del art�culo.
	 * @param _abstract Abstract del art�culo.
	 * @param note Nota del art�culo.
	 * @param key Claves del art�culo.
	 * @param user Usuario que ha subido el art�culo.
	 * @param proyectos Proyectos a los que pertenece el art�culo.
	 * @param author Autores del art�culo.
	 * @param journal Journal del art�culo.
	 * @param volume Volumen del art�culo.
	 * @param number Number del art�culo.
	 * @param pages Longitud en p�ginas del art�culo.
	 */
	public Article(int idDoc, String referencia, String title,
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
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note,
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
		
	}

	/**
	 * Establece el valor de un atributo(lista) del art�culo.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valor Valor del campo que se quiere insertar.
	 */
	private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("authors") && author == null)
			author = valor;
	}

	@Override
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Article");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);

		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML();
		elemento.addContent(eAuthor);

		Element eJournal = new Element("journal");
		eJournal.addContent(journal);
		elemento.addContent(eJournal);

		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);

		Element eVolume = new Element("volume");
		eVolume.addContent(volume);
		elemento.addContent(eVolume);

		Element eNumber = new Element("number");
		eNumber.addContent(number);
		elemento.addContent(eNumber);

		Element ePages = new Element("pages");
		ePages.addContent(pages);
		elemento.addContent(ePages);

		Element eMonth = new Element("month");
		eMonth.addContent(month);
		elemento.addContent(eMonth);

		Element eNote = new Element("note");
		eNote.addContent(note);
		elemento.addContent(eNote);

		Element eAbstract = new Element("abstract");
		eAbstract.addContent(_abstract);
		elemento.addContent(eAbstract);

		Element eKey = new Element("key");
		eKey.addContent(convertirTextoBibtexKeys(key));
		elemento.addContent(eKey);

		return elemento;
	}

	/**
	 * Genera un elemento XML con todos los autores.
	 * @return El elemento generado.
	 */
	private Element generarAutoresEditoresXML() 
	{	
		Element eAuthor = new Element("authors");
		if (author != null)
		{
			Iterator<AutorEditor> it = author.iterator();
			while (it.hasNext())
				eAuthor.addContent(it.next().generarAuthorXML());
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
	 * @return La lista de autores.
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
	/**
	 * Genera una serie de sentencias SQL que se deber�n ejecutar para
	 * que el art�culo quede correctamente insertado.
	 * @return Vector con todas las sentencias SQL.
	 * @throws BDException Si hay alg�n problema relacionado con la conexi�n a la base de datos.
	 * @throws ExistingElementException Si hay alg�n problema relacionado con la existencia de tuplas en la base de datos.
	 */
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException
	{
		idDoc = 0;
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO article VALUES (");
		str1 += Integer.toString(getIdDoc());

		if (getTitle()!= null)
			str1 += ",'" + getTitle() + "'";
		else str1+= ",null";

		if (getJournal()!=null)
			str1 += ",'" + getJournal() + "'";
		else str1+= ",null";

		if (getYear()!=null)
			str1 += ",'" + getYear() + "'";
		else str1+= ",null";

		if(getVolume()!=null)
			str1 += ",'" + getVolume() + "'";
		else str1+= ",null";

		if(getNumber()!=null)
			str1 += ",'" + getNumber() + "'";
		else str1+= ",null";

		if(getPages()!=null)
			str1 += ",'" + getPages() + "'";
		else str1+= ",null";

		if(getMonth()!=null)
			str1 += ",'" + getMonth() + "'";
		else str1+= ",null";

		if(getNote()!=null)
			str1 += ",'" + getNote() + "'";
		else str1+= ",null";

		if(get_abstract()!=null)
			str1 += ",'" + get_abstract() + "'";
		else str1+= ",null";

		if(getURL()!=null)
			str1 += ",'" + getURL() + "'";
		else str1+= ",null";

		if(getUser()!=null)
			str1 += ",'" + getUser() + "'";
		else str1+= ",null";

		if(getReferencia()!=null)
			str1 += ",'" + getReferencia() + "'";
		else str1+= ",null";

		str1+=");";

		DataBaseControler dbc = new DataBaseControler();
		dbc.ejecutaString("BEGIN;", conn); //Comenzar transacci�n.
		dbc.ejecutaString(str1, conn);
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
	 * @param v Resultado obtenido por una consulta a la base de datos.
	 * @return Vector de art�culos resultante.
	 */
	public static Vector<Article> generaPub(Vector<Object[]> v){
		//	"SELECT DISTINCT ART1.idDoc, ART1.title, ART1.journal, ART1.year, ART1.volume, ART1.number, ART1.pages, ART1.address, ART1.month, ART1.publisher, ART1.note, ART1.abstract, ART1.URL, ART1.user, ART1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM articles AS ART1, pertenecea AS PRY1, autoreseditores AS AUT1, escrito_editado_por AS EEX1, tienekey AS KEY1 WHERE PRY1.idDoc = ART1.idDoc AND EEX1.idDoc = ART1.idDoc AND AUT1.idAut = EEX1.idPer AND KEY1.idDoc = ART1.idDoc"
		Vector <Article> vector = new Vector <Article>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String title,journal,volume,pages,number,year;
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
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
			//if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			Article	art1 = new Article(idDoc,referencia,title,year,month,URL,abstracts,note,claves,user,proyecto,autores,journal,volume,number,pages); 
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
		if (!author.contains(e)) author.add(e);
	}
}
