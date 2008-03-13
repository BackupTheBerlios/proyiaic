package personas;

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
		if (ae.charAt(0) == ' ')
			ae = quitarEspacios(ae, true);
		if (ae.charAt(ae.length()-1) == ' ')
			ae = quitarEspacios(ae, false);
		
		if (ae.charAt(0) == '{') //Encerrado entre llaves --> Todo es apellido.
		{
			nombre = null;
			apellidos = ae.substring(1, ae.length() - 2);
		}
		else
		{
			if (ae.contains(",")) //Lo de detrás de la coma es el nombre, y lo de antes son los apellidos.
			{
				int posicionComa = ae.indexOf(',');
				asignar(ae.substring(0, posicionComa-1), true);
				asignar(ae.substring(posicionComa+1), false);
			}
			else
			{
				if (ae.contains(" ")) //Hay varias palabras: la última es el apellido y el resto es el nombre.
				{
					int posicionUltEspacio = ae.lastIndexOf(' ');
					asignar(ae.substring(0, posicionUltEspacio - 1), false);
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

	private String quitarEspacios(String ae, boolean alPrincipio) 
	{
		if (alPrincipio)
		{
			int i = 0;
			while (ae.charAt(i) == ' ')
				i++;
			return ae.substring(i);
		}
		else
		{
			int i = ae.length() -1;
			while (ae.charAt(i) == ' ')
				i--;
			return ae.substring(0, i);
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
		if (nombre != null)
			System.out.println("      - Nombre: " + nombre);
		if (apellidos != null)
			System.out.println("      - Apellidos: " + apellidos);
		if (web != null)
			System.out.println("      - Web: " + web);
	}
}
