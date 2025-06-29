import java.util.Random;
import java.awt.*;

public class Ball {
    //Startposition
    public int x, y;
    //Bewegungskoordinaten
    public int dx, dy;
    //Ballgrösse
    public final int size = 20;

    //Konstruktor
    public Ball(int x, int y){
        //Startposition initialisieren
        this.x = x;
        this.y = y;

        //Random-Objekt Instanzieren
        Random random = new Random();

        //Zuweisung zufälliger X-Richtung (ausser 0)
        do{
            dx = random.nextInt(7)-3;
        } while(dx == 0);
    
        //Zuweisung zufälliger Y-Richtung (ausser 0)
        do{
            dy = random.nextInt(7)-3;
        } while(dy == 0);
    }
        public void move(){
            //Bewegung aktualisieren
            x += dx;
            y += dy;

            //Kollisionserkennung mit Wand
            if(x <= 40){ dx *= -1; x = 41;}
                else if(x + size >= 440){dx *= -1; x = 439-size;}
            if(y <= 150){ dy *= -1; y = 149;}
                else if(y + size >= 770){ dy *= -1; y = 769 - size;}
        }
        //Ball zeichnen
        public void draw(Graphics g){
            g.setColor(Color.WHITE);
            g.fillOval(x, y, size, size);
        }
}
