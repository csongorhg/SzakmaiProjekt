package hu.pejedlik.game.Game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
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
        addBackEventStackListener();
        setCameraResetToLeftBottomOfScreen();
        OneSpriteStaticActor asd = new OneSpriteStaticActor(Assets.manager.get(Assets.imagePath.get(0), Texture.class));
        asd.setPosition(0,0);
        addActor(asd);

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
