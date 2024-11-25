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
        p1 = new Player(name1, hand1);
        p2 = new Player(name2, hand2);

        // Game has not been one
        hasWon = false;

        // Play Game
        while (!hasWon) {
            // Find winner of round
            Player winner = compareCard(p1, p2);
            Player loser;
            if (winner == p1) {
                loser = p2;
            }
            else {
                loser = p1;
            }
            // Print the winner and cards
            printWinner(winner, loser);
            // Check if Game is won
            hasWon = checkWin(p1, p2);
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


    // Compare cards
    public Player compareCard(Player p1, Player p2) {
        if (p1.getHand().get(0).getValue() > p2.getHand().get(0).getValue()) {
            return p1;
        }
        return p2;
    }

    // Print winner of round and adjust cards
    public void printWinner(Player winner, Player loser) {
        System.out.println(winner.getName() +  " won" +
                " the round because they had a " + winner.getHand().get(0).getRank() + " and " + loser.getName() +
                " had a " + loser.getHand().get(0).getRank());
        winner.getHand().add(winner.getHand().size() - 1, winner.getHand().remove(0));
        winner.getHand().add(winner.getHand().size() - 1, loser.getHand().remove(0));
    }

    // War

    // Check for Win
    public boolean checkWin(Player p1, Player p2) {
        if (p1.getHand().isEmpty()|| p2.getHand().isEmpty()) {
            if (p1.getHand().size() > p2.getHand().size()) {
                    System.out.println("\n" + p1.getName() + " wins with all the cards");
                    return true;
                }
                System.out.println("\n" + p2.getName() + " wins with all the cards");
                return true;
            }
        return false;
    }


    // Main
    public static void main(String[] args) {
        // Start the game
        Game game = new Game();
    }
}
