package takeaway.gameofthree.api.exception;

import org.springframework.http.HttpStatus;
import takeaway.gameofthree.api.util.ErrorCode;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class InvalidTurnMoveException extends AppException {

    private static final long serialVersionUID = 1L;

    public InvalidTurnMoveException() {
        super(ErrorCode.INVALID_TURN_MOVE.getMessage(), ErrorCode.INVALID_TURN_MOVE, HttpStatus.BAD_REQUEST);
    }
}
