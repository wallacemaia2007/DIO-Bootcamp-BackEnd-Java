package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio9 {

	public static void main(String[] args) {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

		List<Integer> numerosSemDuplicados = numeros.stream().distinct().toList();

		Integer tamanho1 = numeros.size();
		Integer tamanho2 = numerosSemDuplicados.size();
		if (tamanho1 - tamanho2 > 0) {
			System.out.println("Há números duplicados!");
		}else {
			System.out.println("Não há números duplicados!");			
		}

	}

}
