package Model;

import Data.EndOfGameException;
import Model.Events.*;
import Model.Events.Event;
import Objects.*;
import Window.GameState;
import Window.PositionShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Klasa
 */
public class Game {
    List<Ground> obsticles;
    List<PlusGravityField> fields;
    List<MinusGravityField> mfields;
    List<IncreaseBallField> ifields;
    Meta meta;
    Ball ball;
    Vector2 size;
    GameState gameState;
    public long lastTime;
    private int lives;
    boolean paused=false;


    /**
     *
     * @param ball  Kulka
     * @param size  Wielkosc ekranu
     * @param meta  Meta
     * @param lives Liczba zyc
     */
    public Game(Ball ball, Vector2 size, Meta meta, int lives) {
        this.lastTime = System.nanoTime();
        this.obsticles = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.mfields = new ArrayList<>();
        this.ifields = new ArrayList<>();
        this.ball = ball;
        this.size = size;
        this.meta = meta;
        this.lives = lives;
        updateState();
    }

    /**
     *
     * @return
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     *
     * @return
     */
    public Ball getBall() {
        return ball;
    }

    /**
     *
     * @param ground przeszkoda
     */
    public void addObsticle(Ground ground) {
        obsticles.add(ground);
    }

    /**
     *
     * @param field pole zwiekszające grawitacje
     */
    public void addField(PlusGravityField field) {
        fields.add(field);
    }

    /**
     *
     * @param mfield pole zmniejszajace grawitacje
     */
    public void addField(MinusGravityField mfield) {
        mfields.add(mfield);
    }

    /**
     *
     * @param ifield pole zwiekszajace wielkosc kulki
     */
    public void addField(IncreaseBallField ifield) { ifields.add(ifield); }


    /**
     *
     */
    private void updateState() {
        GameState newState = new GameState(size, lives);

        for (PlusGravityField field : fields) {
            newState.addShape(
                    new PositionShape(
                            Color.blue,
                            new Rectangle2D.Double(
                                    field.getPosition().x,
                                    field.getPosition().y,
                                    field.getWidth(),
                                    field.getHeight()
                            )
                    )
            );
        }

        for (IncreaseBallField field : ifields) {
            newState.addShape(
                    new PositionShape(
                            Color.red,
                            new Rectangle2D.Double(
                                    field.getPosition().x,
                                    field.getPosition().y,
                                    field.getWidth(),
                                    field.getHeight()
                            )
                    )
            );
        }

        for (MinusGravityField mfield : mfields) {
            newState.addShape(
                    new PositionShape(
                            Color.yellow,
                            new Rectangle2D.Double(
                                    mfield.getPosition().x,
                                    mfield.getPosition().y,
                                    mfield.getWidth(),
                                    mfield.getHeight()
                            )
                    )
            );
        }

        newState.addShape(
                new PositionShape(
                        Color.orange,
                        new Ellipse2D.Double(
                                meta.getPosition().x,
                                meta.getPosition().y,
                                meta.getR(),
                                meta.getR()
                        )
                )
        );

        for (Ground ground : obsticles) {
            newState.addShape(
                    new PositionShape(
                            Color.black,
                            new Rectangle2D.Double(
                                    ground.getPosition().x,
                                    ground.getPosition().y,
                                    ground.getWidth(),
                                    ground.getHeight()
                            )
                    )
            );
        }

        newState.addShape(
                new PositionShape(
                        Color.darkGray,
                        new Ellipse2D.Double(
                                ball.getPosition().x,
                                ball.getPosition().y,
                                ball.getR(),
                                ball.getR()
                        )
                )
        );

        gameState = newState;
    }

    /**
     *
     * @return
     */
    private boolean gameover() {
        return false;
    }

    /**
     *
     * @param eventQueue kolejka zdarzen
     */
    public void processPhysics(ConcurrentLinkedQueue<Event> eventQueue) {
        //try {
        long currentTime = System.nanoTime();
        long dTime = currentTime - lastTime;
        lastTime = currentTime;
        ball.moveBall(dTime / 1e9);

        for (Ground ground : obsticles)
            if (ball.collidesWith(ground) || ball.collidesWith(size)) {
                System.out.println("Zderzenie z bloczkiem!");
                lives--;
                ball = new Ball(ball);
            }

            if(lives <= 0) {
                eventQueue.add(new EventNewGame());
                boolean lose=gameover();
                //throw new EndOfGameException("Przegrana");
            }

        for (PlusGravityField field : fields)

            if (ball.collidesWith(field)) {
                System.out.println("Grawitacja zwiększa się ");
                Vector2 g=new Vector2(0,0.06);
                this.ball.addforce(g);
            }

        for (MinusGravityField mfield : mfields)

            if (ball.collidesWith(mfield)) {
                System.out.println("Grawitacja zmniejsza się ");
                Vector2 g=new Vector2(0,-0.06);
                this.ball.addforce(g);
            }

        for (IncreaseBallField ifield : ifields)

            if (ball.collidesWith(ifield)) {
                System.out.println("Wielkość piłki zwiększa się");
                this.ball.r=this.ball.r+0.004;
            }


        if (ball.collidesWith(meta)) {
            System.out.println("Wygrałeś!! ");
            eventQueue.add(new EventNextLevel());
        }
        updateState();

        }

    /**
     *
     * @return
     */
    public int getLives() {
        return lives;
    }
}
