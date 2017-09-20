package takeaway.gameofthree.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import takeaway.gameofthree.api.component.GameManager;
import takeaway.gameofthree.api.component.GamePlayerRegistration;
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
@Service
public class GameRestServiceImpl implements GameRestService {

    @Autowired
    private GameManager gameManager;

    public GameDTO registerPlayer() {
        GamePlayerRegistration gamePlayerRegistration = gameManager.registerGame();
        return new GameDTO(gamePlayerRegistration.getGame().getId(), gamePlayerRegistration.getPlayer().getPlayerId());
    }

    public SseEmitter registerGame(String gameId, String playerId) throws IOException {
        return gameManager.getEmitterForPlayer(gameId, playerId);
    }

    public Move nextMove(String gameId, String playerId, Integer number)
            throws IOException, InvalidTurnMoveException, InvalidNumberMoveException, GameAlreadyFinishedException {
        return gameManager.nextMove(gameId, playerId, number);
    }
}
