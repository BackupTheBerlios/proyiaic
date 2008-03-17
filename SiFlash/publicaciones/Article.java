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
		
		Element eAbstract = new Element("_abstract");
		eAbstract.addContent(_abstract);
		elemento.addContent(eAbstract);
		
		Element eKey = new Element("key");
		eKey.addContent(key);
		elemento.addContent(eKey);
		
		return elemento;
	}

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
