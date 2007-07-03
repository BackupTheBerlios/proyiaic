package maker.vista.editor.Importar;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author  David García
 */
public class VistaPrevia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton cerrarButton;
	private JLabel label;
	private PanelImportar llamante;
	
	/**
	 * Create the frame
	 */
	public VistaPrevia(PanelImportar pan, File imagen) 
	{
		llamante = pan;
		pan.setEnabled(false);
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(imagen.getName());

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = Toolkit.getDefaultToolkit().getImage(imagen.getAbsolutePath());
		label.setIcon(new ImageIcon(img));
		getContentPane().add(label, BorderLayout.CENTER);

		cerrarButton = new JButton();
		cerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				llamante.setEnabled(true);
				dispose();
			}
		});
		cerrarButton.setText("Cerrar");
		getContentPane().add(cerrarButton, BorderLayout.SOUTH);
		setSize(img.getWidth(this), img.getHeight(this) + 50);
		//
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) 
			{
				llamante.setEnabled(true);
			}
		});
	}

}
