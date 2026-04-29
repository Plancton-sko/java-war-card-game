import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Decks decks = new Decks();

            String name1 = readPlayerName(scanner, "primeiro");
            String name2 = readPlayerName(scanner, "segundo");

            Player player1 = new Player(name1, decks.getDeck1());
            Player player2 = new Player(name2, decks.getDeck2());

            Game game = new Game(player1, player2);

            startGame(game);
        }
    }

    private static String readPlayerName(Scanner scanner, String position) {
        
        System.out.print("Nome do " + position + " jogador: ");
        return scanner.nextLine().trim();
        
    }

    private static void startGame(Game game) {
        
        System.out.println("\nIniciando o jogo...\n");

        game.playGame();

        System.out.println("\n=== Resultado Final ===");
        System.out.println(game.getGameResult());
        
    }
}
