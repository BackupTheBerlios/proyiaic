package parserFicherosBibtex;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

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
	
	private LinkedList<CampoString> strings;
	
	public ParserBibtex()
	{
		publicaciones = new LinkedList<Publication>();
		strings = new LinkedList<CampoString>();
	}
	
	/**
	 * Intenta abrir el fichero indicado y, si hay éxito, lo procesa.
	 * @param ruta Ruta del fichero que se quiere procesar.
	 */
	public LinkedList<Publication> procesar(String ruta)
	{
		try {
			FileReader fr = new FileReader(ruta);
			
			extraerDocumento(fr);
			
		} 
		catch (ExcepcionLexica e)
		{
			e.imprimirError();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		sustituirStrings();
		imprimirPublicaciones();
		return publicaciones;
	}

	/**
	 * Lee el tipo de publicación a procesar, así como sus campos. Después, genera la publicación.
	 * @param fr Fichero del que se va a leer.
	 * @throws IOException
	 * @throws ExcepcionLexica
	 */
	private void extraerDocumento(FileReader fr) throws IOException, ExcepcionLexica
	{
		char actual = siguienteCaracter(fr);
		while (actual != '\0')
		{
			if (actual == '%')
				saltarComentario(fr);
			else if (actual == '@')
			{
				String tipoDoc = extraerTipoDoc(fr);
				if (tipoDoc.equals("string"))
				{
					CampoString campoString = extraerCampoString(fr);
					strings.add(campoString);
				}
				else
				{
					LinkedList<CampoPublicacion> campos = extraerCampos(fr);
					generarDocumentoBibtex(tipoDoc, campos);
				}
			}
			else
				throw new ExcepcionLexica("El primer caracter encontrado no es '@' ni '%'.");
			actual = siguienteCaracter(fr);
		}
	}

	private CampoString extraerCampoString(FileReader fr) throws IOException, ExcepcionLexica 
	{
		char actual = siguienteCaracter(fr);
		String abrev = "";
		while (actual != '=')
		{
			abrev += actual;
			actual = siguienteCaracter(fr);
		}
		
		String texto = "";
		actual = siguienteCaracter(fr);
		if (actual == '"') //Comienza por comillas.
		{
			texto = copiarIntegroDesdeHasta(fr, texto, '"', '"');
			actual = siguienteCaracter(fr); //Pasamos el caracter '"'.
		}
		else
			if (actual == '{') //Comienza por llave.
			{
				texto = copiarIntegroDesdeHasta(fr, texto, '{', '}');
				actual = siguienteCaracter(fr); //Pasamos el caracter '}'.
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
			throw new ExcepcionLexica("El tipo de documento encontrado no es válido.");
	}

	/**
	 * Extrae los campos del documento.
	 * @param fr Fichero del que se va a leer.
	 * @return La lista de los campos extraídos.
	 * @throws IOException
	 * @throws ExcepcionLexica
	 */
	private LinkedList<CampoPublicacion> extraerCampos(FileReader fr) throws IOException, ExcepcionLexica 
	{
		LinkedList<CampoPublicacion> listaCampos = new LinkedList<CampoPublicacion>();
		
		boolean terminado;
		boolean posibleReferencia = true;
		CampoPublicacion nuevo;
		do
		{
			nuevo = extraerCampo(fr, posibleReferencia);
			if (!nuevo.getNombre().equals("referencia"))
			{
				nuevo.sustituirTildes();
				listaCampos.add(nuevo);
			}
			terminado = nuevo.getEsUltimo();
			posibleReferencia = false;
		}
		while (!terminado);
		return listaCampos;
		
	}

	/**
	 * Extrae un campo de un documento.
	 * @param fr Fichero del que se va a leer.
	 * @param posibleReferencia Indica si hay posibilidad de que lo que se extraiga sea la referencia de un documento, y no uno de sus campos.
	 * @return Un campo de una publicación.
	 * @throws ExcepcionLexica
	 * @throws IOException
	 */
	private CampoPublicacion extraerCampo(FileReader fr, boolean posibleReferencia) throws ExcepcionLexica, IOException 
	{
		String nombreCampo = "";
		String valorCampo = "";
		boolean esReferencia = false;
		char actual = siguienteCaracter(fr);
		if (actual != '}') //Hay campo.
		{
			if (!posibleReferencia)
				while (actual != '=')
				{
					nombreCampo += actual;
					actual = siguienteCaracter(fr);
				}
			else
			{
				while (actual != '=' && actual != ',')
				{
					nombreCampo += actual;
					actual = siguienteCaracter(fr);
				}
				if (actual == ',')
					esReferencia = true;
			}
			nombreCampo = nombreCampo.toLowerCase();
			if (!esReferencia)
			{
				actual = siguienteCaracter(fr); //Pasamos el '='.
				if (actual == '"') //Comienza por comillas.
				{
					valorCampo = copiarIntegroDesdeHasta(fr, valorCampo, '"', '"');
					actual = siguienteCaracter(fr); //Pasamos el caracter '"'.
				}
				else
					if (actual == '{') //Comienza por llave.
					{
						valorCampo = copiarIntegroDesdeHasta(fr, valorCampo, '{', '}');
						actual = siguienteCaracter(fr); //Pasamos el caracter '}'.
					}
					else //No comienza ni por '"' ni por '{'.
					{
						while (actual != ',' && actual != '}')
						{
							if (actual == '{')
							{
								valorCampo += '{';
								valorCampo = copiarIntegroDesdeHasta(fr, valorCampo, '{', '}');
								valorCampo += '}';
							}
							else
								valorCampo += actual;
							actual = siguienteCaracter(fr);
						}
					}
			}
			else
			{
				nombreCampo = "referencia";
				valorCampo = null;
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
	 * Copia íntegramente desde un fichero, hasta que encuentra un caracter determinado.
	 * @param fr Fichero del que se va a leer.
	 * @param valorCampo String inicial.
	 * @param desde Caracter anterior al punto desde donde se empezará a copiar. Sirve para permitir niveles.
	 * @param hasta Caracter que indicará que se tiene que dejar de copiar.
	 * @return String resultante.
	 * @throws IOException
	 */
	private String copiarIntegroDesdeHasta(FileReader fr, String valorCampo, char desde, char hasta) throws IOException 
	{
		String nuevoString = valorCampo;
		int nivel = 1;
		char actual = (char)fr.read();
		if (actual == hasta)
			nivel--;
		else
			if (actual == desde)
				nivel++;
		while (!(actual == hasta && nivel == 0))
		{
			nuevoString += actual;
			actual = (char)fr.read();
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
	 * @param fr Fichero desde el que se va a leer.
	 * @return El tipo de documento.
	 * @throws IOException
	 */
	private String extraerTipoDoc(FileReader fr) throws IOException {
		String s = "";
		char actual = siguienteCaracter(fr);
		while (actual != '{')
		{
			s += actual;
			actual = siguienteCaracter(fr);
		}
		return s.toLowerCase();
	}

	/**
	 * Lee el siguiente caracter desde un fichero. Omite tabulaciones, saltos de línea, espacios, ...
	 * @param fr Fichero desde el que se va a leer.
	 * @return El siguiente caracter.
	 * @throws IOException
	 */
	private char siguienteCaracter(FileReader fr) throws IOException
	{
		int num = fr.read();
		char c = (char)num;
		if (num == -1)
			return '\0';
		while (c == ' ' || c == '\t' || c == '\n' || c == '\r')
		{
			num = fr.read();
			c = (char)num;
			if (num == -1)
				return '\0';
		}
		return c;
	}


	private void saltarComentario(FileReader fr) throws IOException 
	{
		char actual = (char)fr.read();
		while(actual != '\n')
			actual = (char)fr.read();
	}
	
	public Publication getUltDoc() {
		return publicaciones.getLast();
	}
	
	public void imprimirPublicaciones()
	{
		Iterator<Publication> it = publicaciones.iterator();
		while (it.hasNext())
		{
			Publication p = it.next();
			p.imprimir();
		}
	}
	
	private void sustituirStrings()
	{
		Iterator<CampoString> itStrings = strings.iterator();
		Publication p;
		while (itStrings.hasNext())
		{
			CampoString c = itStrings.next();
			Iterator<Publication> itPubl = publicaciones.iterator();
			while (itPubl.hasNext())
			{
				p = itPubl.next();
				p.sustituir(c.getAbrev(), c.getTexto());
			}
		}
	}
}
