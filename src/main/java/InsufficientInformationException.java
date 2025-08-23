public class InsufficientInformationException extends IllegalArgumentException {
    InsufficientInformationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return TsundereChan.LINE + "\n" + getMessage() + "\n" + TsundereChan.LINE;
    }
}
