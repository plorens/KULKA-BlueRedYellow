package Objects;

import Model.Vector2;

/**
 * Klasa mety, czyli miejsca do którego użytkownik będzie musiał dotrzeć
 */
public class Meta {
    private Vector2 position;
    private double r;

    /**
     * Funkcja zwracająca promień mety
     * @return promień mety
     */
    public double getR() {
        return r;
    }

    /**
     *  Funkcja zwracająca pozycję mety
     * @return pozycja mety
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Konstruktor mety
     * @param r promień mety
     * @param position pozycja mety
     */
    public Meta(double r, Vector2 position) {
        this.r = r;
        this.position= position;
    }
}