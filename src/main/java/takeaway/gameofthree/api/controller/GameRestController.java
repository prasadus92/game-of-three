package takeaway.gameofthree.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import takeaway.gameofthree.api.dto.ErrorDTO;
import takeaway.gameofthree.api.dto.GameDTO;
import takeaway.gameofthree.api.exception.AppException;
import takeaway.gameofthree.api.exception.GameAlreadyFinishedException;
import takeaway.gameofthree.api.exception.InvalidNumberMoveException;
import takeaway.gameofthree.api.exception.InvalidTurnMoveException;
import takeaway.gameofthree.api.model.Move;
import takeaway.gameofthree.api.service.GameRestService;

import java.io.IOException;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
@RestController
@RequestMapping(value = "/game")
public class GameRestController {

    @Autowired
    GameRestService gameRestService;

    @RequestMapping(method = RequestMethod.POST, value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDTO> registerPlayer() throws IOException {
        GameDTO game = gameRestService.registerPlayer();
        return new ResponseEntity<GameDTO>(game, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{gameId}/{playerId}")
    public SseEmitter registerGame(@PathVariable String gameId, @PathVariable String playerId) throws IOException {
        SseEmitter sseEmitter = gameRestService.registerGame(gameId, playerId);
        return sseEmitter;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{gameId}/{playerId}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendNumber(@PathVariable String gameId, @PathVariable String playerId,
                                        @PathVariable Integer number)
            throws IOException, InvalidTurnMoveException, GameAlreadyFinishedException, InvalidNumberMoveException {
        Move move = gameRestService.nextMove(gameId, playerId, number);
        return new ResponseEntity<Move>(move, HttpStatus.OK);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDTO> handleAppException(AppException appException) {
        ErrorDTO errorDTO = new ErrorDTO(appException.getErrorCode());
        return new ResponseEntity<ErrorDTO>(errorDTO, appException.getHttpStatus());
    }
}