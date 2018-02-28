package hu.pejedlik.game.Game;

/**
 * Created by Hegedüs Csongor on 2/27/2018.
 */

public class ReadImages implements Comparable {

    private String path;
    private String path2;
    private String id;

    public String getSource() {
        return source;
    }

    private String source;

    public ReadImages(String path, String id) {
        this.path = path + ".png";
        this.path2 = path + "2.png";
        this.id = id;
        source = id.substring(0,id.length()-1);
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
