// War by Logan Tran
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements ActionListener {

    // Instance variables
    private GameViewer window;
    private Deck deck;
    private Player p1;
    private Player p2;
    private boolean hasWon;
    private int state;
    private static final int SLEEP_TIME = 500;

    // Constructor
    public Game() {

        this.window = new GameViewer(this);
        state = 1;

        // Initialize Deck & Shuffle
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q", "K"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        this.deck = new Deck(ranks, suits, values, window);
        this.deck.shuffle();
        // Initialize Players
        Scanner s = new Scanner(System.in);
        // Ask for names
        System.out.println("Enter P1: ");
        String name1 = s.nextLine();
        System.out.println("Enter P2: ");
        String name2 = s.nextLine();
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        // Give each player half of a deck
        while (deck.getCardsLeft() > 0) {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
        }
        // Initialize players with new info
        p1 = new Player(name1, hand1);
        p2 = new Player(name2, hand2);

        Timer clock = new Timer(SLEEP_TIME, this);
        clock.start();
    }

    public Player getP1() {return p1;}

    public Player getP2() {return p2;}

    public int getState() {return this.state;}

    public void actionPerformed(ActionEvent e) {
        if (state == 2) {
            // Play a single round
            playRound();
            window.repaint();
            // Check for a win condition after the round
            hasWon = checkWin();
        }
        else if (state == 3) {
            window.repaint();
            state = 2;
        }
    }

    // Play Game
    public void playGame() {
        // Game has not been one
        hasWon = false;
        // Print Instructions
        printInstructions();
        Scanner s = new Scanner(System.in);
        System.out.println("Press Enter to Start");
        String enter = s.nextLine();
        if (enter.equals("")) {
            state = 2;
            window.repaint();
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
                window.repaint();
    }

    // Play each round
    public Player playRound() {
        // Compare the cards and return winner
        if (p1.getHand().get(0).getValue() > p2.getHand().get(0).getValue()) {
            return roundWinner(p1, p2);
        }
        else if (p2.getHand().get(0).getValue() > p1.getHand().get(0).getValue()) {
            return roundWinner(p2, p1);
        }
        // If they are equal then war is declared
        else {
            state = 3;
            System.out.println("\nBoth Players placed " + p1.getHand().get(0).getRank());
            System.out.println("*** WAR DECLARED! ***\n");
            return war(p1.getHand().remove(0), p2.getHand().remove(0));
        }
    }

    public Player roundWinner(Player winner, Player loser) {
        // Each player draws from the top card from their hand
        Card winnerCard = winner.getHand().remove(0);
        Card loserCard = loser.getHand().remove(0);
        System.out.println(winner.getName() + " wins the round because placed " + winnerCard.getRank() + " and " +
                loser.getName() + " placed " + loserCard.getRank());
        // Add cards to round winner
        winner.getHand().add(winnerCard);
        winner.getHand().add(loserCard);
        return winner;
    }

    // War
    public Player war(Card card1, Card card2) {
        // Make a pile to hold the cards
        ArrayList<Card> warPile = new ArrayList<Card>();
        warPile.add(card1);
        warPile.add(card2);
        // Check if both players can participate in war if not return winner of round
        if (p1.getHand().size() < 4) {
            return noWar(p2, p1, warPile);
        }
        if (p2.getHand().size() < 4) {
            return noWar(p1, p2, warPile);
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

    // If there is no war
    public Player noWar (Player winner, Player loser, ArrayList<Card> warPile) {
        System.out.println(winner.getName() + " wins the round because " + loser.getName() + " doesn't have enough " +
                "cards for war ");
        // Add cards to round winner
        for (Card c : warPile) {
            winner.getHand().add(c);
        }
        return winner;
    }

    // Check for Win
    public boolean checkWin() {
        // If hand is empty then player loses & Game is over
        if (p1.getHand().isEmpty()) {
            state = 4;
            System.out.println("\n" + p2.getName() + " wins with all the cards");
            return true;
        }
        if (p2.getHand().isEmpty()) {
            state = 4;
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
