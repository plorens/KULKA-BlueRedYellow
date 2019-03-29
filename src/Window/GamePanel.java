package Window;

import Model.Clock;
import Model.Vector2;
import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 */
public class GamePanel extends JPanel {

    GameStateProvider provider;
    /**
     *
     * @param provider
     */
    public GamePanel(GameStateProvider provider) {
        this.provider= provider;
    }

    @Override
    /**
     *  Funkcja rysująca aktualny stan gry
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameState state= provider.getCurrentGameState();
        if (state == null)
            return;
        Graphics2D g2d = (Graphics2D) g;
        Vector2 scale = new Vector2(getWidth() / state.getSize().x, getHeight() / state.getSize().y);
        AffineTransform transform = new AffineTransform();
        transform.scale(scale.x, scale.y);

        g2d.setColor(Color.white);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        for (PositionShape shape : state.getShapes()) {
            g2d.setColor(shape.getColor());
            Shape newshape = transform.createTransformedShape(shape.getShape());
            g2d.fill(newshape);
        }

        String score= "Wynik   : ";      //wynik jest mierzony w sekundach (w sumie to nie muszą być sekundy, ważne żeby wynik rósł proporcjonalnie z czasem) od rozpoczęcia gry
        String level= "Poziom  : ";
        String lives= "Życia   : ";

        Font stringFont= new Font("Cambria", Font.PLAIN, 17);
        g2d.setFont(stringFont);
        g.drawString(score+state.getTime(), (int) state.getSize().x * (int) scale.x - 80, (int) state.getSize().y * (int) scale.y - 29 );
        //g.drawString(level,(int) state.getSize().x * (int) scale.x - 80, (int) state.getSize().y * (int) scale.y - 15 );
        g.drawString(lives+state.lives,(int) state.getSize().x * (int) scale.x - 80, (int) state.getSize().y * (int) scale.y - 1 );
    }
}
