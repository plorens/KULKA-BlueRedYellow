package Window;

import Model.Clock;
import Model.Vector2;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

/**
 *  Klasa z zapisanym stanem gry
 */
public class GameState {

    private Clock clock;
    private static Thread thread;
    public int lives;

    private Vector2 size;
    private List<PositionShape> shapes;

    /**
     *
     * @return wymiary gry
     */
    public Vector2 getSize() {
        return size;
    }

    /**
     *  Funkcja zwracająca listę z kształtami do narysowania
     * @return lista z kształtami do narysowania
     */
    public List<PositionShape> getShapes() {
        return shapes;
    }

    /**
     *  Konstruktor aktualnego stanu gry
     * @param size wielkość mapki
     */
    public GameState(Vector2 size, int lives) {
        this.size = size;
        this.lives = lives;
        shapes= new LinkedList<PositionShape>();
        this.clock = Clock.getInstance();
        if (GameState.thread == null) {
            GameState.thread = new Thread(this.clock);
            thread.start();
        }
    }

    /**
     * Funkcja dodająca kształt do listy kształtów
     * @param shape kształt do dodania do listy
     */
    public void addShape(PositionShape shape)
    {
        shapes.add(shape);
    }

    /**
     *
     * @return
     */
    public int getLives() {
        return lives;
    }

    public long getTime() {
        return this.clock.getTime();
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void endClock() {
        this.clock.sendSignal();
        thread.interrupt();
    }

    public long getToScore() {
        return this.clock.getAllTime();
    }
}
