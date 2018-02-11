package com.mygdx.game.Menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Math.RandomNumber;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by Hegedüs Csongor on 2/11/2018.
 */

public class EmojiStream extends OneSpriteAnimatedActor {

    private float emojiDelay = 5, viewPortWidth, viewPortHeight;
    private int currentFrame;

    public EmojiStream(float viewPortWidth, float viewPortHeight) {
        super(Assets.manager.get(Assets.EMOJIS_TEXTUREATLAS));
        this.viewPortWidth = viewPortWidth;
        this.viewPortHeight = viewPortHeight;
    }

    @Override
    public void init() {
        super.init();
        setZIndex(1);
        stop();
        elapsedTime = 5;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (elapsedTime >= emojiDelay) {
            elapsedTime = 0;
            setFrame(currentFrame = new RandomNumber(0, getFrameCount() - 1, currentFrame).getGenNumber());
            setPosition(new RandomNumber(0, viewPortWidth - getWidth()).getGenNumberf(),
                    new RandomNumber(0, viewPortHeight - getHeight()).getGenNumberf());
            setSize(0,0);
        }
        setSize(getWidth() + 0.5f, getHeight() + 0.5f);
    }
}
