package am.gevorg.scope.exception;


public class ModelAlreadyExistException extends Exception {
    public ModelAlreadyExistException() {
    }

    public ModelAlreadyExistException(String message) {
        super(message);
    }

    public ModelAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public ModelAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
