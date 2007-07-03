package maker.vista.editor.Oyentes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class OyenteCerrar extends WindowAdapter
{
	private Editor editor;

	public OyenteCerrar(Editor editor) 
	{
		this.editor = editor;
	}

	public void windowClosing(WindowEvent evento) 
	{
		if (editor.getDirectorio() != null)
		{
			editor.guardarXML();
			editor.getSistemacarga().guardar();
		}
		editor.detener();
	}
}
