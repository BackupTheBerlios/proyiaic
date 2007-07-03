package maker.vista.editor;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import maker.modelo.recursos.Textura;
import maker.recursos.SistemaCarga;
import maker.vista.editor.Oyentes.OyenteCambioTextura;

/**
 * @author  David García
 */
public class ListaTexturas extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private Editor editor;

	public ListaTexturas(Editor ed)
	{
		editor = ed;
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setSize(100,160);
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		crearLista();
	}

	public void crearLista()
	{
		for (int i = 0; i < editor.getSistemacarga().getTamaño(SistemaCarga.TEXTURAS); i++)
		{
			Textura tex = (Textura) editor.getSistemacarga().getRecurso(SistemaCarga.TEXTURAS, i);
			JButton boton = new JButton();
			boton.setToolTipText(tex.getNombre());
			boton.setMargin(new Insets(1,1,1,1));
			try 
			{
				BufferedImage imagen = ImageIO.read(new File(tex.getPath_superficie()));
				BufferedImage imagen2 = new BufferedImage(148,30, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = imagen2.createGraphics();
				g.drawImage(imagen, 0, 0, 148, 30, this);
				boton.setIcon(new ImageIcon(imagen2));
				boton.setPreferredSize(new Dimension(148,30));
				boton.addActionListener(new OyenteCambioTextura(editor, i));
				add(boton);
			} 
			catch (IOException e) {}
		}
	}
}
