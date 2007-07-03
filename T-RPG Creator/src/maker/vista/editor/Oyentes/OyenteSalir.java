package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class OyenteSalir implements ActionListener 
{
	private Editor editor;

	public OyenteSalir(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (editor.getDirectorio() != null)
		{
			editor.guardarXML();
			editor.getSistemacarga().guardar();
		}
		editor.detener();
		System.exit(0);
	}

}
