import javax.swing.*;
import java.awt.*;

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
    }
}
