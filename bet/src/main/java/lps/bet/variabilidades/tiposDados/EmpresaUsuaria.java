package lps.bet.variabilidades.tiposDados;

import java.util.Collection;

import lps.bet.basico.tiposDados.Passageiro;
import lps.bet.basico.tiposDados.Usuario;

public class EmpresaUsuaria extends Usuario{

	private int empresaID;
	private String contato;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String endereco;
	private String telefone; //na documentação estava como int
	private String email;
	private Collection<Passageiro> passageiros;

	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public int getEmpresaID() {
		return empresaID;
	}
	public void setEmpresaID(int empresaID) {
		this.empresaID = empresaID;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Passageiro> getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(Collection<Passageiro> passageiros) {
		this.passageiros = passageiros;
	}	
}
