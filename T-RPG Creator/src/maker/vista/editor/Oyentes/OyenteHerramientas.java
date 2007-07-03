package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.baseDatos.DatabaseGUI;
import maker.vista.editor.Editor;



/**
 * @author  David García
 */
public class OyenteHerramientas implements ActionListener 
{
	private Editor puntero;

	public OyenteHerramientas(Editor punt)
	{
		puntero = punt;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		DatabaseGUI dbg = new DatabaseGUI(puntero, puntero.getDirectorio());
		dbg.setVisible(true);
		puntero.setEnabled(false);
	}
}
