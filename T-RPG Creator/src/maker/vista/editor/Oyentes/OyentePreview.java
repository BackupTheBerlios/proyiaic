package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;
import maker.vista.editor.VentanaVistaPrevia;

public class OyentePreview implements ActionListener 
{
	private Editor editor;

	public OyentePreview(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		VentanaVistaPrevia vpm = new VentanaVistaPrevia(editor);
		vpm.setEnabled(true);
		editor.setEnabled(false);
	}

}
