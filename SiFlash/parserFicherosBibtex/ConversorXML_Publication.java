package parserFicherosBibtex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import personas.AutorEditor;
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

public class ConversorXML_Publication 
{
	public ConversorXML_Publication() {}
	
	public Publication convertir(InputStream input)
	{
		try 
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			doc = builder.build(input);
			Element root = doc.getRootElement();
			String tipoPublicacion = root.getAttributeValue("tipoPublicacion");
			
			LinkedList<Campo> listaCampos = new LinkedList<Campo>();
			String referencia = root.getAttributeValue("referencia");
			if (referencia != null)
				listaCampos.add(new CampoPublicacion("referencia", referencia, false));
			
			List<Element> campos = root.getChildren();
			Iterator<Element> it = campos.iterator();
			Element e;
			String nombre, valor;
			while (it.hasNext())
			{
				e = it.next();
				nombre = e.getName();
				if (nombre.equalsIgnoreCase("authors") || nombre.equalsIgnoreCase("editors"))
					listaCampos.add(new CampoPublicacionAutorEditor(nombre, procesarAutorEditor(e)));
				else
				{
					valor = e.getValue();
					listaCampos.add(new CampoPublicacion(nombre, valor, false));
				}
			}
			
			Publication p = generarPublicacion(tipoPublicacion, listaCampos);
			return p;
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private LinkedList<AutorEditor> procesarAutorEditor(Element e) 
	{
		List<Element> autoresEditores = e.getChildren();
		LinkedList<AutorEditor> listaAutoresEditores = new LinkedList<AutorEditor>();

		Element autorEditorActual;
		Iterator<Element> it = autoresEditores.iterator();
		while (it.hasNext())
		{
			autorEditorActual = it.next();
			Element nombre = autorEditorActual.getChild("nombre");
			Element apellidos = autorEditorActual.getChild("apellidos");
			Element web = autorEditorActual.getChild("web");

			AutorEditor aeNuevo = new AutorEditor(nombre.getValue(), apellidos.getValue(), web.getValue());
			listaAutoresEditores.add(aeNuevo);
		}

		return listaAutoresEditores;
	}

	private Publication generarPublicacion(String tipoP, LinkedList<Campo> listaCampos) 
	{
		if (tipoP.equalsIgnoreCase("article"))
			return new Article(listaCampos);
		else if (tipoP.equalsIgnoreCase("book"))
			return new Book(listaCampos);
		else if (tipoP.equalsIgnoreCase("booklet"))
			return new Booklet(listaCampos);
		else if (tipoP.equalsIgnoreCase("conference"))
			return new Conference(listaCampos);
		else if (tipoP.equalsIgnoreCase("inBook"))
			return new InBook(listaCampos);
		else if (tipoP.equalsIgnoreCase("inCollection"))
			return new InCollection(listaCampos);
		else if (tipoP.equalsIgnoreCase("inProceedings"))
			return new InProceedings(listaCampos);
		else if (tipoP.equalsIgnoreCase("manual"))
			return new Manual(listaCampos);
		else if (tipoP.equalsIgnoreCase("mastersThesis"))
			return new MastersThesis(listaCampos);
		else if (tipoP.equalsIgnoreCase("misc"))
			return new Misc(listaCampos);
		else if (tipoP.equalsIgnoreCase("phdThesis"))
			return new PhdThesis(listaCampos);
		else if (tipoP.equalsIgnoreCase("proceedings"))
			return new Proceedings(listaCampos);
		else if (tipoP.equalsIgnoreCase("techReport"))
			return new TechReport(listaCampos);
		else if (tipoP.equalsIgnoreCase("unpublished"))
			return new Unpublished(listaCampos);
		else return null;
	}
}