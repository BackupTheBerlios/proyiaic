package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;


/**
 * @author  David García
 */
public class OyenteTexturas implements ActionListener 
{
	private Editor editor;

	public OyenteTexturas(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent evento) 
	{
		editor.mostrarPanelTexturas();
	}
}
