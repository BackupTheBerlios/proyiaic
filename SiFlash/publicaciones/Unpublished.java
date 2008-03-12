//Source file: C:\\GENERADO\\publicaciones\\Unpublished.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import parserFicherosBibtex.CampoPublicacion;
import personas.Autor;


/**
 * Un documento que tiene un autor y t�tulo, pero que no fue formalmente publicado.
 */
public class Unpublished extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private Vector<Autor> author;
   
   /**
    * @roseuid 47C8A71203B9
    */
   public Unpublished(LinkedList<CampoPublicacion> campos)
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
		title = null;
		author = null;
		note = null;
		month = null;
		_abstract = null;
		key = null;
		year = null;
	}

	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutores(valorString);
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("note") && note == null)
			note = valorString;
		else if (nombreCampo.equals("month") && month == null)
			month = valorString;
		else if (nombreCampo.equals("abstract") && _abstract == null)
			_abstract = valorString;
		else if (nombreCampo.equals("key") && key == null)
			key = valorString;
		else if (nombreCampo.equals("year") && year == null)
			year = valorString;
	}

	public void imprimir()
	{
		System.out.println("- Tipo de documento: Unpublished");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + author);
		if (note != null)
			System.out.println("   - Note: " + note);
		if (month != null)
			System.out.println("   - Month: " + month);
		if (year != null)
			System.out.println("   - Year: " + year);
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

	@Override
	public void sustituir(String abrev, String texto) 
	{
		title = title.replaceAll(" " + abrev + " ", " " + texto + " ");
		
		Iterator<Autor> it = author.iterator();
		while (it.hasNext())
		{
			Autor a = it.next();
			a.sustituir(abrev, texto);
		}

		note = note.replaceAll(" " + abrev + " ", " " + texto + " ");
		month = month.replaceAll(" " + abrev + " ", " " + texto + " ");
		year = year.replaceAll(" " + abrev + " ", " " + texto + " ");
		_abstract = _abstract.replaceAll(" " + abrev + " ", " " + texto + " ");
		key = key.replaceAll(" " + abrev + " ", " " + texto + " ");
		
		//Tambi�n reemplazamos cuando est� pegado a una coma:
		title = title.replaceAll(" " + abrev + ",", " " + texto + ",");
		note = note.replaceAll(" " + abrev + ",", " " + texto + ",");
		month = month.replaceAll(" " + abrev + ",", " " + texto + ",");
		year = year.replaceAll(" " + abrev + ",", " " + texto + ",");
		_abstract = _abstract.replaceAll(" " + abrev + ",", " " + texto + ",");
		key = key.replaceAll(" " + abrev + ",", " " + texto + ",");
	}
}
