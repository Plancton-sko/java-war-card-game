//src/Game.java

import java.util.ArrayList;

public class Game {

    private ArrayList<Card> table = new ArrayList<>();
    private Player player1;
    private Player player2;

    private RoundResult roundResult = RoundResult.NONE;
    private GameResult gameResult = GameResult.NONE;
    private State currentState = State.CONTINUE;

    public enum RoundResult {
        NONE, P1_WIN, P2_WIN, WAR, DRAW
    }

    ;

    public enum GameResult {
        P1_WIN, P2_WIN, DRAW, NONE
    }

    ;

    private enum State {
        BOTH_NULL, P1_EMPTY, P2_EMPTY, CONTINUE
    }

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void playGame() {
        while (gameResult == GameResult.NONE) {
            playRound();
        }

        System.out.println("Final result: " + gameResult);
    }

    public void playRound() {

        if (gameResult != GameResult.NONE) {
            return;
        }

        roundResult = RoundResult.NONE;

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        if (card1 == null && card2 == null) {
            gameResult = GameResult.DRAW;
            return;
        }

        if (card1 == null) {
            gameResult = GameResult.P2_WIN;
            return;
        }

        if (card2 == null) {
            gameResult = GameResult.P1_WIN;
            return;
        }

        table.add(card1);
        table.add(card2);

        roundResult = findWinner(card1, card2);


        if (roundResult == RoundResult.WAR) {
            handleWar();
            return;
        }

        if (roundResult == RoundResult.P1_WIN) {
            giveTable(player1);
        } else if (roundResult == RoundResult.P2_WIN) {
            giveTable(player2);
        }

        checkGameWinner();
    }

    public void handleWar() {

        ArrayList<Card> bet1 = player1.playWar();
        ArrayList<Card> bet2 = player2.playWar();

        if (bet1 == null && bet2 == null) {
            currentState = State.BOTH_NULL;
        } else if (bet1 == null) {
            currentState = State.P1_EMPTY;
        } else if (bet2 == null) {
            currentState = State.P2_EMPTY;
        } else {
            currentState = State.CONTINUE;
        }

        if (bet1 != null) {
            table.addAll(bet1);
        }
        if (bet2 != null) {
            table.addAll(bet2);
        }

        switch (currentState) {
            case BOTH_NULL:
                roundResult = RoundResult.DRAW;
                gameResult = GameResult.DRAW;
                table.clear();
                return;

            case P1_EMPTY:
                roundResult = RoundResult.P2_WIN;
                gameResult = GameResult.P2_WIN;
                giveTable(player2);
                return;

            case P2_EMPTY:
                roundResult = RoundResult.P1_WIN;
                gameResult = GameResult.P1_WIN;
                giveTable(player1);
                return;

            case CONTINUE:

                Card warCard1 = bet1.get(1);
                Card warCard2 = bet2.get(1);

                roundResult = findWinner(warCard1, warCard2);

                if (roundResult == RoundResult.WAR) {
                    handleWar();
                    return;
                }

                if (roundResult == RoundResult.P1_WIN) {
                    giveTable(player1);
                } else if (roundResult == RoundResult.P2_WIN) {
                    giveTable(player2);
                }

                checkGameWinner();
                return;
        }

    }

    public RoundResult findWinner(Card card1, Card card2) {
        int value1 = card1.getValue();
        int value2 = card2.getValue();

        if (value1 == value2) {
            return RoundResult.WAR;
        }

        return value1 > value2 ? RoundResult.P1_WIN : RoundResult.P2_WIN;
    }

    private void giveTable(Player winner) {
        if (table.isEmpty()) {
            return;
        }

        winner.getTable(new ArrayList<>(table));
        table.clear();
    }

    private void checkGameWinner() {
        if (gameResult != GameResult.NONE) {
            return;
        }

        if (player1.cardCount() == 0 && player2.cardCount() == 0) {
            gameResult = GameResult.DRAW;
        } else if (player1.cardCount() == 0) {
            gameResult = GameResult.P2_WIN;
        } else if (player2.cardCount() == 0) {
            gameResult = GameResult.P1_WIN;
        }
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
