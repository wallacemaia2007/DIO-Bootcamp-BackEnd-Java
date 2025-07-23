package Desafios;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Desafio14 {

    public static void main(String[] args) {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 5, 4, 3);

        OptionalInt maiorPrimo = numeros.stream()
                                       .mapToInt(x -> x)
                                       .filter(Desafio14::temPrimo)
                                       .max();

            System.out.println("O maior primo é: " + maiorPrimo.getAsInt());

    }

    public static boolean temPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero <= 3) return true;  

        if (numero % 2 == 0 || numero % 3 == 0) return false;

        for (int i = 5; i * i <= numero; i++) { 
            if (numero % i == 0) return false;
        }

        return true;
    }
}

