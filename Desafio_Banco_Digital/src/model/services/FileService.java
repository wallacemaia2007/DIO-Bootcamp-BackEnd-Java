package model.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.entities.Cliente;

public class FileService {
	public void gerarComprovante(Cliente cliente) {
		String caminhoAreaDeTrabalho = System.getProperty("user.home") + File.separator + "Desktop";
		String caminho = caminhoAreaDeTrabalho + File.separator + "fatura_";
		int contador = 1;
		String caminhoFinal;

		do {
			caminhoFinal = caminho + contador + ".txt";
			contador++;
		} while (new File(caminhoFinal).exists());

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoFinal))) {
			bw.write("========== FATURA BANCÁRIA ==========");
			bw.newLine();
			bw.write("Cliente: " + cliente.getNome());
			bw.newLine();
			bw.write("CPF: " + cliente.getCpf());
			bw.newLine();
			bw.write("======================================");
			bw.newLine();
			bw.write("====== INFORMAÇÕES DE TRANSAÇÃO ======");
			bw.newLine();

			if (cliente.isPossuiContaCorrente()) {
				bw.write("Conta Corrente ");
				bw.newLine();
				bw.write("Saldo :  R$ " + cliente.getContaCorrente().getSaldo());
				bw.newLine();
				if (cliente.getContaCorrente().getTipoTransacao() != null) {
					transacao(cliente.getContaCorrente().getTipoTransacao(),
							cliente.getContaCorrente().getValorMovimentado(), bw);
					bw.newLine();
				}

			}

			if (cliente.isPossuiContaPoupanca()) {
				bw.write("Conta Poupança ");
				bw.newLine();
				bw.write("Saldo :  R$ " + cliente.getContaPoupanca().getSaldo());
				bw.newLine();
				if (cliente.getContaPoupanca().getTipoTransacao() != null) {
					transacao(cliente.getContaPoupanca().getTipoTransacao(),
							cliente.getContaPoupanca().getValorMovimentado(), bw);
					bw.newLine();
				}

			}
			bw.write("======================================");


			System.out.println("Sua fatura foi gerada na área de trabalho!");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private void transacao(String tipoTransacao, Double valorMovimentado, BufferedWriter bw) throws IOException {
		if (tipoTransacao.equals("Saque")) {
			bw.write("Saque de R$ " + valorMovimentado);
		} else if (tipoTransacao.equals("Depósito")) {
			bw.write("Depósito de R$ " + valorMovimentado);
		} else if (tipoTransacao.equals("Transferência")) {
			bw.write("Transferência de R$ " + valorMovimentado);
		}
		bw.newLine();
	}
}
