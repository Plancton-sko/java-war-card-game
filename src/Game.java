//src/Game.java

import java.util.ArrayList;

public class Game {
    private ArrayList<Card> table = new ArrayList<Card>();

    private Player player1;
    private Player player2;

    public String result = "";

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

//    #TODO: create the combat
    public void playRound(){
        System.out.println("Game Started");

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        table.add(card1);
        table.add(card2);

        findWinner(card1, card2);

        if (result.equals("war")){
            handleWar();
        }; if (result.equals("Player 1 wins")){
            // Juntar a player1 hand com a table
        }; if (result.equals("Player 2 wins")){
            // Juntar a player2 hand com a table
        }

    }

    public void handleWar() {
        System.out.println("War");

        ArrayList<Card> bet1 = player1.playWar();
        ArrayList<Card> bet2 = player2.playWar();

//        I need to change this
        if (bet1 == null) {
            result = "Bet 2 won";
        }
        if (bet2 == null) {
            result = "Bet 1 won";
        }

        Card card1 = bet1.get(1);
        Card card2 = bet2.get(1);

    }

    public String findWinner(Card card1, Card card2) {
        int c1 = card1.getValue();
        int c2 = card2.getValue();

        if (c1 == c2) {
            return result = "war";
        }
        if (c1 > c2) {
            return result = "Player 1 wins";
        }
        if (c2 > c1) {
            return result = "Player 2 wins";
        }

        return null;
    }

    public void findWinnerWar(Card card1, Card card2) {
        int c1 = card1.getValue();
        int c2 = card2.getValue();

        if (c1 == c2) {
            result = "war";
        }
        if (c1 > c2) {
            result = "Player 1 wins";
        }
        if (c2 > c1) {
            result = "Player 2 wins";
        }
    }

//    #TODO: if equal repeat
}
