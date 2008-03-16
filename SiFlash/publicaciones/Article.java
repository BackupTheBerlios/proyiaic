//Source file: C:\\GENERADO\\publicaciones\\Article.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Clase que representa un artículo publicado en un periódico o revista.
 */
public class Article extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
	
   /**
    * Journal en el que se publicó.
    */
   private String journal;
   
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
		referencia = null;
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
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("referencia") && referencia == null)
			referencia = valorString;
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
	
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Article");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);
		if (title != null)
			elemento.setAttribute("title", title);
		if (author != null)
			generarAutoresEditoresXML();
		if (journal != null)
			elemento.setAttribute("journal", journal);
		if (year != null)
			elemento.setAttribute("year", year);
		if (volume != null)
			elemento.setAttribute("volume", volume);
		if (number != null)
			elemento.setAttribute("number", number);
		if (pages != null)
			elemento.setAttribute("pages", pages);
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

	private void generarAutoresEditoresXML() 
	{	
		System.out.println("   - Author:");
		Iterator<AutorEditor> it = author.iterator();
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

	public String getJournal() {
		return journal;
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
}
