/*
 * ControlarCorrida.java
 *
 * Created on 14 de Junho de 2007, 20:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.controlarCorrida;

import lps.bet.basico.linhaMgr.IAtualizarCorrida;

/**
 *
 * @author Paula
 */
public class CorridaCtrl implements IRegistrarCorrida {
    
    IAtualizarCorrida interfaceAtualizarCorrida;
    

	public IAtualizarCorrida getInterfaceAtualizarCorrida() {
		return interfaceAtualizarCorrida;
	}

	public void setInterfaceAtualizarCorrida(IAtualizarCorrida atualizarCorrida) {
		this.interfaceAtualizarCorrida = atualizarCorrida;
	}

	/** Creates a new instance of ControlarCorrida */
    public CorridaCtrl() {
    }
    
    public String registrarCorrida(int onibusID){
        String resposta = new String();
        System.out.println("Controlar Corrida-> OnibusID: " + onibusID);
        resposta = interfaceAtualizarCorrida.atualizarCorrida(onibusID);
        return resposta;
   }

}
