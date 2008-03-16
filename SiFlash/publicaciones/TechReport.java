//Source file: C:\\GENERADO\\publicaciones\\TechReport.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

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
		referencia = null;
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
		else if (nombreCampo.equals("referencia") && referencia == null)
			referencia = valorString;
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

	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "TechReport");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);
		if (title != null)
			elemento.setAttribute("title", title);
		if (author != null)
			imprimirAutoresEditores();
		if (institution != null)
			elemento.setAttribute("institution", institution);
		if (year != null)
			elemento.setAttribute("year", year);
		if (type != null)
			elemento.setAttribute("type", type);
		if (address != null)
			elemento.setAttribute("address", address);
		if (number != null)
			elemento.setAttribute("number", number);
		if (month != null)
			elemento.setAttribute("month", month);
		if (note != null)
			elemento.setAttribute("note", note);
		if (_abstract != null)
			elemento.setAttribute("abstract", _abstract);
		if (key != null)
			elemento.setAttribute("key", key);
		return elemento;
	}

	private void imprimirAutoresEditores() 
	{	
		System.out.println("   - Author:");
		Iterator<AutorEditor> it = author.iterator();
		while (it.hasNext())
			it.next().imprimir();
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
}
