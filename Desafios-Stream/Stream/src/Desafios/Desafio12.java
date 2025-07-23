package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio12 {

	public static void main(String[] args) {

		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

		
		Integer produto = numeros.stream().reduce(1,(x,y) -> x * y);
		
		System.out.println("O produto dos elementos na lista é de : "+produto);

	}

}
