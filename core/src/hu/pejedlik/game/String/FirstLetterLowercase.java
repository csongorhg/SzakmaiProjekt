package hu.pejedlik.game.String;

/**
 * Created by Hegedüs Csongor on 2/24/2018.
 */

public class FirstLetterLowercase {
    private String s;

    public FirstLetterLowercase(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }
}
