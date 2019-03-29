package Model;
import Data.EndOfGameException;
import Data.GameLoader;
import Model.Events.Event;
import Window.GameState;
import Window.GameStateProvider;

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 */
public class Model implements GameStateProvider {
    private final ConcurrentLinkedQueue<Event> eventQueue;
    private Component canvas;
    private boolean isShuttingDown;
    private Game game;
    private GameLoader gameLoader;
    public int level;

    /**
     *  Funkcja zwracająca obiekt typu Game
     * @return obiekt typu Game
     */
    public Game getGame() {
        return game;
    }

    /**
     *  Konstruktor Modelu
     * @param eventQueue kolejka zdarzeń
     */
    public Model(ConcurrentLinkedQueue<Event> eventQueue) {
        this.isShuttingDown = false;
        this.eventQueue = eventQueue;
        gameLoader = new GameLoader();
        this.level = 1;
    }

    /**
     *
     * @param canvas
     */
    public void setCanvas(Component canvas) {
        this.canvas = canvas;
    }

    /**
     * Główna pętla gry
     */
    public void gameLoop()
    {
        while(!isShuttingDown())
        {
            Event event = eventQueue.poll();
            while (event != null) {
                try {
                    event.process(this);
                } catch (EndOfGameException e) {

                }
                event = eventQueue.poll();
            }

            if (game!=null&&!game.paused) {
                game.processPhysics(eventQueue);
            }

            if (canvas!=null) {
                canvas.repaint();
            }

            try {
                Thread.sleep(1000/70);
            }
            catch (InterruptedException e) {
            }
        }
    }

    /**
     *
     * @return informacja czy gra ma się zakończyć
     */
    private boolean isShuttingDown() {
            return isShuttingDown;
    }


    @Override
    /**
     *
     */
    public GameState getCurrentGameState() {
        if (game == null)
            return null;
        return game.getGameState();
    }

    /**
     *
     */
    public void newGame() {
        try {
            this.level=1;
            game = gameLoader.loadLevel("lvl" + this.level);
            this.level++;
        } catch (EndOfGameException e) {
            System.out.println("Koniec gry");
        }
    }
    public void nextLevel() throws EndOfGameException {
        game = gameLoader.loadLevel("lvl" + this.level);
        this.level++;
    }
    /**
     *
     */
    public void close() {
        this.isShuttingDown = true;
    }

    /**
     *
     */
    public void pause() {
        this.game.paused=!game.paused;
        this.game.lastTime=System.nanoTime();
    }
}
