//Source file: C:\\GENERADO\\publicaciones\\TechReport.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Un reporte publicado por una escuela u otra institución, usualmente numerado 
 * dentro de una serie.
 */
public class TechReport extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
   
   /**
    * Institucion que lo realiza.
    */
   private String institution;
   
   /**
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Numero de volumen.
    */
   private String number;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * @roseuid 47C8A712034B
    */
   public TechReport(LinkedList<CampoPublicacion> campos)
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
		institution = null;
		type = null;
		address = null;
		month = null;
		note = null;
		_abstract = null;
		key = null;
		year = null;
		number = null;
	}

	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equals("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("institution") && institution == null)
			institution = valorString;
		else if (nombreCampo.equals("type") && type == null)
			type = valorString;
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
		else if (nombreCampo.equals("number") && number == null)
			number = valorString;
	}

	public void imprimir()
	{
		System.out.println("- Tipo de documento: Techreport");
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			System.out.println("   - Author: " + author);
		if (institution != null)
			System.out.println("   - Institution: " + institution);
		if (year != null)
			System.out.println("   - Year: " + year);
		if (type != null)
			System.out.println("   - Type: " + type);
		if (address != null)
			System.out.println("   - Address: " + address);
		if (number != null)
			System.out.println("   - Number: " + number);
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

	public String getInstitution() {
		return institution;
	}

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}

	public String getAddress() {
		return address;
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

		if (institution != null)
		{
			institution = institution.replaceAll(" " + abrev + " ", " " + texto + " ");
			institution = institution.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (year != null)
		{
			year = year.replaceAll(" " + abrev + " ", " " + texto + " ");
			year = year.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (type != null)
		{
			type = type.replaceAll(" " + abrev + " ", " " + texto + " ");
			type = type.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (address != null)
		{
			address = address.replaceAll(" " + abrev + " ", " " + texto + " ");
			address = address.replaceAll(" " + abrev + ",", " " + texto + ",");
		}

		if (number != null)
		{
			number = number.replaceAll(" " + abrev + " ", " " + texto + " ");
			number = number.replaceAll(" " + abrev + ",", " " + texto + ",");
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
