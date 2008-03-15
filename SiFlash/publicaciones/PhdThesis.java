//Source file: C:\\GENERADO\\publicaciones\\PhdThesis.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;


/**
 * Una tesis de doctorado.
 */
public class PhdThesis extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
   
   /**
    * Escuela en la que ser ralizó la investigación.
    */
   private String school;
   
   /**
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * @roseuid 47C8A7120232
    */
   public PhdThesis(LinkedList<CampoPublicacion> campos)
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
		school = null;
		type = null;
		address = null;
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
		else if (nombreCampo.equals("school") && school == null)
			school = valorString;
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
	}

	public void imprimir()
	{
		System.out.println("- Tipo de documento: Phdthesis");
		if (referencia != null)
			System.out.println("   - Referencia: " + referencia);
		if (title != null)
			System.out.println("   - Title: " + title);
		if (author != null)
			imprimirAutoresEditores();
		if (school != null)
			System.out.println("   - School: " + school);
		if (year != null)
			System.out.println("   - Year: " + year);
		if (type != null)
			System.out.println("   - Type: " + type);
		if (address != null)
			System.out.println("   - Address: " + address);
		if (month != null)
			System.out.println("   - Month: " + month);
		if (note != null)
			System.out.println("   - Note: " + note);
		if (_abstract != null)
			System.out.println("   - Abstract: " + _abstract);
		if (key != null)
			System.out.println("   - Key: " + key);
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

	public String getSchool() {
		return school;
	}

	public String getType() {
		return type;
	}

	public String getAddress() {
		return address;
	}
}
