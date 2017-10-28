package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.ImgButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private Table table;
    private ImgButton playButton, settingsButton, exitButton;


    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        addBackEventStackListener();
        playButton = new ImgButton(Assets.manager.get(Assets.PLAYBUTTON_TEXTURE));
        playButton.addListener(new ClickListener()
        {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Klikkelve");
                super.clicked(event, x, y);
            }
        });

        settingsButton = new ImgButton(Assets.manager.get(Assets.SETTINGSBUTTON_TEXTURE));
        settingsButton.addListener(new ClickListener()
        {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Klikkelve");
                super.clicked(event, x, y);
            }
        });

        exitButton = new ImgButton(Assets.manager.get(Assets.EXITBUTTON_TEXTURE));
        exitButton.addListener(new ClickListener()
        {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Klikkelve");
                super.clicked(event, x, y);
            }
        });

        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(playButton).size(200,200).spaceRight(50);
        table.add(settingsButton).size(200,200).spaceRight(50);
        table.add(exitButton).size(200,200);

        this.addActor(table);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
