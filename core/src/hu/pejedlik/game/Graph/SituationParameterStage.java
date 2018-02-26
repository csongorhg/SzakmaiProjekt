package hu.pejedlik.game.Graph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyGdxGame;

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
    private GraphStage stage;
    private GestureDetector gt;
    private ShapeRenderer sr;


    public SituationParameterStage(Viewport viewport, Batch batch, MyGdxGame game, InputMultiplexer im) {
        super(viewport, batch, game);
        im.addProcessor(gt);
        im.addProcessor(stage);


    }

    @Override
    public void init() {
        super.init();
        sr = new ShapeRenderer();
        addBackEventStackListener();
        situationParameters = new ArrayList<GraphElement>();
        //this.setDebugAll(true);
        stage = new GraphStage();
        gt = new GestureDetector(20, 0.5f, 2, 0.15f, stage);
        int row = 0;
        String[] data = Assets.data;
        int[] coll = new int[Assets.longestLine];
        for (int i = 0; i < data.length; i++) {
            row = data[i].length() - 1;
            GraphElement element = new GraphElement(data[i], row, coll[row], getViewport().getWorldWidth(), getViewport().getWorldHeight());
            stage.addActor(element);
            situationParameters.add(element);
            coll[row]++;
        }
        for (int i = 0; i < coll.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (situationParameters.get(j).getRow() == i) {
                    float x = situationParameters.get(j).getX();
                    float newx = x - (coll[i] * 400) / 2;
                    situationParameters.get(j).setX(newx);

                }
            }
        }

    }

    public void linedraw(ShapeRenderer sr) {
        sr.setProjectionMatrix(stage.getCamera().combined);
        for (GraphElement a : situationParameters) {
            for (GraphElement b : situationParameters) {
                if (a.source.hashCode() == b.getSituationId().hashCode()) {
                    sr.line(a.getX() + a.getWidth() / 2, a.getY() + a.getHeight() / 2, b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2);
                }
            }

        }
    }

    @Override
    public void draw() {
        stage.draw();
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stage.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
