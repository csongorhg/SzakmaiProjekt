package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graph.SituationParameterScreen;
import com.mygdx.game.Math.RandomNumber;
import com.mygdx.game.MyBaseClasses.ImgButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

/**
 * Created by tuskeb on 2016. 09. 30..
 */



public class MenuStage extends MyStage {

    private Table table;
    private Array<OneSpriteStaticActor> eventImages;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        super.init();
        addBackEventStackListener();

        // Menu buttons
        eventImages = new Array<OneSpriteStaticActor>();
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.TRANSPORT_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.STRANGERS_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.SCHOOL_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.ROOM_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.KITCHEN_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.INTERNET_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.PLAYGROUND_TEXTURE)));
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.CONFLICT_TEXTURE)));

        int posX = 100, posY = 500, imgCount = 0;
        for (final OneSpriteStaticActor actor : eventImages) {
            actor.setSize(180, 135);
            imgCount++;
            if (imgCount == 5) {
                posY = 200;
                posX = 100;
            }
            actor.setPosition(posX, posY);
            posX += 120 + actor.getWidth();
            actor.setRotation(new RandomNumber(-15,15).getGenNumber());
            actor.setZIndex(0);

            actor.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    game.setScreen(new SituationParameterScreen(game ,"param2"+".txt"));
                }
            });
            addActor(actor);
        }

        // Menu emojis
        final EmojiStream emojiStream;
        addActor(emojiStream = new EmojiStream(getViewport().getWorldWidth(), getViewport().getWorldHeight()));

        // Menu emojis onclick hearts
        emojiStream.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Heart heart = new Heart(emojiStream);

                if (heart.getHeartCount() > 5) { // ha ötnél több szív van, akkor az legkorábban letettet leszedi
                    int i = 0;
                    while (!(getActors().get(i) instanceof Heart)) {
                        i++;
                    }
                    getActors().get(i).remove();
                    heart.setHeartCount(heart.getHeartCount() - 1);
                }

                addActor(heart);

                emojiStream.setVisible(false);

            }
        });

        /*table = new Table();
        table.setFillParent(true);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                ImgButton currentImgButton = new ImgButton(Assets.manager.get(Assets.EXPLOSION_TEXTUREATLAS).createSprite(""+(i+j)));
                currentImgButton.setSize(200, 300);
                currentImgButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new SituationParameterScreen(game ,"param2"+".txt"));


                        super.clicked(event, x, y);
                    }
                });

                table.add(currentImgButton).size(200,300);
            }
            table.row();

        }

        table.center();
        this.addActor(table);*/




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
