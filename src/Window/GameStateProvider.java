package Window;

import java.awt.*;

/**
 *  Publiczny interfejs do dostarczania aktualnego stanu gry
 */
public interface GameStateProvider {

    GameState getCurrentGameState();
    void setCanvas(Component canvas);
}
