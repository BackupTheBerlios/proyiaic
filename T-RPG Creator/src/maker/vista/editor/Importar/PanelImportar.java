package maker.vista.editor.Importar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import maker.vista.editor.Editor;
import maker.vista.editor.Importar.Oyentes.CerrarVentana;
import maker.vista.editor.Importar.Oyentes.ListaArchivoSeleccionado;
import maker.vista.editor.Importar.Oyentes.OyenteEliminar;
import maker.vista.editor.Importar.Oyentes.OyenteExportar;
import maker.vista.editor.Importar.Oyentes.OyenteImportar;
import maker.vista.editor.Importar.Oyentes.OyenteSelección;
import maker.vista.editor.Importar.Oyentes.OyenteVistaPrevia;


/**
 * @author  David García
 */
public class PanelImportar extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JList listaArchivo;
	private JList listaCat;
	private Editor ventana;
	private File dir;
	private DefaultListModel dlmArchivo;
	private JButton eliminarButton;
	private JButton vistaPreviaButton;
	private JButton exportarButton;

	public PanelImportar(Editor ven, File directorio) 
	{
		ventana = ven;
		dir = directorio;
		setTitle("Importar Archivos Externos");

		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(10, 0));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		getContentPane().add(panel, BorderLayout.EAST);

		final JButton importarButton = new JButton();
		importarButton.setText("Importar");
		panel.add(importarButton);

		exportarButton = new JButton();
		exportarButton.setText("Exportar");
		panel.add(exportarButton);

		vistaPreviaButton = new JButton();
		vistaPreviaButton.setText("Vista Previa");
		panel.add(vistaPreviaButton);

		eliminarButton = new JButton();
		eliminarButton.setText("Eliminar");
		panel.add(eliminarButton);
		
		enableAll(false);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Categoria", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1, BorderLayout.WEST);

		listaCat = new JList();
		listaCat.setSelectionForeground(Color.WHITE);
		listaCat.setSelectionBackground(Color.DARK_GRAY);
		listaCat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaCat.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel_1.add(listaCat);
		DefaultListModel dlmCat = new DefaultListModel();
		listaCat.setModel(dlmCat);
		
		dlmCat.addElement("Caras de Personaje");
		dlmCat.addElement("Objetos");
		dlmCat.addElement("Personajes");
		dlmCat.addElement("Sonidos MIDI");
		dlmCat.addElement("Texturas");


		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Archivos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		listaArchivo = new JList();
		listaArchivo.setSelectionForeground(Color.WHITE);
		listaArchivo.setSelectionBackground(Color.DARK_GRAY);
		listaArchivo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaArchivo.setBorder(new BevelBorder(BevelBorder.LOWERED));
		dlmArchivo = new DefaultListModel();
		listaArchivo.setModel(dlmArchivo);
		scrollPane.setViewportView(listaArchivo);

		setSize(600,450);
		setVisible(true);
		
		listaCat.addListSelectionListener(new OyenteSelección(this, dir, listaCat, dlmArchivo));
		listaCat.setSelectedIndex(0);

		importarButton.addActionListener(new OyenteImportar(listaCat, dir, dlmArchivo));
		vistaPreviaButton.addActionListener(new OyenteVistaPrevia(listaArchivo, listaCat, dir, this));
		listaArchivo.addListSelectionListener(new ListaArchivoSeleccionado(listaArchivo, this));
		eliminarButton.addActionListener(new OyenteEliminar(listaArchivo, listaCat, dlmArchivo, dir));
		exportarButton.addActionListener(new OyenteExportar(listaArchivo, listaCat, dir));
		addWindowListener(new CerrarVentana(ventana));
	}

	public void enableAll(boolean b) 
	{
		exportarButton.setEnabled(b);
		vistaPreviaButton.setEnabled(b);
		eliminarButton.setEnabled(b);	
	}

}
