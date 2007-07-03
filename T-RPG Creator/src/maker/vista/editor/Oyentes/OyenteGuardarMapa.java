package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;

public class OyenteGuardarMapa implements ActionListener {

	private Editor editor;

	public OyenteGuardarMapa(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		editor.getController().getModelo().guardar(editor.getDirectorio());
		editor.vaciarPila();
	}

}
