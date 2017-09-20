package takeaway.gameofthree.api.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import takeaway.gameofthree.api.dto.GameDTO;
import takeaway.gameofthree.api.exception.GameAlreadyFinishedException;
import takeaway.gameofthree.api.exception.InvalidNumberMoveException;
import takeaway.gameofthree.api.exception.InvalidTurnMoveException;
import takeaway.gameofthree.api.model.Move;

import java.io.IOException;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public interface GameRestService {

    GameDTO registerPlayer();

    SseEmitter registerGame(String gameId, String playerId) throws IOException;

    Move nextMove(String gameId, String playerId, Integer number)
            throws IOException, InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException;
}
