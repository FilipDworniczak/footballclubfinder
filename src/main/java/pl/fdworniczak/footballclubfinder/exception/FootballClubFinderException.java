package pl.fdworniczak.footballclubfinder.exception;

/**
 * Created by filip on 03.10.19
 */
public class FootballClubFinderException extends RuntimeException {
    private static final String CUSTOM_MESSAGE = "Couldn't find football club by provided name";
    String message;

    public FootballClubFinderException() {
        super(CUSTOM_MESSAGE);
        this.message = CUSTOM_MESSAGE;
    }

    public FootballClubFinderException(final String message) {
        super(message);
        this.message = message;
    }
}
