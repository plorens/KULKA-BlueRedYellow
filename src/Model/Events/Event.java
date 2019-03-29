package Model.Events;
import Data.EndOfGameException;
import Model.Model;


/**
 * Publiczny interfejs modelu
 */
public interface Event {

    /**
     *
     * @param model Obiekt typu Model
     */
    void process(Model model) throws EndOfGameException;

}
