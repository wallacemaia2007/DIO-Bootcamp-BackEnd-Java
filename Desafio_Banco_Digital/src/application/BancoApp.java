package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.DomainException;
import model.entities.Banco;
import model.entities.Cliente;
import model.entities.ContaCorrente;
import model.entities.ContaPoupanca;
import model.services.FileService;
import model.services.TransferenciasService;

public class BancoApp {

	public static Scanner ln = new Scanner(System.in);
	public static Banco banco = new Banco("Banco Santander");
	public static FileService fileService = new FileService();

	public static void iniciarBanco() {
		System.out.println("Bem-vindo ao banco!");
		System.out.println("Para primeiro acesso vamos cadastra-lo");
		while (true) {
			try {
				String nome;
				String cpf;
				do {

					System.out.println("Digite seu nome: ");
					nome = ln.nextLine();
					System.out.println("Digite seu Cpf: ");
					cpf = ln.nextLine();
					if (nome.isEmpty() || cpf.isEmpty()) {
						throw new DomainException("Preencha corretamente os campos");
					}

				} while (nome.isEmpty() || cpf.isEmpty());

				System.out.println("Cliente criado com sucesso!");
				Cliente cliente = new Cliente(nome, cpf);
				banco.adicionarCliente(cliente);
				break;

			} catch (DomainException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	public static void menu() {
		try {
			
			int opcao = -1;

			System.out.println("Oque deseja realizar? ");
			System.out.println("1 - Cadastrar novo Cliente");
			System.out.println("2 - Criar Conta");
			System.out.println("3 - Realizar transação");
			System.out.println("4 - Verificar saldo da conta");
			System.out.println("5 - Alterar informações da conta");
			System.out.println("6 - Imprimir comprovante da ultima transação");
			System.out.println("0 - Sair");
			opcao = ln.nextInt();
			ln.nextLine();

			switch (opcao) {

			case 1 -> cadastrarCliente();
			case 2 -> criarConta();
			case 3 -> realizarTransacao();
			case 4 -> verificarSaldoConta();
			case 5 -> alterarInformacoesConta();
			case 6 -> comprovante();
			case 0 ->  {
                System.out.println("Encerrando o programa...");
                System.exit(0);
            }
			default -> throw new DomainException("Opção inválida! Tente novamente");
			}
			return;

		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		} catch(InputMismatchException e) {
			System.err.println("Error: Opção inválida! Tente novamente");	
			ln.nextLine();
		}
	}

	public static void alterarInformacoesConta() {
		try {

			System.out.println("Digite o CPF da conta a ser alterada: ");
			var cpf = ln.nextLine();

			Cliente cliente = banco.acharCliente(cpf);

			if (cliente == null) {
				throw new DomainException("Este cliente não possui um cadastro no banco!");
			} else {

				System.out.println("Digite o novo nome: ");
				var nomeNovo = ln.nextLine();
				System.out.println("Digite o novo CPF: ");
				var cpfNovo = ln.nextLine();

				if (banco.acharCliente(cpfNovo) == null || cpfNovo.equals(cpf)) {
					banco.alterarDadosCliente(cpf, nomeNovo, cpfNovo);
					System.out.println("Dados alterados com sucesso!");
				} else {
					throw new DomainException("Já existe um cliente com esse CPF no sistema!");
				}
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void cadastrarCliente() {
		try {
			System.out.println("Digite seu nome: ");
			var nome = ln.nextLine();
			System.out.println("Digite seu Cpf: ");
			var cpf = ln.nextLine();
			if (nome.isEmpty() || cpf.isEmpty()) {
				throw new DomainException("Preencha corretamente os campos");
			}
			if (banco.acharCliente(cpf) == null) {
				banco.adicionarCliente(new Cliente(nome, cpf));
				System.out.println("Cliente criado com sucesso!");
			} else {
				throw new DomainException("Este cliente já possui um cadastro no banco!");
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void criarConta() {
		try {
			System.out.println("Cpf do cliente: ");
			String cpf = ln.nextLine();

			System.out.println("Digite o tipo de conta a ser criada\nConta Corrente / Conta Poupança\n:");
			String tipoConta = ln.nextLine();

			System.out.println("Digite o saldo inicial da sua conta: ");
			Double saldo = ln.nextDouble();
			if (cpf.isEmpty() || tipoConta.isEmpty() || saldo == null) {
				throw new DomainException("Preencha corretamente os campos");
			}

			Cliente cliente = banco.acharCliente(cpf);

			if (cliente != null) {
				if (tipoConta.toUpperCase().contains("POU")) {

					if (cliente.adicionarContaPoupança(new ContaPoupanca(saldo, cliente)))
						System.out.println("Conta criada com sucesso!");

				} else if (tipoConta.toUpperCase().contains("CORRENTE")) {

					if (cliente.adicionarContaCorrente(new ContaCorrente(saldo, cliente)))
						System.out.println("Conta criada com sucesso!");

				} else {
					throw new DomainException("Tipo de conta inválida!");
				}
			} else {
				throw new DomainException("Não existe nenhum cliente com esse Cpf!");
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void realizarTransacao() {
		try {

			System.out.println("Digite o CPF da conta que será efetuada as operações : ");
			var cpf = ln.nextLine();

			Cliente cliente = banco.acharCliente(cpf);

			if (cliente == null) {
				throw new DomainException("Este cpf não possui um cadastro no banco!");
			} else {

				TransferenciasService transacao = new TransferenciasService();
				int opcao = -1;

				System.out.println("O que deseja realizar? ");
				System.out.println("1 - Realizar Depósito");
				System.out.println("2 - Realizar Saque");
				System.out.println("3 - Realizar Transferência");
				System.out.println("0 - Sair");
				opcao = ln.nextInt();
				ln.nextLine();

				switch (opcao) {
				case 1 -> transacao.deposito(cliente, banco);
				case 2 -> transacao.sacar(cliente, banco);
				case 3 -> {
					if (banco.getCliente().size() < 2) {
						throw new DomainException("Para realizar uma transferência é necessário no mínimo 2 cliente!");
					} else {
						transacao.transferir(cliente, banco);
					}

				}
				case 0 -> menu();
				default -> throw new DomainException("Opção inválida! Tente novamente");
				}
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void verificarSaldoConta() {
		try {

			System.out.println("Digite seu Cpf: ");
			var Cpf = ln.nextLine();
			Cliente cliente = banco.acharCliente(Cpf);
			if (cliente == null)
				throw new DomainException("Não existe nenhum cliente com esse Cpf!");
			else {
				System.out.println("Nome : " + cliente.getNome());
				System.out.println("Cpf : " + cliente.getCpf());
				System.out.print("Possui Conta Corrente : ");
				System.out.println(
						cliente.isPossuiContaCorrente() ? "Sim\n   Saldo : " + cliente.getContaCorrente().getSaldo()
								: "Não");
				System.out.print("Possui Conta Poupança : ");
				System.out.println(
						cliente.isPossuiContaPoupanca() ? "Sim\n   Saldo : " + cliente.getContaPoupanca().getSaldo()
								: "Não");
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void comprovante() {
		try {
			System.out.println("Digite seu Cpf: ");
			var Cpf = ln.nextLine();
			Cliente cliente = banco.acharCliente(Cpf);
			if (cliente == null)
				throw new DomainException("Não existe nenhum cliente com esse Cpf!");
			else {
				fileService.gerarComprovante(cliente);
			}
		} catch (DomainException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
