package parserFicherosBibtex;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

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
	private Publication ultDoc;
	
	public ParserBibtex()
	{
	}
	
	public void procesar(String ruta)
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
	}

	private void extraerDocumento(FileReader fr) throws IOException, ExcepcionLexica
	{
		char actual = siguienteCaracter(fr);
		if (actual != '@')
			throw new ExcepcionLexica("El primer caracter encontrado no es '@'.");

		String tipoDoc = extraerTipoDoc(fr);
		
		LinkedList<Campo> campos = extraerCampos(fr);
		
		generarDocumentoBibtex(tipoDoc, campos);
	}

	private void generarDocumentoBibtex(String tipoDoc, LinkedList<Campo> campos) throws ExcepcionLexica 
	{
		if (tipoDoc.equals("article")){
			ultDoc = new Article(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("book")){
			ultDoc = new Book(campos);
			ultDoc.imprimir();	
		}
		else if (tipoDoc.equals("booklet")){
			ultDoc = new Booklet(campos);	
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("conference")){
			ultDoc = new Conference(campos);
			ultDoc.imprimir();	
		}
		else if (tipoDoc.equals("inbook")){
			ultDoc = new InBook(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("incollection")){
			ultDoc = new InCollection(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("inproceeding")){
			ultDoc = new InProceedings(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("manual")){
			ultDoc = new Manual(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("mastersthesis")){
			ultDoc = new MastersThesis(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("misc")){
			ultDoc = new Misc(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("phdthesis")){
			ultDoc = new PhdThesis(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("proceeding")){
			ultDoc = new Proceedings(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("techreport")){
			ultDoc = new TechReport(campos);
			ultDoc.imprimir();
		}
		else if (tipoDoc.equals("unpublished")){
			ultDoc = new Unpublished(campos);
			ultDoc.imprimir();
		}
		else
			throw new ExcepcionLexica("El tipo de documento encontrado no es v�lido.");
	}

	private LinkedList<Campo> extraerCampos(FileReader fr) throws IOException, ExcepcionLexica 
	{
		LinkedList<Campo> listaCampos = new LinkedList<Campo>();
		
		boolean terminado;
		boolean posibleKey = true;
		Campo nuevo;
		do
		{
			nuevo = extraerCampo(fr, posibleKey);
			listaCampos.add(nuevo);
			terminado = nuevo.getEsUltimo();
			posibleKey = false;
		}
		while (!terminado);
		return listaCampos;
		
	}

	private Campo extraerCampo(FileReader fr, boolean posibleKey) throws ExcepcionLexica, IOException 
	{
		String nombreCampo = "";
		String valorCampo = "";
		boolean valorString;
		boolean esKey = false;
		char actual = siguienteCaracter(fr);
		
		if (!posibleKey)
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
				esKey = true;
		}
		nombreCampo = nombreCampo.toLowerCase();
		if (!esKey)
		{
			actual = siguienteCaracter(fr); //Pasamos el '='.
			valorString = !(nombreCampo.equals("year"));
			if (!valorString) //Es un n�mero.
			{
				if (actual == '"') //Comienza por comillas.
				{
					actual = siguienteCaracter(fr); //Pasamos las comillas.
					while(actual != '"')
					{
						valorCampo += actual;
						actual = siguienteCaracter(fr);
					}
					actual = siguienteCaracter(fr); //Pasamos las comillas.
				}
				else //No lleva comillas.
				{
					valorCampo += actual;
					actual = siguienteCaracter(fr);
					while(actual != ',' && actual != '}')
					{
						valorCampo += actual;
						actual = siguienteCaracter(fr);
					}
				}
			}
			else //Es un String.
			{
				if (actual == '"') //Comienza por comillas.
				{
					actual = (char)fr.read();
					while (actual != '"')
					{
						if (actual == '{')
							valorCampo = copiarIntegroDesdeHasta(fr, valorCampo, '{', '}');
						else
							valorCampo += actual;
						actual = (char)fr.read(); //Dentro de las comillas no saltamos espacios.
					}
					actual = siguienteCaracter(fr); //Pasamos las '"'.
				}
				else
					if (actual == '{') //Comienza por llave.
						valorCampo = copiarIntegroDesdeHasta(fr, valorCampo, '{', '}');
					else
						throw new ExcepcionLexica("Caracter '\"' o '}' esperado.");
			}
		}
		else
		{
			valorCampo = nombreCampo;
			nombreCampo = "key";
			valorString = true;
		}

		boolean ultimoCampo = actual == '}';
		Campo campoNuevo;
		if (valorString)
			campoNuevo = new Campo(nombreCampo, valorCampo, ultimoCampo);
		else
		{
			int valorCampoInt = Integer.parseInt(valorCampo);
			campoNuevo = new Campo(nombreCampo, valorCampoInt, ultimoCampo);
		}
		return campoNuevo;
	}

	private String copiarIntegroDesdeHasta(FileReader fr, String valorCampo, char desde, char hasta) throws IOException 
	{
		String nuevoString = valorCampo;
		int nivel = 1;
		char actual = (char)fr.read();
		if (actual == desde)
			nivel++;
		else
			if (actual == hasta)
				nivel--;
		while (!(actual == hasta && nivel == 0))
		{
			nuevoString += actual;
			actual = (char)fr.read();
			if (actual == desde)
				nivel++;
			else
				if (actual == hasta)
					nivel--;
		}
		actual = siguienteCaracter(fr); //Pasamos el caracter "hasta".
		return nuevoString;
	}

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

	private char siguienteCaracter(FileReader fr) throws IOException
	{
		char c = (char)fr.read();
		while (c == ' ' || c == '\t' || c == '\n' || c == '\r')
			c = (char)fr.read();
		return c;
	}

	public Publication getUltDoc() {
		return ultDoc;
	}
	
}
