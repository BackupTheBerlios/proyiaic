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
 * Clase abstracta que agrupa los atributos comunes a todos los 
 * tipos de documentos, igualmente define y/o implementa el 
 * comportamiento que ha de ser común a todos ellos.
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
	 * keyword/s de la publicación.
	 */
	protected LinkedList<String> key;

	/**
	 * Usuario que ha añadido la publicación al sistema.
	 */
	protected String user;

//	/**
//	 * Contiene todos los proyectos a los que pertenece la aplicación.
//	 */
//	protected Vector<String> proyectos;
	
	/**
	 * Proyecto al que está asociado la publicación.
	 */
	protected String proyecto;

//	/**
//	 * Separador de keywords.
//	 */
//	public static final String separador = new String (", ");


	/**
	 * Setter de la publicación el que se fijan todos sus atributos a los que se pasan
	 * por parámetro, equivalente a un constructor dados los parámetros, pero al
	 * ser una clase abstracta es más comodo el multisetter.
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
	 * @param proyecto Proyectos a los que pertenece la publicación.
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
				key.add(keys.get(i));
			}
		}
		this.user = user;
		this.proyecto = proyecto;
	}

	/**
	 * Añade una keywords nueva a la publicación.
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
	 * @param quitarLlaves Indica si se quieren quitar las llaves que aparezcan en los campos de la publicación.
	 * @return El elemento generado.
	 */
	public abstract Element generarElementoXML(boolean quitarLlaves);

	/**
	 * Extrae los autores/editores de una cadena de caracteres que se le pasa por
	 * parámetro.
	 * @param autoresEditores Cadena que se  quiere separar.
	 * @return Una lista con los autores/editores.
	 */
	protected LinkedList<AutorEditor> extraerAutoresEditores(String autoresEditores)
	{
		LinkedList<AutorEditor> lista = new LinkedList<AutorEditor>();
		if (autoresEditores != null)
		{
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
			String[] keysSep = keys.split(",");
			int numKeys = keysSep.length;
			for (int i = 0; i < numKeys; i++)
				claves.add(quitarEspacios(keysSep[i]));
			return claves;
		}
		else return null;		
	}

	/**
	 * Quita los caracteres en blanco que hay al comienzo y al final de una cadena.
	 * @param c Cadena a procesar.
	 * @return El resultado, tras eliminar los caracteres en blanco.
	 */
	private String quitarEspacios(String c) 
	{
		if (c != null)
		{
			int i = 0;
			while (blanco(c.charAt(i)))
				i++;
			int j = c.length()-1;
			while (blanco(c.charAt(j)))
				j--;
			return c.substring(i, j+1);
		}
		else return null;
	}

	/**
	 * Indica si un caracter es un 'blanco' (espacio, salto de línea, tabulador o retorno de carro).
	 * @param actual Caracter a evaluar.
	 * @return El resultado de la evaluación
	 */
	private boolean blanco(char actual) 
	{
		return ((actual == ' ') || (actual == '\n') || (actual == '\r') || (actual == '\t')); 
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
		String cadena = quitarTildesYOtros(cadena2);
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
	 * Idem con la letra 'ñ'.
	 * @param cadena2 Cadena a procesar.
	 * @return El resultado, tras sustituir las tildes y la 'ñ'.
	 */
	private String quitarTildesYOtros(String cadena2) 
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
				sinTildes += "\\\'i";
			else if (actual == 'ó')
				sinTildes += "\\\'o";
			else if (actual == 'ú')
				sinTildes += "\\\'u";
			else if (actual == 'ñ')
				sinTildes += "\\~n";
			else
				sinTildes += actual;
		}
		return sinTildes;
	}


	/**
	 * Comprueba si el caracter pasado por parámetro es mayuscula. 
	 * @param actual Caracter a comprobar
	 * @return True si es una letra mayúscula, false en cualquier otro caso.
	 */
	private boolean esMayuscula(char actual) 
	{
		//MÉTODO NO USADO.
//		char 'A' = 65.
//		char 'Z' = 90.
//		return ((actual > 64) && (actual <91)); 
		
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
					salida = salida + ", " + it.next();
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
			String apellidos = quitarTildesYOtros(ae.getApellidos());
			if (tieneVariasPalabras(apellidos))
				return ("{" + apellidos + "}");
			else
				return apellidos;
		}
		else
		{
			String nombre = quitarTildesYOtros(ae.getNombre());
			if (ae.getApellidos() != null)
			{
				String apellidos = quitarTildesYOtros(ae.getApellidos());
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
	 * @return LinkedList con las keywords de la publicación.
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
	 * @param url de la publicación.
	 */
	public final void setURL(String url) {
		URL = url;
	}


	/**
	 * @return El usuario que añadió la publicación.
	 */
	public final String getUser() {
		return user;
	}



	/**
	 * Fija el usuario del documento.
	 * @param user usuario de la publicación.
	 */
	public final void setUser(String user) {
		this.user = user;
	}


	/*
	 * @return Los proyectos de la publicación.
	 */
//	public final Vector<String> getProyectos() {
//		return proyectos;
//	}


	/*
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
	 * Fija la referencia del documento.
	 * @param  referencia La referencia de la publicación.
	 */
	public final void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	

	/**
	 * Fija el título del documento.
	 * @param title El título a fijar.
	 */
	public final void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Fija el año del documento.
	 * @param year El año de la publicación.
	 */
	public final void setYear(String year) {
		this.year = year;
	}


	/**
	 * Fija el mes del documento.
	 * @param month El mes de la publicación.
	 */
	public final void setMonth(String month) {
		this.month = month;
	}


	/**
	 * Fija el abstract del documento.
	 * @param _abstract El resumen de la publicación.
	 */
	public final void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}


	/**
	 * Fija los comentarios del documento.
	 * @param note La nota de la publicación.
	 */
	public final void setNote(String note) {
		this.note = note;
	}


	/**
	 * Fija el conjunto de keywords del documento.
	 * @param key Lista con las nuevas claves para el documento.
	 */
	public final void setKey(LinkedList<String> key)
	{
		this.key = key;
	}

	/**
	 * Genera una serie de sentencias SQL que se deberán ejecutar para
	 * que la publicación quede correctamente insertada.
	 * @param conn Conexión a la base de datos que será usada.
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
			DataBaseControler dbc = new DataBaseControler();
			while (keysit.hasNext()){
				String k = keysit.next();
				if (k != null && !k.equals(" "))
				{
					if (!dbc.consultaExistenciaKey(k, conn))
						dbc.ejecutaString("INSERT INTO claves VALUES('" + k + "');", conn);
					vector.add("INSERT INTO tienekey VALUES (" + getIdDoc() + ",'" + k +"');");
				}			
			}
		}
		
		return vector;
	}
	
	/**
	 * Antepone una barra oblícua a todas las comillas simples que aparecen, para evitar malinterpretaciones en las sentencias SQL.
	 * @param s Cadena a procesar.
	 * @return La cadena entrante, con las barras oblícuas delante de las comillas simples.
	 */
	public static String sustituirComillasSQL(String s)
	{
		String[] strings = s.split("\'");
		String result = strings[0];
		for (int i = 1; i < strings.length; i++)
			result += "\\\'" + strings[i];
		return result;
	}
	
	/**
	 * Quita las llaves que aparecen en una cadena de caracteres, si así lo especifica uno de los parámetros.
	 * @param s Cadena a procesar.
	 * @param quitarLlaves Indica si se deben quitar las llaves.
	 * @return La cadena, tras ser procesada.
	 */
	public static String quitarLlaves(String s, boolean quitarLlaves)
	{
		if (quitarLlaves)
		{
			if (s != null)
			{
				String result = "";
				int numChar = s.length();
				char actual;
				for (int i = 0; i < numChar; i++)
				{
					actual = s.charAt(i);
					if (actual != '{' && actual != '}')
						result += actual;
				}
				return result;
			}
			else 
				return null;
		}
		else 
			return s;
	}
}
