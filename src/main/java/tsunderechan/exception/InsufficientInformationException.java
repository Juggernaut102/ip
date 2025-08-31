package tsunderechan.exception;

import tsunderechan.ui.Ui;

/**
 * Represents an Exception that is thrown when user does not provide enough arguments.
 */
public class InsufficientInformationException extends IllegalArgumentException {
    /**
     * Instantiates an InsufficientInformationException object.
     *
     * @param message Message to be printed when this Exception is caught.
     */
    public InsufficientInformationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return Ui.LINE + "\n" + getMessage() + "\n" + Ui.LINE;
    }
}
