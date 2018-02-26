package hu.pejedlik.game.Graph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.pejedlik.game.Loading.EventType;

/**
 * Created by Hegedüs Csongor on 10/28/2017.
 */

public class GraphElement extends Actor {

    private int row, coll;
    private String situationId;
    private Texture text;



    public GraphElement(String s, int row,int coll, float worldWidth, float worldHeight) {
        situationId = s;
        System.out.println(s);
        this.row = row;
        this.coll = coll;
        this.setWidth(200);
        this.setHeight(200);
        this.setX((worldWidth / 2 + 35) + coll * 250); // approximately
        this.setY((worldHeight / 2 - 25)- row * 250);
        this.text = new Texture(Gdx.files.internal("events//"+ EventType.currentEventType+"_event//"+situationId+".png"));

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
