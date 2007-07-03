package maker.vista.editor.Importar.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JList;
import maker.vista.editor.Importar.PanelImportar;
import maker.vista.editor.Importar.VistaPrevia;

/**
 * @author  David García
 */
public class OyenteVistaPrevia implements ActionListener 
{
	private JList listaArchivo;
	private JList listaCat;
	private File dir;
	private PanelImportar pan;

	public OyenteVistaPrevia(JList listaArchivo, JList listaCat, File dir, PanelImportar pan) 
	{
		this.pan = pan;
		this.listaArchivo = listaArchivo;
		this.listaCat = listaCat;
		this.dir = dir; 
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		if (!listaArchivo.isSelectionEmpty())
		{
			String rutadef = (String) listaCat.getSelectedValue();
			String archivodef = (String) listaArchivo.getSelectedValue();
			File f = new File(dir.getAbsolutePath() + "\\Imagenes\\" + rutadef + "\\" + archivodef);
			@SuppressWarnings("unused")
			VistaPrevia vp = new VistaPrevia(pan, f);
		}
	}

}
