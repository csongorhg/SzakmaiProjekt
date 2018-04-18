package hu.pejedlik.game.Graph;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import hu.pejedlik.game.Game.GameScreen;
import hu.pejedlik.game.Game.ReadImages;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Hegedüs Csongor on 10/28/2017.
 */

public class GraphElement extends Actor {

    private int row, coll;
    private String stiuationId, path, path2;
    Sprite text;
    Sprite lock;
    boolean played;

    public Array<String> getSource() {
        return source;
    }

    private Array<String> source;

    public GraphElement(final ReadImages readImages, int row, int coll, float worldWidth, float worldHeight, final MyGdxGame game) {
        stiuationId = readImages.getId();
        if(stiuationId.equals("a"))
        {
            readImages.setPlayed(true);
        }
        this.played = readImages.isPlayed();
        source = readImages.getSource();
        path = readImages.getPath();
        path2 = readImages.getPath2();
        if(played) {
            text = new Sprite((Texture) Assets.manager.get(path));
        }
        else
        {
            text = new Sprite((Texture)Assets.manager.get(path2));
        }
        this.row = row;
        this.coll = coll;
        setSize(worldWidth / 7, worldHeight / 5);
        setPosition((worldHeight / 2 + 35) + coll * 400, (worldHeight / 2 - 25) - row * 400); // approximately
        addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(readImages.isPlayed())
                {
                   EventType.currentId = stiuationId;
                    EventType.nextId = stiuationId;
                    game.setScreen(new GameScreen(game),true);

                }
            }
        });
        lock = new Sprite(Assets.manager.get(Assets.LOCK_TEXTURE));
    }
    public void resize()
    {
        text.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        lock.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        text.draw(batch);
        if(!played)
        {
            lock.draw(batch);
        }
    }

    @Override
    public String toString() {
        return "GraphElement{" +
                "stiuationId='" + stiuationId + '\'' +
                '}';
    }

    public int getRow() {
        return row;
    }

    public int getColl() {
        return coll;
    }

    public String getStiuationId() {
        return stiuationId;
    }

    public String getPath() {
        return path;
    }

    public String getPath2() {
        return path2;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColl(int coll) {
        this.coll = coll;
    }

    public void setStiuationId(String stiuationId) {
        this.stiuationId = stiuationId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

   // @Override
   // public void init() {
      //  super.init();
    //}

    /*




    private int row, coll;
    private String situationId, path;
    private Texture text;



    public GraphElement(ReadImages readImages, int row, int coll, float worldWidth, float worldHeight) {
        super();
        situationId = readImages.getId();
        path = readImages.getPath();
        this.row = row;
        this.coll = coll;
        this.setWidth(worldWidth / 7);
        this.setHeight(worldHeight / 5);
        this.setX((worldWidth / 2 + 35) + coll * 400); // approximately
        this.setY((worldHeight / 2 - 25)- row * 400);
        this.text = Assets.manager.get(source);
        final String id = situationId;
        this.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(id);
                super.clicked(event, x, y);
            }
        });

    }

    public String getSituationId() {
        return situationId;
    }

    public void setSituationId(String situationId) {
        this.situationId = situationId;
    }

    public int getRow() {
        return row;
    }

    public int getColl() {
        return coll;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColl(int coll) {
        this.coll = coll;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(text,this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }

    @Override
    public String toString() {
        return situationId;
    }*/
}
