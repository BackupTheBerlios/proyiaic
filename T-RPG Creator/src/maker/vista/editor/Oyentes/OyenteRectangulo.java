package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Acciones.Acci�nRect�ngulo;
import maker.vista.editor.Editor;

/**
 * @author  David Garc�a
 */
public class OyenteRectangulo implements ActionListener {

	private Editor editor;

	public OyenteRectangulo(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent e) 
	{
		editor.cambiarEscucha(new Acci�nRect�ngulo(editor));
	}

}
