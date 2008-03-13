//Source file: C:\\GENERADO\\publicaciones\\Article.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Clase que representa un art�culo publicado en un peri�dico o revista.
 */
public class Article extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private LinkedList<AutorEditor> author;
	
   /**
    * Journal en el que se public�.
    */
   private String journal;
   
   /**
    * Volumen en el que est� contenido.
    */
   private String volume;

   /**
    * N�mero de volumen.
    */
   private String number;
   
   /**
    * Longitud en p�ginas.
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
			System.out.println("   - Author: " + ((AutorEditor)author.getFirst()).getNombre());
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
		
		if (journal != null)
		{
			journal = journal.replaceAll(" " + abrev + " ", " " + texto + " ");
			journal = journal.replaceAll(" " + abrev + ",", " " + texto + ",");
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
		
		if (number != null)
		{
			number = number.replaceAll(" " + abrev + " ", " " + texto + " ");
			number = number.replaceAll(" " + abrev + ",", " " + texto + ",");
		}
		
		if (pages != null)
		{
			pages = pages.replaceAll(" " + abrev + " ", " " + texto + " ");
			pages = pages.replaceAll(" " + abrev + ",", " " + texto + ",");
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
