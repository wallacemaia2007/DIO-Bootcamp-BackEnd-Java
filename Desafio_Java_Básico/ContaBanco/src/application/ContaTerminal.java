package application;

import java.util.Scanner;

public class ContaTerminal {

	public static void main(String[] args) {
		
		Scanner ln = new Scanner(System.in);
		String nomeCliente , agencia;
		int numero;
		float saldo;
		
		System.out.println("Por favor, digite o seu nome: ");
		nomeCliente = ln.next();
		System.out.println("Por favor, digite sua Agência: ");
		agencia = ln.next();
		System.out.println("Por favor, digite o numero da conta: ");
		numero = ln.nextInt();
		System.out.println("Por favor, digite o seu saldo: ");
		saldo = ln.nextFloat();
		
		System.out.printf("Olá %s, "
				+ "obrigado por criar uma conta em nosso banco, "
				+ "sua agência é %s, conta %d "
				+ "e seu saldo %.2f já está disponível para saque",nomeCliente,agencia,numero,saldo);
		

	}

}
