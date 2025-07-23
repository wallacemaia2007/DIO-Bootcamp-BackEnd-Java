package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio18 {

	public static void main(String[] args) {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);
        
        int primeiro = numeros.get(0);
        Boolean resposta = numeros.stream().allMatch(x -> x.equals(primeiro));
        
        System.out.println(resposta ?"Todos os numeros sao iguais" : "Nem todos os numeros sao iguais");

        
	}

}
