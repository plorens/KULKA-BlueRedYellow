import Model.Events.Event;
import Window.Frame;
import Model.Model;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Główna klasa gry
 */
public class Main {

    public static void main(String[] args) {

        ConcurrentLinkedQueue<Event> queue= new ConcurrentLinkedQueue<>();
        Model model= new Model(queue);
        Frame window = new Frame(model, queue);
        model.gameLoop();
        //window.setVisible(false);
    }
}