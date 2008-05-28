//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import org.jdom.Element;

import personas.AutorEditor;
import controlador.DataBaseControler;
import controlador.exceptions.ExistingElementException;
import database.BDException;

/**
 * Agrupa los atributos comunes a todos los tipos de publicación. 
 * Usada para realizar las operaciones genéricas sobre ellas.
 */
public abstract class Publication 
{

	/**
	 * Identificador de la publicación en la base de datos (unívoco).
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
	 * Comentarios de la publicación.
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
//	protected Vector<String> proyectos;
	
	/**
	 * Proyecto al que está asociado la publicación.
	 */
	protected String proyecto;

	/**
	 * Separador de claves.
	 */
	public static final String separador = new String (", ");


	/**
	 * @param idDoc Identificador de la publicación.
	 * @param referencia Referencia de la publicación.
	 * @param title Título de la publicación.
	 * @param year Año de la publicación.
	 * @param month Mes de la publicación.
	 * @param url Dirección URL de la publicación.
	 * @param _abstract Resumen de la publicación.
	 * @param note Notas de la publicación.
	 * @param keys Claves de la publicación.
	 * @param user Usuario que ha subido la publicación al sistema.
	 * @param proyectos Proyectos a los que pertenece la publicación.
	 */
	protected void SetAll(int idDoc, String referencia, String title, String year,
			String month, String url, String _abstract, String note, Vector<String> keys,
			String user, String proyecto) {
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
		this.proyecto = proyecto;
	}


	/*protected void SetAll(int idDoc, String referencia, String title, String year,
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
	}	*/

	/**
	 * Añade un proyecto nuevo al que pertenece la publicación.
	 * @param un_proyecto Proyecto a añadir.
	 */
//	protected void addProyect(String un_proyecto) {
//		if (this.proyectos == null){
//			this.proyectos = new Vector<String>();
//		}
//		if (un_proyecto != null && !proyectos.contains(un_proyecto)) this.proyectos.add(un_proyecto);
//
//	}

	/**
	 * Añade una clave nueva a la publicación.
	 * @param un_key La clave nueva a insertar.
	 */
	protected void addKey(String un_key) {
		if (this.key == null)
			key = new LinkedList<String>();			
		if (un_key != null && !key.contains(un_key)) 			
			key.add(un_key);	
	}

	/**
	 * Genera el código BibTeX asociado al artículo.
	 * @return El código BibTeX generado.
	 */
	public abstract String getBibTeX();

	/**
	 * Genera un elemento XML con la información del objeto.
	 * @return El elemento generado.
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
	 * Separa las claves, para guardarlas como una lista.
	 * @param keys Claves a separar.
	 * @return Lista de claves ya separadas.
	 */
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

	/**
	 * Convierte una cadena de caracteres a código BibTeX, insertando llaves y sustituyendo tildes.
	 * @param cadena2 Cadena de caracteres a procesar.
	 * @return El código BibTeX generado.
	 */
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

	/**
	 * Sustituye las tildes existentes en una cadena
	 * por sus correspondientes "sustitutos" en código LaTeX.
	 * @param cadena2 Cadena a procesar.
	 * @return El resultado, tras sustituir las tildes.
	 */
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
//		char 'A' = 65.
//		char 'Z' = 90.
//		return ((actual > 64) && (actual <91)); 
		
		//Mejor no usar todavía...
		return false;
	}


	/**
	 * Convierte una lista de autores/editores en código LaTeX.
	 * @param autoresEditores Lista de autores/editores a procesar.
	 * @return El código LaTeX resultante.
	 */
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
	
	/**
	 * Convierte una lista de claves en código LaTeX.
	 * @param keys Lista de claves a procesar.
	 * @return El código LaTeX resultante.
	 */
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

	/**
	 * Convierte un autor/editor en código LaTeX.
	 * @param ae Autor/editor a procesar.
	 * @return El código LaTeX resultante.
	 */
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

	/**
	 * Indica si una cadena está formada por varias palabras.
	 * @param apellidos Cadena a procesar.
	 * @return El resultado de la evaluación.
	 */
	private boolean tieneVariasPalabras(String apellidos) 
	{
		return apellidos.contains(" ");
	}

	/**
	 * @return El título de la publicación.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return El resumen de la publicación.
	 */
	public String get_abstract() {
		return _abstract;
	}

	/**
	 * @return La nota de la publicación.
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @return Las claves de la publicación.
	 */
	public LinkedList<String> getKeys()
	{
		if (key == null){
			key = new LinkedList<String>();
			key.add (new String (" "));
		}
		return key;
	}

	/**
	 * @return El mes de la publicación.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @return El año de la publicación.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return La referencia de la publicación.
	 */
	public String getReferencia() {
		return referencia;
	}


	/**
	 * @return La URL de la publicación.
	 */
	public final String getURL() {
		return URL;
	}

	/**
	 * @param URL de la publicación.
	 */
	public final void setURL(String url) {
		URL = url;
	}


	/**
	 * @return El usuario de la publicación.
	 */
	public final String getUser() {
		return user;
	}


	/**
	 * @param El usuario de la publicación.
	 */
	public final void setUser(String user) {
		this.user = user;
	}


	/**
	 * @return Los proyectos de la publicación.
	 */
//	public final Vector<String> getProyectos() {
//		return proyectos;
//	}


	/**
	 * @param Los proyectos de la publicación.
	 */
//	public final void setProyectos(Vector<String> proyectos) {
//		this.proyectos = proyectos;
//	}


	/**
	 * @return El identificador de la publicación.
	 */
	public final int getIdDoc() {
		return idDoc;
	}


	/**
	 * @param  La referencia de la publicación.
	 */
	public final void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	/**
	 * @param El título de la publicación.
	 */
	public final void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param El año de la publicación.
	 */
	public final void setYear(String year) {
		this.year = year;
	}


	/**
	 * @param El mes de la publicación.
	 */
	public final void setMonth(String month) {
		this.month = month;
	}


	/**
	 * @param El resumen de la publicación.
	 */
	public final void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}


	/**
	 * @param La nota de la publicación.
	 */
	public final void setNote(String note) {
		this.note = note;
	}


	/**
	 * @param Las claves de la publicación.
	 */
	public final void setKey(LinkedList<String> key)
	{
		this.key = key;
	}

	/**
	 * Genera una serie de sentencias SQL que se deberán ejecutar para
	 * que la publicación quede correctamente insertada.
	 * @return Vector con todas las sentencias SQL.
	 * @throws BDException Si hay algún problema relacionado con la conexión a la base de datos.
	 * @throws ExistingElementException Si hay algún problema relacionado con la existencia de tuplas en la base de datos.
	 */
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException
	{
		Vector <String> vector = new Vector<String>();
		
//		if (proyectos != null)
//		{
//			for(int i=0;i<this.proyectos.size();i++){
//				String str = new String ("INSERT INTO pertenecea VALUES(" + getIdDoc());
//				str += ",'" + proyectos.get(i) + "');";
//				vector.add(str);
//			}
//		}
		
		if (proyecto != null)
		{
			String str = new String ("INSERT INTO pertenecea VALUES(" + getIdDoc());
			str += ",'" + proyecto + "');";
			vector.add(str);
		}
		
		if (key != null)
		{
			ListIterator <String> keysit = this.getKeys().listIterator();
			//str1 = new String("INSERT INTO tienekey VALUES (" + getIdDoc() + ",' ');");
			//vector.add(str1);
			DataBaseControler dbc = new DataBaseControler();
			while (keysit.hasNext()){
				String k = keysit.next();
				if (k != null && !k.equals(" ")){
					/*String str2= new String ("INSERT INTO claves  VALUES ('" + k + "') ON DUPLICATE KEY UPDATE clave=clave;");  
					str1 = new String("INSERT INTO tienekey VALUES (" + getIdDoc() + ",'" + k +"');");
					vector.add(str2);
					vector.add(str1);*/
					if (!dbc.consultaExistenciaKey(k, conn))
						dbc.ejecutaString("INSERT INTO claves VALUES('" + k + "');", conn);
					vector.add("INSERT INTO tienekey VALUES (" + getIdDoc() + ",'" + k +"');");
				}			
			}
		}
		/*else {
			str1 = new String("INSERT INTO tienekey VALUES (" + getIdDoc() + ",' ');");
			vector.add(str1);
			key = new LinkedList<String>();
		}*/
		vector.add("COMMIT;");
		
		return vector;
	}
}
