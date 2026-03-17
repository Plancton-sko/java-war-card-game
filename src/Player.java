//src/Player.java

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> hand;
    private final String name;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.hand = new ArrayList<>(cards);
    }

    public void showHand() {
        System.out.println("Cartas de " + name + ":");

        for (Card card : hand) {
            System.out.println(card.getName());
        }
    }

    public Card playCard() {
        return hand.isEmpty() ? null : hand.remove(0);
    }

    public ArrayList<Card> playWar() {
        if (hand.size() >= 2) {
            Card playedBet = hand.remove(0);
            Card newPlayedCard = hand.remove(0);

            ArrayList<Card> bet = new ArrayList<Card>();
            bet.add(playedBet);
            bet.add(newPlayedCard);

            return bet;
        }
        return null;
    }

    public int cardCount() {
        return hand.size();
    }

    public void getTable(ArrayList<Card> cards) {
        hand.addAll(new ArrayList<>(cards));
    }

    @Override
    public String toString() {
        return name + " (" + hand.size() + " cards)";
    }
}
