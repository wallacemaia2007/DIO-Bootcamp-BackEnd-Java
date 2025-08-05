package Desafios;

import java.util.Arrays;
import java.util.List;

public class Desafio16 {

	public static void main(String[] args) {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

        List<Integer> numerosPares = numeros.stream().filter(x -> x % 2 == 0).toList();
        List<Integer> numerosImpares = numeros.stream().filter(x -> x % 2 != 0).toList();
        
        System.out.println("-=-=-=-=-PARES=-=-=-=-=-=-=");
        numerosPares.forEach(System.out::println);
        System.out.println("-=-=-=-=-iMPARES=-=-=-=-=-=");
        numerosImpares.forEach(System.out::println);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");


        
	}

}
