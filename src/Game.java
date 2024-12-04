import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    // Instance variables
    private Deck deck;
    private Player p1;
    private Player p2;
    private boolean hasWon;

    // Constructor
    public Game() {

        // Initialize Deck & Shuffle
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] values = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        this.deck = new Deck(ranks, suits, values);
        this.deck.shuffle();
        // Initialize Players
        Scanner S = new Scanner(System.in);
        // Ask for names
        System.out.println("Enter P1: ");
        String name1 = S.nextLine();
        System.out.println("Enter P2: ");
        String name2 = S.nextLine();
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        // Give each player half of a deck
        for (int i = 0; i < deck.getCardsLeft() / 2; i++) {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
        }
        // Initialize players with new info
        p1 = new Player(name1, hand1);
        p2 = new Player(name2, hand2);
    }

    // Play Game
    public void playGame() {
        // Game has not been one
        hasWon = false;
        // Print Instructions
        printInstructions();
        while (!hasWon) {
            // Play a single round
            playRound();
            // Check for a win condition after the round
            hasWon = checkWin();

        }
    }

    // Print Instructions
    public void printInstructions () {
        System.out.println("""
                The Deal
                The deck is divided evenly, with each player receiving 26 cards, dealt one at a time, face down. \
                P1 deals first. Each player has their stack of cards face down.
                
                The Play
                Each player turns up a card at the same time and the player with the higher card takes both cards and\
                 puts them, face down, on the bottom of his stack.
                
                If the cards are the same rank, it is War. Each player turns up three cards face down. Then each\
                 player places a card face up. The player with the higher cards takes both piles (10 cards). \
                
                The Score
                The game ends when one player has won all the cards.
                """);
    }

    // Play each round
    public Player playRound() {
        // Each player draws from the top card from their hand
        Card p1Card = p1.getHand().remove(0);
        Card p2Card = p2.getHand().remove(0);
        // Compare the cards and return winner
        if (p1Card.getValue() > p2Card.getValue()) {
            System.out.println(p1.getName() + " wins the round because placed " + p1Card.getRank() + " and " +
                    p2.getName() + " placed " + p2Card.getRank());
            // Add cards to round winner
            p1.getHand().add(p1Card);
            p1.getHand().add(p2Card);
            return p1;
        }
        else if (p2Card.getValue() > p1Card.getValue()) {
            System.out.println(p2.getName() + " wins the round because they placed " + p2Card.getRank() + " and " +
                    p1.getName() + " placed " + p1Card.getRank());
            // Add cards to round winner
            p2.getHand().add(p2Card);
            p2.getHand().add(p1Card);
            return p2;
        }
        // If they are equal then war is declared
        else {
            System.out.println("\nBoth Players placed " + p1Card.getRank());
            System.out.println("*** WAR DECLARED! ***\n");
            return war(p1Card, p2Card);
        }
    }

    // War
    public Player war(Card card1, Card card2) {
        // Make a pile to hold the cards
        ArrayList<Card> warPile = new ArrayList<Card>();
        warPile.add(card1);
        warPile.add(card2);
        // Check if both players can participate in war if not return winner of round
        if (p1.getHand().size() < 4) {
            System.out.println(p2.getName() + " wins the round because " + p1.getName() + " doesn't have enough cards for war ");
            // Add cards to round winner
            for (Card c : warPile) {
                p2.getHand().add(c);
            }
            return p2;
        }
        if (p2.getHand().size() < 4) {
            System.out.println(p1.getName() + " wins the round" + p2.getName() + " doesn't have enough cards for war ");
            // Add cards to round winner
            for (Card c : warPile) {
                p1.getHand().add(c);
            }
            return p1;
        }
        // Remove 3 face-down cards for both players
        System.out.println(p1.getName() + " places 3 cards face down.");
        System.out.println(p2.getName() + " places 3 cards face down.");
        for (int i = 0; i < 3; i++) {
            warPile.add(p1.getHand().remove(0));  // Player 1's face-down cards
            warPile.add(p2.getHand().remove(0));  // Player 2's face-down cards
        }
        // Compare the face-up cards
        Player winner = playRound();
        // The winner takes all the cards in the war pile
        for (Card c : warPile) {
            winner.getHand().add(c);
        }
        // Spacing
        System.out.println();
        return winner;
    }

    // Check for Win
    public boolean checkWin() {
        // If hand is empty then player loses & Game is over
        if (p1.getHand().isEmpty()) {
            System.out.println("\n" + p2.getName() + " wins with all the cards");
            return true;
        }
        if (p2.getHand().isEmpty()) {
            System.out.println("\n" + p1.getName() + " wins with all the cards");
            return true;
        }
        return false;
    }

    // Main
    public static void main(String[] args) {
        // Start the game
        Game game = new Game();
        game.playGame();
    }
}
