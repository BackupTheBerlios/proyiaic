package maker.vista.baseDatos;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

import maker.modelo.recursos.Recurso;

public abstract class PanelBaseDatos extends JPanel 
{
	public PanelBaseDatos() {}
	
	public abstract int getTipoPanel();

	public abstract void eliminarElemento(Recurso rec, int selec);

	public abstract void cambiarSeleccionado(Recurso rec);

	public abstract void cargarObjetos(DefaultListModel dlm);

	public abstract void cambiarTamaño();
	
	protected abstract void guardarObjeto(Recurso tex, int selec); 
}