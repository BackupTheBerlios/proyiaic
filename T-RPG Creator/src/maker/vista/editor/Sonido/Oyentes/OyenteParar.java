package maker.vista.editor.Sonido.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;

public class OyenteParar implements ActionListener {

	private Editor editor;

	public OyenteParar(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		editor.detener();
	}
}
