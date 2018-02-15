package hu.pejedlik.game.Menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import hu.pejedlik.game.GlobalClasses.Assets;
import hu.pejedlik.game.Math.RandomNumber;
import hu.pejedlik.game.MyBaseClasses.OneSpriteAnimatedActor;
import hu.pejedlik.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by Hegedüs Csongor on 2/11/2018.
 */

public class EmojiStream extends OneSpriteAnimatedActor {

    private float emojiDelay = 6, rotateSpeed, viewPortWidth, viewPortHeight;
    private int currentFrame;

    public EmojiStream(float viewPortWidth, float viewPortHeight) {
        super(Assets.manager.get(Assets.EMOJIS_TEXTUREATLAS));
        this.viewPortWidth = viewPortWidth;
        this.viewPortHeight = viewPortHeight;
    }

    @Override
    public void init() {
        super.init();
        stop();
        elapsedTime = 6;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (elapsedTime >= emojiDelay) {
            elapsedTime = 0;
            setVisible(true);
            setFrame(currentFrame = new RandomNumber(0, getFrameCount() - 1, currentFrame).getGenNumber());
            setPosition(0 - getWidth(),
                    new RandomNumber(0, viewPortHeight - getHeight()).getGenNumberf());
            setRotation(0);
            rotateSpeed = new RandomNumber(0.5f, 3f).getGenNumberf();
            rotateSpeed = new RandomNumber(0, 1).getGenNumber() == 1 ? -rotateSpeed : rotateSpeed;
        }

        setPosition(getX() + 5, getY());
        setRotation(getRotation() + rotateSpeed);
    }

    public float getEmojiDelay() {
        return emojiDelay;
    }
}
