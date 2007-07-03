package maker.recursos;

import maker.vista.editor.Editor;

/**
 * @author  David Garc�a
 */
public class SistemaCarga 
{
	private Editor editor;
	
	private CargadorTexturas cargaTex;
	
	private CargadorObjetos cargaObj;
	
	public static final int TEXTURAS = 234;
	public static final int OBJETOS = 235;
	
	public SistemaCarga(Editor ed)
	{
		editor = ed;
		cargaTex = new CargadorTexturas(editor);
		cargaObj = new CargadorObjetos(editor);
	}
	
	public int getTama�o(int lista)
	{
		switch (lista)
		{
			case TEXTURAS : return cargaTex.getTama�o(); 
			case OBJETOS : return cargaObj.getTama�o(); 
		}
		return -1;
	}
	
	public Object getRecurso(int lista, int posicion)
	{
		switch (lista)
		{
			case TEXTURAS : return cargaTex.getRecurso(posicion);
			case OBJETOS : return cargaObj.getRecurso(posicion); 
		}
		return null;
	}
	
	public void setRecurso(int lista, int selec, Object objeto)
	{
		switch (lista)
		{
			case TEXTURAS : cargaTex.setRecurso(selec, objeto); break;
			case OBJETOS : cargaObj.setRecurso(selec, objeto); break;
		}
	}

	public void guardar() 
	{
		cargaTex.guardar();
		cargaObj.guardar();
	}

	public void setTama�o(int lista, int i) 
	{
		switch (lista)
		{
			case TEXTURAS : cargaTex.setTama�o(i); break;
			case OBJETOS : cargaObj.setTama�o(i); break;
		}
	}
}
