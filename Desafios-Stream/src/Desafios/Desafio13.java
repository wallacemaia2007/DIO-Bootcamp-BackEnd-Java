package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio13 {

	public static void main(String[] args) {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

		List<Integer> numerosNoIntervalo = numeros.stream()
				.filter(x -> x >= 5 && x <= 10).toList();
		
		numerosNoIntervalo.forEach(System.out::println);

	}

}
