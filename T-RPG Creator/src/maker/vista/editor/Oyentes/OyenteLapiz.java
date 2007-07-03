package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Acciones.AcciónLápiz;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class OyenteLapiz implements ActionListener {

	private Editor editor;

	public OyenteLapiz(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent e) 
	{
		editor.cambiarEscucha(new AcciónLápiz(editor));
	}

}
