package application;

import java.util.Locale;
import java.util.Scanner;

import system.IPhone;

public class Main {

	public static final Scanner sc = new Scanner(System.in);
	public static final IPhone cell = new IPhone();

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		int funcao;

		do {
			System.out.println("\n|=-=-=-=-=-=-=-=-=-=-= Menu iPhone =-=-=-=-=-=-=-=-=-=-=-=|");
			System.out.println("| Selecione uma função para executar no iPhone:             |");
			System.out.println("|  1 - Reprodutor Musical                                   |");
			System.out.println("|  2 - Navegador de Internet                                |");
			System.out.println("|  3 - Aplicativo de Telefone                               |");
			System.out.println("|  0 - Desligar iPhone                                      |");
			System.out.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=|");
			System.out.print("Opção: ");
			funcao = sc.nextInt();

			switch (funcao) {
			case 1 -> abrirReprodutorMusical();
			case 2 -> abrirNavegador();
			case 3 -> abrirTelefone();
			case 0 -> System.out.println("Desligando o iPhone...");
			default -> System.err.println("Opção inválida! Tente novamente.");
			}

		} while (funcao != 0);
	}

	public static void abrirReprodutorMusical() {
		int opcao;
		do {
			System.out.println("\n--- Reprodutor Musical ---");
			System.out.println("1 - Tocar música");
			System.out.println("2 - Pausar música");
			System.out.println("3 - Selecionar música");
			System.out.println("0 - Voltar ao menu principal");
			System.out.print("Opção: ");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1 -> cell.tocar();
			case 2 -> cell.pausar();
			case 3 -> {
				System.out.print("Digite o nome da música: ");
				String musica = sc.nextLine();
				cell.selecionarMusica(musica);
			}
			case 0 -> System.out.println("Voltando ao menu principal...");
			default -> System.err.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);
	}

	public static void abrirNavegador() {
		int opcao;
		do {
			System.out.println("\n--- Navegador de Internet ---");
			System.out.println("1 - Adicionar nova aba");
			System.out.println("2 - Atualizar página");
			System.out.println("3 - Acessar URL");
			System.out.println("0 - Voltar ao menu principal");
			System.out.print("Opção: ");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1 -> cell.adicionarNovaAba();
			case 2 -> cell.atualizarPagina();
			case 3 -> {
				System.out.print("Digite a URL do site: ");
				String url = sc.nextLine();
				cell.exibirPagina(url);
			}
			case 0 -> System.out.println("Voltando ao menu principal...");
			default -> System.err.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);
	}

	public static void abrirTelefone() {
		int opcao;
		do {
			System.out.println("\n--- Aplicativo de Telefone ---");
			System.out.println("1 - Atender ligação");
			System.out.println("2 - Desligar ligação");
			System.out.println("3 - Ligar para um número");
			System.out.println("4 - Iniciar correio de voz");
			System.out.println("0 - Voltar ao menu principal");
			System.out.print("Opção: ");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1 -> cell.atender();
			case 2 -> cell.desligar();
			case 3 -> {
				System.out.print("Digite o número de telefone: ");
				String numero = sc.nextLine();
				cell.ligar(numero);
			}
			case 4 -> cell.iniciarCorreioVoz();
			case 0 -> System.out.println("Voltando ao menu principal...");
			default -> System.err.println("Opção inválida! Tente novamente.");
			}
		} while (opcao != 0);
	}
}
