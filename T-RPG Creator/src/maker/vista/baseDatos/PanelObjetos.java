package maker.vista.baseDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maker.modelo.recursos.FactoriaRecursosVacios;
import maker.modelo.recursos.Objeto;
import maker.modelo.recursos.Recurso;
import maker.recursos.SistemaCarga;
import maker.vista.baseDatos.OyenteListaTexturas.OyenteNumeroMaximo;
import maker.vista.editor.Editor;


/**
 * @author  David García
 */
public class PanelObjetos extends PanelBaseDatos 
{
	private JPanel panel_5;
	private JLabel label;
	private JComboBox comboBox;
	private JSpinner spinnerDaño;

	private static final long serialVersionUID = 1L;

	private JLabel nombreLabel;

	private Lista lista;

	private JTextField nomTF;

	private File directorio;

	private JLabel etqDaño;

	private JCheckBox atravesableCheckBox = new JCheckBox();
	
	private JCheckBox golpeableCheckBox = new JCheckBox();
	
	private PanelObjetos esto;
	
	private String path;
	private JButton botonImagenObjeto;
	private Editor editor;
	
	public PanelObjetos(Editor ed, File direct) 
	{
		super();

		this.editor = ed;
		this.esto = this;
		this.path = "";
		this.directorio = direct;
		
		setLayout(new BorderLayout());

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		add(panel_1, BorderLayout.CENTER);

		JLabel escenarioLabel;

		botonImagenObjeto = new JButton();

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		add(panel_2, BorderLayout.WEST);

		final JButton aplicarButton = new JButton();
		aplicarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				aplicar();
			}
		});
		aplicarButton.setText("Aplicar Cambios");
		panel_1.add(aplicarButton, BorderLayout.SOUTH);

		final JPanel panelGraficosNombre = new JPanel();
		panelGraficosNombre.setLayout(new BorderLayout());
		panel_1.add(panelGraficosNombre, BorderLayout.WEST);

		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		panelGraficosNombre.add(panel, BorderLayout.NORTH);
		panel.setFocusCycleRoot(true);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));

		nombreLabel = new JLabel("Nombre:");
		panel.add(nombreLabel);

		nomTF = new JTextField();
		nomTF.setColumns(30);
		panel.add(nomTF);

		final JPanel panelGraficos = new JPanel();
		panelGraficos.setLayout(new BorderLayout());
		panelGraficosNombre.add(panelGraficos);
		panelGraficos.setPreferredSize(new Dimension(165, 200));
		panelGraficos.setBorder(new TitledBorder(null, "Gráficos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		escenarioLabel = new JLabel();
		panelGraficos.add(escenarioLabel, BorderLayout.NORTH);
		escenarioLabel.setName("escenarioLabel");
		escenarioLabel.setText("Imagen del objeto:");
		
		botonImagenObjeto.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) 
			{
				PanelSeleccionar pans = new PanelSeleccionar(new File(directorio.getAbsolutePath() + "\\Imagenes\\Objetos"));
				JOptionPane.showConfirmDialog(esto, pans, "Selecciona la imagen que quieres que tenga el objeto.",
											  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);	
				path = pans.getSelected();
				botonImagenObjeto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(directorio.getAbsolutePath() + "\\Imagenes\\Objetos\\" + path)));
				setVisible(true);
				repaint();
			}
		});
		
		botonImagenObjeto.setForeground(Color.WHITE);
		botonImagenObjeto.setBackground(Color.WHITE);
		panelGraficos.add(botonImagenObjeto);
		botonImagenObjeto.setMinimumSize(new Dimension(150, 150));
		botonImagenObjeto.setPreferredSize(new Dimension(150, 210));

		panel_5 = new JPanel();
		panel_5.setLayout(new BorderLayout());
		panel_1.add(panel_5);

		label = new JLabel();
		label.setText("Tamaño: ");

		final JPanel caracteristicasPanel = new JPanel();
		panel_5.add(caracteristicasPanel, BorderLayout.NORTH);
		caracteristicasPanel.setName("caracteristicasPanel");
		final GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setVgap(1);
		caracteristicasPanel.setLayout(gridLayout);
		caracteristicasPanel.setBorder(new TitledBorder(null, "Características", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));


		atravesableCheckBox.setText("Atravesable");
		caracteristicasPanel.add(atravesableCheckBox);
		
		golpeableCheckBox.setText("Golpeable");
		caracteristicasPanel.add(golpeableCheckBox);
		golpeableCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				etqDaño.setEnabled(golpeableCheckBox.isSelected());
				spinnerDaño.setEnabled(golpeableCheckBox.isSelected());
			}
		});

		etqDaño = new JLabel();
		caracteristicasPanel.add(etqDaño);


		etqDaño.setText("Daño hasta destrucción: ");
		etqDaño.setEnabled(false);

		spinnerDaño = new JSpinner();
		caracteristicasPanel.add(spinnerDaño);
		spinnerDaño.setEnabled(false);
		
		caracteristicasPanel.add(label);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1x1", "2x2", "3x3", "4x4", "5x5"}));
		caracteristicasPanel.add(comboBox);
		
		crearLista();
	}

	public void cargarObjetos(DefaultListModel dlm) 
	{
		dlm.removeAllElements();
		for (int i = 0; i < editor.getSistemacarga().getTamaño(SistemaCarga.OBJETOS); i++)
		{
			Objeto obj = (Objeto) editor.getSistemacarga().getRecurso(SistemaCarga.OBJETOS, i);
			dlm.addElement(obj);
		}
	}

	protected void guardarObjeto(Recurso obj, int selec) 
	{
		editor.getSistemacarga().setRecurso(SistemaCarga.OBJETOS, selec, obj);
		/*File objetos = new File(directorio.getAbsolutePath() + "\\Base de datos\\Objetos.srpgdata");
		LinkedList<Objeto> lista;
		try {
			FileInputStream fis = new FileInputStream(objetos);
			ObjectInputStream ois = new ObjectInputStream(fis);
			lista = (LinkedList<Objeto>)ois.readObject(); 
			lista.add(selec, obj);
			lista.remove(selec + 1);
			FileOutputStream fos = new FileOutputStream(objetos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
		} 
		catch (IOException e1) 
		{
			JOptionPane.showMessageDialog(this, "Error al cargar el archivo. No se encuentra o han tenido lugar errores de lectura.", "Error al abrir los objetos.",JOptionPane.ERROR_MESSAGE);
		} 
		catch (ClassNotFoundException e1) 
		{
			JOptionPane.showMessageDialog(this, "Datos inesperados en el archivo de objetos.", "Error al abrir los objetos.",JOptionPane.ERROR_MESSAGE);
		}*/
	}

	public int getTipoPanel() 
	{
		return FactoriaRecursosVacios.OBJETO;
	}

	public void cambiarSeleccionado(Recurso rec) 
	{
		Objeto obj = (Objeto) rec;
		nomTF.setText(obj.getNombre());
		atravesableCheckBox.setSelected(obj.isAtravesable());
		golpeableCheckBox.setSelected(obj.isGolpeable());
		path = obj.getPath();
		spinnerDaño.setValue(obj.getDaño());
		comboBox.setSelectedItem(obj.getPasable());
		botonImagenObjeto.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(directorio.getAbsolutePath() + "\\Imagenes\\Objetos\\" + path)));
	
	}

	public void aplicar()
	{
		String nom = nomTF.getText();
		boolean at = atravesableCheckBox.isSelected();
		boolean golp = golpeableCheckBox.isSelected();
		int damage = (Integer)spinnerDaño.getValue();
		int selec = lista.getSelectedIndex();
		Objeto obj = new Objeto(nom, path, (String)comboBox.getSelectedItem() ,at, golp, damage);
		guardarObjeto(obj, selec);
		lista.aplicar(obj, selec);
		setVisible(true);
	}
	
	public void eliminarElemento(Recurso rec, int selec) 
	{
		Objeto obj = (Objeto)rec;
		guardarObjeto(obj, selec);
		nomTF.setText("");
		atravesableCheckBox.setSelected(false);
		golpeableCheckBox.setSelected(false);
		path = "";
		spinnerDaño.setValue(0);
		setVisible(true);
	}
	
	private void crearLista() 
	{
		JScrollPane scrollPane = new JScrollPane();

		lista = new Lista(this);
		scrollPane.setViewportView(lista);
		scrollPane.setPreferredSize(new Dimension(175,0));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JButton botonCambiarNumero = new JButton("Número máximo...");
		botonCambiarNumero.addActionListener(new OyenteNumeroMaximo(editor, this));

		JPanel panelLista = new JPanel(new BorderLayout());
		panelLista.add(scrollPane);
		panelLista.add(botonCambiarNumero, BorderLayout.SOUTH);
		add(panelLista, BorderLayout.WEST);
	}

	public void cambiarTamaño() 
	{
		lista.cargarObjetos();
	}
}
