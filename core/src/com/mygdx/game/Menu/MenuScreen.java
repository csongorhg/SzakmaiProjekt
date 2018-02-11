package com.mygdx.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {

    protected MenuStage menuStage;
    private MyStage bgStage;

    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        bgStage.act(delta);
        bgStage.draw();

        menuStage.act(delta);
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void dispose() {
        menuStage.dispose();
        super.dispose();
    }

    @Override
    public void init() {
        bgStage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game) {

            private OneSpriteStaticActor backGroundActor;
            private OneSpriteStaticActor money;

            @Override
            public void init() {
                r = 1;
                g = 1;
                b = 1;
                backGroundActor = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
                backGroundActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                setCameraZoomXY(backGroundActor.getWidth() / 2, backGroundActor.getHeight() / 2, 40);
                setCameraMoveToXY(backGroundActor.getWidth() / 2
                        , backGroundActor.getHeight() / 2, 1, 80);
                addActor(backGroundActor);
            }

            @Override
            protected void resized() {

            }
        };

        menuStage  = new MenuStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game);
        Gdx.input.setInputProcessor(menuStage);

        setBackGroundColor(1f, 1f, 1f);

    }


}
