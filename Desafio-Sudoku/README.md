# Sudoku Console Game

Este projeto é uma implementação em Java de um jogo estilo Sudoku executado via console. O usuário pode iniciar um jogo, inserir números, remover números, visualizar o tabuleiro, verificar o status do jogo, limpar o progresso e finalizar o jogo.

---

## Funcionalidades

- Inicializar um novo jogo com valores fixos e variáveis.
- Inserir números em posições específicas.
- Remover números de posições não fixas.
- Exibir o estado atual do tabuleiro.
- Verificar o status do jogo (não iniciado, incompleto, completo).
- Detectar erros no tabuleiro, comparando valores inseridos com os esperados.
- Limpar todo o progresso do jogo.
- Finalizar o jogo ao completar corretamente o tabuleiro.

---

## Estrutura do Projeto

- **application.Main**: Classe principal com interface de menu no console, onde o usuário interage.
- **model.entities.Board**: Representa o tabuleiro do jogo, composto por uma matriz de espaços (`Space`).
- **model.entities.Space**: Representa cada espaço do tabuleiro, com valor esperado, valor atual e se é fixo.
- **model.entities.enums.GameStatusEnum**: Enum que representa os status do jogo (`NON_STARTED`, `INCOMPLETE`, `COMPLETE`).

---

## Tecnologias Utilizadas

- **Java 17+** (ou superior recomendado): Uso de recursos modernos como `var`, `Stream API`, `switch` com lambda (`case 1 ->`).
- **Java Collections Framework**: Listas aninhadas para representar o tabuleiro.
- **Java Streams e Lambdas**: Para manipulação e avaliação do estado do jogo.
- **Console Input/Output**: Interação via `Scanner` e menus de texto simples.

---

## Como Executar

1. Compile o projeto:

```bash
javac -d bin $(find src -name "*.java")
```

2. Execute passando as posições iniciais no formato:

```
linha,coluna;valorEsperado,fixo
```

Exemplo:

```bash
java -cp bin application.Main "0,0;5,true" "0,1;3,true" "0,2;0,false" ...
```

3. Use o menu interativo para jogar.

---

## Considerações

- As posições fixas não podem ser alteradas pelo usuário.
- O jogo valida se o tabuleiro está completo e correto antes de permitir a finalização.
- Os índices de linha e coluna começam em 0 e vão até 8 (tabuleiro 9x9).
- Entrada de dados inválida é tratada e solicitada novamente.

---

## Possíveis Melhorias Futuras

- Interface gráfica (GUI) para melhor experiência.
- Salvar e carregar jogos.
- Validação mais robusta das regras do Sudoku.
- Implementação de diferentes níveis de dificuldade.

---

## Autor

Wallace Candido Maia Sousa

---

## Licença

Projeto para fins educacionais. Use à vontade!