package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio6 {

	public static void main(String[] args) {
		
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

		Boolean tem =  numeros.stream()
		.anyMatch(x -> x > 10);
		
		System.out.println(tem ? "A lista possui ao menos 1 numero maior que 10" : "Todos os numeros são menores que 10" );

	}

}
