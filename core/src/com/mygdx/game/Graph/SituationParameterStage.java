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

    public SituationParameterStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        situationParameters = new ArrayList<GraphElement>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("param.txt")));
            while (br.ready()) {
                GraphElement graphElement = new GraphElement(br.readLine());
                situationParameters.add(graphElement);
                System.out.println(graphElement);
            }
        br.close();
        } catch(Exception e) {}
    }
    @Override
    public void init() {
        super.init();
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
