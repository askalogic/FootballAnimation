package Football;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    //Instanzvariablen deklarieren
int width;
int height;
private Ball ball;
List<Player> teamA = new ArrayList<>();
List<Player> teamB = new ArrayList<>();

//------------------------------------------------------------------------------------

    public GamePanel(int width, int height){
        
        //Dimensionen festlegen
        this.width = width;
        this.height = height;
        int centerY = height / 2;
        int centerX = width / 2;
        int feldBreite = width - 80;
        int feldHöhe = height - 180;
        int feldMitteY = (feldHöhe / 2) + 150;
        int feldMitteX = centerX;
        int paddingTop = 150;
        int paddingBottom = 30;
        int paddingSides = 40;  
        
        //Grösse des Panels festlegen
        this.setPreferredSize(new Dimension(this.width, this.height));
        
        //Ball instanzieren
        ball = new Ball((width/2)-10, height-(150+380));
        
        //Team-Erstellung
        teamA.add(new Player(feldMitteX-60, feldMitteY-80, "FC Barcelona", "logos/barca.png"));
        teamA.add(new Player(feldMitteX+20, feldMitteY-80, "FC Barcelona", "logos/barca.png"));

        teamB.add(new Player(feldMitteX-60, feldMitteY+80, "BVB", "logos/bvb.png"));
        teamB.add(new Player(feldMitteX+20, feldMitteY+80, "BVB", "logos/bvb.png"));

        //Bewegungstimer alle 16ms
        Timer timer = new Timer(16, e -> {
            ball.move();

            for (Player p : teamA) p.move();
            for (Player p : teamB) p.move();

            repaint();

        }
        );
        timer.start();
    }

//------------------------------------------------------------------------------------

protected void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

//------------------------------------------------------------------------------------

//Koordinaten holen
int width = getWidth();
int height = getHeight();
int centerY = height / 2;
int centerX = width / 2;
int feldBreite = width - 80;
int feldHöhe = height - 180;
int feldMitteY = (feldHöhe / 2) + 150;
int feldMitteX = centerX;
int paddingTop = 150;
int paddingBottom = 30;
int paddingSides = 40;

//------------------------------------------------------------------------------------

    //Farbe auf Grün setzen
    g.setColor(Color.decode("0x35682d"));
    //Hintergrund zeichnen
    g.fillRect(0,0,width,height);

//------------------------------------------------------------------------------------

    //Transparentes Weiss erstellen
    Color transparentWeiss = new Color(0xFFFFFF80, true);
    //Farbe aus transparentes Weiss setzen
    g.setColor(transparentWeiss);
    //Linien-Dicke anpassen
    g2d.setStroke(new BasicStroke(5));
    //Mittellinie zeichnen
    g.drawLine(paddingSides, feldMitteY, width-paddingSides, feldMitteY);

//------------------------------------------------------------------------------------

    //Feldrand zeichnen
    g.drawRect(paddingSides, paddingTop, feldBreite, feldHöhe);

//------------------------------------------------------------------------------------

    //Mittelkreis zeichnen
    g.drawOval(feldMitteX - 72, feldMitteY - 72, 144, 144);

//------------------------------------------------------------------------------------

    //Tore zeichnen
    //oben
    g.fillRect(centerX-56, 140, 112, 10);
    //unten
    g.fillRect(centerX-56, paddingTop+feldHöhe, 112, 10);

//------------------------------------------------------------------------------------

    //Ball zeichnen
    ball.draw(g);

//------------------------------------------------------------------------------------

    //Spieler zeichnen
    for (Player p : teamA) p.draw(g);
    for (Player p : teamB) p.draw(g);


//------------------------------------------------------------------------------------

    //Kollision Spieler - Ball
    Rectangle ballBounds = new Rectangle(ball.x, ball.y, ball.size, ball.size);
    for (Player p : teamA) {
        if (ballBounds.intersects(p.getBounds())) {
            if (ball.dx > 0 && ball.x < p.x) ball.dx *= -1;
            if (ball.dx < 0 && ball.x > p.x) ball.dx *= -1;
            if (ball.dy > 0 && ball.y < p.y) ball.dy *= -1;
            if (ball.dy < 0 && ball.y > p.y) ball.dy *= -1;
        
            ball.x += ball.dx;
            ball.y += ball.dy;
        }
    }
    
    for (Player p : teamB) {
        if (ballBounds.intersects(p.getBounds())) {
            if (ball.dx > 0 && ball.x < p.x) ball.dx *= -1;
            if (ball.dx < 0 && ball.x > p.x) ball.dx *= -1;
            if (ball.dy > 0 && ball.y < p.y) ball.dy *= -1;
            if (ball.dy < 0 && ball.y > p.y) ball.dy *= -1;
        
            ball.x += ball.dx;
            ball.y += ball.dy;
        }
    }
    

//------------------------------------------------------------------------------------

    //Kollision Spieler - Spieler

    }
}