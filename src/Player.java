//src/Player.java

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    String name;

    public Player(String name, ArrayList<Card> cards) {
        this.name = name;
        this.hand = cards;
    }

    public void showHand() {
        System.out.println("Cartas de " + name + ":");

        for (Card card : hand) {
            System.out.println(card.getName());
        }
    }

    public Card playCard() {
        if (!hand.isEmpty()) {
            return hand.remove(0);
        }
        return null;
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
        hand.addAll(cards);
    }
}
