package maker.vista.baseDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelSeleccionar extends JPanel 
{
	private JLabel label_1;
	private JLabel label;
	private JScrollPane scrollPane_1;
	private JList list;
	private JScrollPane scrollPane;
	private static final long serialVersionUID = 1L;

	private File tipoRecursos;
	private DefaultListModel dlm;

	public PanelSeleccionar(File file2) {
		super();
		setPreferredSize(new Dimension(640, 480));
		setLayout(new BorderLayout());

		tipoRecursos = file2;

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.WEST);

		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) 
			{
				String obj = (String)list.getSelectedValue();
				File f = new File(tipoRecursos.getAbsolutePath() + "\\" + obj);
				label.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath())));
				setVisible(true);
				repaint();
			}
		});
		list.setBorder(new LineBorder(Color.black, 1, false));
		list.setPreferredSize(new Dimension(100, 0));
		dlm = new DefaultListModel();
		list.setModel(dlm);
		cargarObjetos();
		scrollPane.setViewportView(list);

		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, BorderLayout.CENTER);

		label = new JLabel();
		label.setBorder(new LineBorder(Color.black, 1, false));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(label);

		label_1 = new JLabel();
		scrollPane_1.setRowHeaderView(label_1);
		label_1.setText("  ");
		list.setSelectedIndex(0);
	}

	@SuppressWarnings("unchecked")
	private void cargarObjetos() 
	{
		File objetos = tipoRecursos;
		File []array = objetos.listFiles();
		int i = 0;
		try
		{
			while (true)
			{
				dlm.addElement(array[i].getName());
				i++;
			} 
		}
		catch (ArrayIndexOutOfBoundsException e){};
	}
	
	public String getSelected()
	{
		return (String)list.getSelectedValue();
	}
}
