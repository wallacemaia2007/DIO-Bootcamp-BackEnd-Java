````mermaid
classDiagram
    direction TB

    %% Classes
    class Iphone {
        -musica: String
        -url: String
        -numero: String
        +tocar()
        +pausar()
        +selecionarMusica(String)
        +ligar(String)
        +desligar()
        +atender()
        +iniciarCorreioVoz()
        +exibirPagina(String)
        +adicionarNovaAba()
        +atualizarPagina()
    }

    class Main {
        +main(String[] args)
    }

    %% Interfaces
    class ReprodutorMusical{
        <<interface>>
        +tocar()*
        +pausar()*
        +selecionarMusica(String)*
    }

    class AparelhoTelefonico {
        <<interface>>
        +atender()*
        +desligar()*
        +iniciarCorreioVoz()*
        +ligar(String)*
    }

    class NavegadorInternet {
        <<interface>>
        +exibirPagina(String)*
        +adicionarNovaAba()*
        ++atualizarPagina()*
    }

    %% Relacionamentos
    Iphone --|> ReprodutorMusical
    Iphone --|> AparelhoTelefonico
    Iphone --|> NavegadorInternet
    Main --> Iphone
