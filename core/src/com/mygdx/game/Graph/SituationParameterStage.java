package com.mygdx.game.Graph;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Felhasznalo on 2017. 10. 28..
 */

public class SituationParameterStage extends MyStage {
    private ArrayList<GraphElement> situationParameters;
    private String eventName;

    public SituationParameterStage(Viewport viewport, Batch batch, MyGdxGame game,String eventName) {
        super(viewport, batch, game);
        this.eventName = eventName;


    }
    @Override
    public void init() {
        super.init();
        addBackEventStackListener();
        situationParameters = new ArrayList<GraphElement>();
        //this.setDebugAll(true);
        int row = 0;
        int index = 0;
        String[] data = new String[100];
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(eventName + ".txt")));
            while (br.ready()) {
                data[index] = br.readLine();
                System.out.println("A");
                System.out.println(index+"  "+data[index]);
                index++;
            }
            br.close();
        } catch(Exception e) {}
        int max = 0;
        for(int i = 0; i < index; i++)
        {
            if(max < data[i].length())
            {
                max = data[i].length();
            }
        }
        int[] coll = new int[max];
        for(int i = 0; i < index;i++) {
            row = data[i].length() - 1;
            GraphElement element = new GraphElement(data[i], row, coll[row], getViewport().getWorldWidth(), getViewport().getWorldHeight());
            this.addActor(element);
            situationParameters.add(element);
            coll[row]++;
        }
        for (int i = 0; i < coll.length; i++) {
            for(int j = 0; j < index; j++) {
                if(situationParameters.get(j).getRow() == i) {
                    float x = situationParameters.get(j).getX();
                    float newx = x-(coll[i]*60)/2;
                    situationParameters.get(j).setX(newx);

                }
            }
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
