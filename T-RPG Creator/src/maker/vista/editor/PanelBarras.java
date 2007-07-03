package maker.vista.editor;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import maker.vista.editor.Oyentes.OyenteAbrir;
import maker.vista.editor.Oyentes.OyenteAbrirMapa;
import maker.vista.editor.Oyentes.OyenteBotePintura;
import maker.vista.editor.Oyentes.OyenteDeshacer;
import maker.vista.editor.Oyentes.OyenteGoma;
import maker.vista.editor.Oyentes.OyenteGuardarMapa;
import maker.vista.editor.Oyentes.OyenteImportar;
import maker.vista.editor.Oyentes.OyenteLapiz;
import maker.vista.editor.Oyentes.OyenteMostrarSonido;
import maker.vista.editor.Oyentes.OyenteNuevo;
import maker.vista.editor.Oyentes.OyenteNuevoMapa;
import maker.vista.editor.Oyentes.OyentePlay;
import maker.vista.editor.Oyentes.OyentePreview;
import maker.vista.editor.Oyentes.OyenteRectangulo;
import maker.vista.editor.Oyentes.OyenteTexturas;

/**
 * @author  David García
 */
public class PanelBarras extends JPanel 
{
	private static final long serialVersionUID = 1L;

	private Editor editor;

	private JToggleButton texturasBoton;

	private JToggleButton objetosBoton;

	private JButton play;

	private JToggleButton lapiz;

	private JToggleButton rect;

	private JToggleButton boteDePintura;

	private JToggleButton goma;

	private JButton guardarBoton;

	private JButton importarButton;

	private JButton deshacer;

	private JButton sonido;

	private JButton nuevoMapa;

	private JButton abrirMapa;

	private ClassLoader cl;

	private JButton preview;

	public PanelBarras(Editor editor)
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.editor = editor;
		
		cl = this.getClass().getClassLoader();
		
		crearBarraArchivo();
		crearBarraMapas();
		crearBarraEdición();
		crearBarraPaletas();
		crearBarraPinceles();
		crearBarraCaminos();
		crearBarraBaseDatos();
		crearBarraEjecucion();
		
		activarBotonesDePintura(false);
		activarBotonesDeEdiciónYPrueba(false);
		activarDeshacer(false);
	}

	private void crearBarraCaminos() 
	{
		final JToolBar barraCaminos = new JToolBar();
		add(barraCaminos);
		
		añadirBoton(barraCaminos, null, "Añade un nuevo nodo al mapa de caminos. (Para añadir ciudades, mazmorras, tiendas, ...)", 
					"nuevoNodo.png", "nuevoNodo2.png", true);
	}

	private void crearBarraArchivo() 
	{
		final JToolBar barraArchivo = new JToolBar();
		add(barraArchivo);
	
		añadirBoton(barraArchivo, new OyenteNuevo(editor), "Crea un nuevo proyecto", "nuevo.png", 
					"nuevo2.png", false);
			
		añadirBoton(barraArchivo, new OyenteAbrir(editor), "Abre un proyecto guardado anteriormente", 
					"abrir.png", "abrir2.png", false);
	}

	private void crearBarraMapas() 
	{
		JToolBar barraMapas = new JToolBar();
		add(barraMapas);
		
		nuevoMapa = (JButton)añadirBoton(barraMapas, new OyenteNuevoMapa(editor), "Crea un nuevo mapa.", 
					"nuevoMapa.png", "nuevoMapa2.png", false);
		
		abrirMapa = (JButton)añadirBoton(barraMapas, new OyenteAbrirMapa(editor), "Abre un mapa guardado con anterioridad.", 
					"abrirMapa.png", "abrirMapa2.png", false);
		
		guardarBoton = (JButton)añadirBoton(barraMapas, new OyenteGuardarMapa(editor), "Guarda el mapa actual. Los últimos cambios no podrán deshacerse.", 
						"guardar.png", "guardar2.png", false);
	}

	private void crearBarraEdición() 
	{
		JToolBar barraEdicion = new JToolBar();
		add(barraEdicion);

		deshacer = (JButton)añadirBoton(barraEdicion, new OyenteDeshacer(editor), "Deshace la última acción que haya sido llevada a cabo en el mapa.", 
				"undo.png", "undo2.png", false);
	}

	private void crearBarraPaletas() 
	{
		final JToolBar barraPaleta = new JToolBar();
		add(barraPaleta);
	
		texturasBoton = (JToggleButton)añadirBoton(barraPaleta, new OyenteTexturas(editor), "Pintar texturas.", 
						"textura.png", "textura2.png", true);
		texturasBoton.setSelected(true);
	
		objetosBoton = (JToggleButton)añadirBoton(barraPaleta, new OyenteTexturas(editor), "Añadir objetos.", 
					   "objetos.png", "objetos2.png", true);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(texturasBoton);
		bg.add(objetosBoton);
	}

	private void crearBarraPinceles() 
	{
		JToolBar barraPinceles = new JToolBar();
		add(barraPinceles);
	
		lapiz = (JToggleButton)añadirBoton(barraPinceles, new OyenteLapiz(editor), "Pintar en modo lápiz.", 
				 "lapiz.png", "lapiz2.png", true);
	
		rect = (JToggleButton)añadirBoton(barraPinceles, new OyenteRectangulo(editor), "Pintar en modo rectángulo.", 
				 "rectangulo.png", "rectangulo2.png", true);
		
		boteDePintura = (JToggleButton)añadirBoton(barraPinceles, new OyenteBotePintura(editor), "Pintar con el bote de pintura.", 
				 "bote.png", "bote2.png", true);
		
		goma = (JToggleButton)añadirBoton(barraPinceles, new OyenteGoma(editor), "Borrar la textura de una casilla.", 
				 "goma.png", "goma2.png", true);
			
		ButtonGroup bg = new ButtonGroup();
		lapiz.setSelected(true);
		bg.add(lapiz);
		bg.add(goma);
		bg.add(boteDePintura);
		bg.add(rect);
	}

	private void crearBarraBaseDatos() 
	{
		JToolBar barraBaseDatos = new JToolBar();
		add(barraBaseDatos);

		importarButton = (JButton)añadirBoton(barraBaseDatos, new OyenteImportar(editor), "Importa nuevas imagenes y sonidos para tus objetos, personajes, enemigos, texturas...", 
						  "gestorArchivos.png", "gestorArchivos2.png", false);
		
		sonido = (JButton)añadirBoton(barraBaseDatos, new OyenteMostrarSonido(editor), "Prueba los sonidos que hayas importado.", 
				  "sonido.png", "sonido2.png", false);
	}

	private void crearBarraEjecucion() 
	{
		JToolBar barraEjecución = new JToolBar();
		add(barraEjecución);

		play = (JButton)añadirBoton(barraEjecución, new OyentePlay(editor), "Probar el juego.", 
				"play.png", "play2.png", false);
		
		preview = (JButton)añadirBoton(barraEjecución, new OyentePreview(editor), "Ver la imagen preeliminar del mapa actual",
				"play.png", "play2.png", false);
	}

	public AbstractButton añadirBoton(JToolBar barra, ActionListener oyente, String tooltip, String imagenpeq, String imagengran, boolean toggled)
	{
		AbstractButton boton;
		if (!toggled)
			boton = new JButton();
		else
			boton = new JToggleButton();
		barra.add(boton);
		boton.addActionListener(oyente);
		boton.setToolTipText("<html>" + tooltip + "<img src=" + cl.getResource("maker/Iconos/" + imagengran) + "></html>");
		boton.setIcon(new ImageIcon(cl.getResource("maker/Iconos/" + imagenpeq)));
		return boton;
	}
	
	public void activarBotonesDePintura(boolean b)
	{
		texturasBoton.setEnabled(b);
		objetosBoton.setEnabled(b);
		lapiz.setEnabled(b);
		rect.setEnabled(b);
		boteDePintura.setEnabled(b);
		goma.setEnabled(b);
		guardarBoton.setEnabled(b);
		preview.setEnabled(b);
	}

	public void activarBotonesDeEdiciónYPrueba(boolean b) 
	{
		nuevoMapa.setEnabled(b);
		abrirMapa.setEnabled(b);
		importarButton.setEnabled(b);
		play.setEnabled(b);
		sonido.setEnabled(b);
	}
	
	public void activarDeshacer(boolean b)
	{
		deshacer.setEnabled(b);
	}
}
