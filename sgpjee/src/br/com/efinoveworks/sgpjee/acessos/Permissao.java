package br.com.efinoveworks.sgpjee.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {

	ADMIN("ADMIN", "Administrador"), USER("USER", "Usuário Padrão"), CADASTRO_ACESSAR("CADASTRO_ACESSAR",
			"Acesso aos Cadastros"), FINANCEIRO_ACESSAR("FINANCEIRO_ACESSAR",
					"Acesso ao Financeiro"), MENSAGEM_ACESSAR("MENSAGEM_ACESSAR", "Acesso as Mensagens"),

	BAIRRO_ACESSAR("BAIRRO_ACESSAR", "Acesso ao Cadastro Bairro"), BAIRRO_NOVO("BAIRRO_NOVO",
			"Acesso ao Cadastro Bairro Novo"), BAIRRO_EDITAR("BAIRRO_EDITAR",
					"Acesso ao Cadastro Bairro Editar"), BAIRRO_EXCLUIR("BAIRRO_EXCLUIR",
							"Acesso ao Cadastro Bairro Excluir"),

	CIDADE_ACESSAR("CIDADE_ACESSAR", "Acesso ao Cadastro Cidade"), CIDADE_NOVO("CIDADE_NOVO",
			"Acesso ao Cadastro Cidade Novo"), CIDADE_EDITAR("CIDADE_EDITAR",
					"Acesso ao Cadastro Cidade Editar"), CIDADE_EXCLUIR("CIDADE_EXCLUIR",
							"Acesso ao Cadastro Cidade Excluir");

	private String valor = "";
	private String descricao = "";

	private Permissao() {

	}

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getValor();
	}
	
	public static List<Permissao> getListPermissao(){
		
		List<Permissao> permissoes = new ArrayList<Permissao>();
		
		for(Permissao permissao: Permissao.values()){
			permissoes.add(permissao);
		}
		
		Collections.sort(permissoes, new Comparator<Permissao>(){

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(o2.ordinal());
			}
			
		});
		
		return permissoes;
	}

}
