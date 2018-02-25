package hu.pejedlik.game.Game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class GameStage extends MyStage {

    private Graph graph;

    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        super.init();
        addBackEventStackListener();
        setCameraResetToLeftBottomOfScreen();
        for (Class a: game.backButtonStack
             ) {
            System.out.println(a.getSimpleName());
        }
        int[] layerLengthCount = new int[Assets.longestLine];

        for (int layerLength = 1; layerLength <= Assets.longestLine; layerLength++) {
            layerLengthCount[layerLength - 1] = 0;
            for (ReadImages readImages : Assets.readImages) {
                layerLengthCount[layerLength - 1] = readImages.getId().length() == layerLength
                        ? layerLengthCount[layerLength - 1] += 1 : layerLengthCount[layerLength - 1];
            }
        }

        float x, y = 0, width, height;
        for (int i = 0; i < layerLengthCount.length; i++) {
            x = 0;
            height = 0;
            for (ReadImages readImages: Assets.readImages) {
                if (readImages.getId().length() == i + 1) {
                    OneSpriteStaticActor actor = new OneSpriteStaticActor(Assets.manager.get(readImages.getPath(), Texture.class));
                    actor.setPosition(x,y);
                    actor.setSize(getViewport().getWorldWidth() / 7,getViewport().getWorldHeight() / 5);
                    height = actor.getHeight();
                    width = actor.getWidth();
                    x += width;
                    addActor(actor);
                }
            }
            y -= height;
        }

        //setCameraMoveToXY(0f,0f, 0.5f, 100f);


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
