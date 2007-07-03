package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.modelo.MapaEscenario;
import maker.vista.editor.Editor;

public class OyenteCambioTextura implements ActionListener {

	private Editor editor;
	
	private int textura;
	
	public OyenteCambioTextura(Editor editor, int i) 
	{
		this.editor = editor;
		textura = i;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		((MapaEscenario)editor.getController().getModelo().getMapa()).setTextura(textura);
	}

}
