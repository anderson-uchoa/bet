package lps.bet.interfaces;

import lps.bet.basico.tiposDados.*;

import java.util.Collection;
import java.util.List;

public interface ICartaoMgt {

    public boolean validarCartao(int cartaoID);

    public TipoPassageiro buscarTipoPassagPorCartao(int cartaoID);

    public float buscarSaldo(int cartaoID);

    public boolean podeDebitar(int cartaoID, float valor);

    //Com essa assinatura, e possivel buscar os tipos permitidos no ato da alteracao
    public Collection buscarTiposPermitidos(Passageiro passageiro, TipoPassageiro tipoSelecionado);

    public Collection buscarTiposPermitidos(Passageiro passageiro);
    public Collection buscarTiposPermitidos(Cartao cartao);

    public void carregarCartao(int cartaoID, float valor);

    public List buscarViagens();

    public List buscarViagensPorCartao(int cartaoID);

    public Viagem buscarUltimaViagem(int cartaoID);

    public Viagem buscarViagem(int viagemID);

    public void criarViagem(Viagem viagem);

    public void alterarViagem(Viagem viagem);

    public void removerViagem(int viagemID);

    public List buscarCartoes();

    public Cartao buscarCartao(int cartaoID);

    public void criarCartao(Cartao cartao);

    public void alterarCartao(Cartao cartao);

    public void removerCartao(int cartaoID);

    public Pagamento buscarPagamento(int pgtoID);

    public int criarPagamento(Pagamento pagamento);

    public void alterarPagamento(Pagamento pagamento);

    public List buscarPagamentos();

    public void removerPagamento(int pgtoID);

    public List buscarTiposPassageiros();

    public TipoPassageiro buscarTipoPassageiro(int tipoID);

    public TipoPassageiro buscarTipoPassageiro(String nomeTipo);

    public void criarTipoPassageiro(TipoPassageiro tipo);

    public void alterarTipoPassageiro(TipoPassageiro tipo);

    public void removerTipoPassageiro(int tipoID);

}
