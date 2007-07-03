package maker.vista.editor.Sonido;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maker.vista.editor.Editor;
import maker.vista.editor.Importar.Filtros.MidiFilter;
import maker.vista.editor.Importar.Oyentes.CerrarVentana;
import maker.vista.editor.Sonido.Oyentes.OyenteParar;
import maker.vista.editor.Sonido.Oyentes.OyenteReproducir;
import maker.vista.editor.Sonido.Oyentes.OyenteSalir;

public class PanelSonido extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JComboBox list;
	private Editor editor;

	public PanelSonido(Editor ed) 
	{
		super();

		editor = ed;
		editor.setEnabled(false);

		setBounds(300,200,300,80);
		setTitle("Prueba de sonido");

		list = new JComboBox();
		getContentPane().add(list);

		crearBotones();

		addWindowListener(new CerrarVentana(editor));
		
		cargarSonidos();
	}

	private void crearBotones() 
	{
		final JPanel panel = new JPanel(new GridLayout(1,3,1,0));
		getContentPane().add(panel, BorderLayout.SOUTH);

		final JButton reproducirButton = new JButton();
		reproducirButton.setText("Reproducir");
		reproducirButton.addActionListener(new OyenteReproducir(editor, list));
		panel.add(reproducirButton);

		final JButton pararButton = new JButton();
		pararButton.setText("Parar");
		pararButton.addActionListener(new OyenteParar(editor));
		panel.add(pararButton);

		final JButton salirButton = new JButton();
		salirButton.setText("Salir");
		salirButton.addActionListener(new OyenteSalir(this, editor));
		panel.add(salirButton);
	}

	private void cargarSonidos() 
	{
		File f = new File(editor.getDirectorio().getAbsolutePath() + "\\Sonidos\\Sonidos MIDI");
		File []array = f.listFiles();
		MidiFilter filtro = new MidiFilter();
		int i = 0;
		int fin;
		if (array == null)
			fin = 0;
		else
			fin = array.length;
		try
		{
			while (i < fin)				
			{
				if (filtro.accept(array[i]))
				{
					list.addItem(array[i].getName());
				}
				i++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{}
	}

}
