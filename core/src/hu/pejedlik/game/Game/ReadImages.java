package hu.pejedlik.game.Game;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

import hu.pejedlik.game.Loading.EventType;

/**
 * Created by Hegedüs Csongor on 2/27/2018.
 */

public class ReadImages implements Comparable {

    private String path;
    private String path2;
    private String id;
    private String type;
    private int value;

    public Array<String> getSource() {
        return source;
    }

    private Array<String> source;

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    private boolean played = false;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    private String subtitle;

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public ReadImages(String path, String id, String type, int value) {
        source = new Array<String>();
        this.path = path + ".png";
        this.path2 = path + "2.png";
        this.id = id;
        this.type = type;
        this.value = value;
        source.add(id.substring(0, id.length() - 1));
    }

    public void addSource(String id) {
        source.add(id);
    }

    public String getPath() {
        return path;
    }

    public String getPath2() {
        return path2;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        return ((ReadImages) o).getId().compareTo(this.getId());
    }
}
