package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;
import maker.vista.editor.Acciones.AccionGoma;

public class OyenteGoma implements ActionListener 
{
	private Editor editor;

	public OyenteGoma(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent e) 
	{
		editor.cambiarEscucha(new AccionGoma(editor));
	}

}
