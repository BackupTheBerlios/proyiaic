//Source file: C:\\GENERADO\\publicaciones\\Misc.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import parserFicherosBibtex.Campo;


/**
 * Para uso cuando los demás tipos no corresponden.
 */
public class Misc extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creación de la misma.
	 */
	private Vector author;
   
   /**
    * Forma en la que ha sido publicado.
    */
   private String howPublished;
   
   /**
    * Mes de publicación.
    */
   private String month;
   
   /**
    * Año de publicación.
    */
   private int year;
   
   /**
    * @roseuid 47C8A71201C5
    */
   public Misc(LinkedList<Campo> campos)
	{
		inicializarCampos();
		Campo campo;
		Iterator<Campo> it = campos.iterator();
		while (it.hasNext())
		{
			campo = it.next();
			String nombreCampo = campo.getNombre();
			String valorString = campo.getValorString();
			boolean tieneValorInt = valorString == null;
			int valorInt = campo.getValorInt();
			if (tieneValorInt)
				insertarValorInt(nombreCampo, valorInt);
			else
				insertarValorString(nombreCampo, valorString);
		}
	}

	private void inicializarCampos() 
	{
		title = null;
		author = null;
		howPublished = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		year = -1;
	}

	private void insertarValorString(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutores(valorString);
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
	}

	private void insertarValorInt(String nombreCampo, int valorInt)
	{
		if (nombreCampo.equals("year") && year == -1)
			year = valorInt;
	}

	public void imprimir()
	{
		System.out.println("- Tipo de documento: Misc");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + author);
		if (howPublished != null)
			System.out.println("   - Howpublished: " + howPublished);
		if (month != null)
			System.out.println("   - Month: " + month);
		if (year != -1)
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
}
