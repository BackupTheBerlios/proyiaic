//Source file: C:\\GENERADO\\publicaciones\\Manual.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Documentación técnica.
 */
public class Manual extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
   
   /**
    * Organización que se encarga de la gestión de la misma.
    */
   private String organization;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Edición del mismo.
    */
   private String edition;
   
   /**
    * @roseuid 47C8A71200FA
    */
   public Manual(LinkedList<CampoPublicacion> campos)
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
		organization = null;
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
		else if (nombreCampo.equals("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("organization") && organization == null)
			organization = valorString;
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
	
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Manual");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);
		if (title != null)
			elemento.setAttribute("title", title);
		if (author != null)
			imprimirAutoresEditores();
		if (organization != null)
			elemento.setAttribute("organization", organization);
		if (address != null)
			elemento.setAttribute("address", address);
		if (edition != null)
			elemento.setAttribute("edition", edition);
		if (month != null)
			elemento.setAttribute("month", month);
		if (year != null)
			elemento.setAttribute("year", year);
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

	public String getOrganization() {
		return organization;
	}

	public String getAddress() {
		return address;
	}

	public String getEdition() {
		return edition;
	}
}
