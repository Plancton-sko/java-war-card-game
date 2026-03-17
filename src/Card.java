//src/Card.java
public class Card {

    private final String name;
    private final String suit;
    private final int value;

    public Card(String name, String suit, int value) {
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }


}
