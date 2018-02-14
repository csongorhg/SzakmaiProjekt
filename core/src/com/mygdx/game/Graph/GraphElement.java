package com.mygdx.game.Graph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
        this.setWidth(50);
        this.setHeight(50);
        this.setX((worldWidth / 2 + 35) + coll * 60); // approximately
        this.setY((worldHeight / 2 - 25)- row * 60);
        this.text = new Texture(Gdx.files.internal("Iskola//"+situationId+".png"));

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
