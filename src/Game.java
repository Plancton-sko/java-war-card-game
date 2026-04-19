//src/Game.java
import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Card> table = new ArrayList<>();
    private Player player1;
    private Player player2;

    private RoundResult roundResult = RoundResult.NONE;
    private GameResult gameResult = GameResult.NONE;

    public enum RoundResult {
        NONE, P1_WIN, P2_WIN, WAR, DRAW
    }

    public enum GameResult {
        P1_WIN, P2_WIN, DRAW, NONE
    }

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void playGame() {
        int maxRounds = 10000; // proteção contra loop infinito
        int rounds = 0;

        while (gameResult == GameResult.NONE && rounds++ < maxRounds) {
            playRound();
        }

        if (rounds >= maxRounds) {
            gameResult = GameResult.DRAW;
        }

        System.out.println("Final result: " + gameResult);
    }

    public void playRound() {
        if (gameResult != GameResult.NONE) return;

        roundResult = RoundResult.NONE;

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        // Verificações de fim de jogo
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

        resolveBattle(card1, card2);

        checkGameWinner();
    }

    private void resolveBattle(Card card1, Card card2) {
        roundResult = findWinner(card1, card2);

        while (roundResult == RoundResult.WAR) {
            if (!handleWar()) return; // encerra se alguém perdeu no WAR

            // pegar últimas cartas da mesa para comparar
            Card last1 = table.get(table.size() - 2);
            Card last2 = table.get(table.size() - 1);

            roundResult = findWinner(last1, last2);
        }

        if (roundResult == RoundResult.P1_WIN) {
            giveTable(player1);
        } else if (roundResult == RoundResult.P2_WIN) {
            giveTable(player2);
        }
    }

    /**
     * @return false se o jogo terminou durante WAR
     */
    private boolean handleWar() {

        List<Card> bet1 = player1.playWar();
        List<Card> bet2 = player2.playWar();

        // Casos de falta de cartas
        if (bet1 == null && bet2 == null) {
            gameResult = GameResult.DRAW;
            table.clear();
            return false;
        }

        if (bet1 == null) {
            gameResult = GameResult.P2_WIN;
            giveTable(player2);
            return false;
        }

        if (bet2 == null) {
            gameResult = GameResult.P1_WIN;
            giveTable(player1);
            return false;
        }

        // Proteção contra listas pequenas
        if (bet1.size() < 2 || bet2.size() < 2) {
            gameResult = GameResult.DRAW;
            table.clear();
            return false;
        }

        table.addAll(bet1);
        table.addAll(bet2);

        return true;
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
        if (table.isEmpty()) return;

        winner.collectCards(new ArrayList<>(table));
        table.clear();
    }

    private void checkGameWinner() {
        if (gameResult != GameResult.NONE) return;

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
