package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio10 {

	public static void main(String[] args) {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

		List<Integer> numerosImpares = numeros.stream().filter(x -> x % 2 != 0).toList();
		
		List<Integer> numerosMultiplos = numerosImpares.stream()
				.filter(x -> x % 3 == 0 || x % 5 == 0).toList();
		
		numerosMultiplos.forEach(System.out::println);

	}

}
