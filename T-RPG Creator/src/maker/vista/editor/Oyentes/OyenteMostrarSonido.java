package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;
import maker.vista.editor.Sonido.PanelSonido;

public class OyenteMostrarSonido implements ActionListener {

	private Editor editor;

	public OyenteMostrarSonido(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		PanelSonido ps = new PanelSonido(editor);
		ps.setVisible(true);
	}

}
