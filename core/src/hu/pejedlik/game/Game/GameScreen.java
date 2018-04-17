package hu.pejedlik.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
        gameStage.dispose();

        super.dispose();
    }

    @Override
    public void init() {
        gameStage = new GameStage(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)),spriteBatch,game);
        OneSpriteStaticActor a = new OneSpriteStaticActor(Assets.manager.get(Assets.CLOSE_TEXTURE));
        a.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(EventType.last.size != 0) {
                    EventType.currentId = EventType.last.get(EventType.last.size - 1);
                    EventType.last.removeIndex(EventType.last.size - 1);
                    GameStage.Newactor = true;
                    super.clicked(event, x, y);
                }
                else
                {
                    game.setScreenBackByStackPop();
                }
            }
        });
        a.setPosition(gameStage.getCamera().viewportWidth-a.getWidth(),0);
        a.setSize(100,100);
        gameStage.addActor(a);
        bgStage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game) {

            OneSpriteStaticActor a;
            Table table;
            Label label;
            OrthographicCamera cam;
            @Override
            public void init() {
                table = new Table();
                table.setFillParent(true);
                a = new OneSpriteStaticActor((Texture)Assets.manager.get(Assets.getImg(EventType.currentId).getPath()));
                a.setBounds(0,0, getViewport().getWorldWidth(), getViewport().getWorldHeight());
                a.setAlpha(0.7f);
                addActor(a);
                setCameraResetToLeftBottomOfScreen();
                label = new Label("asd",Assets.manager.get(Assets.SKIN)){
                    Pixmap a = new Pixmap(1,1, Pixmap.Format.RGB888);
                    Sprite text = new Sprite(new Texture(a));
                    @Override
                    public void draw(Batch batch, float parentAlpha) {
                        text.setAlpha(0.75f);
                        text.setBounds(this.getX(),this.getY(),this.getWidth(),this.getHeight());
                        text.draw(batch,parentAlpha);
                        super.draw(batch, parentAlpha);
                    }
                };
                label.setColor(Color.WHITE);
                label.setText(Assets.getImg(EventType.currentId).getSubtitle());
                table.top();
                table.row();
                table.add(label).spaceTop(5f);
                addActor(table);
                ShapeRenderer sr = new ShapeRenderer();
            }

            @Override
            public void act(float delta)
            {
                if(GameStage.Newactor) {
                    a.setTexture((Texture) Assets.manager.get(Assets.getImg(EventType.currentId).getPath()));
                }
                    label.setText(Assets.getImg(EventType.currentId).getSubtitle());
            }
        };
        Gdx.input.setInputProcessor(gameStage);
        setBackGroundColor(1f, 1f, 1f);
    }
}
