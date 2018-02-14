package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Graph.SituationParameterScreen;
import com.mygdx.game.Math.RandomNumber;
import com.mygdx.game.MyBaseClasses.ImgButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

import sun.font.TextLabel;

/**
 * Created by tuskeb on 2016. 09. 30..
 */



public class MenuStage extends MyStage {

    private Table table;
    private Array<OneSpriteStaticActor> eventImages;
    private Array<MyLabel> eventLabels;

    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Heart.setHeartCount(0);
    }


    public void init()
    {
        super.init();
        addBackEventStackListener();

        // Menu buttons
        MyLabel myLabel;

        // Történetszálak listája
        eventImages = new Array<OneSpriteStaticActor>();
        eventLabels = new Array<MyLabel>();

        // Közlekedés
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.TRANSPORT_TEXTURE)));
        myLabel = new MyLabel("Közlekedés", game.getSkin());

        eventLabels.add(myLabel);

        // Idegenek
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.STRANGERS_TEXTURE)));
        myLabel = new MyLabel("Idegenek", game.getSkin());
        eventLabels.add(myLabel);

        // Iskola
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.SCHOOL_TEXTURE)));
        myLabel = new MyLabel("Iskola", game.getSkin());
        eventLabels.add(myLabel);

        // Szoba
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.ROOM_TEXTURE)));
        myLabel = new MyLabel("Szoba", game.getSkin());
        eventLabels.add(myLabel);

        // Konyha
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.KITCHEN_TEXTURE)));
        myLabel = new MyLabel("Konyha", game.getSkin());
        eventLabels.add(myLabel);

        // Internet
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.INTERNET_TEXTURE)));
        myLabel = new MyLabel("Internet", game.getSkin());
        eventLabels.add(myLabel);

        // Játszótér
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.PLAYGROUND_TEXTURE)));
        myLabel = new MyLabel("Játszótér", game.getSkin());
        eventLabels.add(myLabel);

        // Konfliktus
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.CONFLICT_TEXTURE)));
        myLabel = new MyLabel("Konfliktus", game.getSkin());
        eventLabels.add(myLabel);

        int posX = 100, posY = 500, imgCount = 0;
        for (final OneSpriteStaticActor actor : eventImages) {

            // Események képei
            actor.setSize(180, 135);
            imgCount++;
            if (imgCount == 5) {
                posY = 200;
                posX = 100;
            }
            actor.setPosition(posX, posY);
            posX += 120 + actor.getWidth();
            actor.setRotation(new RandomNumber(-10,10).getGenNumber());
            actor.setZIndex(0);

            actor.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    game.setScreen(new SituationParameterScreen(game ,"param2"+".txt"));
                }
            });
            addActor(actor);

            // Események feliratai
            MyLabel currentLabel = eventLabels.get(imgCount - 1);
            currentLabel.setPosition(actor.getX() + currentLabel.getWidth() / 2, actor.getY() + actor.getHeight());
            currentLabel.setColor(0,0,0,1);
            currentLabel.setRotation(10);
            addActor(currentLabel);
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

                if (heart.getHeartCount() > 5) { // ha ötnél több szív van, akkor a legkorábban letettet leszedi
                    int i = 0;
                    while (i != getActors().size - 1 && !(getActors().get(i) instanceof Heart)) {
                        i++;
                    }
                    if (i != getActors().size - 1) {
                        getActors().get(i).remove();
                        heart.setHeartCount(heart.getHeartCount() - 1);
                    }

                }

                addActor(heart);

                emojiStream.setVisible(false);

            }
        });




        // Animáció, középről a háttér
        setCameraZoomXY(getViewport().getWorldWidth() / 2, getViewport().getWorldHeight() / 2, 40);
        setCameraMoveToXY(getViewport().getWorldWidth() / 2
                , getViewport().getWorldHeight() / 2, 1, 80);

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
