package takeaway.gameofthree.api.exception;

import org.springframework.http.HttpStatus;
import takeaway.gameofthree.api.util.ErrorCode;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    private HttpStatus httpStatus;

    public AppException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
