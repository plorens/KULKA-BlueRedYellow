package Data;

/**
 *
 */
public class EndOfGameException extends Exception {
    public EndOfGameException(String message) {
        super(message);
    }

    public EndOfGameException() {
        super("Koniec gry");
    }
}
