package Window;

import java.awt.*;

/**
 *
 */
public class PositionShape {

    private Color color;
    private Shape shape;

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public Shape getShape() {
        return shape;
    }

    /**
     *
     * @param color Kolor kształtu do narysowania
     * @param shape Obiekt typu shape (z awt) do narysowania)
     */
    public PositionShape(Color color, Shape shape)
    {
        this.color=color;
        this.shape= shape;
    }
}
