package personas;

import org.jdom.Element;

/**
 * Clase que guarda los datos de un autor o un editor.
 */
public class AutorEditor 
{
	/**
	 * Identificador único de autorEditor
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
	 * Web del autor.
	 */
	private String web;

	/**
	 * @param idAut
	 * @param nombre
	 * @param apellidos
	 * @param web
	 */
	public AutorEditor(int idAut, String nombre, String apellidos, String web) {
		this.idAut = idAut;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.web = web;
	}
	
	public AutorEditor (Object vector_datos[]){
		if (vector_datos[0] != null) this.idAut = ((Long) vector_datos[0]).intValue();
		if (vector_datos[1] != null) this.nombre = (String)vector_datos[1];
		if (vector_datos[2] != null) this.apellidos = (String)vector_datos[2];
		if (vector_datos[3] != null) this.web = (String)vector_datos[3];
	}

	/**
	 * @param nombre
	 * @param apellidos
	 * @param web
	 */
	public AutorEditor(String nombre, String apellidos, String web) {
		this.idAut = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.web = web;
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
	 * Asigna un string a el nombre o los apellidos.
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

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getWeb() {
		return web;
	}
	
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
		eNombre.addContent(nombre);
		eAuthor.addContent(eNombre);
		
		Element eApellidos = new Element("apellidos");
		eApellidos.addContent(apellidos);
		eAuthor.addContent(eApellidos);
		
		Element eWeb = new Element("web");
		eWeb.addContent(web);
		eAuthor.addContent(eWeb);
		
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
		eNombre.addContent(nombre);
		eEditor.addContent(eNombre);
		
		Element eApellidos = new Element("apellidos");
		eApellidos.addContent(apellidos);
		eEditor.addContent(eApellidos);
		
		Element eWeb = new Element("web");
		eWeb.addContent(web);
		eEditor.addContent(eWeb);
		
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
