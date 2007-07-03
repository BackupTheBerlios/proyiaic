package maker.vista.baseDatos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class DatabaseGUI extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private Editor editor;
	
	public DatabaseGUI(Editor ed, File directorio) 
	{
		super();
		setTitle(":: Base de datos :: ");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) 
			{
				editor.setEnabled(true);
			}
		});
		editor = ed;
		setAlwaysOnTop(true);
		setSize(800,600);
		setVisible(true);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		final JButton cerrarButton = new JButton();
		cerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				editor.setEnabled(true);
				dispose();
			}
		});
		cerrarButton.setToolTipText("Cierra la ventana de la base de datos. Se perderan todos los cambios que no hayan sido confirmados con Aplicar.");
		cerrarButton.setText("Cerrar");
		panel.add(cerrarButton);

		final PanelCentral panel_1 = new PanelCentral(directorio, editor);
		getContentPane().add(panel_1, BorderLayout.CENTER);
	}

}
