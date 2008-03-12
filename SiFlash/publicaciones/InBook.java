//Source file: C:\\GENERADO\\publicaciones\\InBook.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import parserFicherosBibtex.CampoPublicacion;


/**
 * Una parte de un libro, que puede ser un cap�tulo (o secci�n o lo que fuere) o un 
 * 
 * 
 * 
 * rango de p�ginas.
 */
public class InBook extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private Vector author;
	 
	/**
	 * Vector que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private Vector editor;
	
   /**
    * Libro al que hace referencia.
    */
   private String booktitle;
   
   /**
    * Cap�tulo en el que est� contenido
    */
   private String chapter;
   
   /**
    * Paginas en las que est� contenido.
    */
   private String pages;
   
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
   /**
    * Volumen en el que est� contenido.
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
    * Lugar de publicaci�n.
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
			author = extraerAutores(valorString);
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerEditores(valorString);
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
	
	public void imprimir()
	{
		System.out.println("- Tipo de documento: Inbook");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + author);
		if (editor != null)
			System.out.println("   - Editor: " + editor);
		if (chapter != null)
			System.out.println("   - Chapter: " + chapter);
		if (pages != null)
			System.out.println("   - Pages: " + pages);
		if (publisher != null)
			System.out.println("   - Publisher: " + publisher);
		if (year != null)
			System.out.println("   - Year: " + year);
		if (volume != null)
			System.out.println("   - Volume: " + volume);
		if (number != null)
			System.out.println("   - Number: " + number);
		if (series != null)
			System.out.println("   - Series: " + series);
		if (type != null)
			System.out.println("   - Type: " + type);
		if (address != null)
			System.out.println("   - Address: " + address);
		if (edition != null)
			System.out.println("   - Edition: " + edition);
		if (month != null)
			System.out.println("   - Month: " + month);
		if (note != null)
			System.out.println("   - Note: " + note);
		if (_abstract != null)
			System.out.println("   - Abstract: " + _abstract);
		if (key != null)
			System.out.println("   - Key: " + key);
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

	public Vector getAuthor() {
		return author;
	}

	public Vector getEditor() {
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

	@Override
	public void sustituir(String abrev, String texto) {
		// TODO Auto-generated method stub
		
	}
}
