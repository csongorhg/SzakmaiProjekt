package hu.pejedlik.game.Graph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.pejedlik.game.Loading.EventType;

/**
 * Created by Hegedüs Csongor on 10/28/2017.
 */

public class GraphElement extends Actor {

    private int row, coll;
    private String situationId;
    private Texture text;
    public String source;



    public GraphElement(String s, int row,int coll, float worldWidth, float worldHeight) {
        situationId = s;
        source = s.substring(0,s.length()-1);
        System.out.println(s+" "+source);
        this.row = row;
        this.coll = coll;
        this.setWidth(200);
        this.setHeight(200);
        this.setX((worldWidth / 2 + 35) + coll * 250); // approximately
        this.setY((worldHeight / 2 - 25)- row * 250);
        this.text = new Texture(Gdx.files.internal("events//"+ EventType.currentEventType+"_event//"+situationId+".png"));
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
    }
}
