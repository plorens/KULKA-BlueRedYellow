package Model.Events;
import Data.EndOfGameException;
import Model.Model;
import Model.Vector2;

/**
 *  Klasa obsługująca zdarzenie dodawania siły przykładanej do kulki przez użytkownika
 */
public class EventForce implements Event {

    private Vector2 force;

    /**
     *  Konstruktor obiektu klasy EventForce
     *  @param force siła
     */
    public EventForce(Vector2 force) {
        this.force = force;
    }

    @Override
    /**
     *  Funkcja dodająca siłę do kulki
     */
    public void process(Model model) throws EndOfGameException {
        if (model.getGame()==null)
                return;
        model.getGame().getBall().addforce(force);
    }
}
