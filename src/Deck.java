import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {

    // Instance variables
    ArrayList<Card> cards;
    int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] values, GameViewer window) {
        // Add cards & Images
        cards = new ArrayList<Card>();
        int counter = 1;
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Image cardImage = new ImageIcon("Resources/" + counter + ".png").getImage();
                cards.add(new Card(suits[j], ranks[i], values[i], cardImage, window));
                counter ++;
            }
        }

        // Initialize cardsLeft
        cardsLeft = cards.size();

        shuffle();
    }

    // IsEmpty Function
    public boolean isEmpty() {
        // Check cardsLeft
        return cardsLeft == 0;
    }

    // Getter for cardsLeft
    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        // Check if deck is empty
        if (isEmpty()) {
            return null;
        }
        // Decrease cards and return last card
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    // Shuffle cards
    public void shuffle() {
        for (int i = cardsLeft - 1; i > 0; i--) {
            // Find random card & swap cards
            int r = (int)(Math.random() * (i + 1));
            cards.add(r, cards.remove(i));
            cards.add(i, cards.remove(r + 1));
        }

    }
}
