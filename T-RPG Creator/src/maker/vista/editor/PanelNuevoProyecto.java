package maker.vista.editor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * @author  David García
 */
public class PanelNuevoProyecto extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField ruta;
	private JTextField nombre;
	private File archivo = null;
	
	public PanelNuevoProyecto() {
		super();
		setSize(400, 100);
		setPreferredSize(new Dimension(400, 100));
		final GridLayout gridLayout = new GridLayout(2, 0);
		gridLayout.setVgap(12);
		gridLayout.setHgap(10);
		setLayout(gridLayout);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		add(panel);

		final JLabel nombreDelProyectoLabel = new JLabel();
		nombreDelProyectoLabel.setText("Nombre del Proyecto:  ");
		panel.add(nombreDelProyectoLabel);

		nombre = new JTextField();
		panel.add(nombre);


		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		add(panel_1);

		JLabel rutaLabel;
		rutaLabel = new JLabel();
		panel_1.add(rutaLabel);
		rutaLabel.setText("Directorio:   ");

		ruta = new JTextField();
		ruta.setEditable(false);
		panel_1.add(ruta);

		JButton examinarButton;

		final JLabel label = new JLabel();
		label.setText("     ");
		panel_1.add(label);
		examinarButton = new JButton();
		examinarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null))
				{
					archivo = jfc.getSelectedFile();
					ruta.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		panel_1.add(examinarButton);
		examinarButton.setText("Examinar...");
	}	

	/**
	 * @return  the nombre
	 */
	public String getNombre()
	{
		return nombre.getText();
	}
	
	/**
	 * @return  the ruta
	 */
	public File getRuta()
	{
		return archivo;
	}
	
}
