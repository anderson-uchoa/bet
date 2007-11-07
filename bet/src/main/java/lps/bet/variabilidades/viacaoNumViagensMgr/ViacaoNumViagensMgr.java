package lps.bet.variabilidades.viacaoNumViagensMgr;

import lps.bet.basico.viacaoMgr.IViacaoMgt;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;

public class ViacaoNumViagensMgr implements IObterNumViagens{

	INumViagensMgt interfaceNumViagensMgt;
	IViacaoMgt interfaceViacaoMgt;
	
	public int obterNumViagens() {
		return interfaceNumViagensMgt.buscarMaxNumViagens();
	}

	public INumViagensMgt getInterfaceNumViagensMgt() {
		return interfaceNumViagensMgt;
	}

	public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
		this.interfaceNumViagensMgt = interfaceNumViagensMgt;
	}

	public IViacaoMgt getInterfaceViacaoMgt() {
		return interfaceViacaoMgt;
	}

	public void setInterfaceViacaoMgt(IViacaoMgt interfaceViacaoMgt) {
		this.interfaceViacaoMgt = interfaceViacaoMgt;
	}	
}
