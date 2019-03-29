package Model.Events;

import Data.EndOfGameException;
import Model.Model;

/**
 *  Klasa obsługująca zdarzenie zmiany poziomu
 */
public class EventNextLevel implements Event {

        @Override
        /**
         * Funkcja rozpoczynająca grę poprzez uruchomienie funkcji newGame w obiekcie typu Model
         */
        public void process(Model model) throws EndOfGameException {
            model.nextLevel();
        }
    }
