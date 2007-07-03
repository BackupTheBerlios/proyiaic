package maker.vista.editor;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import maker.modelo.MapaEscenario;

public class PanelCreacionMapa extends JPanel 
{
	private static final long serialVersionUID = 1L;

	private JSpinner filas;

	private JSpinner columnas;

	private JTextField nombre;
	
	private JComboBox sonidos;

	private Editor editor;
	
	private JComboBox tipoMapa;

	private JPanel panelCentral;
	
	public PanelCreacionMapa(Editor ed) 
	{
		this.editor = ed;
		sonidos = new JComboBox();
		
		setLayout(new BorderLayout());
		panelCentral = new JPanel(new BorderLayout());
		add(panelCentral);
		
		crearTipoMapa();
		crearNombreID();
		crearSpinners();
		crearSelecciónSonidos();
	}

	public String getTipoMapa() {
		return (String) tipoMapa.getSelectedItem();
	}

	private void crearTipoMapa() 
	{
		JLabel tipoMapalabel = new JLabel("Tipo de mapa: ");
		
		String[] valores = {"Escenario", "Caminos"};
		tipoMapa = new JComboBox(valores);
		
		JPanel panelTipoMapa = new JPanel();
		panelTipoMapa.setLayout(new BorderLayout());
		
		panelTipoMapa.add(tipoMapalabel, BorderLayout.WEST);
		panelTipoMapa.add(tipoMapa);
		
		add(panelTipoMapa, BorderLayout.NORTH);
	}

	private void crearSelecciónSonidos() 
	{
		CargadorSonidos cs = new CargadorSonidos(editor, sonidos);
		cs.cargarSonidos();
		
		JPanel panelsonidos = new JPanel();
		panelsonidos.setLayout(new BorderLayout());
		panelsonidos.setBorder(BorderFactory.createTitledBorder("Sonido del mapa"));
		
		JLabel etqsonidos = new JLabel("Música de ambiente: ");
		panelsonidos.add(etqsonidos, BorderLayout.WEST);
		etqsonidos.setHorizontalAlignment(JLabel.RIGHT);
		
		panelsonidos.add(sonidos, BorderLayout.EAST);
		
		panelCentral.add(panelsonidos, BorderLayout.SOUTH);
	}

	/** Crea el panel donde el usuario puede insertar el nombre del mapa, así como donde puede 
	 * verificar el ID del mapa que va a crear.*/
	private void crearNombreID() 
	{
		JPanel panelnombre = new JPanel();
		panelnombre.setLayout(new BoxLayout(panelnombre, BoxLayout.LINE_AXIS));
		panelnombre.setBorder(BorderFactory.createTitledBorder("Propiedades del mapa"));
		
		JLabel etqnombre = new JLabel("Nombre del mapa: ");
		panelnombre.add(etqnombre);
		etqnombre.setHorizontalAlignment(JLabel.RIGHT);
		
		nombre = new JTextField(20);
		panelnombre.add(nombre);
		
		JLabel id = new JLabel("   ID: ");
		panelnombre.add(id);
		
		JTextField idtextfield = new JTextField("" + MapaEscenario.getId(), 4);
		idtextfield.setEnabled(false);
		panelnombre.add(idtextfield);
		
		panelCentral.add(panelnombre, BorderLayout.NORTH);
	}

	private void crearSpinners() 
	{
		JPanel paneltamaño = new JPanel();
		paneltamaño.setLayout(new BorderLayout());
		paneltamaño.setBorder(BorderFactory.createTitledBorder("Tamaño del mapa"));
		panelCentral.add(paneltamaño);
		
		JPanel panelLabels = new JPanel();
		panelLabels.setLayout(new GridLayout(0, 1));
		paneltamaño.add(panelLabels, BorderLayout.WEST);
		
		JLabel etqcolumnas = new JLabel();
		etqcolumnas.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		etqcolumnas.setName("label_1");
		etqcolumnas.setText("Número de Columnas:               ");
		panelLabels.add(etqcolumnas);
		
		JLabel etqfilas = new JLabel();
		etqfilas.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		etqfilas.setName("label");
		etqfilas.setText("Número de Filas: ");
		panelLabels.add(etqfilas);

		final JPanel panelSpinners = new JPanel();
		panelSpinners.setLayout(new GridLayout(2, 0));
		paneltamaño.add(panelSpinners);
		
		filas = new JSpinner();
		filas.setModel(new SpinnerNumberModel(15,10,20,1));
		panelSpinners.add(filas);
		
		columnas = new JSpinner();
		columnas.setModel(new SpinnerNumberModel(15,10,20,1));
		panelSpinners.add(columnas);
	}

	public int getFilas()
	{
		return (Integer)(filas.getValue());
	}
	
	public int getColumnas()
	{
		return (Integer)(columnas.getValue());
	}

	public String getNombre() 
	{
		return nombre.getText();
	}
}
