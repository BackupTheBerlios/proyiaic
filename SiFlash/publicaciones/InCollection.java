//Source file: C:\\GENERADO\\publicaciones\\InCollection.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Una parte de un libro que tiene su propio título.
 */
public class InCollection extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
   
   /**
    * Libro al que hace referencia.
    */
   private String booktitle;
   
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;

   /**
    * Ayuda a generar referecias cruzadas internas.
    */
   private String crossref;
   
   /**
	 * LinkedList que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
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
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Capítulo en el que está contenido
    */
   private String chapter;
   
   /**
    * Paginas en las que está contenido.
    */
   private String pages;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Edicion del mismo.
    */
   private String edition;
   
   /**
    * @roseuid 47C8A71103B9
    */
   public InCollection(LinkedList<CampoPublicacion> campos)
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
		title = null; 
		booktitle = null;
		publisher = null;
		crossref = null;
		editor = null;
		volume = null;
		series = null;
		type = null;
		chapter = null;
		address = null;
		edition = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		year = null;
		number = null;
		pages = null;
	}
	
	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("booktitle") && booktitle == null)
			booktitle = valorString;
		else if (nombreCampo.equals("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equals("crossref") && crossref == null)
			crossref = valorString;
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equals("series") && series == null)
			series = valorString;
		else if (nombreCampo.equals("type") && type == null)
			type = valorString;
		else if (nombreCampo.equals("chapter") && chapter == null)
			chapter = valorString;
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
		elemento.setAttribute ("tipo", "InCollection");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);
		if (title != null)
			elemento.setAttribute("title", title);
		if (author != null)
			imprimirAutoresEditores(true);
		if (booktitle != null)
			elemento.setAttribute("booktitle", booktitle);
		if (publisher != null)
			elemento.setAttribute("publisher", publisher);
		if (year != null)
			elemento.setAttribute("year", year);
		if (crossref != null)
			elemento.setAttribute("crossref", crossref);
		if (editor != null)
			imprimirAutoresEditores(false);
		if (volume != null)
			elemento.setAttribute("volume", volume);
		if (number != null)
			elemento.setAttribute("number", number);
		if (series != null)
			elemento.setAttribute("series", series);
		if (type != null)
			elemento.setAttribute("type", type);
		if (chapter != null)
			elemento.setAttribute("chapter", chapter);
		if (pages != null)
			elemento.setAttribute("pages", pages);
		if (address != null)
			elemento.setAttribute("address", address);
		if (edition != null)
			elemento.setAttribute("edition", edition);
		if (month != null)
			elemento.setAttribute("month", month);
		if (note != null)
			elemento.setAttribute("note", note);
		if (_abstract != null)
			elemento.setAttribute("abstract", _abstract);
		if (key != null)
			elemento.setAttribute("key", key);
		return elemento;
	}
	
	private void imprimirAutoresEditores(boolean b) 
	{
		LinkedList<AutorEditor> lista;
		if (b)
		{
			lista = author;
			System.out.println("   - Author:");
		}
		else
		{
			lista = editor;
			System.out.println("   - Editor:");
		}
		
		Iterator<AutorEditor> it = lista.iterator();
		while (it.hasNext())
			it.next().imprimir();
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

	public String getBooktitle() {
		return booktitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getYear() {
		return year;
	}

	public String getCrossref() {
		return crossref;
	}

	public LinkedList<AutorEditor> getEditor() {
		return editor;
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

	public String getChapter() {
		return chapter;
	}

	public String getPages() {
		return pages;
	}

	public String getAddress() {
		return address;
	}

	public String getEdition() {
		return edition;
	}

	public String getMonth() {
		return month;
	}
}
