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
	
	public ParserBibtex()
	{
		publicaciones = new LinkedList<Publication>();
		strings = new LinkedList<CampoString>();
	}
	
	/**
	 * Intenta abrir el fichero indicado y, si hay �xito, lo procesa.
	 * @param ruta Ruta del fichero que se quiere procesar.
	 */
	public void procesar(InputStream is, String nombreFichero)
	{
		try {
			
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
			Element elemento = p.generarElementoXML();
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
	 * Lee el tipo de publicaci�n a procesar, as� como sus campos. Despu�s, genera la publicaci�n.
	 * @param is Fichero del que se va a leer.
	 * @throws IOException
	 * @throws ExcepcionLexica
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
					LinkedList<CampoPublicacion> campos = extraerCampos(is);
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
	 * @throws IOException
	 * @throws ExcepcionLexica
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
	 * Genera una publicaci�n, a partir del tipo de documento y los campos extra�dos.
	 * @param tipoDoc Tipo de publicaci�n a generar.
	 * @param campos Campos de la publicaci�n a generar.
	 * @throws ExcepcionLexica
	 */
	private void generarDocumentoBibtex(String tipoDoc, LinkedList<CampoPublicacion> campos) throws ExcepcionLexica 
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
			throw new ExcepcionLexica("El tipo de documento encontrado no es v�lido.");
	}

	/**
	 * Extrae los campos del documento.
	 * @param is Fichero del que se va a leer.
	 * @return La lista de los campos extra�dos.
	 * @throws IOException
	 * @throws ExcepcionLexica
	 */
	private LinkedList<CampoPublicacion> extraerCampos(InputStream is) throws IOException, ExcepcionLexica 
	{
		LinkedList<CampoPublicacion> listaCampos = new LinkedList<CampoPublicacion>();
		
		boolean terminado;
		boolean posibleReferencia = true;
		CampoPublicacion nuevo;
		do
		{
			nuevo = extraerCampo(is, posibleReferencia);
			nuevo.sustituirTildes();
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
	 * @return Un campo de una publicaci�n.
	 * @throws ExcepcionLexica
	 * @throws IOException
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
		else //No hay m�s campos.
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
	 * Copia �ntegramente desde un fichero, hasta que encuentra un caracter determinado.
	 * @param is Fichero del que se va a leer.
	 * @param valorCampo String inicial.
	 * @param desde Caracter anterior al punto desde donde se empezar� a copiar. Sirve para permitir niveles.
	 * @param hasta Caracter que indicar� que se tiene que dejar de copiar.
	 * @return String resultante.
	 * @throws IOException
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
	 * @throws IOException
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
	 * Lee el siguiente caracter desde un fichero. Omite tabulaciones, saltos de l�nea, espacios, ...
	 * @param is Fichero desde el que se va a leer.
	 * @return El siguiente caracter.
	 * @throws IOException
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
	 * @throws IOException
	 */
	private void saltarComentario(InputStream is) throws IOException 
	{
		char actual = (char)is.read();
		while(actual != '\n')
			actual = (char)is.read();
	}
	
	/**
	 * Devuelve la �ltima publicaci�n procesada.
	 * @return La �ltima publicaci�n procesada.
	 */
	public Publication getUltDoc() {
		return publicaciones.getLast();
	}
}
