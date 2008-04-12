package controlador;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import personas.AutorEditor;

public class ConversorBusquedas 
{
	private int tipoPublicaciones;
	
	private String referencia;
	private String title;
	private String year;
	private String month;
	private String URL;
	private String note;
	private String key;
	private String journal;
	private String volume;
	private String number;
	private String pages;
	private String series;
	private String publisher;
	private String address;
	private String edition;
	private String howPublished;
	private String booktitle;
	private String crossref;
	private String organization;
	private String chapter;
	private String type;
	private String school;
	private String institution;
	private Vector<AutorEditor> authors; 
	private Vector<AutorEditor> editors;
	
	public ConversorBusquedas()
	{
		tipoPublicaciones = -1;
		referencia = null;
		title = null;
		year = null;
		month = null;
		URL = null;
		note = null;
		key = null;
		journal = null;
		volume = null;
		number = null;
		pages = null;
		series = null;
		publisher = null;
		address = null;
		edition = null;
		howPublished = null;
		booktitle = null;
		crossref = null;
		organization = null;
		chapter = null;
		type = null;
		school = null;
		institution = null;
		authors = null;
		editors = null;
	}
	
	public void procesar(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			tipoPublicaciones = Integer.parseInt(root.getAttributeValue("tipoPublicaciones"));
			referencia = root.getAttributeValue("referencia");
			List campos = root.getChildren();
			
			Iterator<Element> it = campos.iterator();
			while (it.hasNext())
				procesarCampo(it.next());
			
			imprimir();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void procesarCampo(Element campo) 
	{
		String nombreCampo = campo.getName();
		if (nombreCampo.equals("title"))
			title = campo.getValue();
		else if (nombreCampo.equals("year"))
			year = campo.getValue();
		else if (nombreCampo.equals("month"))
			month = campo.getValue();
		else if (nombreCampo.equals("URL"))
			URL = campo.getValue();
		else if (nombreCampo.equals("note"))
			note = campo.getValue();
		else if (nombreCampo.equals("key"))
			key = campo.getValue();
		else if (nombreCampo.equals("journal"))
			journal = campo.getValue();
		else if (nombreCampo.equals("volume"))
			volume = campo.getValue();
		else if (nombreCampo.equals("number"))
			number = campo.getValue();
		else if (nombreCampo.equals("pages"))
			pages = campo.getValue();
		else if (nombreCampo.equals("series"))
			series = campo.getValue();
		else if (nombreCampo.equals("publisher"))
			publisher = campo.getValue();
		else if (nombreCampo.equals("address"))
			address = campo.getValue();
		else if (nombreCampo.equals("edition"))
			edition = campo.getValue();
		else if (nombreCampo.equals("howPublished"))
			howPublished = campo.getValue();
		else if (nombreCampo.equals("booktitle"))
			booktitle = campo.getValue();
		else if (nombreCampo.equals("crossref"))
			crossref = campo.getValue();
		else if (nombreCampo.equals("organization"))
			organization = campo.getValue();
		else if (nombreCampo.equals("chapter"))
			chapter = campo.getValue();
		else if (nombreCampo.equals("type"))
			type = campo.getValue();
		else if (nombreCampo.equals("school"))
			school = campo.getValue();
		else if (nombreCampo.equals("institution"))
			institution = campo.getValue();
		else if (nombreCampo.equals("authors"))
			authors = procesarAutoresEditores(campo);
		else if (nombreCampo.equals("editors"))
			editors = procesarAutoresEditores(campo);
	}

	private Vector<AutorEditor> procesarAutoresEditores(Element campo) 
	{
		List autoresEditores = campo.getChildren();
		Vector<AutorEditor> vectorAutoresEditores = new Vector<AutorEditor>();
		
		Element autorEditorActual;
		Iterator<Element> it = autoresEditores.iterator();
		while (it.hasNext())
		{
			autorEditorActual = it.next();
			Element nombre = autorEditorActual.getChild("nombre");
			Element apellidos = autorEditorActual.getChild("apellidos");
			Element web = autorEditorActual.getChild("web");
			
			AutorEditor aeNuevo = new AutorEditor(nombre.getValue(), apellidos.getValue(), web.getValue());
			vectorAutoresEditores.add(aeNuevo);
		}
		
		return vectorAutoresEditores;
	}
	
	private void realizarConsulta() 
	{
		
	}
	
	//Solo sirve para realizar pruebas.
	private void imprimir() 
	{
		if (tipoPublicaciones != -1)
			System.out.println("TipoPublicaciones: " + tipoPublicaciones);
		if (referencia != null)
			System.out.println("Referencia: " + referencia);
		if (title != null)
			System.out.println("Title: " + title);
		if (year != null)
			System.out.println("Year: " + year);
		if (month != null)
			System.out.println("Month: " + month);
		if (URL != null)
			System.out.println("URL: " + URL);
		if (note != null)
			System.out.println("Note: " + note);
		if (key != null)
			System.out.println("Key: " + key);
		if (journal != null)
			System.out.println("Journal: " + journal);
		if (volume != null)
			System.out.println("Volume: " + volume);
		if (number != null)
			System.out.println("Number: " + number);
		if (pages != null)
			System.out.println("Pages: " + pages);
		if (series != null)
			System.out.println("Series: " + series);
		if (publisher != null)
			System.out.println("Publisher: " + publisher);
		if (address != null)
			System.out.println("Address: " + address);
		if (edition != null)
			System.out.println("Edition: " + edition);
		if (howPublished != null)
			System.out.println("HowPublished: " + howPublished);
		if (booktitle != null)
			System.out.println("Booktitle: " + booktitle);
		if (crossref != null)
			System.out.println("Crossref: " + crossref);
		if (organization != null)
			System.out.println("Organization: " + organization);
		if (chapter != null)
			System.out.println("Chapter: " + chapter);
		if (type != null)
			System.out.println("Type: " + type);
		if (school != null)
			System.out.println("School: " + school);
		if (institution != null)
			System.out.println("Institution: " + institution);
		if (authors != null)
		{
			System.out.println("Authors:");
			int numAuthors = authors.size();
			for (int i = 0; i < numAuthors; i++)
			{
				System.out.println("  Author:");
				if (authors.elementAt(i).getNombre() != null)
					System.out.println("  - Nombre: " + authors.elementAt(i).getNombre());
				if (authors.elementAt(i).getApellidos() != null)
					System.out.println("  - Apellidos: " + authors.elementAt(i).getApellidos());
				if (authors.elementAt(i).getWeb() != null)
					System.out.println("  - Web: " + authors.elementAt(i).getWeb());
			}
		}
		if (editors != null)
		{
			System.out.println("Editors:");
			int numEditors = editors.size();
			for (int i = 0; i < numEditors; i++)
			{
				System.out.println("  Editor:");
				if (authors.elementAt(i).getNombre() != null)
					System.out.println("  - Nombre: " + editors.elementAt(i).getNombre());
				if (authors.elementAt(i).getApellidos() != null)
					System.out.println("  - Apellidos: " + editors.elementAt(i).getApellidos());
				if (authors.elementAt(i).getWeb() != null)
					System.out.println("  - Web: " + editors.elementAt(i).getWeb());
			}
		}
	}
}
