package hu.pejedlik.game.Graph;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyGdxGame;
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
        addBackEventStackListener();
        setCameraResetToLeftBottomOfScreen();
        sr = new ShapeRenderer();
        situationParameters = new ArrayList<GraphElement>();
        //this.setDebugAll(true);
        stage = new GraphStage();
        gt = new GestureDetector(20, 0.5f, 2, 0.15f, stage);
        int row = 0;
        int[] coll = new int[Assets.longestLine];
        System.out.println(Assets.readImages.size);
        for (int i = 0; i < Assets.readImages.size; i++) {
            row = Assets.readImages.get(i).getId().length() - 1;
            GraphElement element = new GraphElement(Assets.readImages.get(i), row, coll[row], getViewport().getWorldWidth(), getViewport().getWorldHeight());
            System.out.println("asd: "+element.getPath());
            stage.addActor(element);
            situationParameters.add(element);
            coll[row]++;
        }
        for (int i = 0; i < coll.length; i++) {
            for (int j = 0; j < Assets.readImages.size; j++) {
                if (situationParameters.get(j).getRow() == i) {
                    float x = situationParameters.get(j).getX();
                    float newx = x - (coll[i] * 400) / 2;
                    situationParameters.get(j).setX(newx);
                    situationParameters.get(j).resize();

                }
            }
        }

    }

    public void linedraw(ShapeRenderer sr) {
        sr.setProjectionMatrix(stage.getCamera().combined);
        for (GraphElement a : situationParameters) {
            for (GraphElement b : situationParameters) {
                for(String source : b.getSource()) {
                    if (a.getStiuationId().equals(source)) {
                        sr.rectLine(a.getX() + a.getWidth() / 2, a.getY(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight(),2);
                    }
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
