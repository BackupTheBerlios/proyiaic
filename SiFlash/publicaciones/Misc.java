//Source file: C:\\GENERADO\\publicaciones\\Misc.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Para uso cuando los dem�s tipos no corresponden.
 */
public class Misc extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private LinkedList<AutorEditor> author;
   
   /**
    * Forma en la que ha sido publicado.
    */
   private String howPublished;
   
   /**
    * @roseuid 47C8A71201C5
    */
   public Misc(LinkedList<CampoPublicacion> campos)
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
		title = null;
		author = null;
		howPublished = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		year = null;
	}

	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("howpublished") && howPublished == null)
			howPublished = valorString;
		else if (nombreCampo.equals("month") && month == null)
			month = valorString;
		else if (nombreCampo.equals("note") && note == null)
			note = valorString;
		else if (nombreCampo.equals("abstract") && _abstract == null)
			_abstract = valorString;
		else if (nombreCampo.equals("key") && key == null)
			key = valorString;
		else if (nombreCampo.equals("year") && year == null)
			year = valorString;
	}

	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Misc");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);
		
		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);
		
		Element eAuthor = generarAutoresEditoresXML();
		elemento.addContent(eAuthor);
		
		Element eHowPublished = new Element("howPublished");
		eHowPublished.addContent(howPublished);
		elemento.addContent(eHowPublished);
		
		Element eMonth = new Element("month");
		eMonth.addContent(month);
		elemento.addContent(eMonth);
		
		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);
		
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

	public String getHowPublished() {
		return howPublished;
	}
}
