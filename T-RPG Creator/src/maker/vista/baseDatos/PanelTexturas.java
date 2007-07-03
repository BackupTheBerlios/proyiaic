 package maker.vista.baseDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import maker.modelo.recursos.FactoriaRecursosVacios;
import maker.modelo.recursos.Recurso;
import maker.modelo.recursos.Textura;
import maker.recursos.SistemaCarga;
import maker.vista.baseDatos.OyenteListaTexturas.AcciónAplicar;
import maker.vista.baseDatos.OyenteListaTexturas.BotonTexturaCentral;
import maker.vista.baseDatos.OyenteListaTexturas.BotonTexturaDerecha;
import maker.vista.baseDatos.OyenteListaTexturas.BotonTexturaIzquierda;
import maker.vista.baseDatos.OyenteListaTexturas.OyenteNumeroMaximo;
import maker.vista.editor.Editor;


/**
 * @author  David García
 */
public class PanelTexturas extends PanelBaseDatos 
{
	private static final long serialVersionUID = 1L;
	private BotonTextura latder;
	private BotonTextura superf;
	private BotonTextura latizbutton;
	private JPanel panel_subglobal;
	private JCheckBox atravesableCheckBox;
	private JTextField nombreTF;
	private JPanel panel_global;
	private Lista listaTexturas;
	private File directorio;	
	private String path_lateral_derecho;
	private String path_lateral_izquierdo;
	private String path_superficie;
	private Editor editor;

	public PanelTexturas(Editor editor, File direct) 
	{
		super();

		this.editor = editor;
		directorio = direct;

		setLayout(new BorderLayout());

		panel_global = new JPanel();
		panel_global.setLayout(new BorderLayout());
		add(panel_global);
		
		crearCaracteristicas();
		crearPanelesTexturas();
		crearBotonAplicar();
		
		crearLista();
	}

	public void aplicar() 
	{
		String nom = nombreTF.getText();
		boolean pa = atravesableCheckBox.isSelected();
		int selec = listaTexturas.getSelectedIndex();
		Textura tex = new Textura(nom, pa, path_lateral_derecho, path_lateral_izquierdo, path_superficie);
		guardarObjeto(tex, selec);
		listaTexturas.aplicar(tex, selec);
		editor.actualizarListaTexturas();
		setVisible(true);	
	}

	public void cambiarSeleccionado(Recurso recurso) 
	{
		Textura rec = (Textura)recurso;
		nombreTF.setText(rec.getNombre());
		atravesableCheckBox.setSelected(rec.isPasable());
		path_lateral_derecho = rec.getPath_lateral_derecho();
		path_lateral_izquierdo = rec.getPath_lateral_izquierdo();
		path_superficie = rec.getPath_superficie();
		if (!path_lateral_derecho.equals(""))
			latder.setTextura(new File(path_lateral_derecho));
		else
			latder.setPreparado(false);
		if (!path_lateral_izquierdo.equals(""))
			latizbutton.setTextura(new File(path_lateral_izquierdo));
		else
			latizbutton.setPreparado(false);
		if (!path_superficie.equals(""))
			superf.setTextura(new File(path_superficie));
		else
			superf.setPreparado(false);
		latder.repaint();
		latizbutton.repaint();
		superf.repaint();	
	}

	/**
	 * @return  the path_lateral_derecho
	 */
	public String getPath_lateral_derecho() 
	{
		return path_lateral_derecho;
	}

	/**
	 * @return  the path_lateral_izquierdo
	 */
	public String getPath_lateral_izquierdo() 
	{
		return path_lateral_izquierdo;
	}

	/**
	 * @return  the path_superficie
	 */
	public String getPath_superficie() 
	{
		return path_superficie;
	}

	/**
	 * @param path_lateral_derecho  the path_lateral_derecho to set
	 */
	public void setPath_lateral_derecho(String path_lateral_derecho) 
	{
		this.path_lateral_derecho = path_lateral_derecho;
	}

	/**
	 * @param path_lateral_izquierdo  the path_lateral_izquierdo to set
	 */
	public void setPath_lateral_izquierdo(String path_lateral_izquierdo) 
	{
		this.path_lateral_izquierdo = path_lateral_izquierdo;
	}

	/**
	 * @param path_superficie  the path_superficie to set
	 */
	public void setPath_superficie(String path_superficie) 
	{
		this.path_superficie = path_superficie;
	}

	public void cargarObjetos(DefaultListModel dlm) 
	{
		dlm.removeAllElements();
		for (int i = 0; i < editor.getSistemacarga().getTamaño(SistemaCarga.TEXTURAS); i++)
		{
			Textura tex = (Textura) editor.getSistemacarga().getRecurso(SistemaCarga.TEXTURAS, i);
			dlm.addElement(tex);
		}
	}

	private void crearBotonAplicar() 
	{
		JButton aplicarCambiosButton = new JButton();
		aplicarCambiosButton.setText("Aplicar Cambios");
		panel_global.add(aplicarCambiosButton, BorderLayout.SOUTH);	
		aplicarCambiosButton.addActionListener(new AcciónAplicar(this));
	}

	private void crearCaracteristicas()
	{
		JPanel panelCaracteristicas = new JPanel(new GridLayout(0, 1, 0, 2));
		panelCaracteristicas.setBorder(new TitledBorder(null, "Características", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_global.add(panelCaracteristicas, BorderLayout.NORTH);

		JLabel nombreLabel = new JLabel();
		nombreLabel.setText("Nombre: ");
		panelCaracteristicas.add(nombreLabel);

		nombreTF = new JTextField();
		nombreTF.setColumns(30);
		panelCaracteristicas.add(nombreTF);

		atravesableCheckBox = new JCheckBox();
		atravesableCheckBox.setText("Atravesable");
		panelCaracteristicas.add(atravesableCheckBox);
	}

	private void crearLista() 
	{
		JScrollPane scrollPane = new JScrollPane();

		listaTexturas = new Lista(this);
		scrollPane.setViewportView(listaTexturas);
		scrollPane.setPreferredSize(new Dimension(175,0));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JButton botonCambiarNumero = new JButton("Número máximo...");
		botonCambiarNumero.addActionListener(new OyenteNumeroMaximo(editor, this));

		JPanel panelLista = new JPanel(new BorderLayout());
		panelLista.add(scrollPane);
		panelLista.add(botonCambiarNumero, BorderLayout.SOUTH);
		add(panelLista, BorderLayout.WEST);
	}

	private void crearPanelCentral()
	{
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setBorder(new TitledBorder(null, "Superficie", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_subglobal.add(panelCentral);

		JLabel texturaLabel2 = new JLabel();
		texturaLabel2.setText("Textura: ");
		panelCentral.add(texturaLabel2, BorderLayout.NORTH);

		superf = new BotonTextura();
		superf.setBackground(Color.WHITE);
		panelCentral.add(superf, BorderLayout.CENTER);

		superf.addActionListener(new BotonTexturaCentral(this, directorio, superf));	
	}

	private void crearPanelDerecho() 
	{
		JPanel panelLatDer = new JPanel();
		panelLatDer.setLayout(new BorderLayout());
		panelLatDer.setBorder(new TitledBorder(null, "Lateral Derecho", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_subglobal.add(panelLatDer);

		JLabel texturaLabel3 = new JLabel();
		texturaLabel3.setText("Textura:");
		panelLatDer.add(texturaLabel3, BorderLayout.NORTH);

		latder = new BotonTextura();
		latder.setBackground(Color.WHITE);
		panelLatDer.add(latder, BorderLayout.CENTER);

		latder.addActionListener(new BotonTexturaDerecha(this, directorio, latder));
	}

	private void crearPanelesTexturas() 
	{
		panel_subglobal = new JPanel();
		panel_subglobal.setLayout(new GridLayout(1, 0));
		panel_global.add(panel_subglobal, BorderLayout.CENTER);

		crearPanelIzquierdo();

		crearPanelCentral();

		crearPanelDerecho();
	}

	private void crearPanelIzquierdo() 
	{
		JPanel panelLAtIzq = new JPanel();
		panelLAtIzq.setLayout(new BorderLayout());
		panelLAtIzq.setBorder(new TitledBorder(null, "Lateral Izquierdo", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_subglobal.add(panelLAtIzq);

		JLabel texturaLabel1 = new JLabel();
		texturaLabel1.setText("Textura: ");
		panelLAtIzq.add(texturaLabel1, BorderLayout.NORTH);

		latizbutton = new BotonTextura();
		latizbutton.setBackground(new Color(255, 255, 255));
		panelLAtIzq.add(latizbutton, BorderLayout.CENTER);

		latizbutton.addActionListener(new BotonTexturaIzquierda(this, directorio, latizbutton));
	}

	protected void guardarObjeto(Recurso tex, int selec) 
	{
		editor.getSistemacarga().setRecurso(SistemaCarga.TEXTURAS, selec, tex);
	}

	public int getTipoPanel() 
	{
		return FactoriaRecursosVacios.TEXTURA;
	}

	public void eliminarElemento(Recurso rec, int selec) 
	{
		guardarObjeto((Textura)rec, selec);
		nombreTF.setText("");
		atravesableCheckBox.setSelected(false);
		path_lateral_derecho = "";
		path_lateral_izquierdo = "";
		path_superficie = "";
		setVisible(true);
	}

	public void cambiarTamaño() 
	{
		listaTexturas.cargarObjetos();
	}
}
