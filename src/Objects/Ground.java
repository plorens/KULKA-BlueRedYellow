package Objects;

import Model.Vector2;

/**
 * Klasa z bloczkiem, który gracz bedzie musiał ominąć
 */
public class Ground {

    private double width;
    private double height;
    private Vector2 position;

    /**
     * Funkcja zwracająca szerokość bloczka
     * @return szerokość bloczka
     */
    public double getWidth() {
        return width;
    }

    /**
     * Funkcja zwracająca wysokość bloczka
     * @return  wysokość bloczka
     */
    public double getHeight() {
        return height;
    }

    /**
     * Funkcja zwracająca pozycję bloczka
     * @return pozycja górnego, lewego kątu bloczka
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     *  Konstruktor bloczka
     * @param width szerokość bloczka
     * @param height wysokość bloczka
     * @param position pozycja górnego, lewego kątu bloczka
     */
    public Ground(double width, double height, Vector2 position) {
        this.width = width;
        this.height = height;
        this.position= position;
    }
}