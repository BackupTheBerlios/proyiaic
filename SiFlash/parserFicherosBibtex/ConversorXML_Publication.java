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

/**
 * Sirve para convertir un fichero XML en una publicación.
 */
public class ConversorXML_Publication 
{
	public ConversorXML_Publication() {}

	/**
	 * Convierte un fichero XML en una publicación.
	 * @param input El fichero XML a procesar.
	 * @return La publicación resultante.
	 */
	@SuppressWarnings("unchecked")
	public Publication convertir(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc;
			doc = builder.build(input);

			Element root = doc.getRootElement();
			String tipoPublicacion = root.getAttributeValue("tipo");

			LinkedList<Campo> listaCampos = new LinkedList<Campo>();
			String referencia = root.getAttributeValue("referencia");
			if (referencia != null && !referencia.equals(""))
				listaCampos.add(new CampoPublicacion("referencia", referencia, false));
			String idDoc = root.getAttributeValue("idDoc");
			if (idDoc != null && !idDoc.equals(""))
				listaCampos.add(new CampoPublicacion("idDoc", idDoc, false));
			String DOI = root.getAttributeValue("DOI");
			if (DOI != null && !DOI.equals(""))
				listaCampos.add(new CampoPublicacion("DOI", DOI, false));

			List<Element> campos = root.getChildren();
			Iterator<Element> it = campos.iterator();
			Element e;
			String nombre, valor;

			while (it.hasNext())
			{
				e = it.next();
				nombre = e.getName();
				if (nombre.equalsIgnoreCase("authors") || nombre.equalsIgnoreCase("editors"))
				{
					LinkedList<AutorEditor> l = procesarAutorEditor(e);
					if (l != null)
						listaCampos.add(new CampoPublicacionAutorEditor(nombre, l));
				}
				else
				{
					valor = e.getValue();
					if (valor != null && !valor.equals(""))
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

	/**
	 * Procesa un elemento XML que contiene la información de uno o varios autores/editores.
	 * @param e Elemento XML a procesar.
	 * @return Una lista enlazada de autores/editores.
	 */
	@SuppressWarnings("unchecked")
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

			AutorEditor aeNuevo = new AutorEditor(nombre.getValue(), apellidos.getValue());
			listaAutoresEditores.add(aeNuevo);
		}
		if (listaAutoresEditores.isEmpty())
			return null;
		else
			return listaAutoresEditores;
	}

	/**
	 * Genera una publicación a partir de la información extraída.
	 * @param tipoP Tipo de publicación a generar (article, book, ...)
	 * @param listaCampos Lista de campos que forman parte de la publicación.
	 * @return La publicación resultante.
	 */
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
