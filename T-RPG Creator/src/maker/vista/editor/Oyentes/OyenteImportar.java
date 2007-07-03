package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import maker.vista.editor.Editor;
import maker.vista.editor.Importar.PanelImportar;



/**
 * @author  David García
 */
public class OyenteImportar implements ActionListener {

	Editor ventana;
	
	public OyenteImportar(Editor editor) 
	{
		ventana = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		PanelImportar pan = new PanelImportar(ventana, ventana.getDirectorio());
		//Para evitar que aparezcan WARNINGS.
		pan.getTitle();
		ventana.setEnabled(false);
	}

}
