package parserFicherosBibtex;

/**
 * Clase que sirve para extraer un campo de una publicación 
 * mediante el ParserBibTeX.
 */
public class CampoPublicacion extends Campo
{	
	/**
	 * Valor del campo.
	 */
	private String valor;
	
	/**
	 * Indica si es el último campo del documento.
	 */
	private boolean esUltimo;
	
	
	public CampoPublicacion(String nombre, String valor, boolean ult)
	{
		this.nombre = nombre;
		this.valor = valor;
		this.esUltimo = ult;
		
		sustituirTildes();
	}
	
	public boolean getEsUltimo()
	{
		return esUltimo;
	}

	public String getValor() {
		return valor;
	}
	/**
	 * Sustituye las cadenas de caracteres que representan las tildes,
	 * por la propia letra con tilde.
	 */
	public void sustituirTildes() 
	{
		if (valor != null)
		{
			int estado = 0;
			String almacen = "";
			String nuevoValor = "";
			int indice = 0;
			int longit = valor.length();
			
			char actual;
			char letraParaTilde = '\0';
			while(indice < longit)
			{
				actual = valor.charAt(indice++);
				switch(estado)
				{
					case 0: almacen += actual;
							if (actual == '\\')
								estado = 1;
							else if (actual == '{')
								estado = 6;
							else
							{
								nuevoValor += almacen;
								almacen = "";
							}
							break;
							
					case 1: almacen += actual;
							if (actual == '\'')
								estado = 2;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 2: almacen += actual;
							letraParaTilde = actual;
							if (letraParaTilde == 'a')
							{
								nuevoValor += 'á';
								estado = 0;
								almacen = "";
							}
							else if (letraParaTilde == 'e')
							{
								nuevoValor += 'é';
								estado = 0;
								almacen = "";
							}
							else if (letraParaTilde == 'o')
							{
								nuevoValor += 'ó';
								estado = 0;
								almacen = "";
							}
							else if (letraParaTilde == 'u')
							{
								nuevoValor += 'ú';
								estado = 0;
								almacen = "";
							}
							else if (actual == '{')
								estado = 3;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 3: almacen += actual;
							if (actual == '\\')
								estado = 4;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 4: almacen += actual;
							if (actual == 'i')
							{
								estado = 5;
								letraParaTilde = actual;
							}
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 5: almacen += actual;
							if (actual == '}' && letraParaTilde == 'i')
							{
								nuevoValor += 'í';
								estado = 0;
								almacen = "";
							}
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 6: almacen += actual;
							if (actual == '\\')
								estado = 7;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 7: almacen += actual;
							if (actual == '\'')
								estado = 8;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 8: almacen += actual;
							if (actual == 'a' || actual == 'e' || actual == 'o' || actual == 'u')
							{	
								estado = 9;
								letraParaTilde = actual;
							}
							else if (actual == '{')
								estado = 10;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 9: almacen += actual;
							if (actual == '}')
							{
								if (letraParaTilde == 'a')
								{
									nuevoValor += 'á';
									estado = 0;
									almacen = "";
								}
								else if (letraParaTilde == 'e')
								{
									nuevoValor += 'é';
									estado = 0;
									almacen = "";
								}
								else if (letraParaTilde == 'o')
								{
									nuevoValor += 'ó';
									estado = 0;
									almacen = "";
								}
								else if (letraParaTilde == 'u')
								{
									nuevoValor += 'ú';
									estado = 0;
									almacen = "";
								}
							}
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 10: almacen += actual;
							if (actual == '\\')
								estado = 11;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 11: almacen += actual;
							if (actual == 'i')
							{
								estado = 12;
								letraParaTilde = actual;
							}
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 12: almacen += actual;
							if (actual == '}')
								estado = 13;
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
							
					case 13: almacen += actual;
							if (actual == '}' && letraParaTilde == 'i')
							{
								nuevoValor += 'í';
								estado = 0;
								almacen = "";
							}
							else
							{
								nuevoValor += almacen;
								almacen = "";
								estado = 0;
							}
							break;
				}
			}
			nuevoValor += almacen;
			valor = nuevoValor;
		}
	}
	
	public String toString()
	{
		return nombre + " " + valor;
	}
}
