import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    // Instance variables
    Deck deck;
    Player p1;
    Player p2;
    boolean hasWon;

    public Game() {

        // Initialize Deck & Shuffle
        String[] ranks = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {14, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        this.deck = new Deck(ranks, suits, values);
        this.deck.shuffle();

        // Print Instructions
        printInstructions();

        // Initialize Players
        Scanner S = new Scanner(System.in);
        System.out.println("Enter P1: ");
        String name1 = S.nextLine();
        System.out.println("Enter P2: ");
        String name2 = S.nextLine();
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        int n = deck.getCardsLeft() / 2;
        for (int i = 0; i < n; i++) {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
        }
        Player p1 = new Player(name1, hand1);
        Player p2 = new Player(name2, hand2);

        // Game has not been one
        hasWon = false;

    }

    public void printInstructions () {
        System.out.println("The Deal\n" +
                "The deck is divided evenly, with each player receiving 26 cards, dealt one at a time, face down. " +
                "P1 deals first. Each player has their stack of cards face down.\n" +
                "\n" + "The Play\n" +
                "Each player turns up a card at the same time and the player with the higher card takes both cards and" +
                " puts them, face down, on the bottom of his stack.\n" +
                "\n" +
                "If the cards are the same rank, it is War. Each player turns up three cards face down. Then each" +
                " player places a card face up. The player with the higher cards takes both piles (10 cards). " +
                "\n" +
                "The Score\n" +
                "The game ends when one player has won all the cards.");
    }



    public static void main(String[] args) {
        // Start the game
        Game game = new Game();
    }
}
