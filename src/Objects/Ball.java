package Objects;

import Model.Vector2;
import javax.swing.*;

import static Model.Vector2.diff;


/**
 *  Klasa kulki, która może poruszać użytkownik
 */
public class Ball extends JPanel {

    public double g= 5;
    public double r= 1;
    public double mass;
    private Vector2 startposition;
    private Vector2 position;
    private Vector2 velocity;
    public Vector2 force;

    /**
     *  Funkcja zwracająca promień kulki
     * @return promień kulki
     */
    public double getR() {
        return r;
    }

    /**
     *  Funkcja zwracająca pozycję kulki
     * @return pozycja kulki
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Konstruktor tworzący obiekt typu Ball
     * @param r promień kulki
     * @param mass masa kulki
     * @param startposition pozycja startowa kulki
     */
    public Ball(int r, double mass, Vector2 startposition) {
        this.startposition = startposition;
        this.r = r;
        this.velocity = new Vector2(0,0);
        this.mass = mass;
        this.position = startposition;
        this.force = Vector2.multiply(new Vector2(0, g), this.mass);

    }

    /**
     *
     * @param ball
     */
    public Ball(Ball ball) {
        this.startposition = new Vector2(ball.startposition);
        this.r = 1;
        this.velocity = new Vector2(0,0);
        this.mass = ball.mass;
        this.position = ball.startposition;
        this.force = Vector2.multiply(new Vector2(0, ball.g), this.mass);
    }

    /**
     *  Funkcja dodająca siłę do kulki
     * @param f
     */
    public void addforce(Vector2 f)
    {
        this.force=Vector2.sum(this.force, f);
    }



    /**
     *
     * @param dt
     */
    public void moveBall(double dt)
    {
        Vector2 dv=Vector2.divide(Vector2.multiply(force,dt),mass);
        velocity= Vector2.sum(velocity,dv);

        Vector2 ds=Vector2.multiply(velocity, dt);
        position= Vector2.sum(position,ds);
    }

    /**
     *
     * @param size
     * @return
     */
    public boolean collidesWith(Vector2 size)
    {
        return (size.x-1 < position.x || size.y-1 < position.y || 0 > position.y || 0 > position.x );
    }

    /**
     *
     * @param ground
     * @return
     */
    public boolean collidesWith(Ground ground)
    {
        double DeltaX = position.x - Math.max(ground.getPosition().x, Math.min(position.x, ground.getPosition().x + ground.getWidth()-1));
        double DeltaY = position.y - Math.max(ground.getPosition().y, Math.min(position.y, ground.getPosition().y + ground.getHeight()-1));
        return (DeltaX*DeltaX + DeltaY*DeltaY) < ((r*r)*0.9);
    }

    /**
     *
     * @param field
     * @return
     */
    public boolean collidesWith(PlusGravityField field)
    {
        double DeltaX = position.x - Math.max(field.getPosition().x, Math.min(position.x, field.getPosition().x + field.getWidth()-1));
        double DeltaY = position.y - Math.max(field.getPosition().y, Math.min(position.y, field.getPosition().y + field.getHeight()-1));
        return (DeltaX*DeltaX + DeltaY*DeltaY) < ((0.5*r));
    }

    /**
     *
     * @param field
     * @return
     */
    public boolean collidesWith(MinusGravityField field)
    {
        double DeltaX = position.x - Math.max(field.getPosition().x, Math.min(position.x, field.getPosition().x + field.getWidth()-1));
        double DeltaY = position.y - Math.max(field.getPosition().y, Math.min(position.y, field.getPosition().y + field.getHeight()-1));
        return (DeltaX*DeltaX + DeltaY*DeltaY) < ((0.5*r));
    }

    /**
     *
     * @param field
     * @return
     */
    public boolean collidesWith(IncreaseBallField field)
    {
        double DeltaX = position.x - Math.max(field.getPosition().x, Math.min(position.x, field.getPosition().x + field.getWidth()-1));
        double DeltaY = position.y - Math.max(field.getPosition().y, Math.min(position.y, field.getPosition().y + field.getHeight()-1));
        return (DeltaX*DeltaX + DeltaY*DeltaY) < ((0.5*r));
    }

    /**
     *
     * @param meta
     * @return
     */
    public boolean collidesWith(Meta meta)
    {
        Vector2 Delta = diff(position,meta.getPosition());
        return (Math.sqrt(Delta.x*Delta.x+Delta.y*Delta.y)) < 1.2*r;
    }
}
