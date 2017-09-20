package takeaway.gameofthree.api.util;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public enum ErrorCode {

    GAME_ALREADY_FINISHED("201", "The game is over. No more moves are allowed."),

    INVALID_NUMBER_MOVE("202", "This number is not allowed in the sequence of numbers."),

    INVALID_TURN_MOVE("203", "It is not your turn. Wait for the other player's move.");

    private String code;

    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
