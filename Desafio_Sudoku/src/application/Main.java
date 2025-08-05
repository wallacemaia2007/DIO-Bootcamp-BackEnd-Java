package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

import model.entities.Board;
import model.entities.Space;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_LIMIT = 9;

    private static final String BOARD_TEMPLATE =
            "%s %s %s | %s %s %s | %s %s %s\n".repeat(9);

    public static void main(String[] args) {
        final var positions = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],    
                        v -> v.split(";")[1]   
                ));

        var option = -1;
        while (true) {
            System.out.println("\nSelecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("O jogo já foi iniciado");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.getOrDefault("%d,%d".formatted(i, j), "0,false");
                var parts = positionConfig.split(",");
                int expected = 0;
                boolean fixed = false;
                try {
                    expected = Integer.parseInt(parts[0]);
                    fixed = Boolean.parseBoolean(parts[1]);
                } catch (Exception e) {
                    System.out.printf("Erro ao interpretar posição %d,%d. Usando valores padrão.\n", i, j);
                }
                spaces.get(i).add(new Space(expected, fixed));
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar");
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna onde o número será inserido (0 a 8):");
        int col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha onde o número será inserido (0 a 8):");
        int row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%d,%d] (1 a 9):\n", col, row);
        int value = runUntilGetValidNumber(1, 9);

        if (!board.changeValue(col, row, value)) {
            System.out.printf("A posição [%d,%d] tem um valor fixo e não pode ser alterada\n", col, row);
        }
    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna onde o número será removido (0 a 8):");
        int col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha onde o número será removido (0 a 8):");
        int row = runUntilGetValidNumber(0, 8);

        if (!board.clearValue(col, row)) {
            System.out.printf("A posição [%d,%d] tem um valor fixo e não pode ser removida\n", col, row);
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        Object[] args = new Object[81];
        int argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (var col : board.getSpaces()) {
                Integer val = col.get(i).getActual();
                args[argPos++] = (val == null ? " " : val.toString());
            }
        }

        System.out.println("Seu jogo se encontra da seguinte forma:");
        System.out.printf(BOARD_TEMPLATE, args);
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status: %s\n", board.getStatus().getLabel());
        if (board.hasErrors()) {
            System.out.println("O jogo contém erros");
        } else {
            System.out.println("O jogo não contém erros");
        }
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/não)");
        String confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")) {
            System.out.println("Informe 'sim' ou 'não'");
            confirm = scanner.next();
        }

        if (confirm.equalsIgnoreCase("sim")) {
            board.reset();
            System.out.println("Jogo limpo com sucesso.");
        } else {
            System.out.println("Limpeza do jogo cancelada.");
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        if (board.gameIsFinished()) {
            System.out.println("Parabéns! Você concluiu o jogo!");
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("Seu jogo contém erros, verifique seu tabuleiro e ajuste-o.");
        } else {
            System.out.println("Você ainda precisa preencher algum espaço.");
        }
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        int current;
        while (true) {
            if (scanner.hasNextInt()) {
                current = scanner.nextInt();
                if (current >= min && current <= max) {
                    return current;
                }
            } else {
                scanner.next(); 
            }
            System.out.printf("Informe um número entre %d e %d\n", min, max);
        }
    }
}
