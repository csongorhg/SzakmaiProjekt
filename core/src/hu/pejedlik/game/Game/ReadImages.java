package hu.pejedlik.game.Game;

import java.util.ArrayList;

/**
 * Created by Hegedüs Csongor on 2/27/2018.
 */

public class ReadImages implements Comparable {

    private String path;
    private String path2;
    private String id;

    public ArrayList<String> getSource() {
        return source;
    }

    private ArrayList<String> source;

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
    public ReadImages(String path, String id) {
        source = new ArrayList<String>();
        this.path = path + ".png";
        this.path2 = path + "2.png";
        this.id = id;
        source.add(id.substring(0,id.length()-1));
    }
    public void addSource(String id)
    {
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
        return ((ReadImages)o).getId().compareTo(this.getId());
    }
}
