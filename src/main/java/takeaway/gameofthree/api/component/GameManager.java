package takeaway.gameofthree.api.component;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import takeaway.gameofthree.api.exception.GameAlreadyFinishedException;
import takeaway.gameofthree.api.exception.InvalidNumberMoveException;
import takeaway.gameofthree.api.exception.InvalidTurnMoveException;
import takeaway.gameofthree.api.model.Move;

import java.io.IOException;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public interface GameManager {

    GamePlayerRegistration registerGame();

    SseEmitter getEmitterForPlayer(String gameId, String playerId);

    Move nextMove(String idGame, String idPlayer, Integer number)
            throws IOException, InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException;
}