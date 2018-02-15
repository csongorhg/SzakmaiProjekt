package hu.pejedlik.game.Game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class GameStage extends MyStage {

    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        super.init();

        //addBackEventStackListener();
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
