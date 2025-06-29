import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;

public class Player {
    //Startposition
    public int x, y;
    //Bewegungskoordinaten
    public int dx, dy;
    //Spielergrösse
    public int width, height;    
    //Team
    public final String team;
    //
    private BufferedImage logo;
    //Random-Objekt Instanzieren
    Random random = new Random();

    //Konstruktor
    public Player(int x, int y, String team, String imagePath){
        //Startposition initialisieren
        this.x = x;
        this.y = y;
        this.team = team;

        //Zuweisung zufälliger X-Richtung (ausser 0)
        do{
            dx = random.nextInt(7)-3;
        } while(dx == 0);
    
        //Zuweisung zufälliger Y-Richtung (ausser 0)
        do{
            dy = random.nextInt(7)-3;
        } while(dy == 0);
    
        try {
            logo = ImageIO.read(new File(imagePath));
            width = logo.getWidth();
            height = logo.getHeight();
        } catch (IOException e) {
            System.err.println("Bild konnte nicht geladen werden: " + imagePath);
            width = height = 30; // Fallback
        }

    }
        public void move(){
            //Bewegung aktualisieren
            x += dx;
            y += dy;

            //Kollisionserkennung
            if(x <= 40 || x + width >= 440) dx *= -1;
            if(y <= 150 || y + height >= 770) dy *= -1;
        }
        //Logo zeichnen
        public void draw(Graphics g){
            if (logo != null) {
                g.drawImage(logo, x, y, null);
            } else {
                // Fallback wenn kein Bild
                g.setColor(Color.GRAY);
                g.fillOval(x, y, width, height);
            }
        }
        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
        
}
