package lps.bet.variabilidades.viacaoTempoMgr;

import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;


public class ViacaoTempoMgr implements IObterTempo{

	IViacaoMgt interfaceViacaoMgt;
	ITempoMgt interfaceTempoMgt;
	
	public int obterTempo() {
		return interfaceTempoMgt.buscarTempo();
	}

	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}

	public ITempoMgt getInterfaceTempoMgt() {
		return interfaceTempoMgt;
	}

	public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
		this.interfaceTempoMgt = interfaceTempoMgt;
	}	
}
