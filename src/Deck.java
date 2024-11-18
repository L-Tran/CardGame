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
}
