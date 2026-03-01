import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Decks {
    ArrayList<Card> deck1;
    ArrayList<Card> deck2;


    String[] Suits = {"Ouros", "Copas", "Espadas", "Páus"};
    int[] Values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    ArrayList<Card> deck = new ArrayList<Card>();

    public Decks() {
        createDeck();
        shuffleDeck();
        splitDeck();
    }

    private void createDeck() {

        for (String suit : Suits) {
            for (int value : Values) {

                String valueName = getValueName(value);
                String name = valueName + " de " + suit;

                Card card = new Card(name, suit, value);
                deck.add(card);
            }
        }

    }

    private String getValueName(int value) {
        switch (value) {
            case 11:
                return "Valete";
            case 12:
                return "Rainha";
            case 13:
                return "Rei";
            case 14:
                return "Ás";
            default:
                return String.valueOf(value);
        }
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    private void shuffleDeck(){
       Collections.shuffle((deck));
    }

    private void splitDeck() {
        int splitPoint = deck.size() / 2;

        deck1 = new ArrayList<>(deck.subList(0, splitPoint));

        deck2 = new ArrayList<>(deck.subList(splitPoint, deck.size()));
    }

    public ArrayList<Card> getDeck1() {
        return deck1;
    }
    public ArrayList<Card> getDeck2() {
        return deck2;
    }

}
