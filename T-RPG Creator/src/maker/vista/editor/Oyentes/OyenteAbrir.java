package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import maker.modelo.ficheros.FiltroArchivo;
import maker.vista.editor.Editor;


/**
 * @author  David García
 */
public class OyenteAbrir implements ActionListener 
{
	private Editor editor;

	public OyenteAbrir(Editor ed)
	{
		editor = ed;
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		JFileChooser jfc = new JFileChooser();
		FiltroArchivo fa = new FiltroArchivo();
		jfc.setFileFilter(fa);
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (jfc.showOpenDialog(editor) == JFileChooser.APPROVE_OPTION)
		{
			File archivo = jfc.getSelectedFile();
			if (fa.accept(archivo))
			{
				editor.abrir(archivo);
			}
			else
			{
				JOptionPane.showMessageDialog(editor, "El archivo seleccionado no tiene el formato esperado. Debe ser un archivo de tipo project_data.srpg.", "Error al abrir archivo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
