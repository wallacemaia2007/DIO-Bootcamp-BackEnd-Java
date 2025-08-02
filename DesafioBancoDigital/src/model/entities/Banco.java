package model.entities;

import java.util.HashSet;
import java.util.Set;

public class Banco {

	private String nome;
	private Set<Cliente> clientes = new HashSet<Cliente>();

	private boolean achou = false;

	public Banco(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Set<Cliente> getCliente() {
		return clientes;
	}

	public void adicionarCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}

	public void removerCliente(Cliente cliente) {
		this.clientes.remove(cliente);
	}

	public boolean isAchou() {
		return achou;
	}

	public Cliente acharCliente(String Cpf) {
		for (Cliente cliente : clientes) {
			if (cliente.getCpf().equals(Cpf)) {
				return cliente;
			}
		}
		return null;
	}

	public void alterarDadosCliente(String cpf, String nomeNovo, String cpfNovo) {
		acharCliente(cpf).setNome(nomeNovo);
		acharCliente(cpf).setCpf(cpfNovo);
	}
}
