import java.util.ArrayList;

public class Deck {

    // Instance variables
    ArrayList<Card> cards;
    int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] values) {
        // Add cards
        for (String s: suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(s, ranks[i], values[i]));
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
            cards.remove(cards.remove(r + 1));
        }

    }


}
