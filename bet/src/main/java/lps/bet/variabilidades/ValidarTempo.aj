package lps.bet.variabilidades;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import lps.bet.variabilidades.tempoMgr.ITempoMgt;
import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.tiposDados.Viagem;

public aspect ValidarTempo {

/*	ITempoMgt interfaceTempoMgt;
	ICartaoMgt interfaceCartaoMgt;

	public ITempoMgt getInterfaceTempoMgt() {
		return interfaceTempoMgt;
	}
	public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
		this.interfaceTempoMgt = interfaceTempoMgt;
	}
	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}
	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}
*/
	pointcut validarViagem(): execution(* lps.bet.basico.cartaoMgr.ICartaoMgt.buscarTipoPassagPorCartao(String));
	
	after() : validarViagem(){

/*		Object[] args = thisJoinPoint.getArgs();
		if (args.length > 0){
			int cartaoID = (Integer) args[0];

			//Verificar se est� dentro do tempo para realizar integra��o
			int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
			//De in�cio considera-se que n�o haver� integra��o, ent�o o tempo passou do que poderia ser:
			long tempoDecorrido = tempoMaxIntegracao+1;

			Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

			//Viagem dentro do tempo de integra��o
			if(viagem != null){
				Calendar horaUltimaViagem = viagem.getHora();
				tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
				if (tempoDecorrido <= tempoMaxIntegracao*1000){
					//N�o recebe dinheiro, mas incrementa o numero de passageiros
//					interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);
//					estado="INT-OK";
				}
			}
			//Viagem fora do tempo de integra��o: nova viagem
			else if ((viagem == null) || (tempoDecorrido > tempoMaxIntegracao*1000)){	
			}
		}
		*/
	}
}