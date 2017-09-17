package com.mygdx.game.DemoInput;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyTextField;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 10. 01..
 */
public class InputScreen extends MyScreen {

    MyStage myStage;

    public InputScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        myStage.act(delta);
        myStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void init() {

        //Ha nem akarunk annyi fájlt, akkor lehet egy anonim osztály is.
        myStage = new MyStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game)
        {
            private String random()
            {
                return String.valueOf((int)(Math.random()*10));
            }

            //Itt a MyStage osztályt folytatjuk. Referenciaként a myStage változó lesz, amiből például a render is dolgozik.
            private Table table;


            public void init() {
                addBackEventStackListener();
                setBackGroundColor(0f,0.2f,0.4f);

                table = new Table();
                table.setWidth(640);
                table.setHeight(480);
                table.add(new MyButton(random(), game.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("+", game.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyButton(random(), game.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("=", game.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyTextField("?", game.getTextFieldStyle()));
                table.row();
                table.add(new MyButton(random(), game.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("*", game.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyButton(random(), game.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("=", game.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyTextField("?", game.getTextFieldStyle()));
                table.row();
                table.add(new MyButton("Vissza", game.getTextButtonStyle()){
                    @Override
                    public void init() {
                        addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                game.setScreenBackByStackPop();
                            }
                        });
                    }
                }).colspan(5).align(Align.center);
                table.debug();
                addActor(table);

            }
        };
        Gdx.input.setInputProcessor(myStage);
    }
}
