package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;
import maker.vista.editor.Acciones.AccionRectangulo;

/**
 * @author  David García
 */
public class OyenteRectangulo implements ActionListener {

	private Editor editor;

	public OyenteRectangulo(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent e) 
	{
		editor.cambiarEscucha(new AccionRectangulo(editor));
	}

}
