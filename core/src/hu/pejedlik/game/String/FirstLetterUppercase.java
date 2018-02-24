package hu.pejedlik.game.String;

/**
 * Created by Hegedüs Csongor on 2/24/2018.
 */

public class FirstLetterUppercase {

    private String s;

    public FirstLetterUppercase(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
