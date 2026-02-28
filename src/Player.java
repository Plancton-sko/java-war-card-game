import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    String name;

    public Player(String name, ArrayList<Card> cards){
        this.name = name;
        this.hand = cards;
    }

    public void showHand() {
        System.out.println("Cartas de " + name + ":");

        for (Card card : hand) {
            System.out.println(card.getName());
        }
    }
}
