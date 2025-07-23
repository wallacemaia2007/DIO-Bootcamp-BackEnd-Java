package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio15 {

	public static void main(String[] args) {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

        Boolean contem = numeros.stream().anyMatch(x -> x < 0);
        
        System.out.println(contem ? "Possui numeros negativos" : "Não possui numeros negativos");
        
	}

}
