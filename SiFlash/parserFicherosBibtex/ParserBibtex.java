package parserFicherosBibtex;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import parserFicherosBibtex.excepciones.ExcepcionLexica;

import publicaciones.*;


public class ParserBibtex 
{
	public ParserBibtex()
	{
	}
	
	/*public void procesar(String ruta)
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
		Publication docNuevo;
		if (tipoDoc.equals("article")){
			docNuevo = new Article(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("book")){
			docNuevo = new Book(campos);
			docNuevo.imprimir();	
		}
		else if (tipoDoc.equals("booklet")){
			docNuevo = new Booklet(campos);	
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("conference")){
			docNuevo = new Conference(campos);
			docNuevo.imprimir();	
		}
		else if (tipoDoc.equals("inbook")){
			docNuevo = new InBook(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("incollection")){
			docNuevo = new InCollection(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("inproceeding")){
			docNuevo = new InProceedings(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("manual")){
			docNuevo = new Manual(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("mastersthesis")){
			docNuevo = new MastersThesis(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("misc")){
			docNuevo = new Misc(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("phdthesis")){
			docNuevo = new PhdThesis(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("proceeding")){
			docNuevo = new Proceedings(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("techreport")){
			docNuevo = new TechReport(campos);
			docNuevo.imprimir();
		}
		else if (tipoDoc.equals("unpublished")){
			docNuevo = new Unpublished(campos);
			docNuevo.imprimir();
		}
		else
			throw new ExcepcionLexica("El tipo de documento encontrado no es válido.");
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
		
	}*/

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
			valorString = actual == '"';
			if (!valorString)
			{
				valorCampo += actual;
				actual = siguienteCaracter(fr);
				while(actual != ',' && actual != '}')
				{
					valorCampo += actual;
					actual = siguienteCaracter(fr);
				}
			}
			else
			{
				actual = (char)fr.read();
				while (actual != '"')
				{
					valorCampo += actual;
					actual = (char)fr.read(); //Dentro de las comillas no saltamos espacios.
				}
				actual = siguienteCaracter(fr); //Pasamos las '"'.
				if (actual != ',' && actual != '}')
					throw new ExcepcionLexica("Caracter \",\" o \"}\" esperado.");
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
	
}
