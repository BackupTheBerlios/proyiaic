//Source file: C:\\GENERADO\\publicaciones\\Article.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import parserFicherosBibtex.CampoPublicacion;
import personas.Autor;


/**
 * Clase que representa un artículo publicado en un periódico o revista.
 */
public class Article extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creación de la misma.
	 */
	private Vector<Autor> author;
	
   /**
    * Journal en el que se publicó.
    */
   private String journal;
   
   /**
    * Año de publicación.
    */
   private String year;
   
   /**
    * Volumen en el que está contenido.
    */
   private String volume;

   /**
    * Número de volumen.
    */
   private String number;
   /**
    * Longitud en páginas.
    */
   private String pages;
   
   /**
    * Mes de publicación.
    */
   private String month;
   
   public Article(LinkedList<CampoPublicacion> campos)
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
		title = null; 
		journal = null;
		volume = null;
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
			author = extraerAutores(valorString);
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("journal") && journal == null)
			journal = valorString;
		else if (nombreCampo.equals("volume") && volume == null)
			volume = valorString;
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
		System.out.println("- Tipo de documento: Article");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + ((Autor)author.firstElement()).getNombre());
		if (journal != null)
			System.out.println("   - Journal: " + journal);
		if (year != null)
			System.out.println("   - Year: " + year);
		if (volume != null)
			System.out.println("   - Volume: " + volume);
		if (number != null)
			System.out.println("   - Number: " + number);
		if (pages != null)
			System.out.println("   - Pages: " + pages);
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

	public Vector<Autor> getAuthor() {
		return author;
	}

	public String getJournal() {
		return journal;
	}

	public String getYear() {
		return year;
	}

	public String getVolume() {
		return volume;
	}

	public String getNumber() {
		return number;
	}

	public String getPages() {
		return pages;
	}

	public String getMonth() {
		return month;
	}
}
