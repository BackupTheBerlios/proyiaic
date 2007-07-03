package maker.recursos;

public interface Cargador 
{
	public Object getRecurso(int i);
	
	public void setRecurso(int i, Object recurso);
	
	public void modificarTamaño(int n_tamaño);
	
	public int getTamaño();
	
	public void guardar();
}
