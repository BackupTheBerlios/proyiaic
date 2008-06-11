package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import controlador.exceptions.ExistenceException;
import controlador.exceptions.NonExistingElementException;

import parserFicherosBibtex.ConversorXML_Publication;
import personas.AutorEditor;
import personas.Usuario;
import publicaciones.Publication;
import database.BDException;
import database.BaseDatos;

public class ConversorXML_BBDD 
{
	private DataBaseControler dbc;
	
	private int tipoPublicaciones; //Solo para consultas.
	
	private String referencia;
	private String title;
	private int yearIni;
	private int yearFin;
	private String month;
	private String URL;
	//private String _abstract;
	private String note;
	private Vector<String> key;
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
	private String proyecto;
	private Vector<AutorEditor> authors; 
	private Vector<AutorEditor> editors;
	//private String user;
	
	public ConversorXML_BBDD()
	{
		dbc = new DataBaseControler(new BaseDatos());
		
		tipoPublicaciones = -1;
		
		referencia = null;
		title = null;
		yearIni = -1;
		yearFin = -1;
		month = null;
		URL = null;
//		_abstract = null;
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
		proyecto = null;
		authors = null;
		editors = null;
	}
	
	@SuppressWarnings("unchecked")
	public String procesarConsulta(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			tipoPublicaciones = Integer.parseInt(root.getAttributeValue("tipoPublicaciones"));
			if (!root.getAttributeValue("referencia").equals(""))
				referencia = root.getAttributeValue("referencia");
			List<Element> campos = (List<Element>)root.getChildren();

			Iterator<Element> it = campos.iterator();
			while (it.hasNext())
			{
				Element actual = it.next();
				if (!actual.getValue().equals(""))
					procesarCampo(actual);
			}

			//imprimir();
			return realizarConsulta();
		}
		catch(Exception e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de SAXBuilder: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarInsercion(InputStream input)
	{
		try
		{
			ConversorXML_Publication conv = new ConversorXML_Publication();
			Publication p = conv.convertir(input);
			return dbc.insertaDocumento(p);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarModificacion(InputStream input)
	{
		try
		{
			ConversorXML_Publication conv = new ConversorXML_Publication();
			Publication p = conv.convertir(input);
			return dbc.modificaDocumento(p);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}

	private void procesarCampo(Element campo) 
	{
		String nombreCampo = campo.getName();
		if (nombreCampo.equals("title"))
			title = campo.getValue();
		else if (nombreCampo.equals("yearIni"))
			yearIni = Integer.parseInt(campo.getValue());
		else if (nombreCampo.equals("yearFin"))
			yearFin = Integer.parseInt(campo.getValue());
		else if (nombreCampo.equals("month"))
			month = campo.getValue();
		else if (nombreCampo.equals("URL"))
			URL = campo.getValue();
//		else if (nombreCampo.equals("abstract"))
//			_abstract = campo.getValue();
		else if (nombreCampo.equals("note"))
			note = campo.getValue();
		else if (nombreCampo.equals("key"))
			key = separarKeys(campo.getValue());
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
			crossref = campo.getValue();//null;
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
		else if (nombreCampo.equals("proyecto"))
			proyecto = campo.getValue();
		else if (nombreCampo.equals("authors"))
			authors = procesarAutoresEditores(campo);
		else if (nombreCampo.equals("editors"))
			editors = procesarAutoresEditores(campo);
	}

	@SuppressWarnings("unchecked")
	private Vector<AutorEditor> procesarAutoresEditores(Element campo) 
	{
		List<Element> autoresEditores = campo.getChildren();
		Vector<AutorEditor> vectorAutoresEditores = new Vector<AutorEditor>();
		
		Element autorEditorActual;
		Iterator<Element> it = autoresEditores.iterator();
		while (it.hasNext())
		{
			autorEditorActual = it.next();
			Element nombre = autorEditorActual.getChild("nombre");
			Element apellidos = autorEditorActual.getChild("apellidos");
			
			AutorEditor aeNuevo = new AutorEditor(nombre.getValue(), apellidos.getValue());
			vectorAutoresEditores.add(aeNuevo);
		}
		
		return vectorAutoresEditores;
	}
	
	private Vector<String> separarKeys(String keys)
	{
		if (keys != null)
		{
			Vector<String> claves = new Vector<String>();
			String[] keysSep = keys.split(Publication.separador);
			int numKeys = keysSep.length;
			for (int i = 0; i < numKeys; i++)
				claves.add(keysSep[i]);
			return claves;
		}
		else return null;		
	}
	
	private String realizarConsulta()
	{
		try
		{
			Vector<String> years = null;
			if (yearIni != -1 && yearFin != -1)
			{
				years = new Vector<String>();
				for (int i = yearIni; i <= yearFin; i++)
					years.add("" + i);
			}

			Vector<Publication> vector = dbc.consultaDocumentos(null, proyecto, tipoPublicaciones, authors, editors, title, true, publisher, journal, years, volume, series, address, organization, school, booktitle, key, true, true, true, true, true, true, true, true, true, null);

			int numPublic = vector.size();
			Publication actual;
			Element root = new Element("listaPublicaciones");
			for(int i = 0; i < numPublic; i++)
			{
				actual = vector.get(i);
				Element elemento = actual.generarElementoXML(true);
				root.addContent(elemento);

			}
			XMLOutputter outputter = new XMLOutputter();
			return (outputter.outputString(new Document(root)));
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent(e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch (NonExistingElementException e) 
		{
			Element root = new Element("exception");
			if (e.getTipo() == ExistenceException.PROYECTO)
				root.addContent("Error: El proyecto no existe.");
			else
				root.addContent("Error desconocido al realizar la consulta.");
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	//Solo sirve para realizar pruebas.
	@SuppressWarnings("unused")
	private void imprimir() 
	{
		if (tipoPublicaciones != -1)
			System.out.println("TipoPublicaciones: " + tipoPublicaciones);
		if (referencia != null)
			System.out.println("Referencia: " + referencia);
		if (title != null)
			System.out.println("Title: " + title);
		if (yearIni != -1)
			System.out.println("Year inicial: " + yearIni);
		if (yearFin != -1)
			System.out.println("Year final: " + yearFin);
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
			}
		}
	}
	
	public String procesarNuevoUsuario(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String nombre = root.getChild("nombre").getValue();
			String pass = root.getChild("password").getValue();
			String tipo = root.getChild("tipo").getValue();
			String proyecto = root.getChild("proyecto").getValue();
			int tipoUser = -1;
			if (tipo.equals("user"))
				tipoUser = Usuario.USUARIO;
			else if (tipo.equals("jefe"))
				tipoUser = Usuario.JEFE;
			else if (tipo.equals("admin"))
				tipoUser = Usuario.ADMINISTRADOR;
			Usuario usuario = new Usuario(nombre, pass, tipoUser);

			if (proyecto.length() == 0)
				proyecto = null;
			return dbc.insertaUsuario(usuario, proyecto);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarNuevoProyecto(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String proyecto = root.getChild("proyecto").getValue();
			String jefe = root.getChild("jefe").getValue();

			return dbc.insertaProyecto(proyecto, jefe);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarEliminarUsuario(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String usuario = root.getChild("usuario").getValue();
			String nuevoUserPublicaciones = root.getChild("hereda").getValue();
			return dbc.eliminaUsuario(usuario, nuevoUserPublicaciones);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarAsociarUsuarioAProyecto(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String usuario = root.getChild("usuario").getValue();
			String proyecto = root.getChild("proyecto").getValue();
			return dbc.asociaUsuarioProyecto(proyecto, usuario);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarDesasociarUsuarioAProyecto(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String usuario = root.getChild("usuario").getValue();
			String proyecto = root.getChild("proyecto").getValue();
			return dbc.desasociaUsuarioProyecto(proyecto, usuario);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarAsociarPublicacionAProyecto(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String publicacion = root.getChild("publicacion").getValue();
			int idDoc = Integer.parseInt(publicacion);
			String proyecto = root.getChild("proyecto").getValue();
			return dbc.asociaDocumentoProyecto(proyecto, idDoc);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarDesasociarPublicacionAProyecto(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String publicacion = root.getChild("publicacion").getValue();
			int idDoc = Integer.parseInt(publicacion);
			String proyecto = root.getChild("proyecto").getValue();
			return dbc.desasociaDocumentoProyecto(proyecto, idDoc);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
	
	public String procesarFusionarAutoresEditores(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			String autor1 = root.getChild("autorEditor1").getValue();
			String autor2 = root.getChild("autorEditor2").getValue();
			return dbc.fusionarAutoresEditores(autor1, autor2);
		}
		catch(BDException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n al realizar Rollback: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(IOException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de entrada/salida: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
		catch(JDOMException e)
		{
			Element root = new Element("exception");
			root.addContent("Excepci�n de JDOM: " + e.getMessage());
			XMLOutputter outputter = new XMLOutputter();
			return outputter.outputString (new Document(root));
		}
	}
}
