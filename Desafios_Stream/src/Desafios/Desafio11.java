package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio11 {
	public static void main(String[] args) {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);
		
		Integer soma = numeros.stream().map(x -> x*x).reduce(0,Integer::sum);
		
		System.out.println("A soma do quadrado dos números da lista é : " + soma);

		
		
	}
}
