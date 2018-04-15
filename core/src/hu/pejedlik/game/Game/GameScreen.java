package hu.pejedlik.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
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
        bgStage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game) {

            OneSpriteStaticActor a;
            Table table;
            Label label;
            OrthographicCamera cam;
            @Override
            public void init() {
                //setCameraResetToLeftBottomOfScreen();
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
                label.setFontScale(2f);
                label.setText(Assets.getImg(EventType.currentId).getSubtitle());
                table.top();
                table.row();
                table.add(label).spaceTop(5f);
                addActor(table);
            }

            @Override
            public void act(float delta)
            {
                    a.setTexture((Texture) Assets.manager.get(Assets.getImg(EventType.currentId).getPath()));
                    label.setText(Assets.getImg(EventType.currentId).getSubtitle());
            }
        };
        Gdx.input.setInputProcessor(gameStage);
        setBackGroundColor(1f, 1f, 1f);
    }
}
