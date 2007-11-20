/*
 * IRegistrarViagem.java
 *
 * Created on 26 de Agosto de 2007, 20:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.interfaces;

import lps.bet.basico.tiposDados.Linha;

public interface IRegistrarViagem {
    
	public void debitarPassagem(int cartaoID, float valor);
	
	public void registrarViagem(int cartaoID, Linha linha);
	
}
