//src/Decks.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Decks {

    private static final String[] SUITS = {"Ouros", "Copas", "Espadas", "Paus"};
    private static final int[] VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    private List<Card> deck;
    private List<Card> deck1;
    private List<Card> deck2;

    public Decks() {
        deck = new ArrayList<>();
        createDeck();
        shuffleDeck();
        splitDeck();
    }

    private void createDeck() {
        for (String suit : SUITS) {
            for (int value : VALUES) {
                deck.add(new Card(getValueName(value) + " de " + suit, suit, value));
            }
        }
    }

    private String getValueName(int value) {
        switch (value) {
            case 11: return "Valete";
            case 12: return "Rainha";
            case 13: return "Rei";
            case 14: return "Ás";
            default: return String.valueOf(value);
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private void splitDeck() {
        int splitPoint = deck.size() / 2;

        deck1 = new ArrayList<>(deck.subList(0, splitPoint));
        deck2 = new ArrayList<>(deck.subList(splitPoint, deck.size()));
    }

    public List<Card> getDeck1() {
        return new ArrayList<>(deck1);
    }

    public List<Card> getDeck2() {
        return new ArrayList<>(deck2);
    }

    public List<Card> getFullDeck() {
        return new ArrayList<>(deck);
    }
}
