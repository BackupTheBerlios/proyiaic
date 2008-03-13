//Source file: C:\\GENERADO\\publicaciones\\InProceedings.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Un artículo en las actas de sesiones (proceedings) de una conferencia
 */
public class InProceedings extends Publication 
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
    * Paginas en las que está contenido.
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
    * @roseuid 47C8A712006D
    */
   public InProceedings(LinkedList<CampoPublicacion> campos)
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
		booktitle = null;
		crossref = null;
		editor = null;
		volume = null;
		series = null;
		address = null;
		month = null;
		organization = null;
		publisher = null;
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
	
	public void imprimir()
	{
		System.out.println("- Tipo de documento: Inproceedings");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + ((AutorEditor)author.getFirst()).getNombre());
		if (booktitle != null)
			System.out.println("   - Booktitle: " + booktitle);
		if (year != null)
			System.out.println("   - Year: " + year);
		if (crossref != null)
			System.out.println("   - Crossref: " + crossref);
		if (editor != null)
			System.out.println("   - Editor: " + editor);
		if (volume != null)
			System.out.println("   - Volume: " + volume);
		if (number != null)
			System.out.println("   - Number: " + number);
		if (series != null)
			System.out.println("   - Series: " + series);
		if (pages != null)
			System.out.println("   - Pages: " + pages);
		if (address != null)
			System.out.println("   - Address: " + address);
		if (month != null)
			System.out.println("   - Month: " + month);
		if (organization != null)
			System.out.println("   - Organization: " + organization);
		if (publisher != null)
			System.out.println("   - Publisher: " + publisher);
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
	public void sustituir(String abrev, String texto)
	{
		if (title != null)
		{
			title = title.replaceAll(" " + abrev + " ", " " + texto + " ");
			title = title.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (author != null)
		{
			Iterator<AutorEditor> it = author.iterator();
			while (it.hasNext())
			{
				AutorEditor a = it.next();
				a.sustituir(abrev, texto);
			}
		}

		if (editor != null)
		{
			Iterator<AutorEditor> it2 = editor.iterator();
			while (it2.hasNext())
			{
				AutorEditor a = it2.next();
				a.sustituir(abrev, texto);
			}
		}

		if (booktitle != null)
		{
			booktitle = booktitle.replaceAll(" " + abrev + " ", " " + texto + " ");
			booktitle = booktitle.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (year != null)
		{
			year = year.replaceAll(" " + abrev + " ", " " + texto + " ");
			year = year.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (crossref != null)
		{
			crossref = crossref.replaceAll(" " + abrev + " ", " " + texto + " ");
			crossref = crossref.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (volume != null)
		{
			volume = volume.replaceAll(" " + abrev + " ", " " + texto + " ");
			volume = volume.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (number != null)
		{
			number = number.replaceAll(" " + abrev + " ", " " + texto + " ");
			number = number.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (series != null)
		{
			series = series.replaceAll(" " + abrev + " ", " " + texto + " ");
			series = series.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (pages != null)
		{
			pages = pages.replaceAll(" " + abrev + " ", " " + texto + " ");
			pages = pages.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (address != null)
		{
			address = address.replaceAll(" " + abrev + " ", " " + texto + " ");
			address = address.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (month != null)
		{
			month = month.replaceAll(" " + abrev + " ", " " + texto + " ");
			month = month.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (organization != null)
		{
			organization = organization.replaceAll(" " + abrev + " ", " " + texto + " ");
			organization = organization.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (publisher != null)
		{
			publisher = publisher.replaceAll(" " + abrev + " ", " " + texto + " ");
			publisher = publisher.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (note != null)
		{
			note = note.replaceAll(" " + abrev + " ", " " + texto + " ");
			note = note.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (_abstract != null)
		{
			_abstract = _abstract.replaceAll(" " + abrev + " ", " " + texto + " ");
			_abstract = _abstract.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (key != null)
		{
			key = key.replaceAll(" " + abrev + " ", " " + texto + " ");
			key = key.replaceAll(" " + abrev + ",", " " + texto + ",");
		}
	}
}
