package musicplayer.exception;

public class AlreadyInCartException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AlreadyInCartException(String message) {
       // super(SongName + " Already In Cart.");
        super(message);
    }
}
