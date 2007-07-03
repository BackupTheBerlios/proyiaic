package maker.vista.baseDatos.OyenteListaTexturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import maker.vista.baseDatos.BotonTextura;
import maker.vista.baseDatos.PanelSeleccionar;
import maker.vista.baseDatos.PanelTexturas;



/**
 * @author  David García
 */
public class BotonTexturaCentral implements ActionListener 
{
	private PanelTexturas padre;
	
	private File directorio;
	
	private BotonTextura boton;
	
	public BotonTexturaCentral(PanelTexturas texturas, File directorio, BotonTextura superf) 
	{
		this.padre = texturas;
		this.directorio = directorio;
		this.boton = superf;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		PanelSeleccionar pans = new PanelSeleccionar(new File(directorio.getAbsolutePath() + "\\Imagenes\\Texturas"));
		JOptionPane.showConfirmDialog(padre, pans, "Selecciona la imagen que quieres que tenga la textura.",
									  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);	
		padre.setPath_superficie(directorio.getAbsolutePath() + "\\Imagenes\\Texturas\\" + pans.getSelected());
		boton.setTextura(new File(padre.getPath_superficie()));
		padre.setVisible(true);
		padre.repaint();
	}

}
