package Model.Events;

import Data.EndOfGameException;
import Model.Model;

/**
 *  Klasa obsługująca zdarzenie rozpoczęcia gry
 */
public class EventNewGame implements Event {
    @Override
    /**
     * Funkcja rozpoczynająca grę poprzez uruchomienie funkcji newGame w obiekcie typu Model
     */
    public void process(Model model) throws EndOfGameException {
        model.newGame();
    }
}
