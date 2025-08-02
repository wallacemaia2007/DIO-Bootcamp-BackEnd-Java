package application;

public class Main {

	// INTERFACE DO BANCO
	
	public static void main(String[] args) {
		BancoApp.iniciarBanco();
		while (true) {
			BancoApp.menu();
		}

	}
}