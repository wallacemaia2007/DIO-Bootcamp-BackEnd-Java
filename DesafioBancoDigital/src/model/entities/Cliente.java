package model.entities;

import java.util.HashSet;
import java.util.Set;

import exceptions.DomainException;

public class Cliente {

	private String nome;
	private String cpf;

	private Set<Conta> contas = new HashSet<Conta>();

	private boolean possuiContaCorrente = false;
	private boolean possuiContaPoupanca = false;

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ContaCorrente getContaCorrente() {
		for (Conta conta : contas) {
			if (conta instanceof ContaCorrente) {
				return (ContaCorrente) conta;
			}
		}
		return null;
	}

	public ContaPoupanca getContaPoupanca() {
		for (Conta conta : contas) {
			if (conta instanceof ContaPoupanca) {
				return (ContaPoupanca) conta;
			}
		}
		return null;
	}

	public boolean isPossuiContaCorrente() {
		return possuiContaCorrente;
	}

	public void setPossuiContaCorrente(boolean possuiContaCorrente) {
		this.possuiContaCorrente = possuiContaCorrente;
	}

	public boolean isPossuiContaPoupanca() {
		return possuiContaPoupanca;
	}

	public void setPossuiContaPoupanca(boolean possuiContaPoupanca) {
		this.possuiContaPoupanca = possuiContaPoupanca;
	}

	public boolean adicionarContaCorrente(ContaCorrente contaCorrente) {
		if (!isPossuiContaCorrente()) {

			contas.add(contaCorrente);
			setPossuiContaCorrente(true);
			return true;

		} else {
			throw new DomainException("Só pode haver uma conta corrente por cliente!");
		}

	}

	public boolean adicionarContaPoupança(ContaPoupanca contaPoupanca) {
		if (!isPossuiContaPoupanca()) {

			contas.add(contaPoupanca);
			setPossuiContaPoupanca(true);
			return true;

		} else {
			throw new DomainException("Só pode haver uma conta poupança por cliente!");
		}
	}

	public Set<Conta> getContas() {
		return contas;
	}

}
