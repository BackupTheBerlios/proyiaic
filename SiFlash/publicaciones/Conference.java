//Source file: C:\\GENERADO\\publicaciones\\Conference.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;
import temporal.UnimplementedException;


/**
 * Lo mismo que inproceedings, incluido para compatibilidad con el lenguaje de 
 * markup Scribe en:Scribe (markup language).
 */
public class Conference extends Publication 
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
	 * Longitud en páginas.
	 */
	private String pages;

	/**
	 * Lugar de publicación.
	 */
	private String address;

	/**
	 * Organización que se encarga de la gestión de la misma.
	 */
	private String organization;

	/**
	 * Representa a la entidad que publica.
	 */
	private String publisher;

	/**
	 * Crea un Conference a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Conference(LinkedList<CampoPublicacion> campos)
	{
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

	public Conference(Object[] objects) throws UnimplementedException {
		throw new UnimplementedException();
	}

	/**
	 * Inserta el campo.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valorString Valor del campo que se quiere insertar.
	 */
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
		else if (nombreCampo.equals("crossref") && crossref == null)
			crossref = valorString;
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equals("series") && series == null)
			series = valorString;
		else if (nombreCampo.equals("address") && address == null)
			address = valorString;
		else if (nombreCampo.equals("month") && month == null)
			month = valorString;
		else if (nombreCampo.equals("organization") && organization == null)
			organization = valorString;
		else if (nombreCampo.equals("publisher") && publisher == null)
			publisher = valorString;
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

	/**
	 * Genera un elemento XML con la información del objeto.
	 * @return El elemento generado.
	 */
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Conference");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);

		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML(true);
		elemento.addContent(eAuthor);

		Element eBooktitle = new Element("booktitle");
		eBooktitle.addContent(booktitle);
		elemento.addContent(eBooktitle);

		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);

		Element eCrossref = new Element("crossref");
		eCrossref.addContent(crossref);
		elemento.addContent(eCrossref);

		Element eEditor = generarAutoresEditoresXML(false);
		elemento.addContent(eEditor);

		Element eVolume = new Element("volume");
		eVolume.addContent(volume);
		elemento.addContent(eVolume);

		Element eNumber = new Element("number");
		eNumber.addContent(number);
		elemento.addContent(eNumber);

		Element eSeries = new Element("series");
		eSeries.addContent(series);
		elemento.addContent(eSeries);

		Element ePages = new Element("pages");
		ePages.addContent(pages);
		elemento.addContent(ePages);

		Element eAddress = new Element("address");
		eAddress.addContent(address);
		elemento.addContent(eAddress);

		Element eMonth = new Element("month");
		eMonth.addContent(month);
		elemento.addContent(eMonth);

		Element eOrganization = new Element("organization");
		eOrganization.addContent(organization);
		elemento.addContent(eOrganization);

		Element ePublisher = new Element("publisher");
		ePublisher.addContent(publisher);
		elemento.addContent(ePublisher);

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

	/**
	 * Genera un elemento XML con todos los autores/editores.
	 * @return El elemento generado.
	 */
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

	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	public String getBooktitle() {
		return booktitle;
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

	public String getPages() {
		return pages;
	}

	public String getAddress() {
		return address;
	}

	public String getOrganization() {
		return organization;
	}

	public String getPublisher() {
		return publisher;
	}

	@Override
	public Vector<String> generaInserciones() {
		// TODO Auto-generated method stub
		return null;
	}
}
