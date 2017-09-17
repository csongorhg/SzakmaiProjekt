package com.mygdx.game.DemoWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.DemoMenu.MenuScreen;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoScreen extends MyScreen {
    WorldDemoStage box2dStage;
    MyStage controlStage;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    InputMultiplexer inputMultiplexer;


    public WorldDemoScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        controlStage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)),spriteBatch,game) {
            @Override
            public void init() {
                addActor(new MyLabel("Kattints a testekre!", game.getLabelStyle())
                {
                    @Override
                    public void init() {
                        setPosition(120,0);
                    }
                });
                addActor(new MyButton("Vissza", game.getTextButtonStyle()){
                    @Override
                    public void init() {
                        super.init();
                        setPosition(0, getViewport().getWorldHeight()-getHeight());
                        addListener(new ClickListener()
                        {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreenBackByStackPop();
                                super.clicked(event, x, y);
                                System.out.println("Klikk");
                            }
                        });
                    }
                });


            }
        };


        box2dStage = new WorldDemoStage(new ExtendViewport(64,48,new OrthographicCamera(64,48)), spriteBatch, game);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(box2dStage);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.setProjectionMatrix(box2dStage.getCamera().combined);
        box2dStage.act();
        box2dStage.draw();
        box2DDebugRenderer.render(box2dStage.world,box2dStage.getCamera().combined);

        spriteBatch.setProjectionMatrix(controlStage.getCamera().combined);
        controlStage.act();
        controlStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        box2dStage.resize(width,height);
        controlStage.resize(width, height);
    }
}
