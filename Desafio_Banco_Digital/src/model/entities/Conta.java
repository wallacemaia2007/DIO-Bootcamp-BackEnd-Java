package model.entities;

import java.util.Objects;

import exceptions.DomainException;

public abstract class Conta {

	protected double saldo;
	protected Cliente cliente;

	private String tipoTransacao;
	private Double valorMovimentado;

	public Conta(double saldo, Cliente cliente) {
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public boolean sacar(double valor) {
		if (valor <= 0) {
			throw new DomainException("O valor do saque deve ser maior que zero.");
		}
		if (valor > saldo) {
			throw new DomainException("Saldo insuficiente.");
		}

		this.saldo -= valor;
		setTipoTransacao("Saque");
		setValorMovimentado(valor);
		return true;
	}

	public boolean depositar(double valor) {
		this.saldo += valor;
		setTipoTransacao("Depósito");
		setValorMovimentado(valor);
		return true;

	}

	public boolean transferir(double valor, Cliente clienteFinal, char charTipoConta) {

		if (valor <= 0) {
			throw new DomainException("O valor do saque deve ser maior que zero.");
		}
		if (valor > saldo) {
			throw new DomainException("Saldo insuficiente.");
		}

		if (charTipoConta == 'P') {

			this.sacar(valor);
			clienteFinal.getContaPoupanca().depositar(valor);
			setTipoTransacao("Transferência");
			setValorMovimentado(valor);

		}
		if (charTipoConta == 'C') {

			this.sacar(valor);
			clienteFinal.getContaCorrente().depositar(valor);
			setTipoTransacao("Transferência");
			setValorMovimentado(valor);

		}
		return true;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(String tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public Double getValorMovimentado() {
		return valorMovimentado;
	}

	public void setValorMovimentado(Double valorMovimentado) {
		this.valorMovimentado = valorMovimentado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(cliente, other.cliente);
	}

}
