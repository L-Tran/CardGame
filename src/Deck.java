import java.util.ArrayList;

public class Deck {

    // Instance Varibles
    ArrayList<Card> cards;
    int cardsLeft;

    // Constructor
    public Deck(String[] ranks, String[] suits, int[] values) {
        for (String s: suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(s, ranks[i], values[i]));
            }
        }

        cardsLeft = cards.size();

        // cards.shuffle();
    }

    // IsEmpty Fuction
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    // Getter for cardsLeft
    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        // If deck is empty
        if (isEmpty()) {
            return null;
        }
        // Decrease cards and return last card
        cardsLeft--
        return cards.get(cardsLeft);
    }

    // Shuffle cards
    public void shuffle() {
        for (int i = cardsLeft - 1; i > 0, i--) {
            int r = (int)(Math.random() * (i + 1);
            cards.add(r, cards.remove(i));
            cards.remove(cards.remove(r + 1));
        }

    }


}
