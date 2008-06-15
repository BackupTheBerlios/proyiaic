package parserFicherosBibtex;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import parserFicherosBibtex.excepciones.ExcepcionLexica;
import publicaciones.Article;
import publicaciones.Book;
import publicaciones.Booklet;
import publicaciones.Conference;
import publicaciones.InBook;
import publicaciones.InCollection;
import publicaciones.InProceedings;
import publicaciones.Manual;
import publicaciones.MastersThesis;
import publicaciones.Misc;
import publicaciones.PhdThesis;
import publicaciones.Proceedings;
import publicaciones.Publication;
import publicaciones.TechReport;
import publicaciones.Unpublished;

/**
 * Sirve para procesar ficheros BibTeX y obtener publicaciones.
 */
public class ParserBibtex 
{
	/**
	 * Publicaciones procesadas.
	 */
	private LinkedList<Publication> publicaciones;
	
	/**
	 * Strings procesados.
	 */
	private LinkedList<CampoString> strings;
	
	/**
	 * Constructor por defecto: inicializa los atributos.
	 */
	public ParserBibtex()
	{
		publicaciones = new LinkedList<Publication>();
		strings = new LinkedList<CampoString>();
	}
	
	/**
	 * Intenta abrir el fichero indicado y, si hay éxito, lo procesa.
	 * @param is Fichero que se quiere procesar.
	 * @param nombreFichero Ruta y nombre del fichero de destino.
	 */
	public void procesar(InputStream is, String nombreFichero)
	{
		try 
		{
			extraerDocumento(is);
			generarXML(nombreFichero);
		} 
		catch (ExcepcionLexica e)
		{
			e.imprimirError();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera un documento XML a partir de las publicaciones procesadas.
	 * @param rutaXML Ruta y nombre del archivo XML que se va a generar.
	 */
	private void generarXML(String rutaXML) 
	{
		Element root = new Element("listaPublicaciones");
		Iterator<Publication> it = publicaciones.iterator();
		Publication p;
		while (it.hasNext())
		{
			p = it.next();
			Element elemento = p.generarElementoXML(false);
			root.addContent(elemento);
		}
		XMLOutputter outputter = new XMLOutputter();
		try
		{
			outputter.output (new Document(root), new FileOutputStream(rutaXML));
		}
		catch (Exception e){
		    e.getMessage();
		}
	}

	/**
	 * Lee el tipo de publicación a procesar, así como sus campos. Después, genera la publicación.
	 * @param is Fichero del que se va a leer.
	 * @throws IOException Causada por errores en la entrada/salida.
	 * @throws ExcepcionLexica Si hay errores léxicos en el fichero entrante.
	 */
	private void extraerDocumento(InputStream is) throws IOException, ExcepcionLexica
	{
		char actual = siguienteCaracter(is);
		while (actual != '\0')
		{
			if (actual == '%')
			{
				saltarComentario(is);
				actual = siguienteCaracter(is);
			}
			else if (actual == '@')
			{
				String tipoDoc = extraerTipoDoc(is);
				if (tipoDoc.equals("string"))
				{
					CampoString campoString = extraerCampoString(is);
					strings.add(campoString);
				}
				else
				{
					LinkedList<Campo> campos = extraerCampos(is);
					generarDocumentoBibtex(tipoDoc, campos);
				}
				actual = siguienteCaracter(is);
			}
			else //Deben ser bytes que se han metido al cargar el fichero.
			{
				//throw new ExcepcionLexica("El primer caracter encontrado no es '@' ni '%'.");
				while (actual != '%' && actual != '@' && actual != '\0')
					actual = siguienteCaracter(is);
			}
		}
	}

	/**
	 * Extrae el contenido de un "@STRING".
	 * @param is Fichero desde donde se va a leer.
	 * @return El contenido del "@STRING"
	 * @throws IOException Causada por errores de entrada/salida.
	 * @throws ExcepcionLexica Causada por errores léxicos en el fichero entrante.
	 */
	private CampoString extraerCampoString(InputStream is) throws IOException, ExcepcionLexica 
	{
		char actual = siguienteCaracter(is);
		String abrev = "";
		while (actual != '=')
		{
			abrev += actual;
			actual = siguienteCaracter(is);
		}
		
		String texto = "";
		actual = siguienteCaracter(is);
		if (actual == '"') //Comienza por comillas.
		{
			texto = copiarIntegroDesdeHasta(is, texto, '"', '"');
			actual = siguienteCaracter(is); //Pasamos el caracter '"'.
		}
		else
			if (actual == '{') //Comienza por llave.
			{
				texto = copiarIntegroDesdeHasta(is, texto, '{', '}');
				actual = siguienteCaracter(is); //Pasamos el caracter '}'.
			}
			else
				throw new ExcepcionLexica("Caracter \'\"\' o \'{\' esperado.");
		if (actual != '}') //Llave que cierra el @STRING.
			throw new ExcepcionLexica("Caracter \'}\' esperado.");
		
		return new CampoString(abrev, texto);
	}

	/**
	 * Genera una publicación, a partir del tipo de documento y los campos extraídos.
	 * @param tipoDoc Tipo de publicación a generar.
	 * @param campos Campos de la publicación a generar.
	 * @throws ExcepcionLexica Causada por errores léxicos en el fichero entrante.
	 */
	private void generarDocumentoBibtex(String tipoDoc, LinkedList<Campo> campos) throws ExcepcionLexica 
	{
		Publication nuevaPublicacion;
		if (tipoDoc.equals("article")){
			nuevaPublicacion = new Article(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("book")){
			nuevaPublicacion = new Book(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("booklet")){
			nuevaPublicacion = new Booklet(campos);	
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("conference")){
			nuevaPublicacion = new Conference(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("inbook")){
			nuevaPublicacion = new InBook(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("incollection")){
			nuevaPublicacion = new InCollection(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("inproceedings")){
			nuevaPublicacion = new InProceedings(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("manual")){
			nuevaPublicacion = new Manual(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("mastersthesis")){
			nuevaPublicacion = new MastersThesis(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("misc")){
			nuevaPublicacion = new Misc(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("phdthesis")){
			nuevaPublicacion = new PhdThesis(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("proceedings")){
			nuevaPublicacion = new Proceedings(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("techreport")){
			nuevaPublicacion = new TechReport(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else if (tipoDoc.equals("unpublished")){
			nuevaPublicacion = new Unpublished(campos);
			publicaciones.add(nuevaPublicacion);
		}
		else
			throw new ExcepcionLexica("El tipo de documento encontrado no es válido.");
	}

	/**
	 * Extrae los campos del documento.
	 * @param is Fichero del que se va a leer.
	 * @return La lista de los campos extraídos.
	 * @throws IOException Causada por errores en la entrada/salida.
	 * @throws ExcepcionLexica Causada por errores léxicos en el fichero entrante.
	 */
	private LinkedList<Campo> extraerCampos(InputStream is) throws IOException, ExcepcionLexica 
	{
		LinkedList<Campo> listaCampos = new LinkedList<Campo>();
		
		boolean terminado;
		boolean posibleReferencia = true;
		CampoPublicacion nuevo;
		do
		{
			nuevo = extraerCampo(is, posibleReferencia);
			listaCampos.add(nuevo);
			terminado = nuevo.getEsUltimo();
			posibleReferencia = false;
		}
		while (!terminado);
		return listaCampos;
		
	}

	/**
	 * Extrae un campo de un documento.
	 * @param is Fichero del que se va a leer.
	 * @param posibleReferencia Indica si hay posibilidad de que lo que se extraiga sea la referencia de un documento, y no uno de sus campos.
	 * @return Un campo de una publicación.
	 * @throws ExcepcionLexica Causada por errores léxicos en el fichero entrante.
	 * @throws IOException Causada por errores en la entrada/salida.
	 */
	private CampoPublicacion extraerCampo(InputStream is, boolean posibleReferencia) throws ExcepcionLexica, IOException 
	{
		String nombreCampo = "";
		String valorCampo = "";
		boolean esReferencia = false;
		char actual = siguienteCaracter(is);
		if (actual != '}') //Hay campo.
		{
			if (!posibleReferencia)
				while (actual != '=')
				{
					nombreCampo += actual;
					actual = siguienteCaracter(is);
				}
			else
			{
				while (actual != '=' && actual != ',')
				{
					nombreCampo += actual;
					actual = siguienteCaracter(is);
				}
				if (actual == ',')
					esReferencia = true;
			}
			nombreCampo = nombreCampo.toLowerCase();
			if (!esReferencia)
			{
				actual = siguienteCaracter(is); //Pasamos el '='.
				if (actual == '"') //Comienza por comillas.
				{
					valorCampo = copiarIntegroDesdeHasta(is, valorCampo, '"', '"');
					actual = siguienteCaracter(is); //Pasamos el caracter '"'.
				}
				else
					if (actual == '{') //Comienza por llave.
					{
						valorCampo = copiarIntegroDesdeHasta(is, valorCampo, '{', '}');
						actual = siguienteCaracter(is); //Pasamos el caracter '}'.
					}
					else //No comienza ni por '"' ni por '{'.
					{
						while (actual != ',' && actual != '}')
						{
							if (actual == '{')
							{
								valorCampo += '{';
								valorCampo = copiarIntegroDesdeHasta(is, valorCampo, '{', '}');
								valorCampo += '}';
							}
							else
								valorCampo += actual;
							actual = siguienteCaracter(is);
						}
						valorCampo = analizarStrings(valorCampo);
					}
			}
			else
			{
				valorCampo = nombreCampo;
				nombreCampo = "referencia";
			}
		}
		else //No hay más campos.
		{
			nombreCampo = "vacio";
			valorCampo = null;
		}
		
		boolean ultimoCampo = actual == '}';
		CampoPublicacion campoNuevo = new CampoPublicacion(nombreCampo, valorCampo, ultimoCampo);

		return campoNuevo;
	}

	/**
	 * Reemplaza una abreviatura por su texto completo, si es preciso.
	 * @param valorCampo Texto a analizar.
	 * @return El texto completo, si se encuentran coincidencias con las abreviaturas.
	 */
	private String analizarStrings(String valorCampo) 
	{
		String nuevo = valorCampo;
		Iterator<CampoString> itStrings = strings.iterator();
		CampoString c;
		while (itStrings.hasNext())
		{
			c = itStrings.next();
			String abrev = c.getAbrev();
			if (nuevo.equals(abrev))
				nuevo = c.getTexto();
		}
		return nuevo;
	}

	/**
	 * Copia íntegramente desde un fichero, hasta que encuentra un caracter determinado.
	 * @param is Fichero del que se va a leer.
	 * @param valorCampo String inicial.
	 * @param desde Caracter anterior al punto desde donde se empezará a copiar. Sirve para permitir niveles.
	 * @param hasta Caracter que indicará que se tiene que dejar de copiar.
	 * @return String resultante.
	 * @throws IOException Causada por errores en la entrada/salida.
	 */
	private String copiarIntegroDesdeHasta(InputStream is, String valorCampo, char desde, char hasta) throws IOException 
	{
		String nuevoString = valorCampo;
		int nivel = 1;
		char actual = (char)is.read();
		if (actual == hasta)
			nivel--;
		else
			if (actual == desde)
				nivel++;
		while (!(actual == hasta && nivel == 0))
		{
			if (!(actual == '\n' || actual == '\t' || actual == '\r' || (actual == ' ' && nuevoString.length() > 0 && nuevoString.charAt(nuevoString.length()-1) == ' ')))
				nuevoString += actual;
			actual = (char)is.read();
			if (actual == hasta)
				nivel--;
			else
				if (actual == desde)
					nivel++;
		}
		return nuevoString;
	}

	/**
	 * Extrae el tipo de documento.
	 * @param is Fichero desde el que se va a leer.
	 * @return El tipo de documento.
	 * @throws IOException Causada por errores en la entrada/salida.
	 */
	private String extraerTipoDoc(InputStream is) throws IOException {
		String s = "";
		char actual = siguienteCaracter(is);
		while (actual != '{')
		{
			s += actual;
			actual = siguienteCaracter(is);
		}
		return s.toLowerCase();
	}

	/**
	 * Lee el siguiente caracter desde un fichero. Omite tabulaciones, saltos de línea, espacios, ...
	 * @param is Fichero desde el que se va a leer.
	 * @return El siguiente caracter.
	 * @throws IOException Causada por errores en la entrada/salida.
	 */
	private char siguienteCaracter(InputStream is) throws IOException
	{
		int num = is.read();
		char c = (char)num;
		if (num == -1)
			return '\0';
		while (c == ' ' || c == '\t' || c == '\n' || c == '\r')
		{
			num = is.read();
			c = (char)num;
			if (num == -1)
				return '\0';
		}
		return c;
	}

	/**
	 * Salta los comentarios que hay en un fichero.
	 * @param is Fichero desde el que se lee.
	 * @throws IOException Causada por errores en la entrada/salida.
	 */
	private void saltarComentario(InputStream is) throws IOException 
	{
		char actual = (char)is.read();
		while(actual != '\n')
			actual = (char)is.read();
	}
	
	/**
	 * Devuelve la última publicación procesada.
	 * @return La última publicación procesada.
	 */
	public Publication getUltDoc() {
		return publicaciones.getLast();
	}
}
