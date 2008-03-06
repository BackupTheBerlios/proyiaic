//Source file: C:\\GENERADO\\publicaciones\\InBook.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import parserFicherosBibtex.Campo;


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
	 * Vector que contiene los autores que han colaborado en la creación de la misma.
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
    * Capítulo en el que está contenido
    */
   private String chapter;
   
   /**
    * Paginas en las que está contenido.
    */
   private int pages;
   
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
   /**
    * Año de publicación.
    */
   private int year;
   
   /**
    * Volumen en el que está contenido.
    */
   private String volume;
   
   /**
    * Numero de volumen.
    */
   private int number;
   
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
    * Mes de publicación.
    */
   private String month;
   
   /**
    * @roseuid 47C8A711031C
    */
   public InBook(LinkedList<Campo> campos)
	{
		inicializarCampos();
		Campo campo;
		Iterator<Campo> it = campos.iterator();
		while (it.hasNext())
		{
			campo = it.next();
			String nombreCampo = campo.getNombre();
			String valorString = campo.getValorString();
			boolean tieneValorInt = valorString == null;
			int valorInt = campo.getValorInt();
			if (tieneValorInt)
				insertarValorInt(nombreCampo, valorInt);
			else
				insertarValorString(nombreCampo, valorString);
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
		pages = -1;
		year = -1;
		number = -1;
	}
	
	private void insertarValorString(String nombreCampo, String valorString)
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
	}

	private void insertarValorInt(String nombreCampo, int valorInt)
	{
		if (nombreCampo.equals("year") && year == -1)
			year = valorInt;
		else if (nombreCampo.equals("number") && number == -1)
			number = valorInt;
		else if (nombreCampo.equals("pages") && pages == -1)
			pages = valorInt;
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
		if (pages != -1)
			System.out.println("   - Pages: " + pages);
		if (publisher != null)
			System.out.println("   - Publisher: " + publisher);
		if (year != -1)
			System.out.println("   - Year: " + year);
		if (volume != null)
			System.out.println("   - Volume: " + volume);
		if (number != -1)
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
}
