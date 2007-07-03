package maker.vista.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

import maker.controlador.IControlador;
import maker.controlador.IHerramienta;
import maker.modelo.MapaEscenario;
import maker.recursos.SistemaCarga;
import maker.vista.editor.Acciones.EscuchaRatón;
import maker.vista.editor.Oyentes.OyenteAbrir;
import maker.vista.editor.Oyentes.OyenteCerrar;
import maker.vista.editor.Oyentes.OyenteDeshacer;
import maker.vista.editor.Oyentes.OyenteHerramientas;
import maker.vista.editor.Oyentes.OyenteNuevo;
import maker.vista.editor.Oyentes.OyenteNuevoMapa;
import maker.vista.editor.Oyentes.OyenteSalir;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import sonido.Reproductor;

/**
 * @author  David García
 */
public class Editor extends JFrame 
{
	public static final int PINTOR_LAPIZ = 101;
	public static final int PINTOR_CUADRADO = 102;
	public static final int PINTOR_BOTE = 103;
	private static final long serialVersionUID = 1L;
	
	/** Panel donde se representa el mapa. */
	private PanelMapa panel_mapa;
	
	/** Panel que contiene las barras superiores de botones. */
	private PanelBarras panel_barras;

	/** Directorio del proyecto abierto o creado en último lugar. */
	private File directorio;
	
	/** Botón de menú para la base de datos.  Esta aqui como atributo para poder banearlo. */
	private JMenuItem baseDeDatosMenuItem;
	
	/** Panel de scroll donde se selecciona a textura con la que se quiere pintar. */
	private JScrollPane selecciónTexturas;
	
	/** Panel que recoge toda la información sobre la textura a elegir, incluyendo la altura */
	private JPanel panelTexturas;
	
	/** Spinner para seleccionar la altura de la textura. */
	private JSpinner jsp_altura;

	/** Controlador del MVC. */
	private IControlador controller;
	
	/** Botón de menú para crear un nuevo mapa.  Esta aqui como atributo para poder banearlo. */
	private JMenuItem nuevomapa;
	
	/** Botón de menú para deshacer una acción. Esta aqui como atributo para poder banearlo. */
	private JMenuItem deshacer;
	
	/** Sistema que se encarga de almacenar todos los recursos del juego. */
	private SistemaCarga sistemacarga;
	
	/** Lista que contiene todas las texturas que se pueden elegir. */
	private ListaTexturas lista_tex;
	
	/** Reproductor de sonido. Se usa para detener la música o iniciarla. */
	private Reproductor reproductor;
	
	/** Esto no se que es. */
	private boolean panelCreado;
	
	public Editor(IControlador controller, File path) 
	{
		this.controller = controller;
		
		jsp_altura = new JSpinner();
		jsp_altura.setModel(new SpinnerNumberModel(0,0,10,1));
		reproductor = new Reproductor();
		lista_tex = null;
		
		setTitle("T-RPG Creator");
		setSize(800,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		panelCreado = false;

		crearMapa();
		
		crearbarraMenús();
		
		crearBarrasHerramientas();
		
		addWindowListener(new OyenteCerrar(this));
		
		if (path != null)
			abrir(path);
	}
	
	public void abrir(File path)
	{
		directorio = new File(path.getParent());	
		JOptionPane.showMessageDialog(this, "Abierto con exito el proyecto " + getDirectorio().getName(), "Información", JOptionPane.INFORMATION_MESSAGE);
		crearSistemaCarga();
		cargarXML(path);
		activarBotonesEdición();
	}

	private void cargarXML(File path) 
	{
		try
		{
			SAXBuilder sax = new SAXBuilder(false); 
			Document doc = sax.build(path);
			Element raiz = doc.getRootElement();
			
			//String titulo = raiz.getAttributeValue("Titulo");
			Element idmapa = raiz.getChild("ID");
			Element ultimo = raiz.getChild("Ultimo");
			MapaEscenario.setId(Integer.parseInt(idmapa.getText()));
			if (!ultimo.getText().equals("-1"))
			{
				controller.getModelo().cargar(new File(directorio.getAbsoluteFile() + "\\Mapas\\mapa" + ultimo.getText() + ".map"));
				activarBotonesPintura();
				mostrarPanelTexturas();
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void mostrarPanelTexturas() 
	{
		ListaTexturas listaTexturas = new ListaTexturas(this);

		if (!panelCreado)
			crearPanelElementos();
		crearPanelArbol(listaTexturas);
		setVisible(true);
		repaint();
	}

	public void guardarXML() 
	{
		Element raiz = new Element("Proyecto");
		raiz.setAttribute("titulo", "");
		Element idmapa = new Element("ID");
		idmapa.setText("" + MapaEscenario.getId());
		Element idultimo = new Element("Ultimo");
		if (controller.getModelo().getMapa() != null)
			idultimo.setText("" + controller.getModelo().getMapa().getIdentificador());
		else
			idultimo.setText("-1");
		raiz.addContent(idmapa);
		raiz.addContent(idultimo);
		
		try
		{
			Document doc = new Document(raiz);
			XMLOutputter out = new XMLOutputter();
			File f = new File(directorio + "\\project_data.srpg");
			FileOutputStream fis = new FileOutputStream(f);
			out.output(doc, fis);
			fis.flush();
			fis.close();
			out.output(doc, System.out);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void crearPanelArbol(ListaTexturas listaTexturas) 
	{
		lista_tex = listaTexturas;
		selecciónTexturas.getViewport().removeAll();
		selecciónTexturas.setViewportView(listaTexturas);
	}

	public void crearSistemaCarga()
	{
		sistemacarga = new SistemaCarga(this);
	}

	public void deshacer() 
	{
		boolean exito = controller.deshacer();
		panel_barras.activarDeshacer(exito);
		deshacer.setEnabled(exito);
	}

	public void activarBotonesEdición() 
	{
		baseDeDatosMenuItem.setEnabled(true);
		nuevomapa.setEnabled(true);
		panel_barras.activarBotonesDeEdiciónYPrueba(true);
	}

	public void activarBotonesPintura() 
	{
		panel_barras.activarBotonesDePintura(true);
	}

	public Integer getAltura() 
	{
		return (Integer)jsp_altura.getValue();
	}

	/**
	 * @return  the controller
	 */
	public IControlador getController() 
	{
		return controller;
	}

	/**
	 * @return  the directorio
	 */
	public File getDirectorio() {
		return directorio;
	}

	/**
	 * @return  the sistemacarga
	 */
	public SistemaCarga getSistemacarga() 
	{
		return sistemacarga;
	}

	public void hacer(IHerramienta herr) 
	{
		boolean exito = controller.hacer(herr);
		panel_barras.activarDeshacer(exito);
		deshacer.setEnabled(exito);
	}

	public void mostrar() 
	{
		setVisible(true);
	}

	public void actualizarListaTexturas() 
	{
		lista_tex = new ListaTexturas(this);
		selecciónTexturas.getViewport().removeAll();
		selecciónTexturas.setViewportView(lista_tex);
		repaint();
	}

	/**
	 * @param directorio  the directorio to set
	 */
	public void setDirectorio(File opened) {
		this.directorio = opened;
	}

	public void update(Graphics g)
	{
		paint(g); //El del padre.
	}
	
	public void paint(Graphics g)
	{
		paintComponents(g); //El del padre.
	}

	public void crearPanelElementos() 
	{
		panelCreado = true;
		JPanel panel_elementos = new JPanel(new GridLayout(0, 1));
		getContentPane().add(panel_elementos, BorderLayout.WEST);
		
		JPanel minipan = new JPanel(new BorderLayout());
		JLabel altura = new JLabel("  Altura  ");
		minipan.add(altura,BorderLayout.WEST);
		minipan.add(jsp_altura);
	
		selecciónTexturas = new JScrollPane();
		selecciónTexturas.setBackground(Color.WHITE);
		selecciónTexturas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		selecciónTexturas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		selecciónTexturas.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		selecciónTexturas.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panelTexturas = new JPanel(new BorderLayout());
		panelTexturas.add(selecciónTexturas);
		panel_elementos.add(panelTexturas);

		panelTexturas.add(minipan, BorderLayout.NORTH);
	
		/*tree = new JTree();
		tree.setBorder(new BevelBorder(BevelBorder.LOWERED));
		tree.setPreferredSize(new Dimension(160, 0));
		panel_elementos.add(tree);*/
	
	}

	private void crearbarraMenús() 
	{
		final JMenuBar barraMenús = new JMenuBar();
		setJMenuBar(barraMenús);

		final JMenu archivo = new JMenu();
		archivo.setText("Archivo");
		archivo.setMnemonic('A');
		barraMenús.add(archivo);
		
		final JMenuItem nuevoMenuItem = new JMenuItem();
		nuevoMenuItem.setText("Nuevo Proyecto");
		nuevoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		archivo.add(nuevoMenuItem);

		final JMenuItem abrirProyectoMenuItem = new JMenuItem();
		abrirProyectoMenuItem.setText("Abrir Proyecto");
		abrirProyectoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		archivo.add(abrirProyectoMenuItem);

		archivo.addSeparator();
		
		nuevomapa = new JMenuItem("Nuevo Mapa");
		nuevomapa.setEnabled(false);
		nuevomapa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		archivo.add(nuevomapa);
		
		archivo.addSeparator();

		final JMenuItem salir = new JMenuItem();
		salir.addActionListener(new OyenteSalir(this));
		salir.setText("Salir");
		archivo.add(salir);

		final JMenu edición = new JMenu("Edición");
		edición.setMnemonic('E');
		barraMenús.add(edición);
		
		deshacer = new JMenuItem("Deshacer");
		deshacer.setToolTipText("Deshace la última acción que haya sido llevada a cabo en el mapa.");
		edición.add(deshacer);
		deshacer.addActionListener(new OyenteDeshacer(this));
		deshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		deshacer.setEnabled(false);

		
		final JMenu herramientasMenu = new JMenu("Herramientas");
		herramientasMenu.setMnemonic('H');
		barraMenús.add(herramientasMenu);

		baseDeDatosMenuItem = new JMenuItem();
		baseDeDatosMenuItem.addActionListener(new OyenteHerramientas(this));
		baseDeDatosMenuItem.setText("Base de datos");
		baseDeDatosMenuItem.setEnabled(false); 
		herramientasMenu.add(baseDeDatosMenuItem);

		abrirProyectoMenuItem.addActionListener(new OyenteAbrir(this));
		nuevoMenuItem.addActionListener(new OyenteNuevo(this));
		nuevomapa.addActionListener(new OyenteNuevoMapa(this));
	}
	
	private void crearBarrasHerramientas() 
	{
		panel_barras = new PanelBarras(this);
		getContentPane().add(panel_barras, BorderLayout.NORTH);
	}

	private void crearMapa() 
	{
		panel_mapa = new PanelMapa(this);
		panel_mapa.setBorder(new BevelBorder(BevelBorder.LOWERED));
		getContentPane().add(panel_mapa, BorderLayout.CENTER);
	}

	public void cambiarEscucha(EscuchaRatón escucha) 
	{
		panel_mapa.cambiarEscucha(escucha);
	}

	public void setAreaSeleccionada(int i, int j, int k, int l) 
	{
		panel_mapa.setAreaSeleccionada(i, j, k, l);
	}

	public void reproducir(File f) 
	{
		try 
		{
			reproductor.repetir(new URL("file:" + f.getAbsolutePath()));
		}
		catch (MalformedURLException e) 
		{	
			e.printStackTrace();
		}
	}
	
	public void detener()
	{
		reproductor.detener();
	}

	public boolean panelCreado() 
	{
		return panelCreado;
	}

	public void vaciarPila() 
	{
		deshacer.setEnabled(false);
		panel_barras.activarDeshacer(false);
		controller.vaciarPila();
	}
}
