package takeaway.gameofthree.api.component;

import takeaway.gameofthree.api.model.Game;
import takeaway.gameofthree.api.model.Player;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class GamePlayerRegistration {
    private Game game;
    private Player player;

    public GamePlayerRegistration(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
