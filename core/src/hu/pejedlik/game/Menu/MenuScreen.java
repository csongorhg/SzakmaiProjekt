package hu.pejedlik.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;

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
