package hu.pejedlik.game.Game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.MyBaseClasses.ImgButton;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyBaseClasses.SituationsActor;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class GameStage extends MyStage {
    public static boolean Newactor = false;
    private Array<String> imgs;
    Table table;
    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        super.init();
        addBackEventStackListener();
        table = new Table();
        table.setFillParent(true);
        actors();
        this.addActor(table);

    }
    private void actors()
    {
        imgs = Assets.getChilderns(EventType.currentId);
        if(imgs != null) {
            for (String a : imgs) {
                table.add(new SituationsActor(a)).size(300, 300).center().spaceLeft(50f);
            }
        }
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(Newactor)
        {
            imgs.clear();
            table.clear();
            if(Assets.getImg(EventType.currentId).getType().equals("?")) {
                actors();
            }
            else
            {

            }
            Newactor = false;

        }
    }

    @Override
    public void dispose() {
        for(ReadImages dat : Assets.readImages)
        {
            Assets.pref.putBoolean(dat.getId()+ EventType.currentEventType,dat.isPlayed());
        }
        Assets.pref.flush();
        super.dispose();

    }
}
