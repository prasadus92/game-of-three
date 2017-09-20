package takeaway.gameofthree.api.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import takeaway.gameofthree.api.exception.GameAlreadyFinishedException;
import takeaway.gameofthree.api.exception.InvalidNumberMoveException;
import takeaway.gameofthree.api.exception.InvalidTurnMoveException;
import takeaway.gameofthree.api.model.Game;
import takeaway.gameofthree.api.model.Move;
import takeaway.gameofthree.api.model.Player;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
@Component
public class GameManagerImpl implements GameManager {

    private Map<String, Game> games = new Hashtable<String, Game>();
    private Game lastGame;

    public synchronized GamePlayerRegistration registerGame() {
        if (lastGame == null) {
            lastGame = new Game(getGameId());
            lastGame.setNextMovePlayer(Player.PLAYER_1);
            games.put(lastGame.getId(), lastGame);
            return new GamePlayerRegistration(lastGame, Player.PLAYER_1);
        } else {
            Game game = lastGame;
            lastGame = null;
            return new GamePlayerRegistration(game, Player.PLAYER_2);
        }
    }

    private String getGameId() {
        return "G" + (games.size() + 1);
    }

    public SseEmitter getEmitterForPlayer(String gameId, String playerId) {
        Game game = games.get(gameId);
        if (game != null) {
            if (playerId.equals(Player.PLAYER_1.getPlayerId()))
                return game.getPlayer1();
            else {
                return game.getPlayer2();
            }
        }
        return null;
    }

    public Move nextMove(String gameId, String playerId, Integer number)
            throws IOException, InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException {
        Game game = games.get(gameId);
        if (game != null) {
            synchronized (game) {
                if (!isGameFinished(game)) {
                    if (!playerId.equals(game.getNextMovePlayer().getPlayerId())) {
                        throw new InvalidTurnMoveException();
                    }

                    if (game.getLastMove() != null && !number.equals(game.getLastMove().getNextNumber())) {
                        throw new InvalidNumberMoveException();
                    }

                    Move move = calculateMove(playerId, number);

                    updateNextTurn(playerId, game, move);

                    game.getPlayer1().send(move);
                    game.getPlayer2().send(move);
                    if (move.isGameFinished()) {
                        game.getPlayer1().complete();
                        game.getPlayer2().complete();
                    }
                    return move;
                } else {
                    throw new GameAlreadyFinishedException();
                }
            }
        }
        return null;
    }

    private void updateNextTurn(String playerId, Game game, Move move) {
        if (playerId.equals(Player.PLAYER_1.getPlayerId())) {
            game.setNextMovePlayer(Player.PLAYER_2);
        } else {
            game.setNextMovePlayer(Player.PLAYER_1);
        }
        game.setLastMove(move);
    }

    private Move calculateMove(String playerId, Integer number) {
        Integer nextNumber;
        Integer added;
        if (number % 3 == 0) {
            nextNumber = number / 3;
            added = 0;
        } else if ((number - 1) % 3 == 0) {
            nextNumber = (number - 1) / 3;
            added = -1;
        } else {
            nextNumber = (number + 1) / 3;
            added = 1;
        }
        return new Move(playerId, number, nextNumber, added, nextNumber == 1 ? true : false);
    }

    private boolean isGameFinished(Game game) {
        return game.getLastMove() != null && game.getLastMove().isGameFinished();
    }
}