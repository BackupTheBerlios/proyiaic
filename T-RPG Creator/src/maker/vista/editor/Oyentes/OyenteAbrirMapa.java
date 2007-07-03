package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import maker.vista.editor.Editor;
import maker.vista.editor.Oyentes.Filtros.MapFilter;

public class OyenteAbrirMapa implements ActionListener 
{
	private Editor editor;

	public OyenteAbrirMapa(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		File ruta = new File(editor.getDirectorio().getAbsolutePath() + "\\Mapas\\");
		JFileChooser fc = new JFileChooser(ruta);
		fc.setFileFilter(new MapFilter());
		fc.setAcceptAllFileFilterUsed(false);
		fc.setMultiSelectionEnabled(false);
		fc.setFileHidingEnabled(false);
		if (fc.showOpenDialog(editor) == JFileChooser.APPROVE_OPTION)
		{
			if (fc.getCurrentDirectory().equals(ruta))
			{
				File f = fc.getSelectedFile();
				editor.getController().getModelo().cargar(f);
				editor.activarBotonesPintura();
				editor.repaint();
			}
			else
				JOptionPane.showMessageDialog(editor, "No se pueden cargar mapas de otros proyectos.", "Error al cargar", JOptionPane.ERROR_MESSAGE);
			
		}
	}

}
