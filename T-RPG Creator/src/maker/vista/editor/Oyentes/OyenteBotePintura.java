package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Acciones.AcciónBotePintura;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class OyenteBotePintura implements ActionListener {

	private Editor editor;

	public OyenteBotePintura(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent e) 
	{
		editor.cambiarEscucha(new AcciónBotePintura(editor));
	}
}
