import java.util.ArrayList;

public class Player {

    // Instance variables
    String name;
    ArrayList<Card> hand;
    int points;

    // Constructor
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        this.points = 0;
    }

    // Getter & setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addCard(Card c) {
        this.hand.add(c);
    }

    @Override
    public String toString() {
        return this.name + " has " + this.points + " points " + this.name + "'s cards: " + this.hand;
    }
}
