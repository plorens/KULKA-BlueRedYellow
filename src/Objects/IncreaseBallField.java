package Objects;

import Model.Vector2;

/**
 *
 */
public class IncreaseBallField {

    private double width;
    private double height;
    private Vector2 position;

    /**
     *
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public double getHeight() { return height; }

    /**
     *
     * @param width
     * @param height
     * @param position
     */
    public IncreaseBallField(double width, double height, Vector2 position) {
        this.width = width;
        this.height = height;
        this.position= position;
    }

}
