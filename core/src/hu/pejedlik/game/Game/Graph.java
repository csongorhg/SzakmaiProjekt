package hu.pejedlik.game.Game;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Hegedüs Csongor on 2/24/2018.
 */

public class Graph {

    private String eventName;
    private Array<String> imagepath;

    public Graph(String eventName, Array<String> imagepath) {
        this.eventName = eventName;
        this.imagepath = imagepath;
    }


}
