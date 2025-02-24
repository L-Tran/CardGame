import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {

    // Constants
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public static final int CARD_WIDTH = 116;
    public static final int CARD_HEIGHT = 180;
    public final int TITLE_BAR_HEIGHT = 23;

    // Instance Varibles
    private Game game;
    private Image backCard;
    private Image arrow;

    // Constructor
    public GameViewer(Game game) {
        this.game = game;

        // Set window
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);

        // Initialize images
        backCard = new ImageIcon("Resources/back.png").getImage();
        arrow = new ImageIcon("Resources/arrow.png").getImage();
    }

    public void paint(Graphics g) {
        // Paint screen depending on game state
        // Instructions
        if (game.getState() == 1) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            Font stringFont = new Font( "PLAIN", Font.BOLD, 15 );
            g.setFont(stringFont);
            paintPlayers(g);
            paintInstructions(g);
        }
        // In game
        else if (game.getState() == 2){
            // Change background color
            Color background = new Color(52,90,55);
            g.setColor(background);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            // Draw hands
            g.drawImage(backCard, 442, 800, CARD_WIDTH, CARD_HEIGHT, this);
            g.drawImage(backCard, 442, 50, CARD_WIDTH, CARD_HEIGHT,this);
            // Draw round card
            game.getP1().getHand().get(0).draw(g, 384);
            game.getP2().getHand().get(0).draw(g, 500);
            // Write # of cards
            g.setColor(Color.white);
            g.drawString(game.getP1().getName() + ": " + game.getP1().getHand().size(), 475, 45);
            g.drawString(game.getP2().getName() + ": " + game.getP2().getHand().size(), 475, 995);
            // Draw arrow for who is winning
            if (game.getRoundWinner() != null) {
                if (game.getRoundWinner().equals(game.getP1())) {
                    g.drawImage(arrow, 375, 90, 75, 60, this);
                }
                else if (game.getRoundWinner().equals(game.getP2())) {
                    g.drawImage(arrow, 375, 840, 75, 60, this);

                }
            }
        }
        // War
        else if(game.getState() == 3) {
            Color background = new Color(52,90,55);
            g.setColor(background);
            int[] xPositions = {616, 732, 848, 268, 152, 36};
            for (int x : xPositions) {
                g.drawImage(backCard, x, 410, CARD_WIDTH, CARD_HEIGHT, this);
            }
        }
        // Win screen
        else if (game.getState() == 4){
            g.setColor(Color.white);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(Color.black);
            Font stringFont = new Font( "PLAIN", Font.BOLD, 35 );
            g.setFont(stringFont);
            g.drawString(game.getRoundWinner().getName() + " wins with all the cards!!!",250 ,450);
        }
    }

    private void clearScreen(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    // Paint player names for instructions
    public void paintPlayers(Graphics g) {
        g.drawString("Player 1: " + game.getP1().getName(),  100, 800);
        g.drawString("Player 2: " + game.getP2().getName(),  100, 850);
    }

    // Paint instructions
    public void paintInstructions(Graphics g) {
        ArrayList<String> instructions = new ArrayList<String>();
        instructions.add("The Deal");
        instructions.add("  The deck is divided evenly, with each player receiving 26 cards, dealt one at a time, face " +
                "down.");
        instructions.add("  P1 deals first. Each player has their stack of cards face down.");
        instructions.add("The Play");
        instructions.add("  Each player turns up a card at the same time and the player with the higher card takes both cards and");
        instructions.add("  puts them, face down, on the bottom of his stack.");
        instructions.add("  If the cards are the same rank, it is War. Each player turns up three cards face down. Then each");
        instructions.add("  player places a card face up. The player with the higher cards takes both piles (10 cards).");
        instructions.add("The Score");
        instructions.add("  The game ends when one player has won all the cards.");
        for (int i = 0; i < instructions.size(); i++) {
            g.drawString(instructions.get(i), 100, 200 + 50 * i);
        }
    }
}
