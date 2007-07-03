package maker.modelo;

import java.io.File;

import maker.controlador.IControlador;

public interface IModelo
{
	public void registrarControlador(IControlador controller);
	
	public IControlador getControlador();

	public MapaAbstracto getMapa();
		
	public void crearMapa(int filas, int columnas, String nombre);

	/** Guarda el mapa actual dentro del directorio especificado como
	 * par�metro en la carpeta mapas.
	 *@param directorio El directorio ra�z del proyecto.*/
	public void guardar(File directorio);

	public void cargar(File f);

	public void crearMapaCaminos(String nombre);
}
