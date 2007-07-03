package maker.recursos;

public interface Cargador 
{
	public Object getRecurso(int i);
	
	public void setRecurso(int i, Object recurso);
	
	public void modificarTamaņo(int n_tamaņo);
	
	public int getTamaņo();
	
	public void guardar();
}
