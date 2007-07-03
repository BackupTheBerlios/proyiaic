package maker.vista.editor.Importar.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class OyenteEliminar implements ActionListener {

	private JList listaArchivo;
	private JList listaCat;
	private DefaultListModel dlmArchivo;
	private File dir;

	public OyenteEliminar(JList listaArchivo, JList listaCat, DefaultListModel dlmArchivo, File dir) 
	{
		this.listaArchivo = listaArchivo;
		this.listaCat = listaCat;
		this.dlmArchivo = dlmArchivo;
		this.dir = dir; 
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (!listaArchivo.isSelectionEmpty())
		{
			String rutadef = (String) listaCat.getSelectedValue();
			String archivodef = (String) listaArchivo.getSelectedValue();
			File f = new File(dir.getAbsolutePath() + "\\Imagenes\\" + rutadef + "\\" + archivodef);
			f.delete();
			dlmArchivo.removeElementAt(listaArchivo.getSelectedIndex());
		}
	}
}
