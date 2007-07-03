package maker.vista.editor.Importar.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import maker.modelo.ficheros.Copiar;

public class OyenteExportar implements ActionListener {

	private JList listaArchivo;
	private JList listaCat;
	private File dir;

	public OyenteExportar(JList listaArchivo, JList listaCat, File dir) 
	{
		this.listaArchivo = listaArchivo;
		this.listaCat = listaCat;
		this.dir = dir; 
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		if (!listaArchivo.isSelectionEmpty())
		{	
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (JFileChooser.APPROVE_OPTION == jfc.showSaveDialog(null))
			{
				String rutadef = (String) listaCat.getSelectedValue();
				String archivodef = (String) listaArchivo.getSelectedValue();
				File desf = jfc.getSelectedFile();
				File fuef = new File(dir.getAbsolutePath() + "\\Imagenes\\" + rutadef + "\\" + archivodef);					
				Copiar cp = new Copiar();
				try 
				{
					cp.copy(fuef, desf);
					JOptionPane.showMessageDialog(null, "Archivo exportado con exito.", "Información", JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error al exportar el archivo.", "Error al exportar", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
