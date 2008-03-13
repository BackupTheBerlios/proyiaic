//Source file: C:\\GENERADO\\publicaciones\\BookLet.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoPublicacion;
import parserFicherosBibtex.CampoString;
import personas.AutorEditor;


/**
 * Una obra que está impresa y encuadernada (bound), pero sin una editorial o 
 * institución patrocinadora (sponsoring).
 */
public class Booklet extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
	
   /**
    * Forma en la que ha sido publicado.
    */
   private String howPublished;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * @param strings 
 * @roseuid 47C8A71101F4
    */
   public Booklet(LinkedList<CampoPublicacion> campos, LinkedList<CampoString> strings)
	{
		inicializarCampos();
		CampoPublicacion campo;
		Iterator<CampoPublicacion> it = campos.iterator();
		while (it.hasNext())
		{
			campo = it.next();
			String nombreCampo = campo.getNombre();
			String valor = campo.getValor();
			insertar(nombreCampo, valor, strings);
		}
	}
	
	private void inicializarCampos() 
	{
		title = null;
		author = null;
		howPublished = null;
		address = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		year = null;
	}
	
	private void insertar(String nombreCampo, String valorString, LinkedList<CampoString> strings)
	{
		valorString = sustituirStrings(strings, valorString);
		if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("howpublished") && howPublished == null)
			howPublished = valorString;
		else if (nombreCampo.equals("address") && address == null)
			address = valorString;
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
		System.out.println("- Tipo de documento: Booklet");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + author);
		if (howPublished != null)
			System.out.println("   - Howpublished: " + howPublished);
		if (address != null)
			System.out.println("   - Address: " + address);
		if (month != null)
			System.out.println("   - Month: " + month);
		if (year != null)
			System.out.println("   - Year: " + year);
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

	public String getHowPublished() {
		return howPublished;
	}

	public String getAddress() {
		return address;
	}
}
