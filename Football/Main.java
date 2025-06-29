package Football;
import Football.GamePanel;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Fenster erstellen
        JFrame frame = new JFrame("Fussball-Animation");

        // GamePanel erstellen (z.B. 480 x 800)
        GamePanel panel = new GamePanel(480, 800);

        // Panel dem Frame hinzufügen
        frame.add(panel);

        // Fenster-Settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // passt das Fenster an die Panel-Größe an
        frame.setLocationRelativeTo(null); // zentriert das Fenster
        frame.setVisible(true); // zeigt das Fenster an
    }
}

