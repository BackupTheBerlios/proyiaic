//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.jdom.Element;

import personas.AutorEditor;
import temporal.UnimplementedException;

/**
 * Clase abstracta que agrupa los dos atributos comunes a todos las "publicaciones" 
 * 
 * 
 * 
 * existentes, su idDoc y su t�tulo.
 * Nos servir� para realizar las operaciones gen�ricas sobre ellas.
 */
public abstract class Publication 
{

	/**
	 * Valor que le corresponde al Id del documento
	 */
	protected int idDoc;

	/**
	 * Referencia del documento BibTeX.
	 */
	protected String referencia;

	/**
	 * T�tulo que le corresponde a la publicaci�n.
	 */
	protected String title;

	/**
	 * A�o de publicaci�n.
	 */
	protected String year;

	/**
	 * Mes de publicaci�n.
	 */
	protected String month;

	/**
	 * URL en el que se puede localizar la publicaci�n.
	 */
	protected String URL = null;

	/**
	 * Resumen de la publicacion.
	 */
	protected String _abstract;

	/**
	 * Comentarios al respecto.
	 */
	protected String note;

	/**
	 * Clave/s de la publicaci�n.
	 */
	protected String key;

	/**
	 * Usuario que ha a�adido la publicaci�n al sistema.
	 */
	protected String user;

	/**
	 * Contiene todos los proyectos a los que pertenece la aplicaci�n.
	 */
	protected Vector<String> proyectos;

	private final String separador = new String (", ");


	/**
	 * @param idDoc
	 * @param referencia
	 * @param title
	 * @param year
	 * @param month
	 * @param url
	 * @param _abstract
	 * @param note
	 * @param key
	 * @param user
	 * @param proyectos
	 * @param thePublicationException
	 * @throws UnimplementedException 
	 */
	protected void SetAll(int idDoc, String referencia, String title, String year,
			String month, String url, String _abstract, String note, Vector<String> keys,
			String user, Vector<String> proyectos) throws UnimplementedException {
		this.idDoc = idDoc;
		this.referencia = referencia;
		this.title = title;
		this.year = year;
		this.month = month;
		URL = url;
		this._abstract = _abstract;
		this.note = note;
		this.key = new String();
		if (keys != null){
			for (int i = 0;i<keys.size();i++){
				if (i>0) key += this.separador;
				key += keys.get(i);
			}
		}
		this.user = user;
		this.proyectos = proyectos;
	}


	protected void SetAll(int idDoc, String referencia, String title, String year,
			String month, String url, String _abstract, String note, String un_key,
			String user, String un_proyecto) {
		this.idDoc = idDoc;
		this.referencia = referencia;
		this.title = title;
		this.year = year;
		this.month = month;
		URL = url;
		this._abstract = _abstract;
		this.note = note;
		this.addKey(un_key);
		this.user = user;
		this.addProyect(un_proyecto);
	}	

	protected void addProyect(String un_proyecto) {
		if (this.proyectos == null){
			this.proyectos = new Vector<String>();
		}
		if (un_proyecto != null) this.proyectos.add(un_proyecto);

	}


	protected void addKey(String un_key) {
		if (this.key == null)this.key = new String();
		if (un_key != null) this.key += un_key;	
	}


	/**
	 * Devuelve el String correspondiente al c�digo HTML de la publicaci�n 
	 * correspondiente.
	 * @return java.lang.String
	 * @roseuid 47C5AA3D006D
	 */
	public abstract String getHTML();

	/**
	 * Devuelve el String correspondiente al c�digo BibTeX de la publicaci�n 
	 * correspondiente.
	 * @return java.lang.String
	 * @roseuid 47C5AA3F0213
	 */
	public abstract String getBibTeX();

	/**
	 * Genera un elemento XML con la informaci�n almacenada.
	 * @return El elemento XML generado.
	 */
	public abstract Element generarElementoXML();

	/**
	 * Extrae los autores/editores de una cadena de caracteres.
	 * @param autoresEditores Cadena que se  quiere separar.
	 * @return Una lista con los autores/editores.
	 */
	protected LinkedList<AutorEditor> extraerAutoresEditores(String autoresEditores)
	{
		LinkedList<AutorEditor> lista = new LinkedList<AutorEditor>();
		if (autoresEditores != null)
		{
			/*if (autoresEditores.charAt(0) == '{')
				lista.add(new AutorEditor(autoresEditores));
			else
			{*/
			String separador = dameSeparador(autoresEditores);
			if (separador != null)
			{
				String[] separados = autoresEditores.split(separador);
				int numSeparados = separados.length;
				LinkedList<AutorEditor> ae = new LinkedList<AutorEditor>();
				for (int i = 0; i < numSeparados; i++)
				{
					ae = extraerAutoresEditores(separados[i]);
					lista.addAll(ae);
				}
			}
			else
			{
				AutorEditor nuevo = new AutorEditor(autoresEditores);
				lista.add(nuevo);
			}
			//}
		return lista;
		}
		else
			return null;
	}

	/**
	 * Retorna un separador de autores/editores, si existe.
	 * @param autoresEditores String que se quiere evaluar.
	 * @return El separador encontrado, o null si no hay ninguno.
	 */
	private String dameSeparador(String autoresEditores) 
	{
		//int posAnd;
		if (autoresEditores.contains(" and "))
			return " and ";
		else if (autoresEditores.contains(" and\n"))
			return " and\n";
		else if (autoresEditores.contains(" and\t"))
			return " and\t";
		else if (autoresEditores.contains(" and\r"))
			return " and\r";
		else if (autoresEditores.contains("\nand "))
			return "\nand ";
		else if (autoresEditores.contains("\nand\n"))
			return "\nand\n";
		else if (autoresEditores.contains("\nand\t"))
			return "\nand\t";
		else if (autoresEditores.contains("\nand\r"))
			return "\nand\r";
		else if (autoresEditores.contains("\tand "))
			return "\tand ";
		else if (autoresEditores.contains("\tand\n"))
			return "\tand\n";
		else if (autoresEditores.contains("\tand\t"))
			return "\tand\t";
		else if (autoresEditores.contains("\tand\r"))
			return "\tand\r";
		else if (autoresEditores.contains("\rand "))
			return "\rand ";
		else if (autoresEditores.contains("\rand\n"))
			return "\rand\n";
		else if (autoresEditores.contains("\rand\t"))
			return "\rand\t";
		else if (autoresEditores.contains("\rand\r"))
			return "\rand\r";
		else 
			return null;

	}

	protected String convertirTextoBibtex(String cadena2)
	{
		String salida = "";
		String cadena = quitarTildes(cadena2);
		int longit = cadena.length();
		char actual;
		boolean mayusculas = false;
		for (int i=0; i < longit; i++)
		{
			actual = cadena.charAt(i);
			if (esMayuscula(actual))
			{
				if (mayusculas)
					salida += actual;
				else
				{
					mayusculas = true;
					salida += "{" + actual;
				}
			}
			else
			{
				if (mayusculas)
				{
					mayusculas = false;
					salida += "}" + actual;
				}
				else
					salida += actual;
			}
		}
		if (mayusculas)
			salida += "}";

		return salida;
	}

	private String quitarTildes(String cadena2) 
	{
		int longit = cadena2.length();
		char actual;
		String sinTildes = "";
		for (int i=0; i < longit; i++)
		{
			actual = cadena2.charAt(i);
			if (actual == '�')
				sinTildes += "\\\'a";
			else if (actual == '�')
				sinTildes += "\\\'e";
			else if (actual == '�')
				sinTildes += "\\\'{\\i}";
			else if (actual == '�')
				sinTildes += "\\\'o";
			else if (actual == '�')
				sinTildes += "\\\'u";
			else
				sinTildes += actual;
		}
		return sinTildes;
	}


	private boolean esMayuscula(char actual) 
	{
		// TODO Auto-generated method stub
		return false;
	}


	protected String convertirTextoBibtex(LinkedList<AutorEditor> autoresEditores)
	{
		Iterator<AutorEditor> it = autoresEditores.iterator();
		String salida = convertirAutorTextoBibtex(it.next());
		while (it.hasNext())
		{
			salida += " and " + convertirAutorTextoBibtex(it.next());
		}
		return salida;
	}

	private String convertirAutorTextoBibtex(AutorEditor ae) 
	{
		if (ae.getNombre() == null)
		{
			String apellidos = quitarTildes(ae.getApellidos());
			if (tieneVariasPalabras(apellidos))
				return ("{" + apellidos + "}");
			else
				return apellidos;
		}
		else
		{
			String nombre = quitarTildes(ae.getNombre());
			if (ae.getApellidos() != null)
			{
				String apellidos = quitarTildes(ae.getApellidos());
				if (tieneVariasPalabras(apellidos))
					return (apellidos + ", " + nombre);
				else
					return (nombre + " " + apellidos);
			}
			else
				return (nombre + " ?");
		}
	}


	private boolean tieneVariasPalabras(String apellidos) 
	{
		return apellidos.contains(" ");
	}


	public String getTitle() {
		return title;
	}

	public String get_abstract() {
		return _abstract;
	}

	public String getNote() {
		return note;
	}

	public Vector<String> getKeys() throws UnimplementedException {
		throw new UnimplementedException();
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getReferencia() {
		return referencia;
	}


	/**
	 * @return the uRL
	 */
	public final String getURL() {
		return URL;
	}


	/**
	 * @param url the uRL to set
	 */
	public final void setURL(String url) {
		URL = url;
	}


	/**
	 * @return the user
	 */
	public final String getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public final void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return the proyectos
	 */
	public final Vector<String> getProyectos() {
		return proyectos;
	}


	/**
	 * @param proyectos the proyectos to set
	 */
	public final void setProyectos(Vector<String> proyectos) {
		this.proyectos = proyectos;
	}


	/**
	 * @return the idDoc
	 */
	public final int getIdDoc() {
		return idDoc;
	}


	/**
	 * @param referencia the referencia to set
	 */
	public final void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param year the year to set
	 */
	public final void setYear(String year) {
		this.year = year;
	}


	/**
	 * @param month the month to set
	 */
	public final void setMonth(String month) {
		this.month = month;
	}


	/**
	 * @param _abstract the _abstract to set
	 */
	public final void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}


	/**
	 * @param note the note to set
	 */
	public final void setNote(String note) {
		this.note = note;
	}


	/**
	 * @param key the key to set
	 * @throws UnimplementedException 
	 */
	public final void setKey(Vector<String> key) throws UnimplementedException {
		throw new UnimplementedException();
		//this.key = key;
	}


	public abstract Vector<String> generaInserciones();	
}
