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
 * comportamiento que ha de ser com�n a todos ellos.
 */
public abstract class Publication 
{

	/**
	 * Identificador de la publicaci�n en la base de datos (un�voco).
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
	 * Comentarios de la publicaci�n.
	 */
	protected String note;

	/**
	 * keyword/s de la publicaci�n.
	 */
	protected LinkedList<String> key;

	/**
	 * Usuario que ha a�adido la publicaci�n al sistema.
	 */
	protected String user;

//	/**
//	 * Contiene todos los proyectos a los que pertenece la aplicaci�n.
//	 */
//	protected Vector<String> proyectos;
	
	/**
	 * Proyecto al que est� asociado la publicaci�n.
	 */
	protected String proyecto;

//	/**
//	 * Separador de keywords.
//	 */
//	public static final String separador = new String (", ");


	/**
	 * Setter de la publicaci�n el que se fijan todos sus atributos a los que se pasan
	 * por par�metro, equivalente a un constructor dados los par�metros, pero al
	 * ser una clase abstracta es m�s comodo el multisetter.
	 * @param idDoc Identificador de la publicaci�n.
	 * @param referencia Referencia de la publicaci�n.
	 * @param title T�tulo de la publicaci�n.
	 * @param year A�o de la publicaci�n.
	 * @param month Mes de la publicaci�n.
	 * @param url Direcci�n URL de la publicaci�n.
	 * @param _abstract Resumen de la publicaci�n.
	 * @param note Notas de la publicaci�n.
	 * @param keys Claves de la publicaci�n.
	 * @param user Usuario que ha subido la publicaci�n al sistema.
	 * @param proyecto Proyectos a los que pertenece la publicaci�n.
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
	 * A�ade una keywords nueva a la publicaci�n.
	 * @param un_key La clave nueva a insertar.
	 */
	protected void addKey(String un_key) {
		if (this.key == null)
			key = new LinkedList<String>();			
		if (un_key != null && !key.contains(un_key)) 			
			key.add(un_key);	
	}

	/**
	 * Genera el c�digo BibTeX asociado al art�culo.
	 * @return El c�digo BibTeX generado.
	 */
	public abstract String getBibTeX();

	/**
	 * Genera un elemento XML con la informaci�n del objeto.
	 * @param quitarLlaves Indica si se quieren quitar las llaves que aparezcan en los campos de la publicaci�n.
	 * @return El elemento generado.
	 */
	public abstract Element generarElementoXML(boolean quitarLlaves);

	/**
	 * Extrae los autores/editores de una cadena de caracteres que se le pasa por
	 * par�metro.
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
	 * Indica si un caracter es un 'blanco' (espacio, salto de l�nea, tabulador o retorno de carro).
	 * @param actual Caracter a evaluar.
	 * @return El resultado de la evaluaci�n
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
	 * Convierte una cadena de caracteres a c�digo BibTeX, insertando llaves y sustituyendo tildes.
	 * @param cadena2 Cadena de caracteres a procesar.
	 * @return El c�digo BibTeX generado.
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
	 * por sus correspondientes "sustitutos" en c�digo LaTeX.
	 * Idem con la letra '�'.
	 * @param cadena2 Cadena a procesar.
	 * @return El resultado, tras sustituir las tildes y la '�'.
	 */
	private String quitarTildesYOtros(String cadena2) 
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
				sinTildes += "\\\'i";
			else if (actual == '�')
				sinTildes += "\\\'o";
			else if (actual == '�')
				sinTildes += "\\\'u";
			else if (actual == '�')
				sinTildes += "\\~n";
			else
				sinTildes += actual;
		}
		return sinTildes;
	}


	/**
	 * Comprueba si el caracter pasado por par�metro es mayuscula. 
	 * @param actual Caracter a comprobar
	 * @return True si es una letra may�scula, false en cualquier otro caso.
	 */
	private boolean esMayuscula(char actual) 
	{
		//M�TODO NO USADO.
//		char 'A' = 65.
//		char 'Z' = 90.
//		return ((actual > 64) && (actual <91)); 
		
		return false;
	}


	/**
	 * Convierte una lista de autores/editores en c�digo LaTeX.
	 * @param autoresEditores Lista de autores/editores a procesar.
	 * @return El c�digo LaTeX resultante.
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
	 * Convierte una lista de claves en c�digo LaTeX.
	 * @param keys Lista de claves a procesar.
	 * @return El c�digo LaTeX resultante.
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
	 * Convierte un autor/editor en c�digo LaTeX.
	 * @param ae Autor/editor a procesar.
	 * @return El c�digo LaTeX resultante.
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
	 * Indica si una cadena est� formada por varias palabras.
	 * @param apellidos Cadena a procesar.
	 * @return El resultado de la evaluaci�n.
	 */
	private boolean tieneVariasPalabras(String apellidos) 
	{
		return apellidos.contains(" ");
	}

	/**
	 * @return El t�tulo de la publicaci�n.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return El resumen de la publicaci�n.
	 */
	public String get_abstract() {
		return _abstract;
	}

	/**
	 * @return La nota de la publicaci�n.
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @return LinkedList con las keywords de la publicaci�n.
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
	 * @return El mes de la publicaci�n.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @return El a�o de la publicaci�n.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @return La referencia de la publicaci�n.
	 */
	public String getReferencia() {
		return referencia;
	}


	/**
	 * @return La URL de la publicaci�n.
	 */
	public final String getURL() {
		return URL;
	}

	/**
	 * @param url de la publicaci�n.
	 */
	public final void setURL(String url) {
		URL = url;
	}


	/**
	 * @return El usuario que a�adi� la publicaci�n.
	 */
	public final String getUser() {
		return user;
	}



	/**
	 * Fija el usuario del documento.
	 * @param user usuario de la publicaci�n.
	 */
	public final void setUser(String user) {
		this.user = user;
	}


	/*
	 * @return Los proyectos de la publicaci�n.
	 */
//	public final Vector<String> getProyectos() {
//		return proyectos;
//	}


	/*
	 * @param Los proyectos de la publicaci�n.
	 */
//	public final void setProyectos(Vector<String> proyectos) {
//		this.proyectos = proyectos;
//	}


	/**
	 * @return El identificador de la publicaci�n.
	 */
	public final int getIdDoc() {
		return idDoc;
	}


	/**
	 * Fija la referencia del documento.
	 * @param  referencia La referencia de la publicaci�n.
	 */
	public final void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	

	/**
	 * Fija el t�tulo del documento.
	 * @param title El t�tulo a fijar.
	 */
	public final void setTitle(String title) {
		this.title = title;
	}


	/**
	 * Fija el a�o del documento.
	 * @param year El a�o de la publicaci�n.
	 */
	public final void setYear(String year) {
		this.year = year;
	}


	/**
	 * Fija el mes del documento.
	 * @param month El mes de la publicaci�n.
	 */
	public final void setMonth(String month) {
		this.month = month;
	}


	/**
	 * Fija el abstract del documento.
	 * @param _abstract El resumen de la publicaci�n.
	 */
	public final void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}


	/**
	 * Fija los comentarios del documento.
	 * @param note La nota de la publicaci�n.
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
	 * Genera una serie de sentencias SQL que se deber�n ejecutar para
	 * que la publicaci�n quede correctamente insertada.
	 * @param conn Conexi�n a la base de datos que ser� usada.
	 * @return Vector con todas las sentencias SQL.
	 * @throws BDException Si hay alg�n problema relacionado con la conexi�n a la base de datos.
	 * @throws ExistingElementException Si hay alg�n problema relacionado con la existencia de tuplas en la base de datos.
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
	 * Antepone una barra obl�cua a todas las comillas simples que aparecen, para evitar malinterpretaciones en las sentencias SQL.
	 * @param s Cadena a procesar.
	 * @return La cadena entrante, con las barras obl�cuas delante de las comillas simples.
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
	 * Quita las llaves que aparecen en una cadena de caracteres, si as� lo especifica uno de los par�metros.
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
