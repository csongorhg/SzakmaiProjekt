package hu.pejedlik.game.Menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.pejedlik.game.Game.GameScreen;
import hu.pejedlik.game.Game.ReadParameter;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Graph.SituationParameterScreen;
import hu.pejedlik.game.Loading.EventType;
import hu.pejedlik.game.Math.RandomNumber;
import hu.pejedlik.game.MyBaseClasses.ImgButton;
import hu.pejedlik.game.MyBaseClasses.MyLabel;
import hu.pejedlik.game.MyBaseClasses.MyStage;
import hu.pejedlik.game.MyBaseClasses.OneSpriteAnimatedActor;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;
import hu.pejedlik.game.MyGdxGame;

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
        myLabel = new MyLabel(EventType.TRANSPORT.toString(), game.getSkin());

        eventLabels.add(myLabel);

        // Idegenek
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.STRANGERS_TEXTURE)));
        myLabel = new MyLabel(EventType.STRANGERS.toString(), game.getSkin());
        eventLabels.add(myLabel);

        // Iskola
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.SCHOOL_TEXTURE)));
        myLabel = new MyLabel(EventType.SCHOOL.toString(), game.getSkin());
        eventLabels.add(myLabel);

        // Szoba
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.ROOM_TEXTURE)));
        myLabel = new MyLabel(EventType.ROOM.toString(), game.getSkin());
        eventLabels.add(myLabel);

        // Konyha
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.KITCHEN_TEXTURE)));
        myLabel = new MyLabel(EventType.KITCHEN.toString(), game.getSkin());
        eventLabels.add(myLabel);

        // Internet
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.INTERNET_TEXTURE)));
        myLabel = new MyLabel(EventType.INTERNET.toString(), game.getSkin());
        eventLabels.add(myLabel);

        // Játszótér
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.PLAYGROUND_TEXTURE)));
        myLabel = new MyLabel(EventType.PLAYGROUND.toString(), game.getSkin());
        eventLabels.add(myLabel);

        // Konfliktus
        eventImages.add(new OneSpriteStaticActor(Assets.manager.get(Assets.CONFLICT_TEXTURE)));
        myLabel = new MyLabel(EventType.CONFLICT.toString(), game.getSkin());
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

            final int currentImgCount = imgCount; // A clicklistener miatt final

            actor.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    //, eventLabels.get(currentImgCount - 1).getText().toString() + ".txt")
                    game.setScreen(new GameScreen(game, new ReadParameter(eventLabels.get(currentImgCount - 1).getText().toString() + ".txt")));
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

        System.out.println(Assets.manager.getLoadedAssets());

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
