package tsunderechan.exception;

import tsunderechan.ui.Ui;

public class InsufficientInformationException extends IllegalArgumentException {
    public InsufficientInformationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return Ui.LINE + "\n" + getMessage() + "\n" + Ui.LINE;
    }
}
