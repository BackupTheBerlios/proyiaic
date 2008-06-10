package personas;

import org.jdom.Element;

import publicaciones.Publication;

/**
 * Clase que guarda los datos de un autor o un editor, también implementa el comportamiento
 * necesario para su correcta gestión y manipulación.
 */
public class AutorEditor 
{
	/**
	 * Identificador único de autorEditor, lo utilizamos para la base de datos.
	 */
	private int idAut;
	
	/**
	 * Nombre del autor.
	 */
	private String nombre;
	
	/**
	 * Apellidos del autor.
	 */
	private String apellidos;

	/**
	 * Constructor de la clase dados sus atributos.
	 * @param idAut Identificador único de autor/editor
	 * @param nombre Nombre del autor/editor.
	 * @param apellidos Apellidos del autor/editor.
	 */
	public AutorEditor(int idAut, String nombre, String apellidos) {
		this.idAut = idAut;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	
	/**
	 * Constructor de la clase dado una array de Object, que contiene los datos que se
	 * utilizarán como atributos del autor/editor.
	 * @param vector_datos Array que contiene los datos para la contrucción. 
	 * vector_datos[0] - Debe ser un Long que contenga el idDoc.
	 * vector_datos[1] - Debe ser un String que contenga el nombre.
	 * vector_datos[2] - Debe ser un String que contenga los apellidos.
	 */
	public AutorEditor (Object vector_datos[]){
		if (vector_datos[0] != null) this.idAut = ((Long) vector_datos[0]).intValue();
		if (vector_datos[1] != null) this.nombre = (String)vector_datos[1];
		if (vector_datos[2] != null) this.apellidos = (String)vector_datos[2];
	}

	/**
	 * COnstructor de la clase dados únicamente los parametros de nombre y apellidos.
	 * Como identificador único colocará un 0.
	 * @param nombre Nombre del autor/editor.
	 * @param apellidos Apellidos del autor/editor.
	 */
	public AutorEditor(String nombre, String apellidos) {
		this.idAut = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	/**
	 * Separa el nombre y apellidos de los autores/editores.
	 * @param autorEditor String que contiene el nombre y apellidos de los autores/editores.
	 */
	public AutorEditor(String autorEditor) 
	{
		String ae = autorEditor;
		if (ae.charAt(0) == ' ' || ae.charAt(0) == '\n' || ae.charAt(0) == '\t' || ae.charAt(0) == '\r')
			ae = quitarEspacios(ae, true);
		if (ae.charAt(ae.length()-1) == ' ' || ae.charAt(ae.length()-1) == '\n' || ae.charAt(ae.length()-1) == '\t' || ae.charAt(ae.length()-1) == '\r')
			ae = quitarEspacios(ae, false);
		
		if (ae.charAt(0) == '{') //Encerrado entre llaves --> Todo es apellido.
		{
			nombre = null;
			apellidos = ae.substring(1, ae.length() - 1);
		}
		else
		{
			if (ae.contains(",")) //Lo de detrás de la coma es el nombre, y lo de antes son los apellidos.
			{
				int posicionComa = ae.indexOf(',');
				asignar(ae.substring(0, posicionComa), true);
				asignar(ae.substring(posicionComa+1), false);
			}
			else
			{
				if (ae.contains(" ")) //Hay varias palabras: la última es el apellido y el resto es el nombre.
				{
					int posicionUltEspacio = ae.lastIndexOf(' ');
					asignar(ae.substring(0, posicionUltEspacio), false);
					asignar(ae.substring(posicionUltEspacio + 1), true);
				}
				else //Sólo hay una palabra: es el apellido.
				{
					nombre = null;
					apellidos = ae;
				}
			}
			
		}
	}


	/**
	 * Asigna un string al nombre o los apellidos.
	 * @param substring String que se quiere asignar (tras omitir espacios)
	 * @param asignarApellidos Si es true se asigna a los apellidos, y si es false se asigna al nombre.
	 */
	private void asignar(String substring, boolean asignarApellidos) 
	{
		String sinEspacios = substring;
		sinEspacios = quitarEspacios(sinEspacios, true);
		sinEspacios = quitarEspacios(sinEspacios, false);
		
		if (asignarApellidos == true)
			apellidos = sinEspacios;
		else
			nombre = sinEspacios;
	}

	/**
	 * Quita los espacios que haya al principio o al final de un string.
	 * @param ae String del que se quieren quitar espacios.
	 * @param alPrincipio Indica si los espacios que se quieren quitar están al principio o al final de la cadena.
	 * @return La cadena entrante, pero sin espacios.
	 */
	private String quitarEspacios(String ae, boolean alPrincipio) 
	{
		if (alPrincipio)
		{
			int i = 0;
			while (ae.charAt(i) == ' ' || ae.charAt(i) == '\n' || ae.charAt(i) == '\t' || ae.charAt(i) == '\r')
				i++;
			return ae.substring(i);
		}
		else
		{
			int i = ae.length() -1;
			while (ae.charAt(i) == ' ' || ae.charAt(i) == '\n' || ae.charAt(i) == '\t' || ae.charAt(i) == '\r')
				i--;
			return ae.substring(0, i+1);
		}
		
	}

	
	/**
	 * Devuelve el nombre del autor/editor.
	 * @return String - Nombre del autor.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Devuelve los apellidos del autor/editor.
	 * @return String - Apellidos del autor.
	 */
	public String getApellidos() {
		return apellidos;
	}

	
	/**
	 * Devuelve el identificador único de autor/editor.
	 * @return int - Identificador único.
	 */
	public int getId(){
		return idAut;
	}

	/**
	 * Genera un elemento XML con la información del autor.
	 * @return El elemento generado.
	 */
	public Element generarAuthorXML() 
	{
		Element eAuthor = new Element("author");
		
		Element eNombre = new Element("nombre");
		eNombre.addContent(Publication.quitarLlaves(nombre));
		eAuthor.addContent(eNombre);
		
		Element eApellidos = new Element("apellidos");
		eApellidos.addContent(Publication.quitarLlaves(apellidos));
		eAuthor.addContent(eApellidos);
		
		return eAuthor;
	}
	
	/**
	 * Genera un elemento XML con la información del editor.
	 * @return El elemento generado.
	 */
	public Element generarEditorXML() 
	{
		Element eEditor = new Element("editor");
		
		Element eNombre = new Element("nombre");
		eNombre.addContent(Publication.quitarLlaves(nombre));
		eEditor.addContent(eNombre);
		
		Element eApellidos = new Element("apellidos");
		eApellidos.addContent(Publication.quitarLlaves(apellidos));
		eEditor.addContent(eApellidos);
		
		return eEditor;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof AutorEditor){
			AutorEditor at1 = (AutorEditor) obj;
			if (at1.idAut != this.idAut) return false;
			if (this.apellidos == null){
				if (at1.apellidos != null) return false;
			}
			else if (!this.apellidos.equalsIgnoreCase(at1.apellidos)) return false;
			
			if (this.nombre == null){
				if (at1.nombre != null) return false;
			}
			else if (!this.nombre.equalsIgnoreCase(at1.nombre)) return false;	
			
			return true;
		}
		else return false;
	}
	
}
