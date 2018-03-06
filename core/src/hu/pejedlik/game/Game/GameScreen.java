package hu.pejedlik.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.MyBaseClasses.MyScreen;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;

/**
 * Created by Hegedüs Csongor on 2/14/2018.
 */

public class GameScreen extends MyScreen{

    protected GameStage gameStage;
    private MyStage bgStage;

    public GameScreen(MyGdxGame game) {
        super(game);



    }

    @Override
    public void render(float delta) {
        super.render(delta);
        bgStage.act(delta);
        bgStage.draw();
        gameStage.act(delta);
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        //gameStage.dispose();
        for(ReadImages dat : Assets.readImages)
        {
            Assets.pref.putBoolean(dat.getId()+ EventType.currentEventType,dat.isPlayed());
        }
        Assets.pref.flush();
        super.dispose();
    }

    @Override
    public void init() {
        gameStage = new GameStage(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)),spriteBatch,game);
        bgStage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game) {

            OneSpriteStaticActor a;
            OneSpriteStaticActor b;
            @Override
            public void init() {
                a = new OneSpriteStaticActor((Texture)Assets.manager.get(Assets.getImg(EventType.currentId).getPath()));
                a.setBounds(0,0,getViewport().getScreenWidth(),getViewport().getScreenHeight());
                addActor(a);
            }
            @Override
            public void act(float delta)
            {
                    a.setTexture((Texture) Assets.manager.get(Assets.getImg(EventType.currentId).getPath()));
            }
        };
        Gdx.input.setInputProcessor(gameStage);
        setBackGroundColor(1f, 1f, 1f);
    }
}
