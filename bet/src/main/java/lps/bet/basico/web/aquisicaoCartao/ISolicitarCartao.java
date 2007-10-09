package lps.bet.basico.web.aquisicaoCartao;

import java.util.Collection;

public interface ISolicitarCartao {
	public Collection buscarTiposPermitidos(long cpf);
	public boolean solicitarCartao(long cpf);
}
