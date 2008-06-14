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

/**
 * Clase que procesar� los XML entrantes, extrayendo su informaci�n asociada.
 */
public class ConversorXML_BBDD 
{
	/**
	 * Controlador de la base de datos al que se le pasar�n los datos extra�dos.
	 */
	private DataBaseControler dbc;
	
	/**
	 * S�lo usado en consultas: tipo de publicaciones a buscar.
	 */
	private int tipoPublicaciones;
	
	/**
	 * S�lo usado en consultas: referencia de las publicaciones a buscar.
	 */
	//private String referencia;
	/**
	 * S�lo usado en consultas: t�tulo de las publicaciones a buscar.
	 */
	private String title;
	/**
	 * S�lo usado en consultas: a�o m�nimo de las publicaciones a buscar.
	 */
	private int yearIni;
	/**
	 * S�lo usado en consultas: a�o m�ximo de las publicaciones a buscar.
	 */
	private int yearFin;
	/**
	 * S�lo usado en consultas: lista de keywords de las publicaciones a buscar.
	 */
	private Vector<String> key;
	/**
	 * S�lo usado en consultas: journal de las publicaciones a buscar.
	 */
	private String journal;
	/**
	 * S�lo usado en consultas: volume de las publicaciones a buscar.
	 */
	private String volume;
	/**
	 * S�lo usado en consultas: series de las publicaciones a buscar.
	 */
	private String series;
	/**
	 * S�lo usado en consultas: publisher de las publicaciones a buscar.
	 */
	private String publisher;
	/**
	 * S�lo usado en consultas: address de las publicaciones a buscar.
	 */
	private String address;
	/**
	 * S�lo usado en consultas: bookTitle de las publicaciones a buscar.
	 */
	private String booktitle;
	/**
	 * S�lo usado en consultas: organization de las publicaciones a buscar.
	 */
	private String organization;
	/**
	 * S�lo usado en consultas: school de las publicaciones a buscar.
	 */
	private String school;
	/**
	 * S�lo usado en consultas: proyecto de las publicaciones a buscar.
	 */
	private String proyecto;
	/**
	 * S�lo usado en consultas: lista de autores de las publicaciones a buscar.
	 */
	private Vector<AutorEditor> authors; 
	/**
	 * S�lo usado en consultas: lista de editores de las publicaciones a buscar.
	 */
	private Vector<AutorEditor> editors;
	
	/**
	 * Constructor por defecto: inicializa todos los atributos.
	 */
	public ConversorXML_BBDD()
	{
		dbc = new DataBaseControler(new BaseDatos());
		
		tipoPublicaciones = -1;
		
		title = null;
		yearIni = -1;
		yearFin = -1;
		key = null;
		journal = null;
		volume = null;
		series = null;
		publisher = null;
		address = null;
		booktitle = null;
		organization = null;
		school = null;
		proyecto = null;
		authors = null;
		editors = null;
	}
	
	/**
	 * Realiza una consulta en la base de datos.
	 * @param input Fichero XML de entrada con los par�metros especificados por el usuario.
	 * @return El resultado de la b�squeda en formato XML.
	 */
	@SuppressWarnings("unchecked")
	public String procesarConsulta(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			tipoPublicaciones = Integer.parseInt(root.getAttributeValue("tipoPublicaciones"));
			List<Element> campos = (List<Element>)root.getChildren();

			Iterator<Element> it = campos.iterator();
			while (it.hasNext())
			{
				Element actual = it.next();
				if (!actual.getValue().equals(""))
					procesarCampo(actual);
			}

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
	
	/**
	 * Inserta un nuevo documento en la base de datos.
	 * @param input Fichero XML con los datos del documento a insertar.
	 * @return El resultado de la inserci�n.
	 */
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
	
	/**
	 * Realiza la modificaci�n de una publicaci�n en la base de datos.
	 * @param input Fichero XML con los datos de la publicaci�n a modificar.
	 * @return El resultado de la operaci�n.
	 */
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

	/**
	 * A partir de un elemento XML de JDOM, establece el valor de uno de los par�metros de b�squeda.
	 * @param campo Elemento XML a procesar.
	 */
	private void procesarCampo(Element campo) 
	{
		String nombreCampo = campo.getName();
		if (nombreCampo.equals("title"))
			title = campo.getValue();
		else if (nombreCampo.equals("yearIni"))
			yearIni = Integer.parseInt(campo.getValue());
		else if (nombreCampo.equals("yearFin"))
			yearFin = Integer.parseInt(campo.getValue());
		else if (nombreCampo.equals("key"))
			key = separarKeys(campo.getValue());
		else if (nombreCampo.equals("journal"))
			journal = campo.getValue();
		else if (nombreCampo.equals("volume"))
			volume = campo.getValue();
		else if (nombreCampo.equals("series"))
			series = campo.getValue();
		else if (nombreCampo.equals("publisher"))
			publisher = campo.getValue();
		else if (nombreCampo.equals("address"))
			address = campo.getValue();
		else if (nombreCampo.equals("booktitle"))
			booktitle = campo.getValue();
		else if (nombreCampo.equals("organization"))
			organization = campo.getValue();
		else if (nombreCampo.equals("school"))
			school = campo.getValue();
		else if (nombreCampo.equals("proyecto"))
			proyecto = campo.getValue();
		else if (nombreCampo.equals("authors"))
			authors = procesarAutoresEditores(campo);
		else if (nombreCampo.equals("editors"))
			editors = procesarAutoresEditores(campo);
	}

	/**
	 * Procesa un elemento XML de JDOM que contiene una lista de autores/editores.
	 * @param campo Elemento XML a procesar.
	 * @return Una lista con todos los autores/editores extra�dos.
	 */
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
	
	/**
	 * Separa las keywords introducidas por el usuario.
	 * @param keys Keywords a separar.
	 * @return Una lista con todas las keywords por separado.
	 */
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
	
	/**
	 * Realiza una consulta a partir de los par�metros de b�squeda (atributos de la clase)
	 * @return El resultado de la consulta.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de un nuevo usuario para insertarlo en la base de datos.
	 * @param input Fichero XML con los datos del nuevo usuario.
	 * @return El resultado de la inserci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de un nuevo proyecto para insertarlo en la base de datos.
	 * @param input Fichero XML con los datos del nuevo proyecto.
	 * @return El resultado de la inserci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de un usuario a eliminar de la base de datos.
	 * @param input Fichero XML con los datos del usuario a eliminar.
	 * @return El resultado de la eliminaci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de una nueva vinculaci�n entre un usuario y un proyecto.
	 * @param input Fichero XML con los datos de la nueva vinculaci�n.
	 * @return El resultado de la vinculaci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de una desvinculaci�n entre un usuario y un proyecto.
	 * @param input Fichero XML con los datos de la desvinculaci�n.
	 * @return El resultado de la desvinculaci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de una nueva vinculaci�n entre una publicaci�n y un proyecto.
	 * @param input Fichero XML con los datos de la nueva vinculaci�n.
	 * @return El resultado de la vinculaci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de una desvinculaci�n entre una publicaci�n y un proyecto.
	 * @param input Fichero XML con los datos de la desvinculaci�n.
	 * @return El resultado de la desvinculaci�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de una fusi�n de dos autores/editores.
	 * @param input Fichero XML con los datos de la fusi�n.
	 * @return El resultado de la fusi�n.
	 */
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
	
	/**
	 * Procesa un fichero XML con los datos de una modificaci�n de un autor/editor.
	 * @param input Fichero XML con los datos de la modificaci�n.
	 * @return El resultado de la modificaci�n.
	 */
	public String procesarModificarAutor(InputStream input)
	{
		try
		{
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root = doc.getRootElement();
			int idAut = Integer.parseInt(root.getAttributeValue("idAut"));
			String nombre = root.getChild("nombre").getValue();
			String apellidos = root.getChild("apellidos").getValue();
			return dbc.modificaAutor(idAut, nombre, apellidos);
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
