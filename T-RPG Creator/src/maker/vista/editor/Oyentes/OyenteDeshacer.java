package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class OyenteDeshacer implements ActionListener 
{
	private Editor editor;

	public OyenteDeshacer(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		editor.deshacer();
		editor.repaint();
	}

}
