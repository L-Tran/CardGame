public class Card {

    // Instance variables
    String rank;
    String suit;
    int value;

    // Constructor
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    // Getter & setter methods
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    // ToString method
    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }
}
