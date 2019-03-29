package Model;

/**
 * Klasa zawierająca 2 współrzędne- punkt/wektor na płaszczyźnie
 */
public class Vector2 {

    public double x,y;

    /**
     * Konstruktor klasy Vector2
     * @param x wartość na osi x
     * @param y wartość na osi y
     */
    public Vector2 (double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @param vector2
     */
    public Vector2 (Vector2 vector2)
    {
        this.x=vector2.x;
        this.y=vector2.y;
    }

    /**
     *  Funkcja mnożąca wektor razy jakąś wartość
     * @param v wektor, który chcemy pomnożyć
     * @param a skalar przez który mnożony jest wektor
     * @return Iloczyn wektora i skalara
     */
    public static Vector2 multiply(Vector2 v, double a)
    {
        return new Vector2(v.x*a, v.y*a);
    }

    /**
     *  Funkcja dzieląca wektor przez jakąś wartość
     * @param v wektor, który chcemy podzielić
     * @param a skalar przez który dzielony jest wektor
     * @return Iloraz wektora i skalara
     */
    public static Vector2 divide(Vector2 v, double a)
    {
        return new Vector2(v.x/a, v.y/a);
    }

    /**
     *  Funkcja sumująca 2 wektory
     * @param v wektor
     * @param v2 wektor
     * @return Suma wektorów
     */
    public static Vector2 sum(Vector2 v, Vector2 v2)
    {
        return new Vector2(v.x+v2.x, v.y+v2.y);
    }

    /**
     *  Funkcja odejmująca 2 wektory
     * @param v wektor
     * @param v2 wektor
     * @return
     */
    public static Vector2 diff(Vector2 v, Vector2 v2) { return new Vector2(v.x-v2.x, v.y-v2.y); }
}
