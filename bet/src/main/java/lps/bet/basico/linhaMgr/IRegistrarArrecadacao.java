/*
 * IRegistrarArrecadacao.java
 *
 * Created on 17 de Junho de 2007, 10:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.linhaMgr;

/**
 *
 * @author Paula
 */
public interface IRegistrarArrecadacao {
    
	//Arrecadação manual é registrada:
	public void registrarArrecadacao(int onibusID, float valor);
	
	//Arrecadação por meio de débito em cartão é registrada
    public void registrarCredito(int onibusID, float valor);
}
