//src/Main.java
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Decks decks = new Decks();

        System.out.print("Nome do primeiro jogador: ");
        String name1 = scanner.nextLine();

        System.out.print("Nome do segundo jogador: ");
        String name2= scanner.nextLine();

        scanner.close();

        Player p1 = new Player(name1, decks.getDeck1());
        Player p2 = new Player(name2, decks.getDeck2());

        Game game = new Game(p1, p2);
        game.playGame();


        System.out.println("Resultado final: " + game.getGameResult());
        };

    }

