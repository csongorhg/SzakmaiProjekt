package hu.pejedlik.game.Loading;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public enum EventType {
    TRANSPORT("Transport"),
    STRANGERS("Strangers"),
    SCHOOL("School"),
    ROOM("Room"),
    KITCHEN("Kitchen"),
    INTERNET("Internet"),
    PLAYGROUND("Playground"),
    CONFLICT("Conflict");

    private final String text;

    EventType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
