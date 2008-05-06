//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import org.jdom.Element;

import database.BDException;

import personas.AutorEditor;
import temporal.UnimplementedException;

/**
 * Clase abstracta que agrupa los dos atributos comunes a todos las "publicaciones" 
 * 
 * 
 * 
 * existentes, su idDoc y su título.
 * Nos servirá para realizar las operaciones genéricas sobre ellas.
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
	 * Título que le corresponde a la publicación.
	 */
	protected String title;

	/**
	 * Año de publicación.
	 */
	protected String year;

	/**
	 * Mes de publicación.
	 */
	protected String month;

	/**
	 * URL en el que se puede localizar la publicación.
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
	 * Clave/s de la publicación.
	 */
	protected LinkedList<String> key;

	/**
	 * Usuario que ha añadido la publicación al sistema.
	 */
	protected String user;

	/**
	 * Contiene todos los proyectos a los que pertenece la aplicación.
	 */
	protected Vector<String> proyectos;

	public static final String separador = new String (", ");


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
		if (keys != null && keys.size() > 0)
		{
			if (key == null)
				key = new LinkedList<String>();
			for (int i = 0;i<keys.size();i++)
			{
				//if (i>0) key += this.separador;
				key.add(keys.get(i));
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
		if (un_proyecto != null && !proyectos.contains(un_proyecto)) this.proyectos.add(un_proyecto);

	}


	protected void addKey(String un_key) {
		if (this.key == null)
			key = new LinkedList<String>();			
		if (un_key != null && !key.contains(un_key)) 			
			key.add(un_key);	
	}


	/**
	 * Devuelve el String correspondiente al código HTML de la publicación 
	 * correspondiente.
	 * @return java.lang.String
	 * @roseuid 47C5AA3D006D
	 */
	public abstract String getHTML();

	/**
	 * Devuelve el String correspondiente al código BibTeX de la publicación 
	 * correspondiente.
	 * @return java.lang.String
	 * @roseuid 47C5AA3F0213
	 */
	public abstract String getBibTeX();

	/**
	 * Genera un elemento XML con la información almacenada.
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
	
	protected LinkedList<String> separarKeys(String keys)
	{
		if (keys != null)
		{
			LinkedList<String> claves = new LinkedList<String>();
			String[] keysSep = keys.split(separador);
			int numKeys = keysSep.length;
			for (int i = 0; i < numKeys; i++)
				claves.add(keysSep[i]);
			return claves;
		}
		else return null;		
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
			if (actual == 'á')
				sinTildes += "\\\'a";
			else if (actual == 'é')
				sinTildes += "\\\'e";
			else if (actual == 'í')
				sinTildes += "\\\'{\\i}";
			else if (actual == 'ó')
				sinTildes += "\\\'o";
			else if (actual == 'ú')
				sinTildes += "\\\'u";
			else
				sinTildes += actual;
		}
		return sinTildes;
	}


	private boolean esMayuscula(char actual) 
	{
		// char 'A' = 65.
		//char 'Z' = 90.
		//return ((actual > 64) && (actual <91)); 
		
		//Mejor no usar todavía...
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
	
	protected String convertirTextoBibtexKeys(LinkedList<String> keys)
	{
		if (keys != null)
		{
			Iterator<String> it = keys.iterator();
			String salida = "";
			if (it.hasNext())
			{
				salida += it.next();
				while (it.hasNext())
					salida = salida + separador + it.next();
			}
			return salida;
		}
		else return null;
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

	public LinkedList<String> getKeys()
	{
		if (key == null){
			key = new LinkedList<String>();
			key.add (new String (" "));
		}
		return key;
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
	public final void setKey(LinkedList<String> key)
	{
		this.key = key;
	}


	public Vector<String> generaInserciones() throws BDException {
		String str1;
		Vector <String> vector = new Vector<String>();
		
		if (proyectos != null)
		{
			for(int i=0;i<this.proyectos.size();i++){
				String str = new String ("INSERT INTO pertenecea VALUES(" + getIdDoc());
				str += ",'" + proyectos.get(i) + "');";
				vector.add(str);
			}
		}
		if (key != null)
		{
			ListIterator <String> keysit = this.getKeys().listIterator();
			str1 = new String("INSERT INTO tienekey VALUES (" + getIdDoc() + ",' ');");
			vector.add(str1);
			while (keysit.hasNext()){
				String k = keysit.next();
				if (k != null && !k.equals(" ")){
					str1 = new String("INSERT INTO tienekey VALUES (" + getIdDoc() + ",'" + k +"');");
					vector.add(str1);
				}			
			}
		}
		else {
			str1 = new String("INSERT INTO tienekey VALUES (" + getIdDoc() + ",' ');");
			vector.add(str1);
			key = new LinkedList<String>();
		}
		
		return vector;
	}
}
