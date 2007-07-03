package maker.vista.baseDatos;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import maker.modelo.recursos.FactoriaRecursosVacios;
import maker.modelo.recursos.Recurso;
import maker.vista.baseDatos.OyenteListaTexturas.ListaSuprimir;
import maker.vista.baseDatos.OyenteListaTexturas.SeleccionLista;

public class Lista extends JList
{
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel dlm;

	private PanelBaseDatos panelbasedatos;

	public Lista(PanelBaseDatos panbasedatos)
	{
		this.panelbasedatos = panbasedatos;
		dlm = new DefaultListModel();
		setModel(dlm);
		
		addKeyListener(new ListaSuprimir(this));
		addListSelectionListener(new SeleccionLista(this));
		cargarObjetos();
		setSelectedIndex(0);
	}

	public void cargarObjetos() 
	{
		panelbasedatos.cargarObjetos(dlm);
		setSelectedIndex(0);
	}

	public void borrarSeleccionado() 
	{
		int selec = getSelectedIndex();
		Recurso rec = FactoriaRecursosVacios.crearRecursoVacio(panelbasedatos.getTipoPanel());
		panelbasedatos.eliminarElemento(rec, selec);
		dlm.add(selec, rec);
		dlm.remove(selec + 1);
		repaint();
	}
	
	public void cambiarSeleccionado()
	{
		if (!isSelectionEmpty())
		{
			Recurso rec = (Recurso)getSelectedValue();
			panelbasedatos.cambiarSeleccionado(rec);
		}
	}

	public void aplicar(Recurso rec, int selec) 
	{
		dlm.add(selec, rec);
		dlm.remove(selec + 1);
	}
}
