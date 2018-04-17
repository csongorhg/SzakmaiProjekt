package hu.pejedlik.game.Loading;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public enum EventType {
    TRANSPORT("transport"),
    STRANGERS("strangers"),
    SCHOOL("school"),
    ROOM("room"),
    KITCHEN("kitchen"),
    INTERNET("internet"),
    PLAYGROUND("playground"),
    CONFLICT("conflict");

    public static EventType currentEventType;
    private final String text;
    public static String currentId;
    public static Array<String> last = new Array<String>();

    EventType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
