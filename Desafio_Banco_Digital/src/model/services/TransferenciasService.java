package model.services;

import java.util.Scanner;

import exceptions.DomainException;
import model.entities.Banco;
import model.entities.Cliente;

public class TransferenciasService {
	public static Scanner ln = new Scanner(System.in);

	public void deposito(Cliente cliente, Banco banco) {
		try {

			System.out.println("Digite a quantia para ser depositada : ");
			Double valor = ln.nextDouble();
			System.out.println("Será depositada na conta corrente ou popança ?");
			ln.nextLine();
			String tipoConta = ln.nextLine().toUpperCase().replace('Ç', 'C');
			
			if (tipoConta.isEmpty() || valor == null) {
				throw new DomainException("Preencha corretamente os campos");
			}
			

			if (tipoConta.contains("POUPANCA")) {
				if (!cliente.isPossuiContaPoupanca()) {
					throw new DomainException("O cliente " + cliente.getNome() + " não possui conta Poupança!");
				} else {
					cliente.getContaPoupanca().depositar(valor);
					System.out.println("O valor de $" + valor
							+ " reais foi depositado com sucesso na conta Poupança de " + cliente.getNome());
				}

			} else if (tipoConta.contains("CORRENTE")) {
				if (!cliente.isPossuiContaCorrente()) {
					throw new DomainException("O cliente " + cliente.getNome() + " não possui conta Corrente!");
				} else {
					cliente.getContaCorrente().depositar(valor);
					System.out.println("O valor de $" + valor
							+ " reais foi depositado com sucesso na conta Corrente de " + cliente.getNome());
				}

			} else {
				throw new DomainException("Tipo de conta inválida!");
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void sacar(Cliente cliente, Banco banco) {
		try {

			System.out.println("Digite a quantia para ser sacada : ");
			Double valor = ln.nextDouble();
			System.out.println("Será sacada da conta corrente ou popança ?");
			ln.nextLine();
			String tipoConta = ln.nextLine().toUpperCase().replace('Ç', 'C');
			
			if (tipoConta.isEmpty() || valor == null) {
				throw new DomainException("Preencha corretamente os campos");
			}

			if (tipoConta.contains("POUPANCA")) {
				if (!cliente.isPossuiContaPoupanca()) {
					throw new DomainException("O cliente " + cliente.getNome() + " não possui conta Poupança!");

				} else {
					if (cliente.getContaPoupanca().sacar(valor))
						System.out.println("O valor de $" + valor
								+ " reais foi sacada com sucesso na conta Poupança de " + cliente.getNome());
				}

			} else if (tipoConta.contains("CORRENTE")) {
				if (!cliente.isPossuiContaCorrente()) {
					throw new DomainException("O cliente " + cliente.getNome() + " não possui conta Corrente!");
				} else {
					if (cliente.getContaCorrente().sacar(valor))
						System.out.println("O valor de $" + valor
								+ " reais foi sacada com sucesso na conta Corrente de " + cliente.getNome());
				}

			} else {
				throw new DomainException("Tipo de conta inválida!");
			}

		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public void transferir(Cliente cliente, Banco banco) {
		try {

			System.out.println("Será transferido da conta corrente ou poupança?");
			String tipoConta = ln.nextLine().toUpperCase().replace('Ç', 'C');

			System.out.println("Digite a quantia para ser transferida:");
			Double valor = ln.nextDouble();
			ln.nextLine();

			System.out.println("Agora digite o CPF de quem irá receber o pagamento:");
			String cpfFinal = ln.nextLine();
			
			if (tipoConta.isEmpty() || valor == null || cpfFinal.isEmpty()) {
				throw new DomainException("Preencha corretamente os campos");
			}

			Cliente clienteDestino = banco.acharCliente(cpfFinal);
			if (clienteDestino == null) {
				throw new DomainException("Este cliente não possui um cadastro no banco!");
			}

			if (tipoConta.contains("CORRENTE") && !cliente.isPossuiContaCorrente()) {
				throw new DomainException("Você não possui uma conta corrente!");
			} else if (tipoConta.contains("POUPANCA") && !cliente.isPossuiContaPoupanca()) {
				throw new DomainException("Você não possui uma conta poupança!");
			}

			char tipoContaDestino;

			if (clienteDestino.isPossuiContaCorrente() && clienteDestino.isPossuiContaPoupanca()) {
				System.out.println("O cliente destino possui conta corrente e poupança. Para qual deseja transferir?");
				tipoContaDestino = ln.nextLine().toUpperCase().replace('Ç', 'C').contains("POUPANCA") ? 'P' : 'C';
			} else if (clienteDestino.isPossuiContaCorrente()) {
				tipoContaDestino = 'C';
			} else if (clienteDestino.isPossuiContaPoupanca()) {
				tipoContaDestino = 'P';
			} else {
				throw new DomainException("O cliente destino não possui nenhuma conta válida!");
			}

			boolean sucesso = false;

			if (tipoConta.contains("CORRENTE")) {
				sucesso = cliente.getContaCorrente().transferir(valor, clienteDestino, tipoContaDestino);
			} else if (tipoConta.contains("POUPANCA")) {
				sucesso = cliente.getContaPoupanca().transferir(valor, clienteDestino, tipoContaDestino);
			} else {
				throw new DomainException("Tipo de conta de origem inválida!");
			}

			if (sucesso) {
				String tipoDest = (tipoContaDestino == 'C') ? "Conta Corrente" : "Conta Poupança";
				System.out.printf("Transferência de R$ %.2f realizada com sucesso para %s de %s.%n", valor, tipoDest,
						clienteDestino.getNome());
			} else {
				throw new DomainException("Transferência falhou. Verifique o saldo ou os dados informados.");
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
