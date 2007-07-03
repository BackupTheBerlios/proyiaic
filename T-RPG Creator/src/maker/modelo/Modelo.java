package maker.modelo;

import java.io.File;
import java.io.FileOutputStream;

import maker.controlador.Controlador;
import maker.controlador.IControlador;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * @author David García
 */
public class Modelo implements IModelo {
	private IControlador controller;

	private MapaEscenario mapaEscenario;

	public Modelo(Controlador con) {
		this.controller = con;
	}

	public IControlador getControlador() {
		return controller;
	}

	public void registrarControlador(IControlador controller) {
		this.controller = controller;
	}

	/**
	 * @return the mapa
	 */
	public MapaAbstracto getMapa() {
		return mapaEscenario;
	}

	public void crearMapa(int filas, int columnas, String nombre) {
		mapaEscenario = new MapaEscenario(filas, columnas, nombre);
	}

	public void guardar(File file) 
	{
		File directorio = new File(file.getAbsolutePath() + "\\Mapas\\mapa"
				+ mapaEscenario.getIdentificador() + ".map");
		Element raiz = new Element("Mapa");
		raiz.setAttribute("Titulo", mapaEscenario.getNombre());
		raiz.setAttribute("Filas", "" + mapaEscenario.getFilas());
		raiz.setAttribute("Columnas", "" + mapaEscenario.getColumnas());
		raiz.setAttribute("ID", "" + mapaEscenario.getIdentificador());

		for (int i = 0; i < mapaEscenario.getFilas(); i++)
			for (int j = 0; j < mapaEscenario.getColumnas(); j++)
			{
				Element casilla = new Element("Casilla" + i + "_" + j);
				casilla.setAttribute("Altura", "" + mapaEscenario.getAltura(i, j));
				casilla.setAttribute("Textura", "" + mapaEscenario.getTextura(i, j));
				raiz.addContent(casilla);
			}
		
		try
		{
			Document doc = new Document(raiz);
			XMLOutputter out = new XMLOutputter();
			FileOutputStream fis = new FileOutputStream(directorio);
			out.output(doc, fis);
			fis.flush();
			fis.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void cargar(File f) 
	{
		try
		{
			SAXBuilder sax = new SAXBuilder(false); 
			Document doc = sax.build(f);
			Element raiz = doc.getRootElement();
			
			int filas = Integer.parseInt(raiz.getAttribute("Filas").getValue());
			int columnas = Integer.parseInt(raiz.getAttribute("Columnas").getValue());
			int id = Integer.parseInt(raiz.getAttribute("ID").getValue());
			String titulo = raiz.getAttributeValue("Titulo");
			mapaEscenario = new MapaEscenario(filas, columnas, titulo);
			MapaEscenario.setId(MapaEscenario.getId() - 1);
			mapaEscenario.setIdentificador(id);
			
			for (int i = 0; i < filas; i++) 
				for (int j = 0; j < columnas; j++) 
				{
					Element elem = raiz.getChild("Casilla" + i + "_" + j);
					int textura = Integer.parseInt(elem.getAttributeValue("Textura"));
					int altura = Integer.parseInt(elem.getAttributeValue("Altura"));
					mapaEscenario.setTextura(i, j, altura, textura);
				}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void crearMapaCaminos(String nombre) 
	{
		
	}

}
