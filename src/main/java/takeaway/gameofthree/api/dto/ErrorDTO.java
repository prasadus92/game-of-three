package takeaway.gameofthree.api.dto;

import takeaway.gameofthree.api.util.ErrorCode;

/**
 * @author prasad on 19-09-2017
 * @project gameofthree
 */
public class ErrorDTO {

    private String errorCode;

    private String errorMessage;

    public ErrorDTO() {
    }

    public ErrorDTO(ErrorCode validationError) {
        this.errorCode = validationError.getCode();
        this.errorMessage = validationError.getMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String code) {
        this.errorCode = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
        result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorDTO other = (ErrorDTO) obj;
        if (errorCode == null) {
            if (other.errorCode != null)
                return false;
        } else if (!errorCode.equals(other.errorCode))
            return false;
        if (errorMessage == null) {
            if (other.errorMessage != null)
                return false;
        } else if (!errorMessage.equals(other.errorMessage))
            return false;
        return true;
    }
}