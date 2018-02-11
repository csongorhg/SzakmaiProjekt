package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graph.SituationParameterScreen;
import com.mygdx.game.Math.RandomNumber;
import com.mygdx.game.MyBaseClasses.ImgButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */



public class MenuStage extends MyStage {

    private Table table;
    private OneSpriteAnimatedActor emojiActor;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        super.init();
        addBackEventStackListener();

        // Menu emojis
        EmojiStream emojiStream;
        addActor(emojiStream = new EmojiStream(getViewport().getWorldWidth(), getViewport().getWorldHeight()));

        // Menu buttons
        table = new Table();
        table.setFillParent(true);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                ImgButton currentImgButton = new ImgButton(Assets.manager.get(Assets.EXPLOSION_TEXTUREATLAS).createSprite(""+(i+j)));
                currentImgButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new SituationParameterScreen(game));


                        super.clicked(event, x, y);
                    }
                });

                table.add(currentImgButton).size(100,100);
            }
            table.row();

        }

        table.center();
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
