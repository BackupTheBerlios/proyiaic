package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Acciones.AccionBotePintura;
import maker.vista.editor.Editor;

/**
 * @author  David Garc�a
 */
public class OyenteBotePintura implements ActionListener {

	private Editor editor;

	public OyenteBotePintura(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent e) 
	{
		editor.cambiarEscucha(new AccionBotePintura(editor));
	}
}
