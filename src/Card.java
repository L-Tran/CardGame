import java.awt.*;

public class Card {

    // Instance variables
    private String rank;
    private String suit;
    private int value;
    private Image cardImage;
    private GameViewer window;

    // Constructor
    public Card(String suit, String rank, int value, Image cardImage, GameViewer window) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.cardImage = cardImage;
        this.window = window;
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

    public void draw(Graphics g, int x) {
        g.drawImage(cardImage, x,410, window.CARD_WIDTH, window.CARD_HEIGHT, window);
    }
}
