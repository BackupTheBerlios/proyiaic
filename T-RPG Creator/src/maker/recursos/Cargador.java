package maker.recursos;

public interface Cargador 
{
	public Object getRecurso(int i);
	
	public void setRecurso(int i, Object recurso);
	
	public void modificarTama�o(int n_tama�o);
	
	public int getTama�o();
	
	public void guardar();
}
