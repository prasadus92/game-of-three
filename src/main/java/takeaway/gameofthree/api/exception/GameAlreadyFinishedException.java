package takeaway.gameofthree.api.exception;

import org.springframework.http.HttpStatus;
import takeaway.gameofthree.api.util.ErrorCode;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class GameAlreadyFinishedException extends AppException {

    private static final long serialVersionUID = 1L;

    public GameAlreadyFinishedException() {
        super(ErrorCode.GAME_ALREADY_FINISHED.getMessage(), ErrorCode.GAME_ALREADY_FINISHED, HttpStatus.BAD_REQUEST);
    }
}
