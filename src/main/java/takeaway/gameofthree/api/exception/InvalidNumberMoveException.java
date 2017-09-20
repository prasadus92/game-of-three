package takeaway.gameofthree.api.exception;

import org.springframework.http.HttpStatus;
import takeaway.gameofthree.api.util.ErrorCode;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class InvalidNumberMoveException extends AppException {

    private static final long serialVersionUID = 1L;

    public InvalidNumberMoveException() {
        super(ErrorCode.INVALID_NUMBER_MOVE.getMessage(), ErrorCode.INVALID_NUMBER_MOVE, HttpStatus.BAD_REQUEST);
    }
}
