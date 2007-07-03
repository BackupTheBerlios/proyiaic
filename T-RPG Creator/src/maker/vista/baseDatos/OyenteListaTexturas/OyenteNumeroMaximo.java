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
		JLabel eti = new JLabel("Número máximo: ");
		JSpinner jsp = new JSpinner();
		int tamaño = 0;
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.TEXTURA)
			tamaño = editor.getSistemacarga().getTamaño(SistemaCarga.TEXTURAS);
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.OBJETO)
			tamaño = editor.getSistemacarga().getTamaño(SistemaCarga.OBJETOS);
		jsp.setModel(new SpinnerNumberModel(tamaño, 1, 1000, 1));
		num_max.add(eti);
		num_max.add(jsp);
		if (JOptionPane.showConfirmDialog(panelBD, num_max, "Elige el tamaño de la lista", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
		{
			if (tamaño > (Integer)jsp.getValue())
			{
				if (JOptionPane.showConfirmDialog(panelBD, "Si disminuyes el tamaño de la lista, algunos " +
												  "registros se perderán. ¿Estás seguro?", 
										          "¡Atención!", JOptionPane.WARNING_MESSAGE, 
										          JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
				{
					cambiarTamaño((Integer) jsp.getValue());
				}
			}
			else
			{
				cambiarTamaño((Integer) jsp.getValue());
			}
		}
	}

	private void cambiarTamaño(Integer tamaño)
	{
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.TEXTURA)
		{
			editor.getSistemacarga().setTamaño(SistemaCarga.TEXTURAS, tamaño);
			panelBD.cambiarTamaño();
			editor.actualizarListaTexturas();
		}
		if (panelBD.getTipoPanel() == FactoriaRecursosVacios.OBJETO)
		{
			editor.getSistemacarga().setTamaño(SistemaCarga.OBJETOS, tamaño);
			panelBD.cambiarTamaño();
			//editor.actualizarListaObjetos();
		}
	}

}
