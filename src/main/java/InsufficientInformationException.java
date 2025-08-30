public class InsufficientInformationException extends IllegalArgumentException {
    InsufficientInformationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return Ui.LINE + "\n" + getMessage() + "\n" + Ui.LINE;
    }
}
