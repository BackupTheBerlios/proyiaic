//Source file: C:\\GENERADO\\publicaciones\\InBook.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Una parte de un libro, que puede ser un capítulo (o sección o lo que fuere) o un 
 * 
 * 
 * 
 * rango de páginas.
 */
public class InBook extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
	 
	/**
	 * LinkedList que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private LinkedList<AutorEditor> editor;
	
   /**
    * Libro al que hace referencia.
    */
   private String booktitle;
   
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
    * @roseuid 47C8A711031C
    */
   public InBook(LinkedList<CampoPublicacion> campos)
	{
		inicializarCampos();
		CampoPublicacion campo;
		Iterator<CampoPublicacion> it = campos.iterator();
		while (it.hasNext())
		{
			campo = it.next();
			String nombreCampo = campo.getNombre();
			String valor = campo.getValor();
			insertar(nombreCampo, valor);
		}
	}

	private void inicializarCampos() 
	{
		referencia = null;
		author = null;
		editor = null;
		title = null; 
		chapter = null;
		publisher = null;
		volume = null;
		series = null;
		type = null;
		address = null;
		edition = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		pages = null;
		year = null;
		number = null;
	}
	
	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("chapter") && chapter == null)
			chapter = valorString;
		else if (nombreCampo.equals("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equals("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equals("series") && series == null)
			series = valorString;
		else if (nombreCampo.equals("type") && type == null)
			type = valorString;
		else if (nombreCampo.equals("address") && address == null)
			address = valorString;
		else if (nombreCampo.equals("edition") && edition == null)
			edition = valorString;
		else if (nombreCampo.equals("month") && month == null)
			month = valorString;
		else if (nombreCampo.equals("note") && note == null)
			note = valorString;
		else if (nombreCampo.equals("abstract") && _abstract == null)
			_abstract = valorString;
		else if (nombreCampo.equals("key") && key == null)
			key = valorString;
		else if (nombreCampo.equals("number") && number == null)
			number = valorString;
		else if (nombreCampo.equals("pages") && pages == null)
			pages = valorString;
		else if (nombreCampo.equals("year") && year == null)
			year = valorString;
	}
	
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "InBook");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);
		
		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);
		
		Element eAuthor = generarAutoresEditoresXML(true);
		elemento.addContent(eAuthor);
		
		Element eEditor = generarAutoresEditoresXML(false);
		elemento.addContent(eEditor);
		
		Element eChapter = new Element("chapter");
		eChapter.addContent(chapter);
		elemento.addContent(eChapter);
		
		Element ePages = new Element("pages");
		ePages.addContent(pages);
		elemento.addContent(ePages);
		
		Element ePublisher = new Element("publisher");
		ePublisher.addContent(publisher);
		elemento.addContent(ePublisher);
		
		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);
		
		Element eVolume = new Element("volume");
		eVolume.addContent(volume);
		elemento.addContent(eVolume);
		
		Element eNumber = new Element("number");
		eNumber.addContent(number);
		elemento.addContent(eNumber);
		
		Element eSeries = new Element("series");
		eSeries.addContent(series);
		elemento.addContent(eSeries);
		
		Element eType = new Element("type");
		eType.addContent(type);
		elemento.addContent(eType);
		
		Element eAddress = new Element("address");
		eAddress.addContent(address);
		elemento.addContent(eAddress);
		
		Element eEdition = new Element("edition");
		eEdition.addContent(edition);
		elemento.addContent(eEdition);
		
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
		eKey.addContent(key);
		elemento.addContent(eKey);
		
		return elemento;
	}

	private Element generarAutoresEditoresXML(boolean b) 
	{
		Element eAuthorEditor;
		if (b) //eAuthor
		{
			eAuthorEditor = new Element("authors");
			if (author != null)
			{
				Iterator<AutorEditor> it = author.iterator();
				while (it.hasNext())
					eAuthorEditor.addContent(it.next().generarAuthorXML());
			}
		}
		else //eEditor
		{
			eAuthorEditor = new Element("editors");
			if (editor != null)
			{
				Iterator<AutorEditor> it = editor.iterator();
				while (it.hasNext())
					eAuthorEditor.addContent(it.next().generarEditorXML());
			}
		}
		return eAuthorEditor;
	}
	
	@Override
	public String getBibTeX() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	public LinkedList<AutorEditor> getEditor() {
		return editor;
	}

	public String getBooktitle() {
		return booktitle;
	}

	public String getChapter() {
		return chapter;
	}

	public String getPages() {
		return pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getVolume() {
		return volume;
	}

	public String getNumber() {
		return number;
	}

	public String getSeries() {
		return series;
	}

	public String getType() {
		return type;
	}

	public String getEdition() {
		return edition;
	}

	public String getAddress() {
		return address;
	}
}
