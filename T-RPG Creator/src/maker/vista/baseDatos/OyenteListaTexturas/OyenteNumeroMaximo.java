package maker.vista.baseDatos.OyenteListaTexturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import maker.modelo.recursos.FactoriaRecursosVacios;
import maker.recursos.SistemaCarga;
import maker.vista.baseDatos.PanelBaseDatos;
import maker.vista.editor.Editor;

public class OyenteNumeroMaximo implements ActionListener 
{
	private Editor editor;
	private PanelBaseDatos panelBD;

	public OyenteNumeroMaximo(Editor editor, PanelBaseDatos texturas) 
	{
		this.editor = editor;
		this.panelBD = texturas;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		JPanel num_max = new JPanel();
		JLabel eti = new JLabel("N�mero m�ximo: ");
		JSpinner jsp = new JSpinner();
		int tama�o = 0;
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.TEXTURA)
			tama�o = editor.getSistemacarga().getTama�o(SistemaCarga.TEXTURAS);
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.OBJETO)
			tama�o = editor.getSistemacarga().getTama�o(SistemaCarga.OBJETOS);
		jsp.setModel(new SpinnerNumberModel(tama�o, 1, 1000, 1));
		num_max.add(eti);
		num_max.add(jsp);
		if (JOptionPane.showConfirmDialog(panelBD, num_max, "Elige el tama�o de la lista", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
		{
			if (tama�o > (Integer)jsp.getValue())
			{
				if (JOptionPane.showConfirmDialog(panelBD, "Si disminuyes el tama�o de la lista, algunos " +
												  "registros se perder�n. �Est�s seguro?", 
										          "�Atenci�n!", JOptionPane.WARNING_MESSAGE, 
										          JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					cambiarTama�o((Integer) jsp.getValue());
				}
			}
			else
			{
				cambiarTama�o((Integer) jsp.getValue());
			}
		}
	}

	private void cambiarTama�o(Integer tama�o)
	{
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.TEXTURA)
		{
			editor.getSistemacarga().setTama�o(SistemaCarga.TEXTURAS, tama�o);
			panelBD.cambiarTama�o();
			editor.actualizarListaTexturas();
		}
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.OBJETO)
		{
			editor.getSistemacarga().setTama�o(SistemaCarga.OBJETOS, tama�o);
			panelBD.cambiarTama�o();
			//editor.actualizarListaObjetos();
		}
	}

}
