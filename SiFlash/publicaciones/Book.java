//Source file: C:\\GENERADO\\publicaciones\\Book.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Clase que representa un libro
 */
public class Book extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
	
	/**
	 * LinkedList que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private LinkedList<AutorEditor> editor;
	
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
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
			String valor = campo.getValor();
			insertar(nombreCampo, valor);
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
		year = null;
	}
	
	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
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
		else if (nombreCampo.equals("year") && year == null)
			year = valorString;
	}
	
	public void imprimir()
	{
		System.out.println("- Tipo de documento: Book");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + ((AutorEditor)author.getFirst()).getNombre());
		if (editor != null)
			System.out.println("   - Editor: " + editor);
		if (publisher != null)
			System.out.println("   - Publisher: " + publisher);
		if (year != null)
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

	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	public LinkedList<AutorEditor> getEditor() {
		return editor;
	}

	public String getPublisher() {
		return publisher;
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

		if (publisher != null)
		{
			publisher = publisher.replaceAll(" " + abrev + " ", " " + texto + " ");
			publisher = publisher.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (year != null)
		{
			year = year.replaceAll(" " + abrev + " ", " " + texto + " ");
			year = year.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (volume != null)
		{
			volume = volume.replaceAll(" " + abrev + " ", " " + texto + " ");
			volume = volume.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (series != null)
		{
			series = series.replaceAll(" " + abrev + " ", " " + texto + " ");
			series = series.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (address != null)
		{
			address = address.replaceAll(" " + abrev + " ", " " + texto + " ");
			address = address.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (edition != null)
		{
			edition = edition.replaceAll(" " + abrev + " ", " " + texto + " ");
			edition = edition.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (month != null)
		{
			month = month.replaceAll(" " + abrev + " ", " " + texto + " ");
			month = month.replaceAll(" " + abrev + ",", " " + texto + ",");
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
