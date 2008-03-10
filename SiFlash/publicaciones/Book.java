//Source file: C:\\GENERADO\\publicaciones\\Book.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import parserFicherosBibtex.CampoPublicacion;
import personas.Autor;


/**
 * Clase que representa un libro
 */
public class Book extends Publication 
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
    * Serie en la que se encuadra la publicacion.
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
    * Mes de publicación.
    */
   private String month;
   
   /**
    * @roseuid 47C8A7110177
    */
   public Book(LinkedList<CampoPublicacion> campos)
	{
		inicializarCampos();
		CampoPublicacion campo;
		Iterator<CampoPublicacion> it = campos.iterator();
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
		publisher = null;
		volume = null;
		series = null;
		address = null;
		edition = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		year = -1;
	}
	
	private void insertarValorString(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutores(valorString);
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerEditores(valorString);
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equals("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equals("series") && series == null)
			series = valorString;
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
	}
	
	public void imprimir()
	{
		System.out.println("- Tipo de documento: Book");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + ((Autor)author.firstElement()).getNombre());
		if (editor != null)
			System.out.println("   - Editor: " + editor);
		if (publisher != null)
			System.out.println("   - Publisher: " + publisher);
		if (year != -1)
			System.out.println("   - Year: " + year);
		if (volume != null)
			System.out.println("   - Volume: " + volume);
		if (series != null)
			System.out.println("   - Series: " + series);
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

	public String getPublisher() {
		return publisher;
	}

	public int getYear() {
		return year;
	}

	public String getVolume() {
		return volume;
	}

	public String getSeries() {
		return series;
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
