import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Decks deck = new Decks();

        System.out.print("Nome do primeiro jogador: ");
        String name1 = scanner.nextLine();

        System.out.print("Nome do segundo jogador: ");
        String name2= scanner.nextLine();

        Player player1 = new Player(name1, deck.getDeck1());
        Player player2 = new Player(name2, deck.getDeck2());

        scanner.close();

    }
}

//       #TODO: create the combat - put the first card of each on the table - after compare it - find winner - if equal - war - new card is open - winner wins tree cards - if equal repeat




