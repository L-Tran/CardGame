import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameViewer extends JFrame {
    // Instance Varibles
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 1000;
    public final int TITLE_BAR_HEIGHT = 23;
    private Game game;

    public GameViewer(Game game) {
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);



    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        if (game.getState() == 1) {
            g.setColor(Color.black);
            Font stringFont = new Font( "PLAIN", Font.BOLD, 15 );
            g.setFont(stringFont);
            paintPlayers(g);
            paintInstructions(g);
        }
        else if (game.getState() == 2){
            Color background = new Color(52,90,55);
            g.setColor(background);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        }
        else if (game.getState() == 3){

        }
    }

    public void paintPlayers(Graphics g) {
        g.drawString("Player 1: " + game.getP1().getName(),  100, 800);
        g.drawString("Player 2: " + game.getP2().getName(),  100, 850);
    }
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
