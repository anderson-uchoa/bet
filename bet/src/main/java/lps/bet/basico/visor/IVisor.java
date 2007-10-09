package lps.bet.basico.visor;

public interface IVisor {
	public void removeVisorListener (VisorListener listener);
	public void addVisorListener (VisorListener listener);
	public void mostrar(String msg);
}
