package system;

import acoes.AparelhoTelefonico;
import acoes.NavegadorNaInternet;
import acoes.ReprodutorMusical;

public class IPhone implements ReprodutorMusical, AparelhoTelefonico, NavegadorNaInternet {

	private String musica;
	private String numero;
	private String url;

	@Override
	public void tocar() {
		if (musica != null) {
			System.out.println("O Iphone está tocando música!");
		} else {
			System.err.println("Primeiro selecione uma música!");
		}
	}

	@Override
	public void pausar() {
		System.out.println("O Iphone parou de toocar a música");

	}

	@Override
	public void selecionarMusica(String musica) {
		System.out.println("A música " + musica + " foi selecionada!");
		setMusica(musica);

	}

	@Override
	public void ligar(String numero) {
		System.out.println("Ligando para " + numero);
		setNumero(numero);
		System.out.println("Chamando.....");

	}
	@Override
	public void desligar() {
		if (numero != null) {
			System.out.println("Desligando a ligação!");
		} else {
			System.err.println("Primeiro ligue para um número!");
		}
	}
	

	@Override
	public void atender() {
		if (numero != null) {
			System.out.println("Atendendo a ligação!");
		} else {
			System.err.println("Primeiro ligue para um número!");
		}

	}

	@Override
	public void iniciarCorreioVoz() {
		System.out.println("Correio de voz iniciado!");

	}

	@Override
	public void exibirPagina(String url) {
		System.out.println("Carregando a página " + url);
		setUrl(url);

	}

	@Override
	public void adicionarNovaAba() {
		System.out.println("Adicionando uma nova aba!");

	}

	@Override
	public void atualizarPagina() {
		if (url != null) {
			System.out.println("Atualizando a página!");
		} else {
			System.err.println("Primeiro escolha o site a ser exibido!");
		}

	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
