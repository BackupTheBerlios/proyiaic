package personas;

import org.jdom.Element;

public class AutorEditor 
{
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
	
	public AutorEditor(String nom, String ap, String w)
	{
		nombre = nom;
		apellidos = ap;
		web = w;
	}

	public AutorEditor(String autorEditor) 
	{
		String ae = autorEditor;
		if (ae.charAt(0) == ' ' || ae.charAt(0) == '\n' || ae.charAt(0) == '\t' || ae.charAt(0) == '\r')
			ae = quitarEspacios(ae, true);
		if (ae.charAt(ae.length()-1) == ' ' || ae.charAt(ae.length()-1) == '\n' || ae.charAt(ae.length()-1) == '\t' || ae.charAt(ae.length()-1) == '\r')
			ae = quitarEspacios(ae, false);
		
		if (ae.charAt(0) == '{') //Encerrado entre llaves --> Todo es apellido.
		{
			nombre = "";
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
					nombre = "";
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

	public void imprimir() 
	{
		if (nombre != "")
			System.out.println("      - Nombre: " + nombre);
		if (apellidos != "")
			System.out.println("      - Apellidos: " + apellidos);
		if (web != "")
			System.out.println("      - Web: " + web);
	}

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
}
