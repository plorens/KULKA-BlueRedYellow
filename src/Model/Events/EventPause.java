package Model.Events;
import Data.EndOfGameException;
import Model.Model;

/**
 *  Klasa obsługująca zdarzenie zamykania aplikacji
 */
public class EventPause implements Event{

    @Override

    /**
     *Funkcja wywołująca pauzowanie apliikacji poprzez uruchomienie metody close w obiekcie typu Model
     */
    public void process(Model model) throws EndOfGameException {
        model.pause();
    }
}
