package maker.vista.editor.Acciones;

import java.awt.event.MouseEvent;

import maker.controlador.herramientas.mapa_escenario.HerramientaBotePintura;
import maker.vista.editor.Editor;

/**
 * @author  David Garc�a
 */
public class AccionBotePintura extends EscuchaRaton 
{
	private Editor editor;

	public AccionBotePintura(Editor editor) 
	{
		this.editor = editor;
	}

	public void mouseClicked(MouseEvent evento)
	{
		int x = evento.getX() / 32;
		int y = evento.getY() / 32;
		int altura = (Integer) editor.getAltura();
		
		HerramientaBotePintura hbp = new HerramientaBotePintura(x, y, altura);
		editor.hacer(hbp);
		editor.repaint();
	}
}
