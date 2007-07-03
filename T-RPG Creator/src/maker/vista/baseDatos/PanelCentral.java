package maker.vista.baseDatos;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class PanelCentral extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private Editor editor;

	public PanelCentral(File directorio, Editor ed) 
	{
		super();
		setLayout(new BorderLayout());

		editor = ed; 
		
		final JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane);

		final PanelObjetos panelObjetos = new PanelObjetos(editor, directorio);
		final PanelTexturas panelTexturas = new PanelTexturas(editor, directorio);
		tabbedPane.addTab("Objetos Decorativos", null, panelObjetos, "Aqui es donde se crean los objetos decorativos: Arboles, columnas, muebles...");
		tabbedPane.addTab("Texturas del Mapa", null, panelTexturas, "Aqui es donde se crean las texturas que luego van a tener las casillas de los mapas.");
		tabbedPane.setSelectedComponent(panelTexturas);
		tabbedPane.setSelectedComponent(panelObjetos);
		setVisible(true);
	}

}
